package lvbumod.ModCore;

import lvbumod.Interface.*;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.badlogic.gdx.graphics.*;
import basemod.*;
import basemod.interfaces.*;
import lvbumod.Characters.*;
import com.google.gson.*;
import com.badlogic.gdx.*;
import java.nio.charset.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.characters.*;
import lvbumod.DynamicVariables.*;
import com.megacrit.cardcrawl.cards.*;
import lvbumod.Cards.Attack.*;
import lvbumod.Cards.Power.*;
import lvbumod.Cards.Skill.*;
import lvbumod.Cards.Special.*;
import com.megacrit.cardcrawl.localization.*;
import lvbumod.Monsters.*;
import com.megacrit.cardcrawl.relics.*;
import lvbumod.Relics.*;
import lvbumod.Helpers.*;
import lvbumod.Potions.*;
import com.megacrit.cardcrawl.dungeons.*;
import lvbumod.Patches.*;
import java.util.*;
import basemod.abstracts.*;
import com.megacrit.cardcrawl.unlock.*;
import com.megacrit.cardcrawl.monsters.*;

@SpireInitializer
public class LvbuMod implements EditKeywordsSubscriber, EditCardsSubscriber, EditStringsSubscriber, EditCharactersSubscriber, EditRelicsSubscriber, AddAudioSubscriber, StartGameSubscriber, PostInitializeSubscriber, SaveLoadSubscriber, SetUnlocksSubscriber
{
    private static final String MY_CHARACTER_BUTTON = "lvbuModResources/img/char/Character_Button.png";
    private static final String MY_CHARACTER_PORTRAIT = "lvbuModResources/img/char/Character_Portrait.png";
    private static final String MY_CHARACTER_PORTRAIT_EXTEND = "lvbuModResources/img/char/Character_Portrait_Extend.png";
    private static final String BG_ATTACK_512 = "lvbuModResources/img/512/bg_attack_512.png";
    private static final String BG_POWER_512 = "lvbuModResources/img/512/bg_power_512.png";
    private static final String BG_SKILL_512 = "lvbuModResources/img/512/bg_skill_512.png";
    private static final String small_orb = "lvbuModResources/img/char/small_orb.png";
    private static final String BG_ATTACK_1024 = "lvbuModResources/img/1024/bg_attack.png";
    private static final String BG_POWER_1024 = "lvbuModResources/img/1024/bg_power.png";
    private static final String BG_SKILL_1024 = "lvbuModResources/img/1024/bg_skill.png";
    private static final String big_orb = "lvbuModResources/img/char/card_orb.png";
    private static final String energy_orb = "lvbuModResources/img/char/cost_orb.png";
    public static final Color MY_COLOR_1_1;
    public static final Color GLOWING_RED;
    private static final String BG_ATTACK_512_2 = "lvbuModResources/img/512_2/bg_attack_512.png";
    private static final String BG_POWER_512_2 = "lvbuModResources/img/512_2/bg_power_512.png";
    private static final String BG_SKILL_512_2 = "lvbuModResources/img/512_2/bg_skill_512.png";
    private static final String small_orb_2 = "lvbuModResources/img/char/small_orb_2.png";
    private static final String BG_ATTACK_1024_2 = "lvbuModResources/img/1024_2/bg_attack.png";
    private static final String BG_POWER_1024_2 = "lvbuModResources/img/1024_2/bg_power.png";
    private static final String BG_SKILL_1024_2 = "lvbuModResources/img/1024_2/bg_skill.png";
    private static final String big_orb_2 = "lvbuModResources/img/char/card_orb_2.png";
    private static final String energy_orb_2 = "lvbuModResources/img/char/cost_orb_2.png";
    public static final Color MY_COLOR2_2;
    public static boolean unlockEverything;

