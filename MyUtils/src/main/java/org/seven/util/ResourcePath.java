package org.seven.util;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglFileHandle;
import com.badlogic.gdx.files.FileHandle;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.ModInfo;
import com.evacipated.cardcrawl.modthespire.Patcher;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.localization.UIStrings;

import basemod.BaseMod;
import cn.hutool.core.io.FileUtil;
import lombok.Getter;

import org.scannotation.AnnotationDB;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

@Getter
public class ResourcePath {

    private final String resourcesFolder;

    private ModInfo info;

    private String modID;

    private final Class<?> modClass;

    public ResourcePath(Class<?> aClass) {
        resourcesFolder = checkResourcesPath(aClass);
        loadModInfo(aClass);
        this.modClass = aClass;
    }

    /**
     * 创建模组的基础信息（初始化工程工具而已）
     */
    public static void createBaseFile(Class<?> modClass, String resourceFolder) {
        final String simpleName = modClass.getSimpleName();
        File baseFile = new File(
            ResourcePath.class.getProtectionDomain().getCodeSource().getLocation().getFile() + "/utils/");
        File targetFile = new File(resourceFolder + "/" + simpleName + "/");
        FileUtil.copyContent(baseFile, targetFile, false);
        // 国际化信息处理
        FileUtil.loopFiles(targetFile, new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getAbsolutePath().contains("localization");
            }
        }).forEach(file -> {
            FileUtil.rename(file, simpleName + "-" + file.getName(), false);
        });
    }

    public String getProperNameByWordName(String keyWordName) {
        // 获取副本前缀
        String keyWords = this.getKeyWords(keyWordName);
        String realKeyWords = BaseMod.getKeywordPrefix(keyWords) + BaseMod.getKeywordUnique(keyWords);
        return BaseMod.getKeywordPrefix(keyWords) + BaseMod.getKeywordProper(realKeyWords);
    }

    public String localizationPath(String lang, String file) {
        return resourcesFolder + "/localization/" + lang + "/" + file;
    }

    public String getLangString() {
        return Settings.language.name().toLowerCase();
    }

    public void loadLocalization(String lang) {
        String simpleName = this.modClass.getSimpleName();
        BaseMod.loadCustomStringsFile(CardStrings.class, localizationPath(lang, simpleName + "-CardStrings.json"));
        BaseMod.loadCustomStringsFile(CharacterStrings.class,
            localizationPath(lang, simpleName + "-CharacterStrings.json"));
        BaseMod.loadCustomStringsFile(EventStrings.class, localizationPath(lang, simpleName + "-EventStrings.json"));
        BaseMod.loadCustomStringsFile(OrbStrings.class, localizationPath(lang, simpleName + "-OrbStrings.json"));
        BaseMod.loadCustomStringsFile(PotionStrings.class, localizationPath(lang, simpleName + "-PotionStrings.json"));
        BaseMod.loadCustomStringsFile(PowerStrings.class, localizationPath(lang, simpleName + "-PowerStrings.json"));
        BaseMod.loadCustomStringsFile(RelicStrings.class, localizationPath(lang, simpleName + "-RelicStrings.json"));
        BaseMod.loadCustomStringsFile(UIStrings.class, localizationPath(lang, simpleName + "-UIStrings.json"));
    }

    public String getKeywordsJsonFile() {
        return this.modClass.getSimpleName() + "-Keywords.json";
    }

    public String getKeyWordsByMod() {
        return this.modID.toLowerCase(Locale.ROOT);
    }

    public String getKeyWords(String key) {
        // 规则见BaseMod.addKeyword源码
        return (this.modID + ":" + key).toLowerCase(Locale.ROOT);
    }

    private void loadModInfo(Class<?> aClass) {
        Optional<ModInfo> infos = Arrays.stream(Loader.MODINFOS).filter((modInfo) -> {
            AnnotationDB annotationDB = Patcher.annotationDBMap.get(modInfo.jarURL);
            if (annotationDB == null) {
                return false;
            }
            Set<String> initializers = annotationDB.getAnnotationIndex()
                .getOrDefault(SpireInitializer.class.getName(), Collections.emptySet());
            return initializers.contains(aClass.getName());
        }).findFirst();
        if (infos.isPresent()) {
            this.info = infos.get();
            this.modID = info.ID;
        } else {
            throw new RuntimeException("Failed to determine mod info/ID based on initializer.");
        }
    }

    public String makeID(String id) {
        return modID + ":" + id;
    }

    public String imagePath(String file) {
        return resourcesFolder + "/images/" + file;
    }

    public String characterPath(String file) {
        return resourcesFolder + "/images/character/" + file;
    }

    public String powerPath(String file) {
        return resourcesFolder + "/images/powers/" + file;
    }

    public String relicPath(String file) {
        return resourcesFolder + "/images/relics/" + file;
    }


    /**
     * 基础路径，和aClass的类名一直，比如类名叫Abc，在resources下的Abc文件夹就是基础路径了
     *
     * @param aClass
     * @return
     */
    private String checkResourcesPath(Class<?> aClass) {
        String name = aClass.getSimpleName(); //getPackage can be iffy with patching, so class name is used instead.
        int separator = name.indexOf('.');
        if (separator > 0)
            name = name.substring(0, separator);

        FileHandle resources = new LwjglFileHandle(name, Files.FileType.Internal);

        if (!resources.exists()) {
            throw new RuntimeException("\n\tFailed to find resources folder; expected it to be named \"" + name + "\"." +
                    " Either make sure the folder under resources has the same name as your mod's package, or change the line\n" +
                    "\t\"private static final String resourcesFolder = checkResourcesPath();\"\n" +
                    "\tat the top of the " + aClass.getSimpleName() + " java file.");
        }
        if (!resources.child("images").exists()) {
            throw new RuntimeException("\n\tFailed to find the 'images' folder in the mod's 'resources/" + name + "' folder; Make sure the " +
                    "images folder is in the correct location.");
        }
        if (!resources.child("localization").exists()) {
            throw new RuntimeException("\n\tFailed to find the 'localization' folder in the mod's 'resources/" + name + "' folder; Make sure the " +
                    "localization folder is in the correct location.");
        }

        return name;
    }
}
