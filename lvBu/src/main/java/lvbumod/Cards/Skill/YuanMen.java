package lvbumod.Cards.Skill;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import lvbumod.Monsters.*;
import lvbumod.Actions.*;
import com.megacrit.cardcrawl.actions.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class YuanMen extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 3;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public YuanMen() {
        super(YuanMen.ID, false, YuanMen.CARD_STRINGS, 3, YuanMen.TYPE, YuanMen.COLOR, YuanMen.RARITY, YuanMen.TARGET);
        this.exhaust = true;
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeBaseCost(2);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        final float x = 0.0f;
        final float y = 0.0f;
        final AbstractMonster m2 = new JiFromYuanMen(x, y, 0);
        this.gainBlock();
        this.addToBot((AbstractGameAction)new NewEnemyAction(p, m2));
        if (LvbuModHelper.lvbuExtend()) {
            this.addToBot((AbstractGameAction)new SFXAction(YuanMen.ID));
        }
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new YuanMen();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(YuanMen.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(YuanMen.ID);
        TYPE = CardType.SKILL;
        RARITY = CardRarity.UNCOMMON;
        TARGET = CardTarget.NONE;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
