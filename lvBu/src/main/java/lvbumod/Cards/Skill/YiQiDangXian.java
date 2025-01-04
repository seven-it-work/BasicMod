package lvbumod.Cards.Skill;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import lvbumod.Helpers.*;
import lvbumod.Powers.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class YiQiDangXian extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 1;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public YiQiDangXian() {
        super(YiQiDangXian.ID, false, YiQiDangXian.CARD_STRINGS, 1, YiQiDangXian.TYPE, YiQiDangXian.COLOR, YiQiDangXian.RARITY, YiQiDangXian.TARGET);
        this.setupBlock(6);
        this.setupMagicNumber(1);
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeMagicNumber(1);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.gainBlock();
        if (LvbuModHelper.getEnemiesCount() > 0) {
            this.applyToPlayer(new FightHardPower((AbstractCreature)p, this.magicNumber * LvbuModHelper.getEnemiesCount()));
        }
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new YiQiDangXian();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(YiQiDangXian.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(YiQiDangXian.ID);
        TYPE = CardType.SKILL;
        RARITY = CardRarity.COMMON;
        TARGET = CardTarget.SELF;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
