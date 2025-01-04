package lvbumod.Patches;

import com.megacrit.cardcrawl.cards.*;
import lvbumod.Helpers.*;
import com.evacipated.cardcrawl.modthespire.lib.*;

@SpirePatch(clz = AbstractCard.class, method = "makeStatEquivalentCopy", paramtypez = {})
public class CardCopyRetainAngerPatch
{
    @SpirePostfixPatch
    public static AbstractCard CardCopyRetainAngerPatch(final AbstractCard card, final AbstractCard __instance) {
        LvbuModHelper.setAnger(card, LvbuModHelper.getAnger(__instance));
        return card;
    }
}
