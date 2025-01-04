package lvbumod.Cards.Skill;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.actions.*;
import lvbumod.Characters.*;
import lvbumod.Powers.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;

public class TiTouLaiJian extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 2;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public TiTouLaiJian() {
        super(TiTouLaiJian.ID, false, TiTouLaiJian.CARD_STRINGS, 2, TiTouLaiJian.TYPE, TiTouLaiJian.COLOR, TiTouLaiJian.RARITY, TiTouLaiJian.TARGET);
        this.setupMagicNumber(10);
        this.exhaust = true;
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeMagicNumber(3);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)p, (AbstractPower)new DadPower(m)));
        final int tmp = this.magicNumber;
        this.addToBot((AbstractGameAction)new AbstractGameAction() {
            public void update() {
                if (lvbu.HasBetray) {
                    this.addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new FightHardPower((AbstractCreature)p, tmp), tmp));
                }
                this.isDone = true;
            }
        });
    }
    
    public void triggerOnGlowCheck() {
        if (lvbu.HasBetray) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
        else {
            LvbuModHelper.resetLvbuCardColor((AbstractCard)this);
        }
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new TiTouLaiJian();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(TiTouLaiJian.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(TiTouLaiJian.ID);
        TYPE = CardType.SKILL;
        RARITY = CardRarity.UNCOMMON;
        TARGET = CardTarget.ENEMY;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
