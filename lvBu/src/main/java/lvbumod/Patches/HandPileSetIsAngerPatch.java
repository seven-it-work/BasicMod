package lvbumod.Patches;

import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import lvbumod.Helpers.*;
import com.evacipated.cardcrawl.modthespire.lib.*;

@SpirePatch(clz = AbstractCard.class, method = "canUse", paramtypez = { AbstractPlayer.class, AbstractMonster.class })
public class HandPileSetIsAngerPatch
{
    @SpirePrefixPatch
    public static void HandPileSetIsAngerPatch(final AbstractCard __instance) {
        LvbuModHelper.setAnger(__instance, false);
    }
}
