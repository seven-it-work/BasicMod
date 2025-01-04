package lvbumod.Actions;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.cards.*;
import java.util.*;
import lvbumod.Helpers.*;
import lvbumod.Cards.Skill.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Powers.*;

public class GetAngerFromHandAction extends AbstractGameAction
{
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private AbstractPlayer p;
    private boolean isRandom;
    private boolean anyNumber;
    private boolean canPickZero;
    public static int numAnger;
    
    public GetAngerFromHandAction(final int amount, final boolean isRandom, final boolean anyNumber, final boolean canPickZero) {
        this.anyNumber = anyNumber;
        this.p = AbstractDungeon.player;
        this.canPickZero = canPickZero;
        this.isRandom = isRandom;
        this.amount = amount;
        final float action_DUR_FAST = Settings.ACTION_DUR_FAST;
        this.startDuration = action_DUR_FAST;
        this.duration = action_DUR_FAST;
        this.actionType = ActionType.SPECIAL;
    }
    
    public GetAngerFromHandAction(final boolean isRandom, final boolean anyNumber, final boolean canPickZero) {
        this(99, isRandom, anyNumber, canPickZero);
    }
    
    public GetAngerFromHandAction(final int amount, final boolean canPickZero) {
        this(amount, false, false, canPickZero);
    }
    
    public GetAngerFromHandAction(final int amount, final boolean isRandom, final boolean anyNumber) {
        this(amount, isRandom, anyNumber, false);
    }
    
    public GetAngerFromHandAction(final int amount, final boolean isRandom, final boolean anyNumber, final boolean canPickZero, final float duration) {
        this(amount, isRandom, anyNumber, canPickZero);
        this.startDuration = duration;
        this.duration = duration;
    }
    
    public void update() {
        if (this.duration == this.startDuration) {
            if (this.p.hand.size() == 0) {
                this.isDone = true;
                return;
            }
            if (!this.anyNumber && this.p.hand.size() <= 1) {
                this.amount = this.p.hand.size();
                GetAngerFromHandAction.numAnger = this.amount;
                for (int tmp = this.p.hand.size(), i = 0; i < tmp; ++i) {
                    final AbstractCard c = this.p.hand.getTopCard();
                    this.setAngerFromHand(c);
                }
                CardCrawlGame.dungeon.checkForPactAchievement();
                return;
            }
            if (this.p.hand.size() <= this.amount) {
                this.amount = this.p.hand.size();
            }
            if (!this.isRandom) {
                GetAngerFromHandAction.numAnger = this.amount;
                final String s = (this.amount > 1) ? GetAngerFromHandAction.TEXT[1] : GetAngerFromHandAction.TEXT[0];
                AbstractDungeon.handCardSelectScreen.open(s, this.amount, this.anyNumber, this.canPickZero);
                this.tickDuration();
                return;
            }
            for (int j = 0; j < this.amount; ++j) {
                final AbstractCard c2 = this.p.hand.getRandomCard(AbstractDungeon.cardRandomRng);
                this.setAngerFromHand(c2);
            }
        }
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (final AbstractCard c2 : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                this.setAngerFromHand(c2);
            }
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }
        this.tickDuration();
    }
    
    private void setAngerFromHand(final AbstractCard c) {
        LvbuModHelper.setAnger(c, true);
        if (c.cardID.equals(LvbuModHelper.MakePath(CaiJi.class.getSimpleName()))) {
            this.addToTop((AbstractGameAction)new DrawCardAction(c.magicNumber));
        }
        if (this.p.hasPower(LvbuModHelper.MakePath(ZhouXuanPower.class.getSimpleName()))) {
            this.addToTop((AbstractGameAction)new GainBlockAction((AbstractCreature)this.p, this.p.getPower(LvbuModHelper.MakePath(ZhouXuanPower.class.getSimpleName())).amount, Settings.FAST_MODE));
            if (this.p.hasPower(LvbuModHelper.MakePath(MatchlessPower.class.getSimpleName()))) {
                this.addToTop((AbstractGameAction)new GainBlockAction((AbstractCreature)this.p, this.p.getPower(LvbuModHelper.MakePath(ZhouXuanPower.class.getSimpleName())).amount, Settings.FAST_MODE));
            }
        }
        this.p.hand.moveToBottomOfDeck(c);
    }
    
    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(LvbuModHelper.MakePath("GetAngerFromHand"));
        TEXT = GetAngerFromHandAction.uiStrings.TEXT;
    }
}
