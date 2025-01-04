package lvbumod.Patches;

import com.megacrit.cardcrawl.actions.defect.*;
import com.megacrit.cardcrawl.cards.*;
import lvbumod.Helpers.*;
import com.evacipated.cardcrawl.modthespire.lib.*;

@SpirePatch(clz = ShuffleAllAction.class, method = "update", paramtypez = {})
public class ShuffleSetIsAngerPatch2
{
    @SpireInsertPatch(rloc = 12, localvars = { "e" })
    public static void ShuffleSetIsAngerPatch2Insert(final ShuffleAllAction __instance, final AbstractCard e) {
        LvbuModHelper.setAnger(e, false);
    }
}
