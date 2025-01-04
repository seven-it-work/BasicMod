package lvbumod.Cards.Power;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import lvbumod.Powers.*;
import com.megacrit.cardcrawl.powers.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class WeiFengMingZhu extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 0;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public WeiFengMingZhu() {
        super(WeiFengMingZhu.ID, false, WeiFengMingZhu.CARD_STRINGS, 0, WeiFengMingZhu.TYPE, WeiFengMingZhu.COLOR, WeiFengMingZhu.RARITY, WeiFengMingZhu.TARGET);
        this.setupMagicNumber(1);
        this.isInnate = true;
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeMagicNumber(1);
        this.upgradeDescription(WeiFengMingZhu.CARD_STRINGS);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.applyToPlayer(new WeiFengMingZhuPower((AbstractCreature)p, this.magicNumber));
        if (LvbuModHelper.lvbuExtend()) {
            this.addToBot((AbstractGameAction)new SFXAction(WeiFengMingZhu.ID));
        }
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new WeiFengMingZhu();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(WeiFengMingZhu.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(WeiFengMingZhu.ID);
        TYPE = CardType.POWER;
        RARITY = CardRarity.UNCOMMON;
        TARGET = CardTarget.SELF;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
