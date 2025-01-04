package lvbumod.Cards.Attack;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import lvbumod.Characters.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.vfx.combat.*;
import com.megacrit.cardcrawl.actions.animations.*;
import com.megacrit.cardcrawl.vfx.*;
import com.megacrit.cardcrawl.dungeons.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.actions.common.*;
import lvbumod.Actions.*;
import lvbumod.Powers.*;
import java.util.*;
import com.megacrit.cardcrawl.core.*;

public class FengZhiTaoZei extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 2;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    private float multiple;
    
    public FengZhiTaoZei() {
        super(FengZhiTaoZei.ID, false, FengZhiTaoZei.CARD_STRINGS, 2, FengZhiTaoZei.TYPE, FengZhiTaoZei.COLOR, FengZhiTaoZei.RARITY, FengZhiTaoZei.TARGET);
        this.multiple = 1.5f;
        this.setupDamage(8);
        this.tags.add(lvbu.Enums.BETRAY_CARD);
        this.isMultiDamage = true;
        this.setupMagicNumber(1);
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeMagicNumber(1);
        this.multiple = (float)this.magicNumber;
        this.upgradeDescription(FengZhiTaoZei.CARD_STRINGS);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.addToBot((AbstractGameAction)new SFXAction("ATTACK_HEAVY"));
        this.addToBot((AbstractGameAction)new VFXAction((AbstractCreature)p, (AbstractGameEffect)new CleaveEffect(), 0.1f));
        final int temp = AbstractDungeon.getCurrRoom().monsters.monsters.size();
        for (int i = 0; i < temp; ++i) {
            final AbstractMonster m2 = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            if (!m2.isDeadOrEscaped() && m2.currentHealth > 0 && !m2.hasPower(LvbuModHelper.MakePath(DadPower.class.getSimpleName()))) {
                this.addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m2, new DamageInfo((AbstractCreature)AbstractDungeon.player, this.multiDamage[i]), AbstractGameAction.AttackEffect.NONE));
            }
        }
        for (int i = 0; i < temp; ++i) {
            final AbstractMonster m3 = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            if (!m3.isDeadOrEscaped() && m3.currentHealth > 0 && m3.hasPower(LvbuModHelper.MakePath(DadPower.class.getSimpleName()))) {
                this.addToBot((AbstractGameAction)new BetrayAction(p, m3, new DamageInfo((AbstractCreature)p, this.multiDamage[i], this.damageTypeForTurn)));
                if (LvbuModHelper.lvbuExtend()) {
                    this.addToBot((AbstractGameAction)new SFXAction(FengZhiTaoZei.ID));
                }
            }
        }
    }
    
    public void calculateCardDamage(final AbstractMonster mo) {
        final int realBaseDamage = this.baseDamage;
        AbstractMonster MaxHateMonster = null;
        int MaxHatePowerNum = 0;
        for (final AbstractMonster m2 : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!m2.isDeadOrEscaped() && m2.currentHealth > 0 && m2.hasPower(LvbuModHelper.MakePath(HatePower.class.getSimpleName())) && m2.getPower(LvbuModHelper.MakePath(HatePower.class.getSimpleName())).amount > MaxHatePowerNum) {
                MaxHatePowerNum = m2.getPower(LvbuModHelper.MakePath(HatePower.class.getSimpleName())).amount;
                MaxHateMonster = m2;
            }
        }
        if (MaxHateMonster != null) {
            this.baseDamage += (int)(MaxHateMonster.getPower(LvbuModHelper.MakePath(HatePower.class.getSimpleName())).amount * this.multiple);
        }
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = (this.damage != this.baseDamage);
        this.initializeDescription();
    }
    
    public void applyPowers() {
        final int realBaseDamage = this.baseDamage;
        AbstractMonster MaxHateMonster = null;
        int MaxHatePowerNum = 0;
        for (final AbstractMonster m2 : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!m2.isDeadOrEscaped() && m2.currentHealth > 0 && m2.hasPower(LvbuModHelper.MakePath(HatePower.class.getSimpleName())) && m2.getPower(LvbuModHelper.MakePath(HatePower.class.getSimpleName())).amount > MaxHatePowerNum) {
                MaxHatePowerNum = m2.getPower(LvbuModHelper.MakePath(HatePower.class.getSimpleName())).amount;
                MaxHateMonster = m2;
            }
        }
        if (MaxHateMonster != null) {
            this.baseDamage += (int)(MaxHateMonster.getPower(LvbuModHelper.MakePath(HatePower.class.getSimpleName())).amount * this.multiple);
        }
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = (this.damage != this.baseDamage);
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new FengZhiTaoZei();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(FengZhiTaoZei.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(FengZhiTaoZei.ID);
        TYPE = CardType.ATTACK;
        RARITY = CardRarity.UNCOMMON;
        TARGET = CardTarget.ALL_ENEMY;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
