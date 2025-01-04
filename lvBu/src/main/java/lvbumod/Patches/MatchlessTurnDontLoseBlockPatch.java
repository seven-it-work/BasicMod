package lvbumod.Patches;

import com.megacrit.cardcrawl.dungeons.*;
import lvbumod.Powers.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.evacipated.cardcrawl.modthespire.lib.*;

@SpirePatch(clz = GameActionManager.class, method = "getNextAction", paramtypez = {})
public class MatchlessTurnDontLoseBlockPatch
{
    @SpireInsertPatch(rloc = 251)
    public static SpireReturn<Void> MatchlessTurnDontLoseBlockPatchInsert(final GameActionManager __instance) {
        if (AbstractDungeon.player.hasPower(LvbuModHelper.MakePath(MatchlessPower.class.getSimpleName()))) {
            if (!AbstractDungeon.getCurrRoom().isBattleOver) {
                __instance.addToBottom((AbstractGameAction)new DrawCardAction((AbstractCreature)null, AbstractDungeon.player.gameHandSize, true));
                AbstractDungeon.player.applyStartOfTurnPostDrawRelics();
                AbstractDungeon.player.applyStartOfTurnPostDrawPowers();
                __instance.addToBottom((AbstractGameAction)new EnableEndTurnButtonAction());
            }
            return SpireReturn.Return();
        }
        return SpireReturn.Continue();
    }
    
    @SpireInsertPatch(rloc = 253)
    public static SpireReturn<Void> MatchlessTurnDontLoseBlockPatchInsert2(final GameActionManager __instance) {
        if (AbstractDungeon.player.hasPower(LvbuModHelper.MakePath(MatchlessPower.class.getSimpleName()))) {
            if (!AbstractDungeon.getCurrRoom().isBattleOver) {
                __instance.addToBottom((AbstractGameAction)new DrawCardAction((AbstractCreature)null, AbstractDungeon.player.gameHandSize, true));
                AbstractDungeon.player.applyStartOfTurnPostDrawRelics();
                AbstractDungeon.player.applyStartOfTurnPostDrawPowers();
                __instance.addToBottom((AbstractGameAction)new EnableEndTurnButtonAction());
            }
            return SpireReturn.Return();
        }
        return SpireReturn.Continue();
    }
}
