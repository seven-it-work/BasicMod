package lvbumod.Cards.Skill;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.actions.*;
import lvbumod.Actions.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class GreenHat extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 0;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public GreenHat() {
        super(GreenHat.ID, false, GreenHat.CARD_STRINGS, 0, GreenHat.TYPE, GreenHat.COLOR, GreenHat.RARITY, GreenHat.TARGET);
        this.setupMagicNumber(1);
        this.secondaryM = 1;
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDescription(GreenHat.CARD_STRINGS);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.addToBot((AbstractGameAction)new GreenHatAction(this.upgraded));
        this.addToBot((AbstractGameAction)new GetAngerFromDrawAction(this.magicNumber));
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new GreenHat();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(GreenHat.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(GreenHat.ID);
        TYPE = CardType.SKILL;
        RARITY = CardRarity.UNCOMMON;
        TARGET = CardTarget.NONE;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
