package lvbumod.Cards.Special;

import basemod.abstracts.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class beg1 extends CustomCard
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = -2;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public beg1() {
        super(beg1.ID, beg1.CARD_STRINGS.NAME, "lvbuModResources/img/cards/Special/beg1.png", -2, beg1.CARD_STRINGS.DESCRIPTION, beg1.TYPE, beg1.COLOR, beg1.RARITY, beg1.TARGET);
        this.exhaust = true;
        final int n = 999;
        this.baseDamage = n;
        this.damage = n;
    }
    
    public void upgrade() {
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new beg1();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(beg1.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(beg1.ID);
        TYPE = CardType.SKILL;
        RARITY = CardRarity.SPECIAL;
        TARGET = CardTarget.ENEMY;
        COLOR = lvbu.Enums.LVBU_CARD2;
    }
}
