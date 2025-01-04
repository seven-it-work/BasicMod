package lvbumod.Cards.Skill;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import lvbumod.Actions.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.dungeons.*;
import lvbumod.Powers.*;
import lvbumod.Helpers.*;
import java.util.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class CeShenShanJi extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 1;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public CeShenShanJi() {
        super(CeShenShanJi.ID, false, CeShenShanJi.CARD_STRINGS, 1, CeShenShanJi.TYPE, CeShenShanJi.COLOR, CeShenShanJi.RARITY, CeShenShanJi.TARGET);
        this.setupBlock(6);
        this.setupMagicNumber(1);
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeBlock(2);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.addToBot((AbstractGameAction)new GetAngerFromDrawAction(this.magicNumber));
        this.gainBlock();
        boolean hasDad = false;
        for (final AbstractMonster m2 : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!m2.isDeadOrEscaped() && m2.hasPower(LvbuModHelper.MakePath(DadPower.class.getSimpleName()))) {
                hasDad = true;
                break;
            }
        }
        if (hasDad) {
            this.gainBlock();
        }
        LvbuModHelper.resetLvbuCardColor((AbstractCard)this);
    }
    
    public void triggerOnGlowCheck() {
        if (LvbuModHelper.checkHasDad()) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
        else {
            LvbuModHelper.resetLvbuCardColor((AbstractCard)this);
        }
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new CeShenShanJi();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(CeShenShanJi.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(CeShenShanJi.ID);
        TYPE = CardType.SKILL;
        RARITY = CardRarity.UNCOMMON;
        TARGET = CardTarget.SELF;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
