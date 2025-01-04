package lvbumod.Powers;

import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.helpers.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.megacrit.cardcrawl.actions.utility.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.dungeons.*;
import lvbumod.Cards.Skill.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import java.util.*;
import lvbumod.Characters.*;
import com.megacrit.cardcrawl.core.*;

public class DeprecatedStupidPower extends AbstractPower
{
    public static final String POWER_ID;
    private static final PowerStrings powerStrings;
    private static final String NAME;
    private static final String[] DESCRIPTIONS;
    
    public DeprecatedStupidPower(final AbstractCreature owner) {
        this.name = DeprecatedStupidPower.NAME;
        this.ID = DeprecatedStupidPower.POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = 0;
        final String path128 = "lvbuModResources/img/powers/StupidPower84.png";
        final String path129 = "lvbuModResources/img/powers/StupidPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path129), 0, 0, 32, 32);
        this.updateDescription();
    }
    
    public void updateDescription() {
        if (this.amount == 0) {
            this.description = DeprecatedStupidPower.powerStrings.DESCRIPTIONS[0];
        }
        else {
            this.description = DeprecatedStupidPower.powerStrings.DESCRIPTIONS[1];
        }
    }
    
    public void onUseCard(final AbstractCard c, final UseCardAction action) {
        super.onUseCard(c, action);
        if (this.owner.hasPower(LvbuModHelper.MakePath(ChenGongPower.class.getSimpleName()))) {
            this.amount = 0;
            this.updateDescription();
        }
        if (c.type.equals((Object)AbstractCard.CardType.SKILL) && !AbstractDungeon.getMonsters().areMonstersBasicallyDead() && !this.owner.hasPower(LvbuModHelper.MakePath(ChenGongPower.class.getSimpleName())) && !c.cardID.equals(LvbuModHelper.MakePath(ChenGong.class.getSimpleName())) && this.amount == 0) {
            ++this.amount;
            this.flash();
            this.updateDescription();
        }
    }
    
    public void atEndOfTurn(final boolean isPlayer) {
        if (isPlayer) {
            this.amount = 0;
            this.updateDescription();
            for (final AbstractMonster m2 : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!m2.isDeadOrEscaped() && m2.currentHealth > 0 && m2.hasPower(LvbuModHelper.MakePath(HatePower.class.getSimpleName()))) {
                    final int HateAmount = m2.getPower(LvbuModHelper.MakePath(HatePower.class.getSimpleName())).amount;
                    if (HateAmount < m2.currentHealth) {
                        continue;
                    }
                    this.addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m2, new DamageInfo((AbstractCreature)AbstractDungeon.player, HateAmount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
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
    
    public void atStartOfTurn() {
        super.atStartOfTurn();
        lvbu.ShuffleFlag = false;
    }
    
    public void onVictory() {
        super.onVictory();
    }
    
    static {
        POWER_ID = LvbuModHelper.MakePath(DeprecatedStupidPower.class.getSimpleName());
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(DeprecatedStupidPower.POWER_ID);
        NAME = DeprecatedStupidPower.powerStrings.NAME;
        DESCRIPTIONS = DeprecatedStupidPower.powerStrings.DESCRIPTIONS;
    }
}
