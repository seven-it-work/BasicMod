package lvbumod.Cards.Special;

import basemod.abstracts.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class beg3 extends CustomCard
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = -2;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public beg3() {
        super(beg3.ID, beg3.CARD_STRINGS.NAME, "lvbuModResources/img/cards/Special/beg3.png", -2, beg3.CARD_STRINGS.DESCRIPTION, beg3.TYPE, beg3.COLOR, beg3.RARITY, beg3.TARGET);
        this.exhaust = true;
    }
    
    public void upgrade() {
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new beg3();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(beg3.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(beg3.ID);
        TYPE = CardType.SKILL;
        RARITY = CardRarity.SPECIAL;
        TARGET = CardTarget.NONE;
        COLOR = lvbu.Enums.LVBU_CARD2;
    }
}
