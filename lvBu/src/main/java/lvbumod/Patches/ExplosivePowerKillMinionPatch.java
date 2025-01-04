package lvbumod.Patches;

import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import java.util.*;
import com.evacipated.cardcrawl.modthespire.lib.*;

@SpirePatch(clz = ExplosivePower.class, method = "duringTurn", paramtypez = {})
public class ExplosivePowerKillMinionPatch
{
    @SpirePrefixPatch
    public static void ExplosivePowerKillMinionPatch(final ExplosivePower __instance) {
        if (__instance.amount == 1 && !__instance.owner.isDying) {
            boolean allMinion = true;
            for (final AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if ((!m.isDeadOrEscaped() && m.currentHealth > 0 && !m.hasPower("Minion") && !m.equals(__instance.owner)) || m.id.equals("AwakenedOne")) {
                    allMinion = false;
                    break;
                }
            }
            if (allMinion) {
                for (final AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                    if (!m.isDead && !m.isDying && m.currentHealth > 0 && !m.isDeadOrEscaped() && !m.id.equals("AwakenedOne")) {
                        AbstractDungeon.actionManager.addToTop((AbstractGameAction)new HideHealthBarAction((AbstractCreature)m));
                        AbstractDungeon.actionManager.addToTop((AbstractGameAction)new SuicideAction(m));
                    }
                }
            }
        }
    }
}
