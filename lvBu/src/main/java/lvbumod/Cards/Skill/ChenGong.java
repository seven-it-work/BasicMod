package lvbumod.Cards.Skill;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import lvbumod.Actions.*;
import com.megacrit.cardcrawl.actions.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class ChenGong extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 2;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public ChenGong() {
        super(ChenGong.ID, false, ChenGong.CARD_STRINGS, 2, ChenGong.TYPE, ChenGong.COLOR, ChenGong.RARITY, ChenGong.TARGET);
        this.exhaust = true;
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeBaseCost(1);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.addToBot((AbstractGameAction)new GetAngerAllAction(p, false));
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new ChenGong();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(ChenGong.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ChenGong.ID);
        TYPE = CardType.SKILL;
        RARITY = CardRarity.RARE;
        TARGET = CardTarget.SELF;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
