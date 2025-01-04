package lvbumod.Cards.Attack;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.actions.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class DeprecatedZhaoJia extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 0;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public DeprecatedZhaoJia() {
        super(DeprecatedZhaoJia.ID, true, DeprecatedZhaoJia.CARD_STRINGS, 0, DeprecatedZhaoJia.TYPE, DeprecatedZhaoJia.COLOR, DeprecatedZhaoJia.RARITY, DeprecatedZhaoJia.TARGET);
        this.setupDamage(4);
        this.setupBlock(4);
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(2);
        this.upgradeBlock(2);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.damageToEnemy(m, AbstractGameAction.AttackEffect.SHIELD);
        if (LvbuModHelper.checkStupid()) {
            this.gainBlock();
        }
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new DeprecatedZhaoJia();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(DeprecatedZhaoJia.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(DeprecatedZhaoJia.ID);
        TYPE = CardType.ATTACK;
        RARITY = CardRarity.COMMON;
        TARGET = CardTarget.ENEMY;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
