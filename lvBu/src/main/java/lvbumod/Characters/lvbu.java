package lvbumod.Characters;

import basemod.abstracts.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.*;
import lvbumod.Patches.*;
import lvbumod.Cards.Skill.*;
import lvbumod.Relics.*;
import com.megacrit.cardcrawl.screens.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.cards.*;
import com.badlogic.gdx.graphics.*;
import lvbumod.ModCore.*;
import com.badlogic.gdx.graphics.g2d.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.cutscenes.*;
import com.megacrit.cardcrawl.events.city.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.dungeons.*;
import lvbumod.Powers.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.powers.*;
import lvbumod.Cards.Attack.*;
import java.util.*;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.helpers.*;

public class lvbu extends CustomPlayer
{
    private static final String MY_CHARACTER_SHOULDER_1 = "lvbuModResources/img/char/shoulder1.png";
    private static final String MY_CHARACTER_SHOULDER_2 = "lvbuModResources/img/char/shoulder2.png";
    private static final String CORPSE_IMAGE = "lvbuModResources/img/char/corpse.png";
    public static final String[] ORB_TEXTURES;
    public static final String[] ORB_TEXTURES2;
    private static final float[] LAYER_SPEED;
    private static final CharacterStrings characterStrings;
    public static boolean ShuffleFlag;
    public static AbstractMonster GaoShunTarget;
    public static boolean HasBetray;
    
    public lvbu(final String name) {
        super(name, Enums.LVBU, lvbu.ORB_TEXTURES, "lvbuModResources/img/UI/orb/vfx.png", lvbu.LAYER_SPEED, (String)null, (String)null);
        this.dialogX = this.drawX + 0.0f * Settings.scale;
        this.dialogY = this.drawY + 300.0f * Settings.scale;
        this.initializeClass(LvbuSelectScreenPatch.characters[0].skins[LvbuSelectScreenPatch.characters[0].reskinCount].orgLvbuPath, "lvbuModResources/img/char/shoulder2.png", "lvbuModResources/img/char/shoulder1.png", "lvbuModResources/img/char/corpse.png", this.getLoadout(), 0.0f, 0.0f, 200.0f, 220.0f, new EnergyManager(3));
    }
    
    public ArrayList<String> getStartingDeck() {
        final ArrayList<String> retVal = new ArrayList<String>();
        for (int x = 0; x < 4; ++x) {
            retVal.add(Strike_lvbu.ID);
        }
        for (int x = 0; x < 4; ++x) {
            retVal.add(Defend_lvbu.ID);
        }
        retVal.add(BaiWeiYiFu.ID);
        retVal.add(QiNengJiuJuRenXia.ID);
        retVal.add(SeYu.ID);
        return retVal;
    }
    
