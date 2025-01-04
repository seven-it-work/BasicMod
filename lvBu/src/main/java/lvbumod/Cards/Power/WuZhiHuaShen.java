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

public class WuZhiHuaShen extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 2;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public WuZhiHuaShen() {
        super(WuZhiHuaShen.ID, true, WuZhiHuaShen.CARD_STRINGS, 2, WuZhiHuaShen.TYPE, WuZhiHuaShen.COLOR, WuZhiHuaShen.RARITY, WuZhiHuaShen.TARGET);
        this.setupMagicNumber(2);
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeBaseCost(1);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.applyToPlayer(new WuZhiHuaShenPower((AbstractCreature)p, this.magicNumber));
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new WuZhiHuaShen();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(WuZhiHuaShen.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(WuZhiHuaShen.ID);
        TYPE = CardType.POWER;
        RARITY = CardRarity.UNCOMMON;
        TARGET = CardTarget.SELF;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
