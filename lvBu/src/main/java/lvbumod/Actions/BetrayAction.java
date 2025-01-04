package lvbumod.Actions;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.rooms.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;
import lvbumod.Powers.*;
import java.util.*;

public class BetrayAction extends AbstractGameAction
{
    public AbstractPlayer p;
    public AbstractMonster m;
    private DamageInfo info;
    
    public BetrayAction(final AbstractPlayer p, final AbstractMonster m, final DamageInfo info) {
        this.p = p;
        this.m = m;
        this.setValues(this.target = (AbstractCreature)m, this.info = info);
        this.actionType = ActionType.REDUCE_POWER;
    }
    
    public void update() {
        int dadWithoutMinionCount = 0;
        int monsterWithoutMinionCount = 0;
        boolean canHeal = false;
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            for (final AbstractMonster m2 : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!m2.isDeadOrEscaped() && !m2.hasPower("Minion") && this.m.hasPower(LvbuModHelper.MakePath(DadPower.class.getSimpleName()))) {
                    ++dadWithoutMinionCount;
                }
                if (!m2.isDeadOrEscaped() && !m2.hasPower("Minion")) {
                    ++monsterWithoutMinionCount;
                }
            }
        }
        if (dadWithoutMinionCount == 1 && monsterWithoutMinionCount == 1) {
            canHeal = true;
        }
        this.addToTop((AbstractGameAction)new BetrayKillAction(this.target, this.info, canHeal));
        if (this.m.hasPower(LvbuModHelper.MakePath(DadPower.class.getSimpleName()))) {
            this.addToTop((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)this.m, (AbstractCreature)this.p, LvbuModHelper.MakePath(DadPower.class.getSimpleName())));
            lvbu.HasBetray = true;
        }
        if (this.m.hasPower(LvbuModHelper.MakePath(HatePower.class.getSimpleName()))) {
            this.addToTop((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)this.m, (AbstractCreature)this.p, LvbuModHelper.MakePath(HatePower.class.getSimpleName())));
        }
        this.isDone = true;
    }
}
