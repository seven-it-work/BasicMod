package lvbumod.Cards.Skill;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.monsters.exordium.*;
import lvbumod.Powers.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.actions.*;
import lvbumod.Actions.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class TouBen extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 1;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public TouBen() {
        super(TouBen.ID, false, TouBen.CARD_STRINGS, 1, TouBen.TYPE, TouBen.COLOR, TouBen.RARITY, TouBen.TARGET);
        this.setupBlock(17);
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeBlock(5);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        final float x = 0.0f;
        final float y = 0.0f;
        final AbstractMonster m2 = (AbstractMonster)(AbstractDungeon.cardRandomRng.randomBoolean() ? new LouseNormal(x, y) : new LouseDefensive(x, y));
        this.gainBlock();
        if (m != null) {
            this.addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)p, (AbstractPower)new DadPower(m)));
        }
        this.addToBot((AbstractGameAction)new NewEnemyAction(p, m2));
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new TouBen();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(TouBen.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(TouBen.ID);
        TYPE = CardType.SKILL;
        RARITY = CardRarity.COMMON;
        TARGET = CardTarget.ENEMY;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
