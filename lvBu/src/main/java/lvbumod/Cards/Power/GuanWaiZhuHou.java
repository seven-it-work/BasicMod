package lvbumod.Cards.Power;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import lvbumod.Powers.*;
import com.megacrit.cardcrawl.powers.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class GuanWaiZhuHou extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 1;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public GuanWaiZhuHou() {
        super(GuanWaiZhuHou.ID, false, GuanWaiZhuHou.CARD_STRINGS, 1, GuanWaiZhuHou.TYPE, GuanWaiZhuHou.COLOR, GuanWaiZhuHou.RARITY, GuanWaiZhuHou.TARGET);
        this.setupMagicNumber(1);
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeMagicNumber(1);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.applyToPlayer(new GuanWaiZhuHouPower((AbstractCreature)p, this.magicNumber));
        if (LvbuModHelper.lvbuExtend()) {
            this.addToBot((AbstractGameAction)new SFXAction(GuanWaiZhuHou.ID));
        }
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new GuanWaiZhuHou();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(GuanWaiZhuHou.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(GuanWaiZhuHou.ID);
        TYPE = CardType.POWER;
        RARITY = CardRarity.UNCOMMON;
        TARGET = CardTarget.SELF;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
