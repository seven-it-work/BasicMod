package lvbumod.Cards.Attack;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import lvbumod.Characters.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.cards.*;
import lvbumod.Actions.*;
import lvbumod.Powers.*;
import com.megacrit.cardcrawl.core.*;

public class QiNengJiuJuRenXia extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 2;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    private int extra;
    private float multiple;
    
    public QiNengJiuJuRenXia() {
        super(QiNengJiuJuRenXia.ID, false, QiNengJiuJuRenXia.CARD_STRINGS, 2, QiNengJiuJuRenXia.TYPE, QiNengJiuJuRenXia.COLOR, QiNengJiuJuRenXia.RARITY, QiNengJiuJuRenXia.TARGET);
        this.extra = 0;
        this.multiple = 1.5f;
        this.setupDamage(10);
        this.tags.add(lvbu.Enums.BETRAY_CARD);
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(4);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (m.hasPower(LvbuModHelper.MakePath(DadPower.class.getSimpleName()))) {
            this.addToBot((AbstractGameAction)new SFXAction(QiNengJiuJuRenXia.ID));
        }
        this.calculateCardDamage(m);
        this.addToBot((AbstractGameAction)new BetrayAction(p, m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn)));
    }
    
    public void applyPowers() {
        super.applyPowers();
        this.initializeDescription();
    }
    
    public void calculateCardDamage(final AbstractMonster mo) {
        this.extra = 0;
        if (mo.hasPower(LvbuModHelper.MakePath(HatePower.class.getSimpleName()))) {
            this.extra = mo.getPower(LvbuModHelper.MakePath(HatePower.class.getSimpleName())).amount;
        }
        final int realBaseDamage = this.baseDamage;
        this.baseDamage += (int)(this.extra * this.multiple);
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = (this.damage != this.baseDamage);
        this.initializeDescription();
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new QiNengJiuJuRenXia();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(QiNengJiuJuRenXia.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(QiNengJiuJuRenXia.ID);
        TYPE = CardType.ATTACK;
        RARITY = CardRarity.BASIC;
        TARGET = CardTarget.ENEMY;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
