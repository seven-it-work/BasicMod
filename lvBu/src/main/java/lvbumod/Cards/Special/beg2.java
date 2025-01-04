package lvbumod.Cards.Special;

import basemod.abstracts.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class beg2 extends CustomCard
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = -2;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public beg2() {
        super(beg2.ID, beg2.CARD_STRINGS.NAME, "lvbuModResources/img/cards/Special/beg2.png", -2, beg2.CARD_STRINGS.DESCRIPTION, beg2.TYPE, beg2.COLOR, beg2.RARITY, beg2.TARGET);
        this.exhaust = true;
    }
    
    public void upgrade() {
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new beg2();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(beg2.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(beg2.ID);
        TYPE = CardType.SKILL;
        RARITY = CardRarity.SPECIAL;
        TARGET = CardTarget.NONE;
        COLOR = lvbu.Enums.LVBU_CARD2;
    }
}
