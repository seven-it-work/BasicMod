package lvbumod.Cards.Attack;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.actions.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class LengQuan extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 1;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public LengQuan() {
        super(LengQuan.ID, false, LengQuan.CARD_STRINGS, 1, LengQuan.TYPE, LengQuan.COLOR, LengQuan.RARITY, LengQuan.TARGET);
        this.setupDamage(7);
        this.setupMagicNumber(1);
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeMagicNumber(1);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.damageToEnemy(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        for (int a = LvbuModHelper.getEnemiesCount(), i = 0; i < a; ++i) {
            this.addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)p, (AbstractPower)new WeakPower((AbstractCreature)m, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        }
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new LengQuan();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(LengQuan.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(LengQuan.ID);
        TYPE = CardType.ATTACK;
        RARITY = CardRarity.COMMON;
        TARGET = CardTarget.ENEMY;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
