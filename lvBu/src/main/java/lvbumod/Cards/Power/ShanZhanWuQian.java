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

public class ShanZhanWuQian extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 2;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public ShanZhanWuQian() {
        super(ShanZhanWuQian.ID, false, ShanZhanWuQian.CARD_STRINGS, 2, ShanZhanWuQian.TYPE, ShanZhanWuQian.COLOR, ShanZhanWuQian.RARITY, ShanZhanWuQian.TARGET);
        this.setupMagicNumber(1);
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeBaseCost(1);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.applyToPlayer(new ShanZhanWuQianPower((AbstractCreature)p, this.magicNumber));
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new ShanZhanWuQian();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(ShanZhanWuQian.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ShanZhanWuQian.ID);
        TYPE = CardType.POWER;
        RARITY = CardRarity.RARE;
        TARGET = CardTarget.SELF;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
