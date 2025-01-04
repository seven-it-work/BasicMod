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

public class FangTianHuaJi extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 0;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public FangTianHuaJi() {
        super(FangTianHuaJi.ID, false, FangTianHuaJi.CARD_STRINGS, 0, FangTianHuaJi.TYPE, FangTianHuaJi.COLOR, FangTianHuaJi.RARITY, FangTianHuaJi.TARGET);
        this.setupMagicNumber(2);
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeMagicNumber(1);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.applyToPlayer(new FangTianHuaJiPower((AbstractCreature)p, this.magicNumber));
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new FangTianHuaJi();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(FangTianHuaJi.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(FangTianHuaJi.ID);
        TYPE = CardType.POWER;
        RARITY = CardRarity.UNCOMMON;
        TARGET = CardTarget.SELF;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
