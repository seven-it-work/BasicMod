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

public class TiTianXingDao extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 1;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    private int extra;
    private float multiple;
    
    public TiTianXingDao() {
        super(TiTianXingDao.ID, false, TiTianXingDao.CARD_STRINGS, 1, TiTianXingDao.TYPE, TiTianXingDao.COLOR, TiTianXingDao.RARITY, TiTianXingDao.TARGET);
        this.extra = 0;
        this.setupDamage(8);
        this.setupMagicNumber(2);
        this.multiple = (float)this.magicNumber;
        this.tags.add(lvbu.Enums.BETRAY_CARD);
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeMagicNumber(1);
        this.multiple = (float)this.magicNumber;
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (m.hasPower(LvbuModHelper.MakePath(DadPower.class.getSimpleName())) && LvbuModHelper.lvbuExtend()) {
            this.addToBot((AbstractGameAction)new SFXAction(TiTianXingDao.ID));
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
        final AbstractCard c = (AbstractCard)new TiTianXingDao();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(TiTianXingDao.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(TiTianXingDao.ID);
        TYPE = CardType.ATTACK;
        RARITY = CardRarity.RARE;
        TARGET = CardTarget.ENEMY;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
