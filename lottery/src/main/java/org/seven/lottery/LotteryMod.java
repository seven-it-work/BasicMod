package org.seven.lottery;

import basemod.AutoAdd;
import basemod.BaseMod;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.seven.lottery.cards.BaseCard;
import org.seven.lottery.relics.BaseRelic;
import org.seven.util.GeneralUtils;
import org.seven.util.KeywordInfo;
import org.seven.util.ResourcePath;
import org.seven.util.TextureLoader;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@SpireInitializer
public class LotteryMod implements basemod.interfaces.EditStringsSubscriber, basemod.interfaces.EditRelicsSubscriber, basemod.interfaces.PostUpdateSubscriber, basemod.interfaces.PostRenderSubscriber, basemod.interfaces.EditCardsSubscriber, basemod.interfaces.PostInitializeSubscriber, basemod.interfaces.StartGameSubscriber, basemod.interfaces.StartActSubscriber, basemod.interfaces.PostDungeonInitializeSubscriber, basemod.interfaces.EditCharactersSubscriber, basemod.interfaces.EditKeywordsSubscriber, basemod.interfaces.AddAudioSubscriber {

    public static final Map<String, KeywordInfo> keywords = new HashMap<>();
    private static final Logger logger = LogManager.getLogger(LotteryMod.class);
    private static final String defaultLanguage = "zhs";
    private static final String KEY_PREFIX = LotteryMod.class.getSimpleName();
    public static ResourcePath resourcePath;

    static {
        resourcePath = new ResourcePath(LotteryMod.class);
    }

    @Override
    public void receiveAddAudio() {

    }

    @Override
    public void receiveEditCards() {
        new AutoAdd(resourcePath.getModID())
                .packageFilter(BaseCard.class)
                .setDefaultSeen(true)
                .cards();
    }

    @Override
    public void receiveEditCharacters() {

    }

    private void registerKeyword(KeywordInfo info) {
        BaseMod.addKeyword(KEY_PREFIX, info.PROPER_NAME, info.NAMES, info.DESCRIPTION);
        if (!info.ID.isEmpty()) {
            keywords.put(info.ID, info);
        }
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(resourcePath.localizationPath(defaultLanguage, resourcePath.getKeywordsJsonFile())).readString(String.valueOf(StandardCharsets.UTF_8));
        KeywordInfo[] keywords = gson.fromJson(json, KeywordInfo[].class);
        for (KeywordInfo keyword : keywords) {
            keyword.prep();
            registerKeyword(keyword);
        }

        if (!defaultLanguage.equals(resourcePath.getLangString())) {
            try {
                json = Gdx.files.internal(resourcePath.localizationPath(resourcePath.getLangString(), resourcePath.getKeywordsJsonFile())).readString(String.valueOf(StandardCharsets.UTF_8));
                keywords = gson.fromJson(json, KeywordInfo[].class);
                for (KeywordInfo keyword : keywords) {
                    keyword.prep();
                    registerKeyword(keyword);
                }
            } catch (Exception e) {
                logger.warn(resourcePath.getModID() + " does not support " + resourcePath.getLangString() + " keywords.");
            }
        }
    }

    @Override
    public void receiveEditRelics() {
        new AutoAdd(resourcePath.getModID())
                .packageFilter(BaseRelic.class)
                .setDefaultSeen(true)
                .cards();
    }

    @Override
    public void receiveEditStrings() {
        resourcePath.loadLocalization(defaultLanguage); //no exception catching for default localization; you better have at least one that works.
        if (!defaultLanguage.equals(resourcePath.getLangString())) {
            try {
                resourcePath.loadLocalization(resourcePath.getLangString());
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
        Texture badgeTexture = TextureLoader.getTexture(resourcePath.imagePath("badge.png"), resourcePath);
        BaseMod.registerModBadge(badgeTexture, resourcePath.getInfo().Name, GeneralUtils.arrToString(resourcePath.getInfo().Authors), resourcePath.getInfo().Description, null);
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
}
