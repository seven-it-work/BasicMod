package lvbumod.Actions;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.actions.common.*;

public class LangZiYeXinAction extends AbstractGameAction
{
    public AbstractPlayer p;
    public int DrawAmount;
    public int AngerAmount;
    
    public LangZiYeXinAction(final AbstractPlayer p, final int drawAmount, final int angerAmount) {
        this.actionType = ActionType.DRAW;
        this.p = p;
        this.DrawAmount = drawAmount;
        this.AngerAmount = angerAmount;
    }
    
    public void update() {
        final int overflow = this.p.hand.size() + this.DrawAmount - 10;
        if (overflow > 0) {
            this.addToTop((AbstractGameAction)new GetAngerFromHandAction(overflow * this.AngerAmount, false));
        }
        this.addToTop((AbstractGameAction)new DrawCardAction(this.DrawAmount));
        this.isDone = true;
    }
}
