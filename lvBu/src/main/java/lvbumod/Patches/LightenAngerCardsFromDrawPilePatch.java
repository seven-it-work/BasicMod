package lvbumod.Patches;

import com.megacrit.cardcrawl.screens.*;
import com.megacrit.cardcrawl.cards.*;
import lvbumod.Helpers.*;
import com.evacipated.cardcrawl.modthespire.lib.*;

@SpirePatch(clz = DrawPileViewScreen.class, method = "hideCards", paramtypez = {})
public class LightenAngerCardsFromDrawPilePatch
{
    @SpireInsertPatch(rloc = 12, localvars = { "i" })
    public static void Insert(final DrawPileViewScreen __instance, final CardGroup ___drawPileCopy, final int i) {
        if (LvbuModHelper.getAnger(___drawPileCopy.group.get(i))) {}
    }
}
