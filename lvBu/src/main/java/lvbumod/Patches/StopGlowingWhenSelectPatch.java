package lvbumod.Patches;

import com.megacrit.cardcrawl.screens.select.*;
import com.megacrit.cardcrawl.cards.*;
import lvbumod.Helpers.*;
import com.evacipated.cardcrawl.modthespire.lib.*;

@SpirePatch(clz = GridCardSelectScreen.class, method = "hideCards", paramtypez = {})
public class StopGlowingWhenSelectPatch
{
    @SpireInsertPatch(rloc = 8, localvars = { "i" })
    public static void Insert(final GridCardSelectScreen __instance, final int i) {
        if (LvbuModHelper.getAnger(__instance.targetGroup.group.get(i))) {
            __instance.targetGroup.group.get(i).stopGlowing();
        }
    }
}
