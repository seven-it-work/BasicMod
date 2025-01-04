package lvbumod.Cards.Skill;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import lvbumod.Powers.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class Retreat extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 0;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public Retreat() {
        super(Retreat.ID, false, Retreat.CARD_STRINGS, 0, Retreat.TYPE, Retreat.COLOR, Retreat.RARITY, Retreat.TARGET);
        this.setupMagicNumber(2);
        this.secondaryM = 2;
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeMagicNumber(-1);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (p.hasPower(LvbuModHelper.MakePath(FightHardPower.class.getSimpleName())) && p.getPower(LvbuModHelper.MakePath(FightHardPower.class.getSimpleName())).amount >= this.magicNumber) {
            final int decreaseNum = Math.min(this.magicNumber, p.getPower(LvbuModHelper.MakePath(FightHardPower.class.getSimpleName())).amount);
            this.addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new FightHardPower((AbstractCreature)p, -decreaseNum), -decreaseNum));
            this.drawCards(this.secondaryM);
        }
        else if (p.hasPower(LvbuModHelper.MakePath(FightHardPower.class.getSimpleName())) && p.getPower(LvbuModHelper.MakePath(FightHardPower.class.getSimpleName())).amount > 0) {
            final int decreaseNum = Math.min(this.magicNumber, p.getPower(LvbuModHelper.MakePath(FightHardPower.class.getSimpleName())).amount);
            this.addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new FightHardPower((AbstractCreature)p, -decreaseNum), -decreaseNum));
        }
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new Retreat();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(Retreat.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(Retreat.ID);
        TYPE = CardType.SKILL;
        RARITY = CardRarity.UNCOMMON;
        TARGET = CardTarget.SELF;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
