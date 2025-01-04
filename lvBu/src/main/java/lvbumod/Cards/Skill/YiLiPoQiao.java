package lvbumod.Cards.Skill;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.dungeons.*;
import lvbumod.Helpers.*;
import java.util.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class YiLiPoQiao extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 1;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public YiLiPoQiao() {
        super(YiLiPoQiao.ID, false, YiLiPoQiao.CARD_STRINGS, 1, YiLiPoQiao.TYPE, YiLiPoQiao.COLOR, YiLiPoQiao.RARITY, YiLiPoQiao.TARGET);
        this.setupBlock(2);
        this.secondaryM = 2;
        this.setupMagicNumber(5);
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeMagicNumber(2);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.gainBlock();
    }
    
    public void applyPowers() {
        int extra = this.secondaryM;
        for (final AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (LvbuModHelper.getAnger(c)) {
                extra += this.magicNumber;
            }
        }
        this.baseBlock = extra;
        super.applyPowers();
        this.initializeDescription();
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new YiLiPoQiao();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(YiLiPoQiao.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(YiLiPoQiao.ID);
        TYPE = CardType.SKILL;
        RARITY = CardRarity.UNCOMMON;
        TARGET = CardTarget.SELF;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
