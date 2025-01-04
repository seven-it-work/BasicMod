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

public class LangZiYeXin extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 1;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public LangZiYeXin() {
        super(LangZiYeXin.ID, false, LangZiYeXin.CARD_STRINGS, 1, LangZiYeXin.TYPE, LangZiYeXin.COLOR, LangZiYeXin.RARITY, LangZiYeXin.TARGET);
        this.setupMagicNumber(10);
        this.secondaryM = 2;
        this.exhaust = true;
        this.isEthereal = true;
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeBaseCost(0);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (LvbuModHelper.lvbuExtend()) {
            this.addToBot((AbstractGameAction)new SFXAction(LangZiYeXin.ID));
        }
        this.addToBot((AbstractGameAction)new LangZiYeXinAction(p, this.magicNumber, this.secondaryM));
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new LangZiYeXin();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(LangZiYeXin.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(LangZiYeXin.ID);
        TYPE = CardType.SKILL;
        RARITY = CardRarity.RARE;
        TARGET = CardTarget.NONE;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
