package lvbumod.Cards.Skill;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.*;
import lvbumod.Powers.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class DuoXuZhou extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 1;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public DuoXuZhou() {
        super(DuoXuZhou.ID, false, DuoXuZhou.CARD_STRINGS, 1, DuoXuZhou.TYPE, DuoXuZhou.COLOR, DuoXuZhou.RARITY, DuoXuZhou.TARGET);
        this.setupMagicNumber(3);
        this.secondaryM = 2;
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeMagicNumber(1);
        this.upgradeSecondaryM(1);
        this.upgradeDescription(DuoXuZhou.CARD_STRINGS);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (p.hasPower(LvbuModHelper.MakePath(MatchlessPower.class.getSimpleName()))) {
            this.addToBot((AbstractGameAction)new GainEnergyAction(this.secondaryM));
        }
        else {
            this.applyToPlayer(new FightHardPower((AbstractCreature)p, this.magicNumber));
        }
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new DuoXuZhou();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(DuoXuZhou.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(DuoXuZhou.ID);
        TYPE = CardType.SKILL;
        RARITY = CardRarity.UNCOMMON;
        TARGET = CardTarget.SELF;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
