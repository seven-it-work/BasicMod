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

public class FengYiTing extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 2;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public FengYiTing() {
        super(FengYiTing.ID, false, FengYiTing.CARD_STRINGS, 2, FengYiTing.TYPE, FengYiTing.COLOR, FengYiTing.RARITY, FengYiTing.TARGET);
        this.setupMagicNumber(1);
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeBaseCost(1);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.applyToPlayer(new FengYiTingPower((AbstractCreature)p, this.magicNumber));
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new FengYiTing();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(FengYiTing.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(FengYiTing.ID);
        TYPE = CardType.POWER;
        RARITY = CardRarity.RARE;
        TARGET = CardTarget.SELF;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