    public LvbuMod() {
        BaseMod.subscribe((ISubscriber)this);
        BaseMod.addColor(lvbu.Enums.LVBU_CARD2, LvbuMod.MY_COLOR_1_1, LvbuMod.MY_COLOR_1_1, LvbuMod.MY_COLOR_1_1, LvbuMod.MY_COLOR_1_1, LvbuMod.MY_COLOR_1_1, LvbuMod.MY_COLOR_1_1, LvbuMod.MY_COLOR_1_1, "lvbuModResources/img/512/bg_attack_512.png", "lvbuModResources/img/512/bg_skill_512.png", "lvbuModResources/img/512/bg_power_512.png", "lvbuModResources/img/char/cost_orb.png", "lvbuModResources/img/1024/bg_attack.png", "lvbuModResources/img/1024/bg_skill.png", "lvbuModResources/img/1024/bg_power.png", "lvbuModResources/img/char/card_orb.png", "lvbuModResources/img/char/small_orb.png");
        BaseMod.addColor(lvbu.Enums.LVBU_CARD, LvbuMod.MY_COLOR2_2, LvbuMod.MY_COLOR2_2, LvbuMod.MY_COLOR2_2, LvbuMod.MY_COLOR2_2, LvbuMod.MY_COLOR2_2, LvbuMod.MY_COLOR2_2, LvbuMod.MY_COLOR2_2, "lvbuModResources/img/512_2/bg_attack_512.png", "lvbuModResources/img/512_2/bg_skill_512.png", "lvbuModResources/img/512_2/bg_power_512.png", "lvbuModResources/img/char/cost_orb_2.png", "lvbuModResources/img/1024_2/bg_attack.png", "lvbuModResources/img/1024_2/bg_skill.png", "lvbuModResources/img/1024_2/bg_power.png", "lvbuModResources/img/char/card_orb_2.png", "lvbuModResources/img/char/small_orb_2.png");
    }

    public static void initialize() {
        new LvbuMod();
    }

