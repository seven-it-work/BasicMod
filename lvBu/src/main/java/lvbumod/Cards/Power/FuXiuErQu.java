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

public class FuXiuErQu extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 1;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public FuXiuErQu() {
        super(FuXiuErQu.ID, false, FuXiuErQu.CARD_STRINGS, 1, FuXiuErQu.TYPE, FuXiuErQu.COLOR, FuXiuErQu.RARITY, FuXiuErQu.TARGET);
        this.setupMagicNumber(1);
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeBaseCost(0);
        this.upgradeDescription(FuXiuErQu.CARD_STRINGS);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.applyToPlayer(new FuXiuErQuPower((AbstractCreature)p, this.magicNumber));
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new FuXiuErQu();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(FuXiuErQu.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(FuXiuErQu.ID);
        TYPE = CardType.POWER;
        RARITY = CardRarity.UNCOMMON;
        TARGET = CardTarget.SELF;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
