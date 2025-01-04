package lvbumod.Cards.Skill;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.dungeons.*;
import lvbumod.Powers.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import lvbumod.Actions.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class WuMingHuo extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 1;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public WuMingHuo() {
        super(WuMingHuo.ID, false, WuMingHuo.CARD_STRINGS, 1, WuMingHuo.TYPE, WuMingHuo.COLOR, WuMingHuo.RARITY, WuMingHuo.TARGET);
        this.setupBlock(8);
        this.secondaryM = 2;
    }
    
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(3);
        }
    }
    
    public void applyPowers() {
        super.applyPowers();
        this.magicNumber = this.baseMagicNumber;
        if (AbstractDungeon.player.hasPower(LvbuModHelper.MakePath(YinJiePower.class.getSimpleName()))) {
            this.magicNumber += AbstractDungeon.player.getPower(LvbuModHelper.MakePath(YinJiePower.class.getSimpleName())).amount;
            this.isMagicNumberModified = (this.magicNumber != this.baseMagicNumber);
        }
        this.refreshDescription();
    }
    
    private void refreshDescription() {
        this.rawDescription = WuMingHuo.CARD_STRINGS.DESCRIPTION;
        this.initializeDescription();
    }
    
    public void onMoveToDiscard() {
        this.refreshDescription();
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.gainBlock();
        this.addToBot((AbstractGameAction)new GetAngerFromHandAction(this.secondaryM, false));
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new WuMingHuo();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(WuMingHuo.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(WuMingHuo.ID);
        TYPE = CardType.SKILL;
        RARITY = CardRarity.COMMON;
        TARGET = CardTarget.ALL_ENEMY;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
