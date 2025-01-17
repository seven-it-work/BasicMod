package lottery;

import basemod.BaseMod;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.evacipated.cardcrawl.modthespire.lib.SpireSideload;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import lottery.characters.BaseLuShiPlayer;
import org.seven.util.QuickStartMod;

@SpireInitializer
@SpireSideload(
        modIDs = {
                "Friendly_Minions_0987678"
        }
)
public class LotteryMod extends QuickStartMod {
    public static final Color MY_COLOR2_2 = new Color(0.015686275f, 0.16470589f, 0.22745098f, 1.0f);
    // 人物选择界面按钮的图片
    public static final String MY_CHARACTER_BUTTON = "lottery/images/char/Character_Button.png";
    // 人物选择界面的立绘
    public static final String MY_CHARACTER_PORTRAIT = "lottery/images/char/Character_Portrait.png";
    // 攻击牌的背景（小尺寸）
    public static final String BG_ATTACK_512 = "lottery/images/512/bg_attack_512.png";
    // 能力牌的背景（小尺寸）
    public static final String BG_POWER_512 = "lottery/images/512/bg_power_512.png";
    // 技能牌的背景（小尺寸）
    public static final String BG_SKILL_512 = "lottery/images/512/bg_skill_512.png";
    // 在卡牌和遗物描述中的能量图标
    public static final String SMALL_ORB = "lottery/images/char/small_orb.png";
    // 攻击牌的背景（大尺寸）
    public static final String BG_ATTACK_1024 = "lottery/images/1024/bg_attack.png";
    // 能力牌的背景（大尺寸）
    public static final String BG_POWER_1024 = "lottery/images/1024/bg_power.png";
    // 技能牌的背景（大尺寸）
    public static final String BG_SKILL_1024 = "lottery/images/1024/bg_skill.png";
    // 在卡牌预览界面的能量图标
    public static final String BIG_ORB = "lottery/images/char/card_orb.png";
    // 小尺寸的能量图标（战斗中，牌堆预览）
    public static final String ENEYGY_ORB = "lottery/images/char/cost_orb.png";

    public static LotteryMod MOD;
    @SpireEnum
    public static AbstractPlayer.PlayerClass BASE_LU_SHI_PLAYER;
    @SpireEnum
    public static AbstractCard.CardColor BASE_LU_SHI_PLAYER_CARD_COLOR;
    // 这个变量未被使用（呈现灰色）是正常的
    @SpireEnum(name = "BASE_LU_SHI_PLAYER_CARD_COLOR")
    public static CardLibrary.LibraryType BASE_LU_SHI_PLAYER_LIBRARY;

    public LotteryMod() {
        super(loadModInfo(LotteryMod.class));
        MOD = this;
        logger.info(this.getModId() + " subscribed to QuickStartMod.");
        // 这里注册颜色
        BaseMod.addColor(BASE_LU_SHI_PLAYER_CARD_COLOR, MY_COLOR2_2, MY_COLOR2_2, MY_COLOR2_2,
                MY_COLOR2_2, MY_COLOR2_2, MY_COLOR2_2, MY_COLOR2_2, BG_ATTACK_512, BG_SKILL_512, BG_POWER_512, ENEYGY_ORB,
                BG_ATTACK_1024, BG_SKILL_1024, BG_POWER_1024, BIG_ORB, SMALL_ORB);
    }

    public static void initialize() {
        new LotteryMod();
    }

    @Override
    public void receiveEditCharacters() {
        // 向basemod注册人物
        BaseMod.addCharacter(new BaseLuShiPlayer(CardCrawlGame.playerName), MY_CHARACTER_BUTTON, MY_CHARACTER_PORTRAIT,
                BASE_LU_SHI_PLAYER);
    }
}
