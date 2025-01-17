package org.seven.util;


import basemod.AutoAdd;
import basemod.BaseMod;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.ModInfo;
import com.evacipated.cardcrawl.modthespire.Patcher;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.scannotation.AnnotationDB;

import java.nio.charset.StandardCharsets;
import java.util.*;

public abstract class QuickStartMod implements basemod.interfaces.EditStringsSubscriber, basemod.interfaces.EditRelicsSubscriber,
        basemod.interfaces.PostUpdateSubscriber, basemod.interfaces.PostRenderSubscriber,
        basemod.interfaces.EditCardsSubscriber, basemod.interfaces.PostInitializeSubscriber,
        basemod.interfaces.StartGameSubscriber, basemod.interfaces.StartActSubscriber,
        basemod.interfaces.PostDungeonInitializeSubscriber, basemod.interfaces.EditCharactersSubscriber,
        basemod.interfaces.EditKeywordsSubscriber, basemod.interfaces.AddAudioSubscriber {
    public static final Logger logger = LogManager.getLogger(QuickStartMod.class);

    public final Map<String, KeywordInfo> keywords = new HashMap<>();
    private final HashMap<String, Texture> textures = new HashMap<>();
    @Getter
    private final ModInfo modInfo;

    public QuickStartMod(ModInfo modInfo) {
        this.modInfo = modInfo;
        BaseMod.subscribe(this);
    }

    protected static ModInfo loadModInfo(Class<?> aClass) {
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
            return infos.get();
        } else {
            throw new RuntimeException("Failed to determine mod info/ID based on initializer.");
        }
    }

    public String characterPath(String file) {
        return resourcesFolder() + "/images/character/" + file;
    }

    public String defaultLanguage() {
        return "zhs";
    }

    public String getCardTextureString(final String cardName, final AbstractCard.CardType cardType) {
        String textureString = imagePath("cards/" + cardType.name().toLowerCase(Locale.ROOT) + "/" + cardName + ".png");

        FileHandle h = Gdx.files.internal(textureString);
        if (!h.exists()) {
            switch (cardType) {
                case ATTACK:
                    textureString = imagePath("cards/attack/default.png");
                    break;
                case POWER:
                    textureString = imagePath("cards/power/default.png");
                    break;
                default:
                    textureString = imagePath("cards/skill/default.png");
                    break;
            }
        }

        return textureString;
    }

    public Texture getHiDefPowerTexture(final String powerName) {
        String textureString = powerPath("large/" + powerName + ".png");
        return getTextureNull(textureString);
    }

    public String getKeywordsJsonFile() {
        return "Keywords.json";
    }

    public String getLangString() {
        return Settings.language.name().toLowerCase();
    }

    public String getModId() {
        return this.modInfo.ID;
    }

    public Texture getPowerTexture(final String powerName) {
        String textureString = powerPath(powerName + ".png");
        return getTexture(textureString);
    }

    public String getProperNameByWordName(String keyWordName) {
        // 获取副本前缀
        String keyWords = this.makeID(keyWordName);
        String realKeyWords = BaseMod.getKeywordPrefix(keyWords) + BaseMod.getKeywordUnique(keyWords);
        return BaseMod.getKeywordPrefix(keyWords) + BaseMod.getKeywordProper(realKeyWords);
    }

    /**
     * @param filePath - String path to the texture you want to load relative to resources,
     *                 Example: imagePath("missing.png")
     * @return <b>com.badlogic.gdx.graphics.Texture</b> - The texture from the path provided, or a "missing image" texture if it doesn't exist.
     */
    public Texture getTexture(final String filePath) {
        return getTexture(filePath, true);
    }

    /**
     * @param filePath - String path to the texture you want to load relative to resources,
     *                 Example: imagePath("missing.png")
     * @param linear   - Whether the image should use a linear or nearest filter for scaling.
     * @return <b>com.badlogic.gdx.graphics.Texture</b> - The texture from the path provided, or a "missing image" texture if it doesn't exist.
     */
    public Texture getTexture(final String filePath, boolean linear) {
        if (textures.get(filePath) == null) {
            try {
                loadTexture(filePath, linear);
            } catch (GdxRuntimeException e) {
                logger.info("Failed to find texture " + filePath, e);
                Texture missing = getTextureNull(imagePath("missing.png"), false);
                if (missing == null) {
                    logger.info("missing.png is missing, should be at " + imagePath("missing.png"));
                }
                return missing;
            }
        }
        Texture t = textures.get(filePath);
        if (t != null && t.getTextureObjectHandle() == 0) {
            textures.remove(filePath);
            t = getTexture(filePath, linear);
        }
        return t;
    }

    /**
     * @param filePath - String path to the texture you want to load relative to resources,
     *                 Example: imagePath("missing.png")
     * @return <b>com.badlogic.gdx.graphics.Texture</b> - The texture from the path provided, or null if it doesn't exist.
     */
    public Texture getTextureNull(final String filePath) {
        return getTextureNull(filePath, true);
    }

    /**
     * @param filePath - String path to the texture you want to load relative to resources,
     *                 Example: imagePath("missing.png")
     * @param linear   - Whether the image should use a linear or nearest filter for scaling.
     * @return <b>com.badlogic.gdx.graphics.Texture</b> - The texture from the path provided, or null if it doesn't exist.
     */
    public Texture getTextureNull(final String filePath, boolean linear) {
        if (!textures.containsKey(filePath)) {
            try {
                loadTexture(filePath, linear);
            } catch (GdxRuntimeException e) {
                textures.put(filePath, null);
            }
        }
        Texture t = textures.get(filePath);
        if (t != null && t.getTextureObjectHandle() == 0) {
            textures.remove(filePath);
            t = getTextureNull(filePath, linear);
        }
        return t;
    }

    public String imagePath(String file) {
        return resourcesFolder() + "/images/" + file;
    }

    public void loadLocalization(String lang) {
        String simpleName = this.getModId();
        BaseMod.loadCustomStringsFile(CardStrings.class, localizationPath(lang,  "CardStrings.json"));
        BaseMod.loadCustomStringsFile(CharacterStrings.class,
                localizationPath(lang,  "CharacterStrings.json"));
        BaseMod.loadCustomStringsFile(EventStrings.class, localizationPath(lang,  "EventStrings.json"));
        BaseMod.loadCustomStringsFile(OrbStrings.class, localizationPath(lang,  "OrbStrings.json"));
        BaseMod.loadCustomStringsFile(PotionStrings.class, localizationPath(lang,  "PotionStrings.json"));
        BaseMod.loadCustomStringsFile(PowerStrings.class, localizationPath(lang,  "PowerStrings.json"));
        BaseMod.loadCustomStringsFile(RelicStrings.class, localizationPath(lang,  "RelicStrings.json"));
        BaseMod.loadCustomStringsFile(UIStrings.class, localizationPath(lang,  "UIStrings.json"));
    }

    private void loadTexture(final String textureString) throws GdxRuntimeException {
        loadTexture(textureString, false);
    }

    private void loadTexture(final String textureString, boolean linearFilter) throws GdxRuntimeException {
        Texture texture = new Texture(textureString);
        if (linearFilter) {
            texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        } else {
            texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        }
        logger.info("Loaded texture " + textureString);
        textures.put(textureString, texture);
    }

    public String localizationPath(String lang, String file) {
        return resourcesFolder() + "/localization/" + lang + "/" + file;
    }

    public String makeID(String id) {
        return this.getModId() + ":" + id;
    }

    public String powerPath(String file) {
        return resourcesFolder() + "/images/powers/" + file;
    }

    @Override
    public void receiveAddAudio() {

    }

    @Override
    public void receiveEditCards() {
        new AutoAdd(this.getModId()).packageFilter(this.getClass()).setDefaultSeen(true).cards();
    }

    @Override
    public void receiveEditCharacters() {
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(localizationPath(defaultLanguage(), getKeywordsJsonFile())).readString(String.valueOf(StandardCharsets.UTF_8));
        KeywordInfo[] keywords = gson.fromJson(json, KeywordInfo[].class);
        for (KeywordInfo keyword : keywords) {
            keyword.prep();
            registerKeyword(keyword);
        }

        if (!defaultLanguage().equals(getLangString())) {
            try {
                json = Gdx.files.internal(localizationPath(getLangString(), getKeywordsJsonFile())).readString(String.valueOf(StandardCharsets.UTF_8));
                keywords = gson.fromJson(json, KeywordInfo[].class);
                for (KeywordInfo keyword : keywords) {
                    keyword.prep();
                    registerKeyword(keyword);
                }
            } catch (Exception e) {
                logger.warn(this.getModId() + " does not support " + getLangString() + " keywords.");
            }
        }
    }

    @Override
    public void receiveEditRelics() {
        new AutoAdd(this.getModId())
                .packageFilter(this.getClass())
                .setDefaultSeen(true)
                .any(QuickStartRelic.class, (info, relic) -> { //Run this code for any classes that extend this class
                    if (relic.pool != null) {
                        BaseMod.addRelicToCustomPool(relic, relic.pool); //Register a custom character specific relic
                    } else {
                        BaseMod.addRelic(relic, relic.relicType); //Register a shared or base game character specific relic
                    }

                    //If the class is annotated with @AutoAdd.Seen, it will be marked as seen, making it visible in the relic library.
                    //If you want all your relics to be visible by default, just remove this if statement.
                    if (info.seen) {
                        UnlockTracker.markRelicAsSeen(relic.relicId);
                    }
                });
    }

    @Override
    public void receiveEditStrings() {
        loadLocalization(defaultLanguage()); //no exception catching for default localization; you better have at least one that works.
        if (!defaultLanguage().equals(getLangString())) {
            try {
                loadLocalization(getLangString());
            } catch (GdxRuntimeException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void receivePostDungeonInitialize() {

    }

    @Override
    public void receivePostInitialize() {
        Texture badgeTexture = getTexture(imagePath("badge.png"));
        BaseMod.registerModBadge(badgeTexture, modInfo.Name, GeneralUtils.arrToString(modInfo.Authors), modInfo.Description, null);
    }

    @Override
    public void receivePostRender(SpriteBatch spriteBatch) {

    }

    @Override
    public void receivePostUpdate() {

    }

    @Override
    public void receiveStartAct() {

    }

    @Override
    public void receiveStartGame() {

    }

    private void registerKeyword(KeywordInfo info) {
        BaseMod.addKeyword(getModId(), info.PROPER_NAME, info.NAMES, info.DESCRIPTION);
        if (!info.ID.isEmpty()) {
            keywords.put(info.ID, info);
        }
    }

    public String relicPath(String file) {
        return resourcesFolder() + "/images/relics/" + file;
    }

    public String resourcesFolder() {
        return this.getModId();
    }
}
