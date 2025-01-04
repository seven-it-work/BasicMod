package lvbumod.Actions;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.cards.*;
import java.util.*;
import lvbumod.Helpers.*;
import lvbumod.Cards.Skill.*;
import com.megacrit.cardcrawl.actions.common.*;
import lvbumod.Powers.*;
import com.megacrit.cardcrawl.core.*;

public class GetAngerFromDiscardAction extends AbstractGameAction
{
    public static final String[] TEXT;
    private AbstractPlayer p;
    private int numberOfCards;
    private boolean optional;
    
    public GetAngerFromDiscardAction(final int numberOfCards, final boolean optional) {
        this.actionType = ActionType.CARD_MANIPULATION;
        final float action_DUR_FAST = Settings.ACTION_DUR_FAST;
        this.startDuration = action_DUR_FAST;
        this.duration = action_DUR_FAST;
        this.p = AbstractDungeon.player;
        this.numberOfCards = numberOfCards;
        this.optional = optional;
    }
    
    public GetAngerFromDiscardAction(final int numberOfCards) {
        this(numberOfCards, false);
    }
    
    public void update() {
        if (this.duration != this.startDuration) {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                for (final AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                    this.setAngerFromDiscard(c);
                }
                for (final AbstractCard c : this.p.discardPile.group) {
                    c.unhover();
                    c.target_x = (float)CardGroup.DISCARD_PILE_X;
                    c.target_y = 0.0f;
                }
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
            }
            this.tickDuration();
            return;
        }
        if (this.p.discardPile.isEmpty() || this.numberOfCards <= 0) {
            this.isDone = true;
            return;
        }
        if (this.p.discardPile.size() <= 1 && !this.optional) {
            final ArrayList<AbstractCard> cardsToMove = new ArrayList<AbstractCard>(this.p.discardPile.group);
            for (final AbstractCard c2 : cardsToMove) {
                this.setAngerFromDiscard(c2);
            }
            this.isDone = true;
            return;
        }
        if (this.p.discardPile.size() <= this.numberOfCards) {
            this.numberOfCards = this.p.discardPile.size();
        }
        if (this.numberOfCards == 1) {
            if (this.optional) {
                AbstractDungeon.gridSelectScreen.open(this.p.discardPile, this.numberOfCards, true, GetAngerFromDiscardAction.TEXT[0]);
            }
            else {
                AbstractDungeon.gridSelectScreen.open(this.p.discardPile, this.numberOfCards, GetAngerFromDiscardAction.TEXT[0], false);
            }
        }
        else if (this.optional) {
            AbstractDungeon.gridSelectScreen.open(this.p.discardPile, this.numberOfCards, true, GetAngerFromDiscardAction.TEXT[1] + this.numberOfCards + GetAngerFromDiscardAction.TEXT[2]);
        }
        else {
            AbstractDungeon.gridSelectScreen.open(this.p.discardPile, this.numberOfCards, GetAngerFromDiscardAction.TEXT[1] + this.numberOfCards + GetAngerFromDiscardAction.TEXT[2], false);
        }
        this.tickDuration();
    }
    
    private void setAngerFromDiscard(final AbstractCard c) {
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
        this.p.discardPile.moveToBottomOfDeck(c);
    }
    
    static {
        TEXT = CardCrawlGame.languagePack.getUIString(LvbuModHelper.MakePath("GetAngerFromDiscard")).TEXT;
    }
}
