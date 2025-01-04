package lvbumod.Cards.Skill;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.monsters.*;
import basemod.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.dungeons.*;
import java.util.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class FanZhi extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 1;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public FanZhi() {
        super(FanZhi.ID, false, FanZhi.CARD_STRINGS, 1, FanZhi.TYPE, FanZhi.COLOR, FanZhi.RARITY, FanZhi.TARGET);
        this.setupMagicNumber(10);
        this.setupBlock(15);
        this.exhaust = true;
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeBlock(5);
    }
    
    public int calIntentDamage(final AbstractMonster m) {
        if (m == null) {
            return 0;
        }
        int dmg = 0;
        if (!m.isDeadOrEscaped() && m.getIntentBaseDmg() >= 0) {
            int multiAmt = 0;
            multiAmt = (int)ReflectionHacks.getPrivate((Object)m, (Class)AbstractMonster.class, "intentMultiAmt");
            dmg = m.getIntentDmg();
            if (multiAmt > 1) {
                dmg *= multiAmt;
            }
        }
        return dmg;
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        boolean getBlock = false;
        for (final AbstractMonster m2 : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!m2.isDeadOrEscaped() && m2.currentHealth > 0 && this.calIntentDamage(m2) > this.magicNumber) {
                getBlock = true;
                break;
            }
        }
        if (getBlock) {
            this.gainBlock();
        }
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new FanZhi();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(FanZhi.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(FanZhi.ID);
        TYPE = CardType.SKILL;
        RARITY = CardRarity.COMMON;
        TARGET = CardTarget.SELF;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
