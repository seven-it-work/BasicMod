package lvbumod.Cards.Skill;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class YiQiJueChen extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 0;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public YiQiJueChen() {
        super(YiQiJueChen.ID, false, YiQiJueChen.CARD_STRINGS, 0, YiQiJueChen.TYPE, YiQiJueChen.COLOR, YiQiJueChen.RARITY, YiQiJueChen.TARGET);
        this.setupMagicNumber(3);
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDescription(YiQiJueChen.CARD_STRINGS);
        this.isInnate = true;
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (LvbuModHelper.checkStupid()) {
            this.addToBot((AbstractGameAction)new DrawCardAction(this.magicNumber));
        }
        LvbuModHelper.resetLvbuCardColor((AbstractCard)this);
    }
    
    public void triggerOnGlowCheck() {
        boolean hasUse = false;
        if (!AbstractDungeon.actionManager.cardsPlayedThisTurn.isEmpty()) {
            for (int i = 0; i < AbstractDungeon.actionManager.cardsPlayedThisTurn.size(); ++i) {
                if (AbstractDungeon.actionManager.cardsPlayedThisTurn.get(i).type == CardType.SKILL) {
                    hasUse = true;
                    break;
                }
            }
        }
        if (!hasUse) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
        else {
            LvbuModHelper.resetLvbuCardColor((AbstractCard)this);
        }
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new YiQiJueChen();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(YiQiJueChen.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(YiQiJueChen.ID);
        TYPE = CardType.SKILL;
        RARITY = CardRarity.RARE;
        TARGET = CardTarget.NONE;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
