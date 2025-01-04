package lvbumod.Actions;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.actions.common.*;

public class ShaMieJiAction extends AbstractGameAction
{
    private int cutoff;
    
    public ShaMieJiAction(final AbstractCreature target, final int cutoff) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.source = null;
        this.target = target;
        this.cutoff = cutoff;
    }
    
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST && this.target.maxHealth < this.cutoff && this.target instanceof AbstractMonster) {
            this.addToTop((AbstractGameAction)new InstantKillAction(this.target));
        }
        this.isDone = true;
    }
}
