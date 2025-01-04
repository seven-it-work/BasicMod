package lvbumod.Actions;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.ui.panels.*;
import lvbumod.Powers.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.powers.*;

public class XiYanZhouAction extends AbstractGameAction
{
    public int[] multiDamage;
    private boolean freeToPlayOnce;
    private AbstractPlayer p;
    private int energyOnUse;
    
    public XiYanZhouAction(final AbstractPlayer p, final int amount, final boolean freeToPlayOnce, final int energyOnUse) {
        this.freeToPlayOnce = false;
        this.energyOnUse = -1;
        this.amount = amount;
        this.p = p;
        this.freeToPlayOnce = freeToPlayOnce;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.SPECIAL;
        this.energyOnUse = energyOnUse;
    }
    
    public void update() {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1) {
            effect = this.energyOnUse;
        }
        if (this.p.hasRelic("Chemical X")) {
            effect += 2;
            this.p.getRelic("Chemical X").flash();
        }
        if (effect > 0) {
            for (int i = 0; i < effect; ++i) {
                this.addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this.p, (AbstractCreature)this.p, (AbstractPower)new FightHardPower((AbstractCreature)this.p, this.amount), this.amount));
            }
            if (!this.freeToPlayOnce) {
                this.p.energy.use(EnergyPanel.totalCount);
            }
        }
        this.isDone = true;
    }
}
