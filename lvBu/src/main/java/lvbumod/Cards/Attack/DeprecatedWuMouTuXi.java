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

public class DeprecatedWuMouTuXi extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 1;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public DeprecatedWuMouTuXi() {
        super(DeprecatedWuMouTuXi.ID, true, DeprecatedWuMouTuXi.CARD_STRINGS, 1, DeprecatedWuMouTuXi.TYPE, DeprecatedWuMouTuXi.COLOR, DeprecatedWuMouTuXi.RARITY, DeprecatedWuMouTuXi.TARGET);
        this.setupDamage(4);
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(2);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.damageToEnemy(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        if (LvbuModHelper.checkStupid()) {
            this.damageToEnemy(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
            this.damageToEnemy(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        }
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new DeprecatedWuMouTuXi();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(DeprecatedWuMouTuXi.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(DeprecatedWuMouTuXi.ID);
        TYPE = CardType.ATTACK;
        RARITY = CardRarity.COMMON;
        TARGET = CardTarget.ENEMY;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
