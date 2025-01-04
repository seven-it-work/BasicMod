package lvbumod.Cards.Power;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.powers.*;
import lvbumod.Powers.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class ZhangYang extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 3;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public ZhangYang() {
        super(ZhangYang.ID, false, ZhangYang.CARD_STRINGS, 3, ZhangYang.TYPE, ZhangYang.COLOR, ZhangYang.RARITY, ZhangYang.TARGET);
        this.setupMagicNumber(1);
        this.secondaryM = 1;
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeMagicNumber(1);
        this.upgradeDescription(ZhangYang.CARD_STRINGS);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.applyToPlayer((AbstractPower)new BufferPower((AbstractCreature)p, this.magicNumber));
        this.applyToPlayer(new GuanWaiZhuHouPower((AbstractCreature)p, this.secondaryM));
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new ZhangYang();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(ZhangYang.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ZhangYang.ID);
        TYPE = CardType.POWER;
        RARITY = CardRarity.RARE;
        TARGET = CardTarget.SELF;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
