package lvbumod.Actions;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Helpers.*;
import lvbumod.Cards.Skill.*;
import com.megacrit.cardcrawl.actions.common.*;
import lvbumod.Powers.*;
import java.util.*;

public class KunShouZhiDouAction extends AbstractGameAction
{
    private int blockPerCard;
    
    public KunShouZhiDouAction(final int blockAmount) {
        this.blockPerCard = blockAmount;
        this.setValues((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player);
        this.actionType = ActionType.BLOCK;
    }
    
    public void update() {
        final ArrayList<AbstractCard> cardsToAnger = new ArrayList<AbstractCard>();
        for (final AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.type != AbstractCard.CardType.ATTACK) {
                cardsToAnger.add(c);
            }
        }
        for (final AbstractCard c : cardsToAnger) {
            this.addToTop((AbstractGameAction)new GainBlockAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, this.blockPerCard, Settings.FAST_MODE));
        }
        for (final AbstractCard c : cardsToAnger) {
            this.addToTop((AbstractGameAction)new AbstractGameAction() {
                public void update() {
                    LvbuModHelper.setAnger(c, true);
                    if (c.cardID.equals(LvbuModHelper.MakePath(CaiJi.class.getSimpleName()))) {
                        this.addToTop((AbstractGameAction)new DrawCardAction(c.magicNumber));
                    }
                    if (AbstractDungeon.player.hasPower(LvbuModHelper.MakePath(ZhouXuanPower.class.getSimpleName()))) {
                        this.addToTop((AbstractGameAction)new GainBlockAction((AbstractCreature)AbstractDungeon.player, AbstractDungeon.player.getPower(LvbuModHelper.MakePath(ZhouXuanPower.class.getSimpleName())).amount, Settings.FAST_MODE));
                        if (AbstractDungeon.player.hasPower(LvbuModHelper.MakePath(MatchlessPower.class.getSimpleName()))) {
                            this.addToTop((AbstractGameAction)new GainBlockAction((AbstractCreature)AbstractDungeon.player, AbstractDungeon.player.getPower(LvbuModHelper.MakePath(ZhouXuanPower.class.getSimpleName())).amount, Settings.FAST_MODE));
                        }
                    }
                    AbstractDungeon.player.hand.moveToBottomOfDeck(c);
                    this.isDone = true;
                }
            });
        }
        this.isDone = true;
    }
}
