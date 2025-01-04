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
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class XiDiaoChan extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 0;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public XiDiaoChan() {
        super(XiDiaoChan.ID, false, XiDiaoChan.CARD_STRINGS, 0, XiDiaoChan.TYPE, XiDiaoChan.COLOR, XiDiaoChan.RARITY, XiDiaoChan.TARGET);
        this.setupMagicNumber(2);
        this.secondaryM = 1;
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeMagicNumber(-1);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (LvbuModHelper.lvbuExtend()) {
            this.addToBot((AbstractGameAction)new SFXAction(XiDiaoChan.ID));
        }
        this.addToBot((AbstractGameAction)new FvkAction(this.secondaryM));
        this.addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new WeakPower((AbstractCreature)p, this.magicNumber, false), this.magicNumber));
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new XiDiaoChan();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(XiDiaoChan.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(XiDiaoChan.ID);
        TYPE = CardType.SKILL;
        RARITY = CardRarity.COMMON;
        TARGET = CardTarget.SELF;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
