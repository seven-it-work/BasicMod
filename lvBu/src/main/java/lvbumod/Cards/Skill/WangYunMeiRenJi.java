package lvbumod.Cards.Skill;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.actions.*;
import lvbumod.Actions.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class WangYunMeiRenJi extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 2;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public WangYunMeiRenJi() {
        super(WangYunMeiRenJi.ID, false, WangYunMeiRenJi.CARD_STRINGS, 2, WangYunMeiRenJi.TYPE, WangYunMeiRenJi.COLOR, WangYunMeiRenJi.RARITY, WangYunMeiRenJi.TARGET);
        this.setupMagicNumber(1);
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeBaseCost(1);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (LvbuModHelper.lvbuExtend()) {
            this.addToBot((AbstractGameAction)new SFXAction(WangYunMeiRenJi.ID));
        }
        this.addToBot((AbstractGameAction)new FvkAction(this.magicNumber, false, true, false));
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new WangYunMeiRenJi();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(WangYunMeiRenJi.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(WangYunMeiRenJi.ID);
        TYPE = CardType.SKILL;
        RARITY = CardRarity.UNCOMMON;
        TARGET = CardTarget.NONE;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
