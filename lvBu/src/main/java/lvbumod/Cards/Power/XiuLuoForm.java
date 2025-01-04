package lvbumod.Cards.Power;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import basemod.helpers.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import lvbumod.Powers.*;
import com.megacrit.cardcrawl.powers.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class XiuLuoForm extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 3;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public XiuLuoForm() {
        super(XiuLuoForm.ID, false, XiuLuoForm.CARD_STRINGS, 3, XiuLuoForm.TYPE, XiuLuoForm.COLOR, XiuLuoForm.RARITY, XiuLuoForm.TARGET);
        this.setupMagicNumber(1);
        this.isEthereal = true;
        this.tags.add(BaseModCardTags.FORM);
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.isEthereal = false;
        this.upgradeDescription(XiuLuoForm.CARD_STRINGS);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.applyToPlayer(new XiuLuoFormPower((AbstractCreature)p, this.magicNumber));
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new XiuLuoForm();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(XiuLuoForm.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(XiuLuoForm.ID);
        TYPE = CardType.POWER;
        RARITY = CardRarity.RARE;
        TARGET = CardTarget.SELF;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
