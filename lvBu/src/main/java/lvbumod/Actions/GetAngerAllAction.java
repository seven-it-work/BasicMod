package lvbumod.Actions;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.cards.*;
import lvbumod.Helpers.*;
import java.util.*;
import lvbumod.Cards.Skill.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Powers.*;

public class GetAngerAllAction extends AbstractGameAction
{
    public AbstractPlayer p;
    public boolean isPotion;
    
    public GetAngerAllAction(final AbstractPlayer p, final boolean isPotion) {
        this.actionType = ActionType.SPECIAL;
        this.p = p;
        this.isPotion = isPotion;
    }
    
    public void update() {
        if (this.isPotion) {
            final ArrayList<AbstractCard> cardsToMoveFromDiscardPile = new ArrayList<AbstractCard>(this.p.discardPile.group);
            for (final AbstractCard c : cardsToMoveFromDiscardPile) {
                this.p.discardPile.moveToBottomOfDeck(c);
                this.setAngerFromChenGong(c);
            }
        }
        if (!this.isPotion) {
            for (final AbstractCard c2 : this.p.drawPile.group) {
                if (!LvbuModHelper.getAnger(c2)) {
                    this.setAngerFromChenGong(c2);
                }
            }
        }
        this.isDone = true;
    }
    
    private void setAngerFromChenGong(final AbstractCard c) {
        if (c.cardID.equals(LvbuModHelper.MakePath(CaiJi.class.getSimpleName()))) {
            this.addToTop((AbstractGameAction)new DrawCardAction(c.magicNumber));
        }
        if (this.p.hasPower(LvbuModHelper.MakePath(ZhouXuanPower.class.getSimpleName()))) {
            this.addToTop((AbstractGameAction)new GainBlockAction((AbstractCreature)this.p, this.p.getPower(LvbuModHelper.MakePath(ZhouXuanPower.class.getSimpleName())).amount, Settings.FAST_MODE));
            if (this.p.hasPower(LvbuModHelper.MakePath(MatchlessPower.class.getSimpleName()))) {
                this.addToTop((AbstractGameAction)new GainBlockAction((AbstractCreature)this.p, this.p.getPower(LvbuModHelper.MakePath(ZhouXuanPower.class.getSimpleName())).amount, Settings.FAST_MODE));
            }
        }
        LvbuModHelper.setAnger(c, true);
    }
}
