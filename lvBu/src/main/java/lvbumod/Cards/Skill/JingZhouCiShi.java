package lvbumod.Cards.Skill;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.monsters.exordium.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.*;
import lvbumod.Actions.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class JingZhouCiShi extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 1;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public JingZhouCiShi() {
        super(JingZhouCiShi.ID, false, JingZhouCiShi.CARD_STRINGS, 1, JingZhouCiShi.TYPE, JingZhouCiShi.COLOR, JingZhouCiShi.RARITY, JingZhouCiShi.TARGET);
        this.setupMagicNumber(3);
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeMagicNumber(1);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        final float x = 0.0f;
        final float y = 0.0f;
        final AbstractMonster m2 = (AbstractMonster)new SpikeSlime_S(x, y, 0);
        this.addToBot((AbstractGameAction)new DrawCardAction(this.magicNumber));
        this.addToBot((AbstractGameAction)new NewEnemyAction(p, m2));
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new JingZhouCiShi();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(JingZhouCiShi.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(JingZhouCiShi.ID);
        TYPE = CardType.SKILL;
        RARITY = CardRarity.UNCOMMON;
        TARGET = CardTarget.NONE;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