    public ArrayList<String> getStartingRelics() {
        final ArrayList<String> retVal = new ArrayList<String>();
        retVal.add(ChiTu.ID);
        return retVal;
    }
    
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(lvbu.characterStrings.NAMES[0], lvbu.characterStrings.TEXT[0] + lvbu.characterStrings.TEXT[2], 81, 81, 0, 99, 5, (AbstractPlayer)this, (ArrayList)this.getStartingRelics(), (ArrayList)this.getStartingDeck(), false);
    }
    
    public String getTitle(final PlayerClass playerClass) {
        return lvbu.characterStrings.NAMES[0];
    }
    
    public AbstractCard.CardColor getCardColor() {
        return Enums.LVBU_CARD;
    }
    
    public AbstractCard getStartCardForEvent() {
        return (AbstractCard)new BaiWeiYiFu();
    }
    
    public Color getCardTrailColor() {
        return LvbuMod.MY_COLOR2_2;
    }
    
    public int getAscensionMaxHPLoss() {
        return 5;
    }
    
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }
    
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA(LvbuModHelper.MakePath("ATTACK_HEAVY"), 1.0f);
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, true);
    }
    
    public ArrayList<CutscenePanel> getCutscenePanels() {
        final ArrayList<CutscenePanel> panels = new ArrayList<CutscenePanel>();
        panels.add(new CutscenePanel("lvbuModResources/img/char/Victory1.png", LvbuModHelper.MakePath("ATTACK_HEAVY")));
        panels.add(new CutscenePanel("lvbuModResources/img/char/Victory2.png"));
        panels.add(new CutscenePanel("lvbuModResources/img/char/Victory3.png"));
        return panels;
    }
    
    public String getCustomModeCharacterButtonSoundKey() {
        return LvbuModHelper.MakePath("ATTACK_HEAVY");
    }
    
    public String getLocalizedCharacterName() {
        return lvbu.characterStrings.NAMES[0];
    }
    
    public AbstractPlayer newInstance() {
        return (AbstractPlayer)new lvbu(this.name);
    }
    
    public String getSpireHeartText() {
        return lvbu.characterStrings.TEXT[1];
    }
    
    public Color getSlashAttackColor() {
        return LvbuMod.MY_COLOR2_2;
    }
    
    public String getVampireText() {
        return Vampires.DESCRIPTIONS[0];
    }
    
    public Color getCardRenderColor() {
        return LvbuMod.MY_COLOR2_2;
    }
    
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[] { AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL, AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL };
    }
    
    public void applyStartOfCombatLogic() {
        super.applyStartOfCombatLogic();
        AbstractDungeon.actionManager.addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new FightHardPower((AbstractCreature)AbstractDungeon.player, 0)));
        lvbu.ShuffleFlag = false;
        lvbu.GaoShunTarget = null;
        for (final AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.cardID.equals(LvbuModHelper.MakePath(GaoShun.class.getSimpleName()))) {
                c.target = AbstractCard.CardTarget.ENEMY;
            }
        }
        lvbu.HasBetray = false;
        LvbuModHelper.resetCharacter();
        LvbuModHelper.glowingBlue();
    }
    
    static {
        ORB_TEXTURES = new String[] { "lvbuModResources/img/UI/orb_2/layer5.png", "lvbuModResources/img/UI/orb_2/layer4.png", "lvbuModResources/img/UI/orb_2/layer3.png", "lvbuModResources/img/UI/orb_2/layer2.png", "lvbuModResources/img/UI/orb_2/layer1.png", "lvbuModResources/img/UI/orb_2/layer6.png", "lvbuModResources/img/UI/orb_2/layer5d.png", "lvbuModResources/img/UI/orb_2/layer4d.png", "lvbuModResources/img/UI/orb_2/layer3d.png", "lvbuModResources/img/UI/orb_2/layer2d.png", "lvbuModResources/img/UI/orb_2/layer1d.png" };
        ORB_TEXTURES2 = new String[] { "lvbuModResources/img/UI/orb/layer5.png", "lvbuModResources/img/UI/orb/layer4.png", "lvbuModResources/img/UI/orb/layer3.png", "lvbuModResources/img/UI/orb/layer2.png", "lvbuModResources/img/UI/orb/layer1.png", "lvbuModResources/img/UI/orb/layer6.png", "lvbuModResources/img/UI/orb/layer5d.png", "lvbuModResources/img/UI/orb/layer4d.png", "lvbuModResources/img/UI/orb/layer3d.png", "lvbuModResources/img/UI/orb/layer2d.png", "lvbuModResources/img/UI/orb/layer1d.png" };
        LAYER_SPEED = new float[] { -40.0f, 0.0f, -20.0f, 20.0f, 10.0f, -10.0f, 10.0f, 5.0f, -5.0f, 0.0f };
        characterStrings = CardCrawlGame.languagePack.getCharacterString(LvbuModHelper.MakePath("Lvbu"));
    }
    
    public static class Enums
    {
        @SpireEnum
        public static PlayerClass LVBU;
        @SpireEnum(name = "LVBU_GREEN")
        public static CardLibrary.LibraryType LVBU_LIBRARY;
        @SpireEnum(name = "\u5415\u5e03\uff08\u5f69\u86cb\uff09")
        public static CardLibrary.LibraryType LVBU_LIBRARY2;
        @SpireEnum
        public static AbstractCard.CardTags FVK_CARD;
        @SpireEnum
        public static AbstractCard.CardTags JI_ATTACK_CARD;
        @SpireEnum
        public static AbstractCard.CardTags BETRAY_CARD;
        @SpireEnum(name = "\u5415\u5e03\uff08\u5f69\u86cb\uff09")
        public static AbstractCard.CardColor LVBU_CARD2;
        @SpireEnum(name = "LVBU_GREEN")
        public static AbstractCard.CardColor LVBU_CARD;
    }
}
