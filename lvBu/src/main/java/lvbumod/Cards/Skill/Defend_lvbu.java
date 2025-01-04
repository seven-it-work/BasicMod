package lvbumod.Cards.Skill;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class Defend_lvbu extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 1;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public Defend_lvbu() {
        super(Defend_lvbu.ID, false, Defend_lvbu.CARD_STRINGS, 1, Defend_lvbu.TYPE, Defend_lvbu.COLOR, Defend_lvbu.RARITY, Defend_lvbu.TARGET);
        this.setupBlock(5);
        this.tags.add(CardTags.STARTER_DEFEND);
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeBlock(3);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.gainBlock();
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new Defend_lvbu();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(Defend_lvbu.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(Defend_lvbu.ID);
        TYPE = CardType.SKILL;
        RARITY = CardRarity.BASIC;
        TARGET = CardTarget.SELF;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
