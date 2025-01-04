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

public class WangYun extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 1;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public WangYun() {
        super(WangYun.ID, false, WangYun.CARD_STRINGS, 1, WangYun.TYPE, WangYun.COLOR, WangYun.RARITY, WangYun.TARGET);
        this.setupMagicNumber(1);
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeMagicNumber(1);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.applyToPlayer(new WangYunPower((AbstractCreature)p, this.magicNumber));
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new WangYun();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(WangYun.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(WangYun.ID);
        TYPE = CardType.POWER;
        RARITY = CardRarity.UNCOMMON;
        TARGET = CardTarget.SELF;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
