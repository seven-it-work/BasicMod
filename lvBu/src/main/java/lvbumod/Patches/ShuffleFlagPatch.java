package lvbumod.Patches;

import com.megacrit.cardcrawl.dungeons.*;
import lvbumod.Characters.*;
import lvbumod.Helpers.*;
import lvbumod.Powers.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.defect.*;

public class ShuffleFlagPatch
{
    @SpirePatch(clz = EmptyDeckShuffleAction.class, method = "<ctor>")
    public static class ShufflePatchOne
    {
        public static void Postfix(final EmptyDeckShuffleAction __instance) {
            if (AbstractDungeon.player != null && AbstractDungeon.player.chosenClass.equals((Object)lvbu.Enums.LVBU)) {
                lvbu.ShuffleFlag = true;
                if (AbstractDungeon.player.hasPower(LvbuModHelper.MakePath(ShenWeiPower.class.getSimpleName()))) {
                    AbstractDungeon.player.getPower(LvbuModHelper.MakePath(ShenWeiPower.class.getSimpleName())).updateDescription();
                }
                if (AbstractDungeon.player.hasPower(LvbuModHelper.MakePath(WangYunPower.class.getSimpleName()))) {
                    AbstractDungeon.player.getPower(LvbuModHelper.MakePath(WangYunPower.class.getSimpleName())).onSpecificTrigger();
                }
            }
        }
    }
    
    @SpirePatch(clz = ShuffleAction.class, method = "update")
    public static class ShufflePatchTwo
    {
        @SpirePostfixPatch
        public static void Postfix(final ShuffleAction __instance) {
            if (AbstractDungeon.player != null && AbstractDungeon.player.chosenClass.equals((Object)lvbu.Enums.LVBU)) {
                lvbu.ShuffleFlag = true;
                if (AbstractDungeon.player.hasPower(LvbuModHelper.MakePath(ShenWeiPower.class.getSimpleName()))) {
                    AbstractDungeon.player.getPower(LvbuModHelper.MakePath(ShenWeiPower.class.getSimpleName())).updateDescription();
                }
                if (AbstractDungeon.player.hasPower(LvbuModHelper.MakePath(WangYunPower.class.getSimpleName()))) {
                    AbstractDungeon.player.getPower(LvbuModHelper.MakePath(WangYunPower.class.getSimpleName())).onSpecificTrigger();
                }
            }
        }
    }
    
    @SpirePatch(clz = ShuffleAllAction.class, method = "<ctor>")
    public static class ShufflePatchThree
    {
        public static void Postfix(final ShuffleAllAction __instance) {
            if (AbstractDungeon.player != null && AbstractDungeon.player.chosenClass.equals((Object)lvbu.Enums.LVBU)) {
                lvbu.ShuffleFlag = true;
                if (AbstractDungeon.player.hasPower(LvbuModHelper.MakePath(ShenWeiPower.class.getSimpleName()))) {
                    AbstractDungeon.player.getPower(LvbuModHelper.MakePath(ShenWeiPower.class.getSimpleName())).updateDescription();
                }
                if (AbstractDungeon.player.hasPower(LvbuModHelper.MakePath(WangYunPower.class.getSimpleName()))) {
                    AbstractDungeon.player.getPower(LvbuModHelper.MakePath(WangYunPower.class.getSimpleName())).onSpecificTrigger();
                }
            }
        }
    }
}
