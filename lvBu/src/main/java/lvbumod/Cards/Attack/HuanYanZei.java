package lvbumod.Cards.Attack;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.actions.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class HuanYanZei extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 1;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public HuanYanZei() {
        super(HuanYanZei.ID, false, HuanYanZei.CARD_STRINGS, 1, HuanYanZei.TYPE, HuanYanZei.COLOR, HuanYanZei.RARITY, HuanYanZei.TARGET);
        this.setupDamage(7);
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(3);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.damageToEnemy(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        if (LvbuModHelper.getEnemiesCount() > 0) {
            this.addToBot((AbstractGameAction)new DrawCardAction(LvbuModHelper.getEnemiesCount()));
        }
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new HuanYanZei();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(HuanYanZei.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(HuanYanZei.ID);
        TYPE = CardType.ATTACK;
        RARITY = CardRarity.UNCOMMON;
        TARGET = CardTarget.ENEMY;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
