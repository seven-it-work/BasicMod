package lvbumod.Actions;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.cards.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.ui.panels.*;
import java.util.*;
import lvbumod.Cards.Skill.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Powers.*;

public class GetAngerFromDrawAction extends AbstractGameAction
{
    public AbstractPlayer p;
    private int amount;
    private boolean atStart;
    
    public GetAngerFromDrawAction(final int Amount, final boolean atStart) {
        this.actionType = ActionType.SPECIAL;
        this.p = AbstractDungeon.player;
        this.amount = Amount;
        this.atStart = atStart;
    }
    
    public GetAngerFromDrawAction(final int Amount) {
        this(Amount, false);
    }
    
    public void update() {
        if (this.p.drawPile.group.size() == 0) {
            this.isDone = true;
            return;
        }
        boolean allAnger = true;
        for (final AbstractCard c : this.p.drawPile.group) {
            if (!LvbuModHelper.getAnger(c)) {
                allAnger = false;
                break;
            }
        }
        if (allAnger) {
            this.isDone = true;
            return;
        }
        int canGetAngerAmount = 0;
        for (final AbstractCard c2 : this.p.drawPile.group) {
            if (!LvbuModHelper.getAnger(c2)) {
                ++canGetAngerAmount;
            }
            if (this.atStart && (c2.inBottleLightning || c2.inBottleFlame || c2.inBottleTornado || c2.isInnate)) {
                --canGetAngerAmount;
            }
        }
        this.amount = Math.min(canGetAngerAmount, this.amount);
        if (this.amount <= 0) {
            this.isDone = true;
            return;
        }
        final ArrayList<UUID> uuidToMove = new ArrayList<UUID>();
        while (this.amount > 0) {
            int maxCost = -3;
            for (final AbstractCard c3 : this.p.drawPile.group) {
                if (!LvbuModHelper.getAnger(c3) && !uuidToMove.contains(c3.uuid) && (!this.atStart || (!c3.inBottleLightning && !c3.inBottleFlame && !c3.inBottleTornado && !c3.isInnate))) {
                    maxCost = Math.max((c3.costForTurn == -1) ? (this.atStart ? this.p.energy.energy : EnergyPanel.getCurrentEnergy()) : c3.costForTurn, maxCost);
                }
            }
            final ArrayList<AbstractCard> chosenList = new ArrayList<AbstractCard>();
            for (final AbstractCard c4 : this.p.drawPile.group) {
                if (!LvbuModHelper.getAnger(c4) && !uuidToMove.contains(c4.uuid) && (!this.atStart || (!c4.inBottleLightning && !c4.inBottleFlame && !c4.inBottleTornado && !c4.isInnate)) && maxCost == ((c4.costForTurn == -1) ? (this.atStart ? this.p.energy.energy : EnergyPanel.getCurrentEnergy()) : c4.costForTurn)) {
                    chosenList.add(c4);
                }
            }
            if (chosenList.size() < this.amount) {
                this.amount -= chosenList.size();
                for (final AbstractCard c4 : chosenList) {
                    uuidToMove.add(c4.uuid);
                }
            }
            else {
                for (int i = 0; i < this.amount; ++i) {
                    final AbstractCard c4 = chosenList.get(AbstractDungeon.cardRng.random(chosenList.size() - 1));
                    chosenList.remove(c4);
                    uuidToMove.add(c4.uuid);
                    --this.amount;
                }
            }
        }
        Collections.reverse(uuidToMove);
        for (final UUID uuid : uuidToMove) {
            for (final AbstractCard c4 : this.p.drawPile.group) {
                if (c4.uuid.equals(uuid)) {
                    this.setAngerFromDraw(c4);
                    break;
                }
            }
        }
        this.isDone = true;
    }
    
    private void setAngerFromDraw(final AbstractCard c) {
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
        this.p.drawPile.moveToBottomOfDeck(c);
    }
}
