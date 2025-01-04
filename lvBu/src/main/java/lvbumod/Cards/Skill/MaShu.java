package lvbumod.Cards.Skill;

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

public class MaShu extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 1;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public MaShu() {
        super(MaShu.ID, false, MaShu.CARD_STRINGS, 1, MaShu.TYPE, MaShu.COLOR, MaShu.RARITY, MaShu.TARGET);
        this.setupBlock(9);
        this.setupMagicNumber(1);
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeBlock(3);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.gainBlock();
        this.applyToPlayer(new MaShuPower((AbstractCreature)p, this.magicNumber));
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new MaShu();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(MaShu.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(MaShu.ID);
        TYPE = CardType.SKILL;
        RARITY = CardRarity.COMMON;
        TARGET = CardTarget.SELF;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
