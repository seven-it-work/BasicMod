package lvbumod.Cards.Special;

import basemod.abstracts.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class beg4 extends CustomCard
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = -2;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public beg4() {
        super(beg4.ID, beg4.CARD_STRINGS.NAME, "lvbuModResources/img/cards/Special/beg4.png", -2, beg4.CARD_STRINGS.DESCRIPTION, beg4.TYPE, beg4.COLOR, beg4.RARITY, beg4.TARGET);
        this.exhaust = true;
    }
    
    public void upgrade() {
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new beg4();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(beg4.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(beg4.ID);
        TYPE = CardType.SKILL;
        RARITY = CardRarity.SPECIAL;
        TARGET = CardTarget.NONE;
        COLOR = lvbu.Enums.LVBU_CARD2;
    }
}
