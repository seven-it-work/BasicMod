package lvbumod.Patches;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.*;
import lvbumod.Helpers.*;
import com.evacipated.cardcrawl.modthespire.lib.*;

@SpirePatch(clz = EmptyDeckShuffleAction.class, method = "update", paramtypez = {})
public class ShuffleSetIsAngerPatch
{
    @SpireInsertPatch(rloc = 9, localvars = { "e" })
    public static void ShuffleSetIsAngerPatchInsert(final EmptyDeckShuffleAction __instance, final AbstractCard e) {
        LvbuModHelper.setAnger(e, false);
    }
}
