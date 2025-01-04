package lvbumod.Actions;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.dungeons.*;
import lvbumod.Characters.*;
import com.megacrit.cardcrawl.cards.*;
import java.util.*;
import com.megacrit.cardcrawl.core.*;

public class GreenHatAction extends AbstractGameAction
{
    public static final String[] TEXT;
    private AbstractPlayer player;
    private int numberOfCards;
    private boolean optional;
    private boolean upgraded;
    
    public GreenHatAction(final boolean upgraded) {
        this.actionType = ActionType.CARD_MANIPULATION;
        final float action_DUR_FAST = Settings.ACTION_DUR_FAST;
        this.startDuration = action_DUR_FAST;
        this.duration = action_DUR_FAST;
        this.player = AbstractDungeon.player;
        this.numberOfCards = 1;
        this.optional = false;
        this.upgraded = upgraded;
    }
    
    public void update() {
        final ArrayList<AbstractCard> betrayCardsDrawPile = new ArrayList<AbstractCard>();
        for (final AbstractCard c : this.player.drawPile.group) {
            if (c.hasTag(lvbu.Enums.BETRAY_CARD)) {
                betrayCardsDrawPile.add(c);
            }
        }
        final ArrayList<AbstractCard> betrayCardsDiscardPile = new ArrayList<AbstractCard>();
        if (this.upgraded) {
            for (final AbstractCard c2 : this.player.discardPile.group) {
                if (c2.hasTag(lvbu.Enums.BETRAY_CARD)) {
                    betrayCardsDiscardPile.add(c2);
                }
            }
        }
        if (this.duration != this.startDuration) {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                for (final AbstractCard c2 : AbstractDungeon.gridSelectScreen.selectedCards) {
                    if (betrayCardsDrawPile.contains(c2)) {
                        if (this.player.hand.size() == 10) {
                            this.player.drawPile.moveToDiscardPile(c2);
                            this.player.createHandIsFullDialog();
                        }
                        else {
                            this.player.drawPile.moveToHand(c2, this.player.drawPile);
                        }
                    }
                    else if (this.player.hand.size() == 10) {
                        this.player.createHandIsFullDialog();
                    }
                    else {
                        this.player.discardPile.moveToHand(c2, this.player.discardPile);
                    }
                }
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                AbstractDungeon.player.hand.refreshHandLayout();
            }
            this.tickDuration();
            return;
        }
        if ((betrayCardsDrawPile.isEmpty() && betrayCardsDiscardPile.isEmpty()) || this.numberOfCards <= 0) {
            this.isDone = true;
            return;
        }
        if (betrayCardsDrawPile.size() + betrayCardsDiscardPile.size() <= this.numberOfCards && !this.optional) {
            final ArrayList<AbstractCard> cardsToMoveFromDrawPile = new ArrayList<AbstractCard>(betrayCardsDrawPile);
            final ArrayList<AbstractCard> cardsToMoveFromDiscardPile = new ArrayList<AbstractCard>(betrayCardsDiscardPile);
            for (final AbstractCard c3 : cardsToMoveFromDrawPile) {
                if (this.player.hand.size() == 10) {
                    this.player.drawPile.moveToDiscardPile(c3);
                    this.player.createHandIsFullDialog();
                }
                else {
                    this.player.drawPile.moveToHand(c3, this.player.drawPile);
                }
            }
            for (final AbstractCard c3 : cardsToMoveFromDiscardPile) {
                if (this.player.hand.size() == 10) {
                    this.player.createHandIsFullDialog();
                }
                else {
                    this.player.discardPile.moveToHand(c3, this.player.discardPile);
                }
            }
            this.isDone = true;
            return;
        }
        final CardGroup temp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (final AbstractCard c4 : betrayCardsDrawPile) {
            temp.addToTop(c4);
        }
        for (final AbstractCard c4 : betrayCardsDiscardPile) {
            temp.addToTop(c4);
        }
        temp.sortAlphabetically(true);
        temp.sortByRarityPlusStatusCardType(false);
        if (this.numberOfCards == 1) {
            if (this.optional) {
                AbstractDungeon.gridSelectScreen.open(temp, this.numberOfCards, true, GreenHatAction.TEXT[0]);
            }
            else {
                AbstractDungeon.gridSelectScreen.open(temp, this.numberOfCards, GreenHatAction.TEXT[0], false);
            }
        }
        else if (this.optional) {
            AbstractDungeon.gridSelectScreen.open(temp, this.numberOfCards, true, GreenHatAction.TEXT[1] + this.numberOfCards + GreenHatAction.TEXT[2]);
        }
        else {
            AbstractDungeon.gridSelectScreen.open(temp, this.numberOfCards, GreenHatAction.TEXT[1] + this.numberOfCards + GreenHatAction.TEXT[2], false);
        }
        this.tickDuration();
    }
    
    static {
        TEXT = CardCrawlGame.languagePack.getUIString("BetterToHandAction").TEXT;
    }
}
