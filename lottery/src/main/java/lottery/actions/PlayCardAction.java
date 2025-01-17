package lottery.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class PlayCardAction extends AbstractGameAction {
    private boolean exhaustCards;
    private AbstractCard card;

    public PlayCardAction(AbstractCard card, final AbstractCreature target, final boolean exhausts) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.WAIT;
        this.source = AbstractDungeon.player;
        this.target = target;
        this.exhaustCards = exhausts;
        this.card = card;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            AbstractDungeon.player.drawPile.group.remove(card);
            AbstractDungeon.getCurrRoom().souls.remove(card);
            card.exhaustOnUseOnce = this.exhaustCards;
            AbstractDungeon.player.limbo.group.add(card);
            card.current_y = -200.0f * Settings.scale;
            card.target_x = Settings.WIDTH / 2.0f + 200.0f * Settings.xScale;
            card.target_y = Settings.HEIGHT / 2.0f;
            card.targetAngle = 0.0f;
            card.lighten(false);
            card.drawScale = 0.12f;
            card.targetDrawScale = 0.75f;
            card.applyPowers();
            this.addToTop(new NewQueueCardAction(card, this.target, false, true));
            this.addToTop(new UnlimboAction(card));
            if (!Settings.FAST_MODE) {
                this.addToTop(new WaitAction(Settings.ACTION_DUR_MED));
            } else {
                this.addToTop(new WaitAction(Settings.ACTION_DUR_FASTER));
            }
            this.isDone = true;
        }
    }
}
