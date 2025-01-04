package lvbumod.Patches;

import com.megacrit.cardcrawl.monsters.beyond.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import java.util.*;
import com.evacipated.cardcrawl.modthespire.lib.*;

@SpirePatch(clz = AwakenedOne.class, method = "die", paramtypez = {})
public class AwakenedOneKillMinionPatch
{
    @SpirePrefixPatch
    public static void AwakenedOneKillMinionPatch(final AbstractMonster __instance) {
        if (!AbstractDungeon.getCurrRoom().cannotLose) {
            for (final AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!m.isDead && !m.isDying && m.currentHealth > 0 && !m.isDeadOrEscaped() && !m.id.equals("Cultist")) {
                    AbstractDungeon.actionManager.addToTop((AbstractGameAction)new HideHealthBarAction((AbstractCreature)m));
                    AbstractDungeon.actionManager.addToTop((AbstractGameAction)new SuicideAction(m));
                }
            }
        }
    }
}
