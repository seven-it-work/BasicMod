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

public class HuLaoGuan extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 2;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public HuLaoGuan() {
        super(HuLaoGuan.ID, false, HuLaoGuan.CARD_STRINGS, 2, HuLaoGuan.TYPE, HuLaoGuan.COLOR, HuLaoGuan.RARITY, HuLaoGuan.TARGET);
        this.setupMagicNumber(2);
        this.isEthereal = true;
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.isEthereal = false;
        this.upgradeDescription(HuLaoGuan.CARD_STRINGS);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.applyToPlayer(new HuLaoGuanPower((AbstractCreature)p, this.magicNumber));
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new HuLaoGuan();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(HuLaoGuan.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(HuLaoGuan.ID);
        TYPE = CardType.POWER;
        RARITY = CardRarity.RARE;
        TARGET = CardTarget.SELF;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
