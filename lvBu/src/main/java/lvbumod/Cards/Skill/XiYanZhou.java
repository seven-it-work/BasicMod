package lvbumod.Cards.Skill;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.actions.*;
import lvbumod.Actions.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class XiYanZhou extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = -1;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public XiYanZhou() {
        super(XiYanZhou.ID, false, XiYanZhou.CARD_STRINGS, -1, XiYanZhou.TYPE, XiYanZhou.COLOR, XiYanZhou.RARITY, XiYanZhou.TARGET);
        this.setupMagicNumber(2);
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDescription(XiYanZhou.CARD_STRINGS);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (LvbuModHelper.lvbuExtend() && this.energyOnUse + (this.upgraded ? 1 : 0) > 0) {
            this.addToBot((AbstractGameAction)new SFXAction(XiYanZhou.ID));
        }
        this.addToBot((AbstractGameAction)new XiYanZhouAction(p, this.magicNumber, this.freeToPlayOnce, this.energyOnUse + (this.upgraded ? 1 : 0)));
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new XiYanZhou();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(XiYanZhou.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(XiYanZhou.ID);
        TYPE = CardType.SKILL;
        RARITY = CardRarity.RARE;
        TARGET = CardTarget.SELF;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
