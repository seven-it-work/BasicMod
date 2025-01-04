package lvbumod.Actions;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.ui.panels.*;
import com.megacrit.cardcrawl.vfx.combat.*;
import com.megacrit.cardcrawl.actions.animations.*;
import com.megacrit.cardcrawl.vfx.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Powers.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.powers.*;

public class LieDiJiAction extends AbstractGameAction
{
    private boolean freeToPlayOnce;
    private int damage;
    private AbstractPlayer p;
    private AbstractMonster m;
    private DamageInfo.DamageType damageTypeForTurn;
    private int energyOnUse;
    private int extraDamageAmount;
    private int targetI;
    
    public LieDiJiAction(final AbstractPlayer p, final int damage, final DamageInfo.DamageType damageTypeForTurn, final boolean freeToPlayOnce, final int energyOnUse, final int targetI) {
        this.p = p;
        this.damage = damage;
        this.freeToPlayOnce = freeToPlayOnce;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.SPECIAL;
        this.damageTypeForTurn = damageTypeForTurn;
        this.energyOnUse = energyOnUse;
        this.targetI = targetI;
    }
    
    public void update() {
        if (this.targetI == -1) {
            this.m = null;
        }
        else {
            this.m = AbstractDungeon.getCurrRoom().monsters.monsters.get(this.targetI);
        }
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1) {
            effect = this.energyOnUse;
        }
        if (this.p.hasRelic("Chemical X")) {
            effect += 2;
            this.p.getRelic("Chemical X").flash();
        }
        if (effect > 1) {
            if (this.m != null) {
                for (int i = effect; i > 1; i -= 2) {
                    this.addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new WeightyImpactEffect(this.m.hb.cX, this.m.hb.cY)));
                    this.addToBot((AbstractGameAction)new WaitAction(1.5f));
                    this.addToBot((AbstractGameAction)new DamageAction((AbstractCreature)this.m, new DamageInfo((AbstractCreature)this.p, this.damage + this.extraDamageAmount, this.damageTypeForTurn), AttackEffect.NONE));
                    if (AbstractDungeon.player.hasPower(LvbuModHelper.MakePath(FangTianHuaJiPower.class.getSimpleName()))) {
                        this.addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this.m, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new VulnerablePower((AbstractCreature)this.m, AbstractDungeon.player.getPower(LvbuModHelper.MakePath(FangTianHuaJiPower.class.getSimpleName())).amount, false), AbstractDungeon.player.getPower(LvbuModHelper.MakePath(FangTianHuaJiPower.class.getSimpleName())).amount));
                    }
                }
            }
            if (!this.freeToPlayOnce) {
                this.p.energy.use(EnergyPanel.totalCount);
            }
        }
        else if (effect > 0 && !this.freeToPlayOnce) {
            this.p.energy.use(EnergyPanel.totalCount);
        }
        this.isDone = true;
    }
}
