package lvbumod.Powers;

import com.megacrit.cardcrawl.localization.*;
import com.badlogic.gdx.graphics.*;
import lvbumod.Panels.*;
import com.megacrit.cardcrawl.vfx.combat.*;
import com.megacrit.cardcrawl.actions.animations.*;
import com.megacrit.cardcrawl.vfx.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.watcher.*;
import com.megacrit.cardcrawl.dungeons.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.powers.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.actions.common.*;
import java.util.*;
import lvbumod.Characters.*;
import lvbumod.Relics.*;
import com.megacrit.cardcrawl.core.*;

public class FightHardPower extends AbstractPower
{
    public static final String POWER_ID;
    private static final PowerStrings powerStrings;
    private static final String NAME;
    private static final String[] DESCRIPTIONS;
    private static int TriggerNum;
    protected Color greenColor2;
    
    public FightHardPower(final AbstractCreature owner, final int amount) {
        this.greenColor2 = Color.GREEN.cpy();
        this.name = FightHardPower.NAME;
        this.ID = FightHardPower.POWER_ID;
        this.owner = owner;
        this.amount = amount;
        final String path128 = "lvbuModResources/img/powers/FightHardPower84.png";
        final String path129 = "lvbuModResources/img/powers/FightHardPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path129), 0, 0, 32, 32);
        this.updateDescription();
    }
    
    public void updateDescription() {
        this.description = FightHardPower.DESCRIPTIONS[0] + FightHardPower.TriggerNum + FightHardPower.DESCRIPTIONS[1] + FightHardPower.TriggerNum + FightHardPower.DESCRIPTIONS[2];
        if (this.amount >= 0) {
            this.type = PowerType.BUFF;
            this.description = FightHardPower.DESCRIPTIONS[0] + FightHardPower.TriggerNum + FightHardPower.DESCRIPTIONS[1] + FightHardPower.TriggerNum + FightHardPower.DESCRIPTIONS[2];
        }
        else {
            this.type = PowerType.DEBUFF;
        }
    }
    
    public void onSpecificTrigger() {
        if (this.amount >= FightHardPower.TriggerNum) {
            this.flash();
            this.amount -= FightHardPower.TriggerNum;
            FightHardPanel.decreaseAnima(FightHardPower.TriggerNum);
            this.addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new WhirlwindEffect(new Color(1.0f, 0.9f, 0.4f, 1.0f), true)));
            this.addToBot((AbstractGameAction)new SkipEnemiesTurnAction());
            this.addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new MatchlessPower((AbstractCreature)AbstractDungeon.player)));
            if (AbstractDungeon.player.hasPower(LvbuModHelper.MakePath(XiuLuoFormPower.class.getSimpleName()))) {
                final int powers = AbstractDungeon.player.getPower(LvbuModHelper.MakePath(XiuLuoFormPower.class.getSimpleName())).amount;
                this.addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new StrengthPower(this.owner, 10 * powers), 10 * powers));
            }
            if (AbstractDungeon.player.hasPower(LvbuModHelper.MakePath(ShanZhanWuQianPower.class.getSimpleName()))) {
                AbstractDungeon.player.getPower(LvbuModHelper.MakePath(ShanZhanWuQianPower.class.getSimpleName())).onSpecificTrigger();
            }
        }
    }
    
    public void reducePower(final int reduceAmount) {
        this.fontScale = 8.0f;
        this.amount -= reduceAmount;
        FightHardPanel.decreaseAnima(reduceAmount);
        this.updateDescription();
        if (this.amount <= 0) {
            this.amount = 0;
        }
        if (this.amount >= 999) {
            this.amount = 999;
        }
    }
    
    public void stackPower(final int stackAmount) {
        this.fontScale = 8.0f;
        this.amount += stackAmount;
        FightHardPanel.increaseAnima(stackAmount);
        this.updateDescription();
        if (this.amount <= 0) {
            this.amount = 0;
        }
        if (this.amount >= 999) {
            this.amount = 999;
        }
    }
    
    public void renderAmount(final SpriteBatch sb, final float x, final float y, Color c) {
        super.renderAmount(sb, x, y, c);
        if (this.amount == 0) {
            if (!this.isTurnBased) {
                this.greenColor2.a = c.a;
                c = this.greenColor2;
            }
            FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(this.amount), x, y, this.fontScale, c);
        }
    }
    
    public void atEndOfTurn(final boolean isPlayer) {
        if (isPlayer) {
            for (final AbstractMonster m2 : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!m2.isDeadOrEscaped() && m2.currentHealth > 0 && m2.hasPower(LvbuModHelper.MakePath(HatePower.class.getSimpleName()))) {
                    final int HateAmount = m2.getPower(LvbuModHelper.MakePath(HatePower.class.getSimpleName())).amount;
                    if (HateAmount < m2.currentHealth) {
                        continue;
                    }
                    this.addToBot((AbstractGameAction)new LoseHPAction((AbstractCreature)m2, (AbstractCreature)AbstractDungeon.player, HateAmount, AbstractGameAction.AttackEffect.FIRE));
                    if (m2.hasPower(LvbuModHelper.MakePath(DadPower.class.getSimpleName()))) {
                        this.addToTop((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)m2, (AbstractCreature)AbstractDungeon.player, LvbuModHelper.MakePath(DadPower.class.getSimpleName())));
                    }
                    this.addToTop((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)m2, (AbstractCreature)AbstractDungeon.player, LvbuModHelper.MakePath(HatePower.class.getSimpleName())));
                }
            }
            if (AbstractDungeon.player.hasPower(LvbuModHelper.MakePath(FightHardPower.class.getSimpleName())) && !AbstractDungeon.player.hasPower(LvbuModHelper.MakePath(MatchlessPower.class.getSimpleName()))) {
                AbstractDungeon.player.getPower(LvbuModHelper.MakePath(FightHardPower.class.getSimpleName())).onSpecificTrigger();
            }
            if (AbstractDungeon.player.hasPower(LvbuModHelper.MakePath(MatchlessPower.class.getSimpleName()))) {
                this.addToTop((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, LvbuModHelper.MakePath(MatchlessPower.class.getSimpleName())));
            }
        }
    }
    
    public void onVictory() {
        super.onVictory();
        LvbuModHelper.glowingBlue();
        LvbuModHelper.resetCharacter();
    }
    
    public void atStartOfTurn() {
        super.atStartOfTurn();
        lvbu.ShuffleFlag = false;
        if (AbstractDungeon.player.hasRelic(LvbuModHelper.MakePath(JinJiaJinPao.class.getSimpleName()))) {
            for (final AbstractMonster m2 : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!m2.isDeadOrEscaped() && m2.currentHealth > 0 && m2.hasPower(LvbuModHelper.MakePath(HatePower.class.getSimpleName()))) {
                    m2.getPower(LvbuModHelper.MakePath(HatePower.class.getSimpleName())).onSpecificTrigger();
                }
            }
        }
    }
    
    static {
        POWER_ID = LvbuModHelper.MakePath(FightHardPower.class.getSimpleName());
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(FightHardPower.POWER_ID);
        NAME = FightHardPower.powerStrings.NAME;
        DESCRIPTIONS = FightHardPower.powerStrings.DESCRIPTIONS;
        FightHardPower.TriggerNum = 10;
    }
}
