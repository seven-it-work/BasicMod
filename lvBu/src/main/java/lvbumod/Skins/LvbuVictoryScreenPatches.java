package lvbumod.Skins;

import com.megacrit.cardcrawl.screens.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.relics.*;
import lvbumod.Helpers.*;
import lvbumod.Relics.*;
import lvbumod.Patches.*;
import com.evacipated.cardcrawl.modthespire.lib.*;

public class LvbuVictoryScreenPatches
{
    @SpirePatch(clz = VictoryScreen.class, method = "updateAscensionAndBetaArtProgress")
    public static class ReskinUnlockPatch
    {
        @SpirePrefixPatch
        public static void Prefix(final VictoryScreen _instance) {
            System.out.println("================\u4f60\u8fd8\u5728\u5417");
            if (!Settings.seedSet && !Settings.isTrial && AbstractDungeon.ascensionLevel == 20 && AbstractDungeon.floorNum >= 54 && (AbstractDungeon.player.relics.get(0).relicId.equals(LvbuModHelper.MakePath(ChiTu.class.getSimpleName())) || AbstractDungeon.player.relics.get(0).relicId.equals(LvbuModHelper.MakePath(WanLiChiTu.class.getSimpleName()))) && AbstractDungeon.player.relics.size() > 5) {
                System.out.println("================\u4f60\u8fd8\u5728\u5417");
                for (final LvbuSkinCharacter c : LvbuSelectScreenPatch.characters) {
                    c.checkUnlock();
                }
                LvbuSkinAll.saveSettings();
            }
            SpireReturn.Continue();
        }
    }
}
