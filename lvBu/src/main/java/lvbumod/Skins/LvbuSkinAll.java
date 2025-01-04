package lvbumod.Skins;

import java.util.*;
import com.evacipated.cardcrawl.modthespire.*;
import basemod.*;
import basemod.interfaces.*;
import com.evacipated.cardcrawl.modthespire.lib.*;
import lvbumod.Patches.*;
import com.megacrit.cardcrawl.core.*;
import org.apache.logging.log4j.*;

@SpireInitializer
public class LvbuSkinAll implements PostInitializeSubscriber, StartGameSubscriber
{
    public static String MOD_ID;
    public static final String MODNAME = "LvbuSkinAll";
    public static final String AUTHOR = "Rita";
    public static final String DESCRIPTION = "";
    public static boolean hexaghostMask;
    public static Properties LvbuSkinAllDefaults;
    public static boolean foundmod_GuardianMod;
    public static final Logger logger;
    public static AssetLoader assets;
    
    public static String makeID(final String id) {
        return LvbuSkinAll.MOD_ID + ":" + id;
    }
    
    public static String assetPath(final String path) {
        return LvbuSkinAll.MOD_ID + "/" + path;
    }
    
    public static void initialize() {
        new LvbuSkinAll();
        LvbuSkinAll.foundmod_GuardianMod = Loader.isModLoaded("Guardian");
        if (LvbuSkinAll.foundmod_GuardianMod) {
            LvbuSkinAll.logger.info("==============\u5b88\u62a4\u8005mod\u5b58\u5728============");
        }
    }
    
    public LvbuSkinAll() {
        BaseMod.subscribe((ISubscriber)this);
    }
    
    public void receivePostInitialize() {
        loadSettings();
    }
    
    public static void saveSettings() {
        try {
            final SpireConfig config = new SpireConfig("LvbuSkinAll", "LvbuSkinAllSaveData", LvbuSkinAll.LvbuSkinAllDefaults);
            for (int i = 0; i <= LvbuSelectScreenPatch.characters.length - 1; ++i) {
                config.setBool(CardCrawlGame.saveSlot + "ReskinUnlock" + i, LvbuSelectScreenPatch.characters[i].reskinUnlockLvbu);
                config.setInt(CardCrawlGame.saveSlot + "reskinCount" + i, LvbuSelectScreenPatch.characters[i].reskinCount);
                for (int k = 0; k <= LvbuSelectScreenPatch.characters[i].skins.length - 1; ++k) {
                    config.setInt(CardCrawlGame.saveSlot + "portraitAnimationType" + i + "_" + k, LvbuSelectScreenPatch.characters[i].skins[k].portraitAnimationType);
                }
            }
            System.out.println("==============reskin\u5b58\u5165\u6570\u636e");
            config.save();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void loadSettings() {
        try {
            final SpireConfig config = new SpireConfig("LvbuSkinAll", "LvbuSkinAllSaveData", LvbuSkinAll.LvbuSkinAllDefaults);
            config.load();
            System.out.println("==============reskin\u8bfb\u53d6\u6570\u636e");
            for (int i = 0; i <= LvbuSelectScreenPatch.characters.length - 1; ++i) {
                LvbuSelectScreenPatch.characters[i].reskinUnlockLvbu = config.getBool(CardCrawlGame.saveSlot + "ReskinUnlock" + i);
                LvbuSelectScreenPatch.characters[i].reskinCount = config.getInt(CardCrawlGame.saveSlot + "reskinCount" + i);
                for (int k = 0; k <= LvbuSelectScreenPatch.characters[i].skins.length - 1; ++k) {
                    if (!LvbuSelectScreenPatch.characters[i].skins[k].forcePortraitAnimationType) {
                        LvbuSelectScreenPatch.characters[i].skins[k].portraitAnimationType = config.getInt(CardCrawlGame.saveSlot + "portraitAnimationType" + i + "_" + k);
                        if (LvbuSelectScreenPatch.characters[i].skins[k].portraitAnimationType > 2 || LvbuSelectScreenPatch.characters[i].skins[k].portraitAnimationType < 0) {
                            LvbuSelectScreenPatch.characters[i].skins[k].portraitAnimationType = 2;
                        }
                        if (!LvbuSelectScreenPatch.characters[i].skins[k].hasAnimation()) {
                            LvbuSelectScreenPatch.characters[i].skins[k].portraitAnimationType = 0;
                        }
                    }
                }
                if (LvbuSelectScreenPatch.characters[i].reskinCount > LvbuSelectScreenPatch.characters[i].skins.length - 1) {
                    LvbuSelectScreenPatch.characters[i].reskinCount = 0;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            clearSettings();
        }
    }
    
    public static void clearSettings() {
        saveSettings();
    }
    
    public static void unlockAllReskinLvbu() {
        for (final LvbuSkinCharacter c : LvbuSelectScreenPatch.characters) {
            c.reskinUnlockLvbu = true;
        }
        saveSettings();
    }
    
    public void receiveStartGame() {
    }
    
    static {
        LvbuSkinAll.MOD_ID = "lvbumod";
        LvbuSkinAll.hexaghostMask = false;
        LvbuSkinAll.LvbuSkinAllDefaults = new Properties();
        LvbuSkinAll.foundmod_GuardianMod = false;
        logger = LogManager.getLogger(LvbuSkinAll.class.getSimpleName());
        LvbuSkinAll.assets = new AssetLoader();
    }
}
