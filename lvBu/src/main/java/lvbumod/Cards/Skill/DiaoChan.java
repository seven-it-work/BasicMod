package lvbumod.Cards.Skill;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.powers.*;
import lvbumod.Powers.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class DiaoChan extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 1;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public DiaoChan() {
        super(DiaoChan.ID, false, DiaoChan.CARD_STRINGS, 1, DiaoChan.TYPE, DiaoChan.COLOR, DiaoChan.RARITY, DiaoChan.TARGET);
        this.setupMagicNumber(4);
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeMagicNumber(1);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.applyToPlayer(new FightHardPower((AbstractCreature)p, this.magicNumber));
        if (m != null) {
            this.addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)p, (AbstractPower)new DadPower(m)));
        }
        if (LvbuModHelper.lvbuExtend()) {
            this.addToBot((AbstractGameAction)new WaitAction(0.1f));
            this.addToBot((AbstractGameAction)new SFXAction(DiaoChan.ID));
        }
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new DiaoChan();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(DiaoChan.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(DiaoChan.ID);
        TYPE = CardType.SKILL;
        RARITY = CardRarity.UNCOMMON;
        TARGET = CardTarget.ENEMY;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
