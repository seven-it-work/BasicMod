package lottery;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import basemod.AutoAdd;
import basemod.BaseMod;
import lottery.cards.BaseCard;
import lottery.characters.BaseLuShiPlayer;
import lottery.relics.BaseRelic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.seven.util.GeneralUtils;
import org.seven.util.KeywordInfo;
import org.seven.util.ResourcePath;
import org.seven.util.TextureLoader;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@SpireInitializer
public class LotteryMod implements basemod.interfaces.EditStringsSubscriber, basemod.interfaces.EditRelicsSubscriber,
    basemod.interfaces.PostUpdateSubscriber, basemod.interfaces.PostRenderSubscriber,
    basemod.interfaces.EditCardsSubscriber, basemod.interfaces.PostInitializeSubscriber,
    basemod.interfaces.StartGameSubscriber, basemod.interfaces.StartActSubscriber,
    basemod.interfaces.PostDungeonInitializeSubscriber, basemod.interfaces.EditCharactersSubscriber,
    basemod.interfaces.EditKeywordsSubscriber, basemod.interfaces.AddAudioSubscriber {

    public static final Map<String, KeywordInfo> keywords = new HashMap<>();

    public static final Logger logger = LogManager.getLogger(LotteryMod.class);

    private static final String defaultLanguage = "zhs";

    public static ResourcePath resourcePath;

    public static final Color MY_COLOR2_2 = new Color(0.015686275f, 0.16470589f, 0.22745098f, 1.0f);

    // 人物选择界面按钮的图片
    public static final String MY_CHARACTER_BUTTON = "LotteryMod/images/char/Character_Button.png";

    // 人物选择界面的立绘
    public static final String MY_CHARACTER_PORTRAIT = "LotteryMod/images/char/Character_Portrait.png";

    // 攻击牌的背景（小尺寸）
    public static final String BG_ATTACK_512 = "LotteryMod/images/512/bg_attack_512.png";

    // 能力牌的背景（小尺寸）
    public static final String BG_POWER_512 = "LotteryMod/images/512/bg_power_512.png";

    // 技能牌的背景（小尺寸）
    public static final String BG_SKILL_512 = "LotteryMod/images/512/bg_skill_512.png";

    // 在卡牌和遗物描述中的能量图标
    public static final String SMALL_ORB = "LotteryMod/images/char/small_orb.png";

    // 攻击牌的背景（大尺寸）
    public static final String BG_ATTACK_1024 = "LotteryMod/images/1024/bg_attack.png";

    // 能力牌的背景（大尺寸）
    public static final String BG_POWER_1024 = "LotteryMod/images/1024/bg_power.png";

    // 技能牌的背景（大尺寸）
    public static final String BG_SKILL_1024 = "LotteryMod/images/1024/bg_skill.png";

    // 在卡牌预览界面的能量图标
    public static final String BIG_ORB = "LotteryMod/images/char/card_orb.png";

    // 小尺寸的能量图标（战斗中，牌堆预览）
    public static final String ENEYGY_ORB = "LotteryMod/images/char/cost_orb.png";

    public LotteryMod() {
        logger.info(resourcePath.getModID() + " subscribed to BaseMod.");
        // 这里注册颜色
        BaseMod.addColor(PlayerColorEnum.BASE_LU_SHI_PLAYER_CARD_COLOR, MY_COLOR2_2, MY_COLOR2_2, MY_COLOR2_2,
            MY_COLOR2_2, MY_COLOR2_2, MY_COLOR2_2, MY_COLOR2_2, BG_ATTACK_512, BG_SKILL_512, BG_POWER_512, ENEYGY_ORB,
            BG_ATTACK_1024, BG_SKILL_1024, BG_POWER_1024, BIG_ORB, SMALL_ORB);

        BaseMod.subscribe(this);
    }

    @Override
    public void receiveEditCharacters() {
        // 向basemod注册人物
//        BaseMod.addCharacter(new BaseLuShiPlayer(CardCrawlGame.playerName), MY_CHARACTER_BUTTON, MY_CHARACTER_PORTRAIT,
//            PlayerColorEnum.BASE_LU_SHI_PLAYER);
    }

    public static void initialize() {
        new LotteryMod();
    }

    static {
        resourcePath = new ResourcePath(LotteryMod.class);
    }

    @Override
    public void receiveAddAudio() {

    }

    public static String getKeywordsTranslation(String keyWordName) {
        return LotteryMod.resourcePath.getProperNameByWordName(keyWordName);
    }

    public static class PlayerColorEnum {
        // 修改为你的颜色名称，确保不会与其他mod冲突
        @SpireEnum
        public static AbstractPlayer.PlayerClass BASE_LU_SHI_PLAYER;

        // ***将CardColor和LibraryType的变量名改为你的角色的颜色名称，确保不会与其他mod冲突***
        // ***并且名称需要一致！***
        @SpireEnum
        public static AbstractCard.CardColor BASE_LU_SHI_PLAYER_CARD_COLOR;
    }

    public static class PlayerLibraryEnum {
        // ***将CardColor和LibraryType的变量名改为你的角色的颜色名称，确保不会与其他mod冲突***
        // ***并且名称需要一致！***

        // 这个变量未被使用（呈现灰色）是正常的
        @SpireEnum
        public static CardLibrary.LibraryType BASE_LU_SHI_PLAYER_CARD_COLOR;
    }

    @Override
    public void receiveEditCards() {
        new AutoAdd(resourcePath.getModID()).packageFilter(BaseCard.class).setDefaultSeen(true).cards();
    }

    private void registerKeyword(KeywordInfo info) {
        BaseMod.addKeyword(resourcePath.getKeyWordsByMod(), info.PROPER_NAME, info.NAMES, info.DESCRIPTION);
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
                logger.warn(
                    resourcePath.getModID() + " does not support " + resourcePath.getLangString() + " keywords.");
            }
        }
    }

    @Override
    public void receiveEditRelics() {
        new AutoAdd(resourcePath.getModID()).packageFilter(BaseRelic.class)
            .setDefaultSeen(true)
            .any(BaseRelic.class, (info, relic) -> { //Run this code for any classes that extend this class
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
