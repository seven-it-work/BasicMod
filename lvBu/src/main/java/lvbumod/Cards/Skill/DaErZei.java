package lvbumod.Cards.Skill;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import lvbumod.Helpers.*;
import lvbumod.Actions.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class DaErZei extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 1;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public DaErZei() {
        super(DaErZei.ID, false, DaErZei.CARD_STRINGS, 1, DaErZei.TYPE, DaErZei.COLOR, DaErZei.RARITY, DaErZei.TARGET);
        this.exhaust = true;
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeBaseCost(0);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (LvbuModHelper.getEnemiesCount() > 0) {
            this.addToBot((AbstractGameAction)new FvkAction(LvbuModHelper.getEnemiesCount()));
        }
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new DaErZei();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(DaErZei.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(DaErZei.ID);
        TYPE = CardType.SKILL;
        RARITY = CardRarity.UNCOMMON;
        TARGET = CardTarget.SELF;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
