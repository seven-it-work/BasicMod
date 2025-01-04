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
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class JieJiu extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 1;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public JieJiu() {
        super(JieJiu.ID, false, JieJiu.CARD_STRINGS, 1, JieJiu.TYPE, JieJiu.COLOR, JieJiu.RARITY, JieJiu.TARGET);
        this.setupMagicNumber(3);
        this.secondaryM = 1;
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDescription(JieJiu.CARD_STRINGS);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (LvbuModHelper.lvbuExtend()) {
            this.addToBot((AbstractGameAction)new SFXAction(JieJiu.ID));
        }
        this.addToBot((AbstractGameAction)new FvkAction(this.secondaryM));
        this.addToBot((AbstractGameAction)new GetAngerFromHandAction(this.magicNumber, false));
        if (p.hasPower("Weakened")) {
            this.addToBot((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)p, (AbstractCreature)p, "Weakened"));
        }
        if (p.hasPower("Vulnerable") && this.upgraded) {
            this.addToBot((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)p, (AbstractCreature)p, "Vulnerable"));
        }
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new JieJiu();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(JieJiu.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(JieJiu.ID);
        TYPE = CardType.SKILL;
        RARITY = CardRarity.UNCOMMON;
        TARGET = CardTarget.SELF;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
