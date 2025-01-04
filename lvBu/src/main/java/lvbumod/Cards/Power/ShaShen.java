package lvbumod.Cards.Power;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import lvbumod.Powers.*;
import com.megacrit.cardcrawl.powers.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class ShaShen extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 2;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public ShaShen() {
        super(ShaShen.ID, false, ShaShen.CARD_STRINGS, 2, ShaShen.TYPE, ShaShen.COLOR, ShaShen.RARITY, ShaShen.TARGET);
        this.setupMagicNumber(1);
        this.isEthereal = true;
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.isEthereal = false;
        this.upgradeDescription(ShaShen.CARD_STRINGS);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.applyToPlayer(new ShaShenPower((AbstractCreature)p, this.magicNumber));
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new ShaShen();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(ShaShen.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ShaShen.ID);
        TYPE = CardType.POWER;
        RARITY = CardRarity.RARE;
        TARGET = CardTarget.SELF;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
