package lottery.characters;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Strike_Red;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import kobting.friendlyminions.characters.AbstractPlayerWithMinions;
import kobting.friendlyminions.characters.CustomCharSelectInfo;
import lottery.LotteryMod;
import lottery.cards.DefaultAttack;
import lottery.cards.DefaultDefault;
import lottery.cards.DrawCards;
import lottery.cards.RandomCopying;
import lottery.relics.SanLianRelic;

import java.util.ArrayList;

public class BaseLuShiPlayer extends AbstractPlayerWithMinions {
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(
        LotteryMod.MOD.makeID(BaseLuShiPlayer.class.getSimpleName()));

    // 人物立绘（行动前）
    private static final String MY_CHARACTER_SHOULDER_1 = "lottery/images/char/shoulder1.png";

    // 人物立绘（行动后）
    private static final String MY_CHARACTER_SHOULDER_2 = "lottery/images/char/shoulder2.png";

    // 人物死亡图像
    private static final String CORPSE_IMAGE = "lottery/images/char/corpse.png";

    // 战斗界面左下角能量图标的每个图层
    private static final String[] ORB_TEXTURES = new String[]{
        "lottery/images/UI/orb/layer5.png",
        "lottery/images/UI/orb/layer4.png",
        "lottery/images/UI/orb/layer3.png",
        "lottery/images/UI/orb/layer2.png",
        "lottery/images/UI/orb/layer1.png",
        "lottery/images/UI/orb/layer6.png",
        "lottery/images/UI/orb/layer5d.png",
        "lottery/images/UI/orb/layer4d.png",
        "lottery/images/UI/orb/layer3d.png",
        "lottery/images/UI/orb/layer2d.png",
        "lottery/images/UI/orb/layer1d.png"
    };
    // 每个图层的旋转速度
    private static final float[] LAYER_SPEED = new float[]{-40.0F, -32.0F, 20.0F, -20.0F, 0.0F, -10.0F, -8.0F, 5.0F, -5.0F, 0.0F};


    public BaseLuShiPlayer(String name) {
        super(name, LotteryMod.BASE_LU_SHI_PLAYER,ORB_TEXTURES,"lottery/images/UI/orb/vfx.png", LAYER_SPEED, null, null);
        // 初始化你的人物，如果你的人物只有一张图，那么第一个参数填写你人物图片的路径。
        this.initializeClass("lottery/images/char/character.png", // 人物图片
            MY_CHARACTER_SHOULDER_2, MY_CHARACTER_SHOULDER_1, CORPSE_IMAGE, // 人物死亡图像
            this.getLoadout(), 0.0F, 0.0F, 200.0F, 220.0F, // 人物碰撞箱大小，越大的人物模型这个越大
            new EnergyManager(3)); // 初始每回合的能量
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        // 初始卡
        ArrayList<String> retVal = new ArrayList<>();
        for(int x = 0; x<4; x++) {
            retVal.add(DefaultAttack.ID);
        }
        for(int x = 0; x<4; x++) {
            retVal.add(DefaultDefault.ID);
        }
        retVal.add(DrawCards.ID);
        retVal.add(RandomCopying.ID);
        return retVal;
    }


    @Override
    public ArrayList<String> getStartingRelics() {
        // 初始遗物
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(SanLianRelic.ID);
        return retVal;
    }

    @Override
    public CharSelectInfo getLoadout() {
        // 角色选择面板信息
        return new CharSelectInfo(characterStrings.NAMES[0], // 人物名字
            characterStrings.TEXT[0], // 人物介绍
            75, // 当前血量
            75, // 最大血量
            0, // 初始充能球栏位
            99, // 初始携带金币
            5, // 每回合抽牌数量
            this, // 别动
            this.getStartingRelics(), // 初始遗物
            this.getStartingDeck(), // 初始卡组
            false // 别动
        );
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        // 角色名称
        return characterStrings.NAMES[0];
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return LotteryMod.BASE_LU_SHI_PLAYER_CARD_COLOR;
    }

    @Override
    public Color getCardRenderColor() {
        // 卡片颜色
        return LotteryMod.MY_COLOR2_2;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        // 翻牌事件出现的你的职业牌（一般设为打击）
        return new Strike_Red();
    }

    @Override
    public Color getCardTrailColor() {
        // 卡片轨迹颜色
        return LotteryMod.MY_COLOR2_2;
    }

    @Override
    public int getAscensionMaxHPLoss() {
        // 高进阶带来的生命值损失
        return 5;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        // 能量数字 字体
        return FontHelper.energyNumFontBlue;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        // 人物选择界面点击你的人物按钮时触发的方法，这里为屏幕轻微震动
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        // 自定义模式选择你的人物时播放的音效
        return "ATTACK_HEAVY";
    }

    @Override
    public String getLocalizedCharacterName() {
        return characterStrings.NAMES[0];
    }

    @Override
    public AbstractPlayer newInstance() {
        return new BaseLuShiPlayer(this.name);
    }

    @Override
    public String getSpireHeartText() {
        return characterStrings.TEXT[0];
    }

    @Override
    public Color getSlashAttackColor() {
        return LotteryMod.MY_COLOR2_2;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[] {
            AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE,
            AbstractGameAction.AttackEffect.SLASH_DIAGONAL, AbstractGameAction.AttackEffect.SLASH_HEAVY,
            AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL
        };
    }

    @Override
    public String getVampireText() {
        return Vampires.DESCRIPTIONS[0];
    }

    @Override
    public CustomCharSelectInfo getInfo() {
        return new CustomCharSelectInfo(
            "Character Name",
            "Character Flavor",
            80, //currentHP
            80, //maxHP
            0,  //maxOrbs
            2,  //maxMinions
            99, //gold
            5,  //cardDraw
            this,
            getStartingRelics(),
            getStartingDeck(),
            false);
    }
}
