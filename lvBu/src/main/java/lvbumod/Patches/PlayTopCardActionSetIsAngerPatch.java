package lvbumod.Patches;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.*;
import lvbumod.Helpers.*;
import com.evacipated.cardcrawl.modthespire.lib.*;

@SpirePatch(clz = PlayTopCardAction.class, method = "update", paramtypez = {})
public class PlayTopCardActionSetIsAngerPatch
{
    @SpireInsertPatch(rloc = 15, localvars = { "card" })
    public static void PlayTopCardActionSetIsAngerPatch(final PlayTopCardAction __instance, final AbstractCard card) {
        LvbuModHelper.setAnger(card, false);
    }
}
