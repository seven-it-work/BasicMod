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

public class ShenWei extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 1;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public ShenWei() {
        super(ShenWei.ID, false, ShenWei.CARD_STRINGS, 1, ShenWei.TYPE, ShenWei.COLOR, ShenWei.RARITY, ShenWei.TARGET);
        this.setupMagicNumber(1);
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeBaseCost(0);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.applyToPlayer(new ShenWeiPower((AbstractCreature)p, this.magicNumber));
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new ShenWei();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(ShenWei.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ShenWei.ID);
        TYPE = CardType.POWER;
        RARITY = CardRarity.UNCOMMON;
        TARGET = CardTarget.SELF;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
