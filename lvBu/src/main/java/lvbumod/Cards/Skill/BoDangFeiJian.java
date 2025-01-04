package lvbumod.Cards.Skill;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import lvbumod.Actions.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.powers.*;
import java.util.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class BoDangFeiJian extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 0;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public BoDangFeiJian() {
        super(BoDangFeiJian.ID, false, BoDangFeiJian.CARD_STRINGS, 0, BoDangFeiJian.TYPE, BoDangFeiJian.COLOR, BoDangFeiJian.RARITY, BoDangFeiJian.TARGET);
        this.setupMagicNumber(1);
        this.secondaryM = 2;
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeMagicNumber(1);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.addToBot((AbstractGameAction)new GetAngerFromDrawAction(this.magicNumber));
        for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            this.addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)mo, (AbstractCreature)p, (AbstractPower)new StrengthPower((AbstractCreature)mo, -this.secondaryM), -this.secondaryM, true, AbstractGameAction.AttackEffect.NONE));
        }
        for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!mo.hasPower("Artifact")) {
                this.addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)mo, (AbstractCreature)p, (AbstractPower)new GainStrengthPower((AbstractCreature)mo, this.secondaryM), this.secondaryM, true, AbstractGameAction.AttackEffect.NONE));
            }
        }
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new BoDangFeiJian();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(BoDangFeiJian.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(BoDangFeiJian.ID);
        TYPE = CardType.SKILL;
        RARITY = CardRarity.COMMON;
        TARGET = CardTarget.SELF;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