    public void receiveEditKeywords() {
        final Gson gson = new Gson();
        String lang = "ENG";
        if (Settings.language == Settings.GameLanguage.ZHS) {
            lang = "ZHS";
        }
        else if (Settings.language == Settings.GameLanguage.RUS) {
            lang = "RUS";
        }
        final String json = Gdx.files.internal("lvbuModResources/localization/" + lang + "/keywords.json").readString(String.valueOf(StandardCharsets.UTF_8));
        final Keyword[] keywords = (Keyword[])gson.fromJson(json, (Class)Keyword[].class);
        if (keywords != null) {
            for (final Keyword keyword : keywords) {
                BaseMod.addKeyword("lvbumod", keyword.NAMES[0], keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    public void receiveEditCharacters() {
        BaseMod.addCharacter((AbstractPlayer)new lvbu(CardCrawlGame.playerName), "lvbuModResources/img/char/Character_Button.png", "lvbuModResources/img/char/Character_Portrait.png", lvbu.Enums.LVBU);
        this.receiveEditPotions();
    }

    public void receiveEditCards() {
        BaseMod.addDynamicVariable((DynamicVariable)new LvbuSecondaryMagicVariable());
        BaseMod.addCard((AbstractCard)new Strike_lvbu());
        BaseMod.addCard((AbstractCard)new Defend_lvbu());
        BaseMod.addCard((AbstractCard)new BaiWeiYiFu());
        BaseMod.addCard((AbstractCard)new QiNengJiuJuRenXia());
        BaseMod.addCard((AbstractCard)new FeiJiangXiaoTian());
        BaseMod.addCard((AbstractCard)new JBStrike());
        BaseMod.addCard((AbstractCard)new ShunShiZhan());
        BaseMod.addCard((AbstractCard)new DuoXuZhou());
        BaseMod.addCard((AbstractCard)new TianCiYinJie());
        BaseMod.addCard((AbstractCard)new ShenWei());
        BaseMod.addCard((AbstractCard)new ZhenSheJi());
        BaseMod.addCard((AbstractCard)new SeYu());
        BaseMod.addCard((AbstractCard)new ShaMieJi());
        BaseMod.addCard((AbstractCard)new WuMingHuo());
        BaseMod.addCard((AbstractCard)new TouBen());
        BaseMod.addCard((AbstractCard)new FangTianHuaJi());
        BaseMod.addCard((AbstractCard)new LangZiYeXin());
        BaseMod.addCard((AbstractCard)new ChenGong());
        BaseMod.addCard((AbstractCard)new TiTianXingDao());
        BaseMod.addCard((AbstractCard)new ZhanJiLiPi());
        BaseMod.addCard((AbstractCard)new GaoShun());
        BaseMod.addCard((AbstractCard)new ZhangLiao());
        BaseMod.addCard((AbstractCard)new GreenHat());
        BaseMod.addCard((AbstractCard)new ShanZhanWuQian());
        BaseMod.addCard((AbstractCard)new WuShuangNuJi());
        BaseMod.addCard((AbstractCard)new HengSaoQianJun());
        BaseMod.addCard((AbstractCard)new HuLaoGuan());
        BaseMod.addCard((AbstractCard)new FuXiuErQu());
        BaseMod.addCard((AbstractCard)new MaShu());
        BaseMod.addCard((AbstractCard)new WuShuangTuCiJi());
        BaseMod.addCard((AbstractCard)new YiQiDangXian());
        BaseMod.addCard((AbstractCard)new LiZhenQianKun());
        BaseMod.addCard((AbstractCard)new Retreat());
        BaseMod.addCard((AbstractCard)new GuanWaiZhuHou());
        BaseMod.addCard((AbstractCard)new WeiFengMingZhu());
        BaseMod.addCard((AbstractCard)new YuanMen());
        BaseMod.addCard((AbstractCard)new HuJia());
        BaseMod.addCard((AbstractCard)new WangYunMeiRenJi());
        BaseMod.addCard((AbstractCard)new QuWoBingRen());
        BaseMod.addCard((AbstractCard)new JieJiu());
        BaseMod.addCard((AbstractCard)new XiaoLiCangDao());
        BaseMod.addCard((AbstractCard)new FengZhiTaoZei());
        BaseMod.addCard((AbstractCard)new XiuLuoForm());
        BaseMod.addCard((AbstractCard)new XuanZhuanFeiJi());
        BaseMod.addCard((AbstractCard)new ManYueGong());
        BaseMod.addCard((AbstractCard)new LengQuan());
        BaseMod.addCard((AbstractCard)new ShiFangHengSao());
        BaseMod.addCard((AbstractCard)new ChuanHouJi());
        BaseMod.addCard((AbstractCard)new BoDangFeiJian());
        BaseMod.addCard((AbstractCard)new ChangHuZi());
        BaseMod.addCard((AbstractCard)new HuanYanZei());
        BaseMod.addCard((AbstractCard)new ZhanBaiYuHe());
        BaseMod.addCard((AbstractCard)new JingZhouCiShi());
        BaseMod.addCard((AbstractCard)new YiLiPoQiao());
        BaseMod.addCard((AbstractCard)new KunShouZhiDou());
        BaseMod.addCard((AbstractCard)new LiuBei());
        BaseMod.addCard((AbstractCard)new XiDiaoChan());
        BaseMod.addCard((AbstractCard)new DaErZei());
        BaseMod.addCard((AbstractCard)new CaiJi());
        BaseMod.addCard((AbstractCard)new XiYanZhou());
        BaseMod.addCard((AbstractCard)new DongZhuo());
        BaseMod.addCard((AbstractCard)new CeShenShanJi());
        BaseMod.addCard((AbstractCard)new DiaoChan());
        BaseMod.addCard((AbstractCard)new WangYun());
        BaseMod.addCard((AbstractCard)new ZhangYang());
        BaseMod.addCard((AbstractCard)new ZhouXuan());
        BaseMod.addCard((AbstractCard)new GuiShenTingWei());
        BaseMod.addCard((AbstractCard)new LieDiJi());
        BaseMod.addCard((AbstractCard)new YiQiJueChen());
        BaseMod.addCard((AbstractCard)new ShaShen());
        BaseMod.addCard((AbstractCard)new FengYiTing());
        BaseMod.addCard((AbstractCard)new TiTouLaiJian());
        BaseMod.addCard((AbstractCard)new ThrowJi());
        BaseMod.addCard((AbstractCard)new ZhanSanYing());
        BaseMod.addCard((AbstractCard)new FanZhi());
        BaseMod.addCard((AbstractCard)new beg1());
        BaseMod.addCard((AbstractCard)new beg2());
        BaseMod.addCard((AbstractCard)new beg3());
        BaseMod.addCard((AbstractCard)new beg4());
    }

    public void receiveEditStrings() {
        String lang;
        if (Settings.language == Settings.GameLanguage.ZHS) {
            lang = "ZHS";
        }
        else if (Settings.language == Settings.GameLanguage.RUS) {
            lang = "RUS";
        }
        else {
            lang = "ENG";
        }
        BaseMod.loadCustomStringsFile((Class)CardStrings.class, "lvbuModResources/localization/" + lang + "/cards.json");
        BaseMod.loadCustomStringsFile((Class)CharacterStrings.class, "lvbuModResources/localization/" + lang + "/characters.json");
        BaseMod.loadCustomStringsFile((Class)RelicStrings.class, "lvbuModResources/localization/" + lang + "/relics.json");
        BaseMod.loadCustomStringsFile((Class)PowerStrings.class, "lvbuModResources/localization/" + lang + "/powers.json");
        BaseMod.loadCustomStringsFile((Class)PotionStrings.class, "lvbuModResources/localization/" + lang + "/potions.json");
        BaseMod.loadCustomStringsFile((Class)UIStrings.class, "lvbuModResources/localization/" + lang + "/uiString.json");
        BaseMod.loadCustomStringsFile((Class)MonsterStrings.class, "lvbuModResources/localization/" + lang + "/monsters.json");
    }

    public void receivePostInitialize() {
        this.initializeMonsters();
    }

    private void initializeMonsters() {
        BaseMod.addMonster(JiFromYuanMen.ID, "JI", () -> new MonsterGroup(new AbstractMonster[] { new JiFromYuanMen(0.0f, 0.0f, 0) }));
    }

    public void receiveEditRelics() {
        BaseMod.addRelicToCustomPool((AbstractRelic)new ChiTu(), lvbu.Enums.LVBU_CARD);
        BaseMod.addRelicToCustomPool((AbstractRelic)new WanLiChiTu(), lvbu.Enums.LVBU_CARD);
        BaseMod.addRelicToCustomPool((AbstractRelic)new ChiTu_sl(), lvbu.Enums.LVBU_CARD);
        BaseMod.addRelicToCustomPool((AbstractRelic)new WanLiChiTu_sl(), lvbu.Enums.LVBU_CARD);
        BaseMod.addRelicToCustomPool((AbstractRelic)new BingQiJia(), lvbu.Enums.LVBU_CARD);
        BaseMod.addRelicToCustomPool((AbstractRelic)new TongJing(), lvbu.Enums.LVBU_CARD);
        BaseMod.addRelicToCustomPool((AbstractRelic)new XiaoShou(), lvbu.Enums.LVBU_CARD);
        BaseMod.addRelicToCustomPool((AbstractRelic)new DianTianDeng(), lvbu.Enums.LVBU_CARD);
        BaseMod.addRelicToCustomPool((AbstractRelic)new XingJunQi(), lvbu.Enums.LVBU_CARD);
        BaseMod.addRelicToCustomPool((AbstractRelic)new JinJiaJinPao(), lvbu.Enums.LVBU_CARD);
    }

    public void receiveEditPotions() {
        BaseMod.addPotion((Class)ArrowPotion.class, (Color)null, (Color)null, (Color)null, LvbuModHelper.MakePath(ArrowPotion.class.getSimpleName()), lvbu.Enums.LVBU);
        BaseMod.addPotion((Class)PitchPotion.class, (Color)null, (Color)null, (Color)null, LvbuModHelper.MakePath(PitchPotion.class.getSimpleName()), lvbu.Enums.LVBU);
        BaseMod.addPotion((Class)AngerPotion.class, (Color)null, (Color)null, (Color)null, LvbuModHelper.MakePath(AngerPotion.class.getSimpleName()), lvbu.Enums.LVBU);
    }

    public void receiveAddAudio() {
        BaseMod.addAudio(QiNengJiuJuRenXia.ID, "lvbuModResources/sound/QiNengJiuJuRenXia.ogg");
        BaseMod.addAudio(BaiWeiYiFu.ID, "lvbuModResources/sound/BaiWeiYiFu.ogg");
        BaseMod.addAudio(DiaoChan.ID, "lvbuModResources/sound/DiaoChan.ogg");
        BaseMod.addAudio(DongZhuo.ID, "lvbuModResources/sound/DongZhuo.ogg");
        BaseMod.addAudio(FeiJiangXiaoTian.ID, "lvbuModResources/sound/FeiJiangXiaoTian.ogg");
        BaseMod.addAudio(FengZhiTaoZei.ID, "lvbuModResources/sound/FengZhiTaoZei.ogg");
        BaseMod.addAudio(GuanWaiZhuHou.ID, "lvbuModResources/sound/GuanWaiZhuHou.ogg");
        BaseMod.addAudio(JieJiu.ID, "lvbuModResources/sound/JieJiu.ogg");
        BaseMod.addAudio(LangZiYeXin.ID, "lvbuModResources/sound/LangZiYeXin.ogg");
        BaseMod.addAudio(LiuBei.ID, "lvbuModResources/sound/LiuBei.ogg");
        BaseMod.addAudio(WangYunMeiRenJi.ID, "lvbuModResources/sound/WangYunMeiRenJi.ogg");
        BaseMod.addAudio(TiTianXingDao.ID, "lvbuModResources/sound/TiTianXingDao.ogg");
        BaseMod.addAudio(WeiFengMingZhu.ID, "lvbuModResources/sound/WeiFengMingZhu.ogg");
        BaseMod.addAudio(XiDiaoChan.ID, "lvbuModResources/sound/XiDiaoChan.ogg");
        BaseMod.addAudio(XiaoLiCangDao.ID, "lvbuModResources/sound/XiaoLiCangDao.ogg");
        BaseMod.addAudio(XiYanZhou.ID, "lvbuModResources/sound/XiYanZhou.ogg");
        BaseMod.addAudio(YuanMen.ID, "lvbuModResources/sound/YuanMen.ogg");
    }

    public void receiveStartGame() {
        for (final AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.cardID.equals(LvbuModHelper.MakePath(HengSaoQianJun.class.getSimpleName()))) {
                c.baseDamage = c.misc;
                c.isDamageModified = false;
            }
            if (c.cardID.equals(LvbuModHelper.MakePath(LiZhenQianKun.class.getSimpleName())) && c.misc != 0) {
                c.updateCost(-c.misc);
                c.initializeDescription();
            }
        }
        SaveLoadSubscriber.super.receiveStartGame();
        LvbuSelectScreenPatch.characters[0].skins[LvbuSelectScreenPatch.characters[0].reskinCount].refresh();
    }

    public void onLoadGame() {
        if (AbstractDungeon.player.hasRelic(LvbuModHelper.MakePath(ChiTu.class.getSimpleName()))) {
            final AbstractRelic r = (AbstractRelic)new ChiTu_sl();
            r.obtain();
        }
        if (AbstractDungeon.player.hasRelic(LvbuModHelper.MakePath(WanLiChiTu.class.getSimpleName()))) {
            final AbstractRelic r = (AbstractRelic)new WanLiChiTu_sl();
            r.obtain();
        }
    }

    public void receiveSetUnlocks() {
        registerUnlockSuiteAlternating(ZhangLiao.ID, ShaMieJi.ID, XiDiaoChan.ID, BingQiJia.ID, DianTianDeng.ID, JinJiaJinPao.ID, GuiShenTingWei.ID, HengSaoQianJun.ID, QuWoBingRen.ID, TongJing.ID, XiaoShou.ID, XingJunQi.ID, HuJia.ID, LangZiYeXin.ID, FuXiuErQu.ID, lvbu.Enums.LVBU);
    }

    public static void registerUnlockSuiteAlternating(final String bundle1card1, final String bundle1card2, final String bundle1card3, final String bundle2relic1, final String bundle2relic2, final String bundle2relic3, final String bundle3card1, final String bundle3card2, final String bundle3card3, final String bundle4relic1, final String bundle4relic2, final String bundle4relic3, final String bundle5card1, final String bundle5card2, final String bundle5card3, final AbstractPlayer.PlayerClass player) {
        registerUnlockCardBundle(player, 0, bundle1card1, bundle1card2, bundle1card3);
        registerUnlockRelicBundle(player, 1, bundle2relic1, bundle2relic2, bundle2relic3);
        registerUnlockCardBundle(player, 2, bundle3card1, bundle3card2, bundle3card3);
        registerUnlockRelicBundle(player, 3, bundle4relic1, bundle4relic2, bundle4relic3);
        registerUnlockCardBundle(player, 4, bundle5card1, bundle5card2, bundle5card3);
    }

    private static void registerUnlockCardBundle(final AbstractPlayer.PlayerClass player, final int index, final String card1, final String card2, final String card3) {
        final CustomUnlockBundle currentBundle = new CustomUnlockBundle(card1, card2, card3);
        UnlockTracker.addCard(card1);
        UnlockTracker.addCard(card2);
        UnlockTracker.addCard(card3);
        BaseMod.addUnlockBundle(currentBundle, player, index);
        if (LvbuMod.unlockEverything || UnlockTracker.unlockProgress.getInteger(player.toString() + "UnlockLevel") > index + 1) {
            UnlockTracker.unlockCard(card1);
            UnlockTracker.unlockCard(card2);
            UnlockTracker.unlockCard(card3);
        }
    }

    private static void registerUnlockRelicBundle(final AbstractPlayer.PlayerClass player, final int index, final String relic1, final String relic2, final String relic3) {
        final CustomUnlockBundle currentBundle = new CustomUnlockBundle(AbstractUnlock.UnlockType.RELIC, relic1, relic2, relic3);
        UnlockTracker.addRelic(relic1);
        UnlockTracker.addRelic(relic2);
        UnlockTracker.addRelic(relic3);
        BaseMod.addUnlockBundle(currentBundle, player, index);
        if (LvbuMod.unlockEverything || UnlockTracker.unlockProgress.getInteger(player.toString() + "UnlockLevel") > index) {
            while (UnlockTracker.lockedRelics.contains(relic1)) {
                UnlockTracker.lockedRelics.remove(relic1);
            }
            while (UnlockTracker.lockedRelics.contains(relic2)) {
                UnlockTracker.lockedRelics.remove(relic2);
            }
            while (UnlockTracker.lockedRelics.contains(relic3)) {
                UnlockTracker.lockedRelics.remove(relic3);
            }
            UnlockTracker.markRelicAsSeen(relic1);
            UnlockTracker.markRelicAsSeen(relic2);
            UnlockTracker.markRelicAsSeen(relic3);
        }
    }

    static {
        MY_COLOR_1_1 = new Color(0.9996078f, 0.0f, 0.0f, 1.0f);
        GLOWING_RED = new Color(0.5137255f, 0.4862745f, 0.4862745f, 1.0f);
        MY_COLOR2_2 = new Color(0.015686275f, 0.16470589f, 0.22745098f, 1.0f);
        LvbuMod.unlockEverything = false;
    }
}
