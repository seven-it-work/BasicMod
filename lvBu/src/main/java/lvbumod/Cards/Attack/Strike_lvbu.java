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

public class Strike_lvbu extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 1;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public Strike_lvbu() {
        super(Strike_lvbu.ID, false, Strike_lvbu.CARD_STRINGS, 1, Strike_lvbu.TYPE, Strike_lvbu.COLOR, Strike_lvbu.RARITY, Strike_lvbu.TARGET);
        this.setupDamage(6);
        this.tags.add(CardTags.STARTER_STRIKE);
        this.tags.add(CardTags.STRIKE);
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(3);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.damageToEnemy(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new Strike_lvbu();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(Strike_lvbu.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(Strike_lvbu.ID);
        TYPE = CardType.ATTACK;
        RARITY = CardRarity.BASIC;
        TARGET = CardTarget.ENEMY;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
