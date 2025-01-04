package lvbumod.Actions;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.vfx.combat.*;
import com.megacrit.cardcrawl.monsters.*;
import lvbumod.Relics.*;
import lvbumod.Helpers.*;

public class BetrayKillAction extends AbstractGameAction
{
    private DamageInfo info;
    private boolean canHeal;
    
    public BetrayKillAction(final AbstractCreature m, final DamageInfo info, final boolean canHeal) {
        this.setValues(this.target = m, this.info = info);
        this.actionType = ActionType.DAMAGE;
        this.duration = 0.1f;
        this.canHeal = canHeal;
    }
    
    public void update() {
        if (this.duration == 0.1f && this.target != null) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.BLUNT_HEAVY));
            this.target.damage(this.info);
            if ((((AbstractMonster)this.target).isDying || this.target.currentHealth <= 0) && !this.target.halfDead && !this.target.hasPower("Minion") && AbstractDungeon.player.hasRelic(LvbuModHelper.MakePath(DianTianDeng.class.getSimpleName())) && this.canHeal) {
                AbstractDungeon.player.heal(DianTianDeng.DianTianDengHeal);
            }
            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }
        this.tickDuration();
    }
}
