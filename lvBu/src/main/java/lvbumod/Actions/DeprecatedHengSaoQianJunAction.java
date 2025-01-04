package lvbumod.Actions;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.monsters.*;
import lvbumod.Powers.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.helpers.*;
import java.util.*;

public class DeprecatedHengSaoQianJunAction extends AbstractGameAction
{
    public int[] damage;
    private AbstractPlayer p;
    private int increaseAmount;
    private UUID uuid;
    private boolean isFvk;
    
    public DeprecatedHengSaoQianJunAction(final AbstractPlayer source, final int[] amount, final DamageInfo.DamageType type, final AttackEffect effect, final int incAmount, final UUID targetUUID, final boolean isFvk) {
        this.setValues((AbstractCreature)null, (AbstractCreature)source, amount[0]);
        this.damage = amount;
        this.actionType = ActionType.DAMAGE;
        this.damageType = type;
        this.attackEffect = effect;
        this.p = source;
        this.increaseAmount = incAmount;
        this.uuid = targetUUID;
        this.isFvk = isFvk;
    }
    
    public void update() {
        this.isDone = true;
        for (final AbstractPower po : AbstractDungeon.player.powers) {
            po.onDamageAllEnemies(this.damage);
        }
        for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); ++i) {
            final AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            if (!target.isDying && target.currentHealth > 0 && !target.isEscaping) {
                target.damage(new DamageInfo(this.source, this.damage[i], this.damageType));
            }
        }
        if (this.p.hasPower(LvbuModHelper.MakePath(MatchlessPower.class.getSimpleName())) && this.isFvk) {
            for (final AbstractCard c : AbstractDungeon.player.masterDeck.group) {
                if (!c.uuid.equals(this.uuid)) {
                    continue;
                }
                final AbstractCard abstractCard = c;
                abstractCard.misc += this.increaseAmount;
                c.applyPowers();
                c.baseDamage = c.misc;
                c.isDamageModified = false;
            }
            for (final AbstractCard abstractCard2 : GetAllInBattleInstances.get(this.uuid)) {
                final AbstractCard c = abstractCard2;
                abstractCard2.misc += this.increaseAmount;
                c.applyPowers();
                c.baseDamage = c.misc;
            }
        }
        if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.clearPostCombatActions();
        }
    }
}
