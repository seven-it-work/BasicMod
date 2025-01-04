package lvbumod.Patches;

import lvbumod.Panels.*;
import com.megacrit.cardcrawl.dungeons.*;
import lvbumod.Characters.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.megacrit.cardcrawl.rooms.*;
import com.evacipated.cardcrawl.modthespire.lib.*;

public class RenderFightHardUIPatch
{
    static FightHardPanel fightHardPanel;
    
    public static boolean hasLvbuExtend() {
        boolean hasEx = false;
        final AbstractPlayer p = AbstractDungeon.player;
        if (p.chosenClass.equals((Object)lvbu.Enums.LVBU) && LvbuModHelper.lvbuExtend()) {
            hasEx = true;
        }
        return hasEx;
    }
    
    static {
        RenderFightHardUIPatch.fightHardPanel = new FightHardPanel();
    }
    
    @SpirePatch(clz = OverlayMenu.class, method = "render", paramtypez = { SpriteBatch.class })
    public static class RenderPatch
    {
        @SpirePrefixPatch
        public static void Prefix(final OverlayMenu _OM, final SpriteBatch _sb) {
            final AbstractPlayer p = AbstractDungeon.player;
            if (RenderFightHardUIPatch.hasLvbuExtend()) {
                RenderFightHardUIPatch.fightHardPanel.render(_sb);
            }
        }
    }
    
    @SpirePatch(clz = OverlayMenu.class, method = "hideCombatPanels")
    public static class hideCombatPatch
    {
        @SpirePrefixPatch
        public static void Prefix() {
            final AbstractPlayer p = AbstractDungeon.player;
            if (RenderFightHardUIPatch.hasLvbuExtend()) {
                RenderFightHardUIPatch.fightHardPanel.hide();
            }
        }
    }
    
    @SpirePatch(clz = OverlayMenu.class, method = "showCombatPanels")
    public static class showCombatPatch
    {
        @SpirePrefixPatch
        public static void Prefix() {
            final AbstractPlayer p = AbstractDungeon.player;
            if (RenderFightHardUIPatch.hasLvbuExtend()) {
                RenderFightHardUIPatch.fightHardPanel.show();
            }
        }
    }
    
    @SpirePatch(clz = OverlayMenu.class, method = "update")
    public static class updatePatch
    {
        @SpirePrefixPatch
        public static void Prefix() {
            final AbstractPlayer p = AbstractDungeon.player;
            if (RenderFightHardUIPatch.hasLvbuExtend()) {
                RenderFightHardUIPatch.fightHardPanel.updatePositions();
            }
        }
    }
    
    @SpirePatch(clz = AbstractRoom.class, method = "update")
    public static class onBattleStartEffect
    {
        @SpireInsertPatch(rloc = 43)
        public static void Insert() {
            FightHardPanel.setAnima(0);
        }
    }
}
