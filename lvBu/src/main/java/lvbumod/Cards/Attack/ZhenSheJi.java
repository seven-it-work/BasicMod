package lvbumod.Cards.Attack;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import lvbumod.Characters.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.actions.*;
import lvbumod.Helpers.*;
import lvbumod.Actions.*;
import com.megacrit.cardcrawl.core.*;

public class ZhenSheJi extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 1;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public ZhenSheJi() {
        super(ZhenSheJi.ID, false, ZhenSheJi.CARD_STRINGS, 1, ZhenSheJi.TYPE, ZhenSheJi.COLOR, ZhenSheJi.RARITY, ZhenSheJi.TARGET);
        this.setupDamage(10);
        this.tags.add(lvbu.Enums.JI_ATTACK_CARD);
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(3);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.damageToEnemy(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        final int a = LvbuModHelper.getEnemiesCount();
        if (a > 0) {
            this.addToBot((AbstractGameAction)new GetAngerFromDrawAction(a));
        }
        this.useJi(m);
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new ZhenSheJi();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(ZhenSheJi.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ZhenSheJi.ID);
        TYPE = CardType.ATTACK;
        RARITY = CardRarity.COMMON;
        TARGET = CardTarget.ENEMY;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
