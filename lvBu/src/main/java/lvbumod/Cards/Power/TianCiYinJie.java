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

public class TianCiYinJie extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 1;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public TianCiYinJie() {
        super(TianCiYinJie.ID, false, TianCiYinJie.CARD_STRINGS, 1, TianCiYinJie.TYPE, TianCiYinJie.COLOR, TianCiYinJie.RARITY, TianCiYinJie.TARGET);
        this.setupMagicNumber(3);
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeMagicNumber(2);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.applyToPlayer(new YinJiePower((AbstractCreature)p, this.magicNumber));
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new TianCiYinJie();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(TianCiYinJie.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(TianCiYinJie.ID);
        TYPE = CardType.POWER;
        RARITY = CardRarity.UNCOMMON;
        TARGET = CardTarget.SELF;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
