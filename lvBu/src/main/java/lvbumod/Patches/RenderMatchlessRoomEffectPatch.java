package lvbumod.Patches;

import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.megacrit.cardcrawl.rooms.*;
import lvbumod.Powers.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;
import javassist.*;
import com.evacipated.cardcrawl.modthespire.lib.*;
import java.util.*;

public class RenderMatchlessRoomEffectPatch
{
    private static ShaderProgram silverShader;
    
    static {
        RenderMatchlessRoomEffectPatch.silverShader = new ShaderProgram(Gdx.files.internal("lvbuModResources/shader/gray/vertexShader.vsh"), Gdx.files.internal("lvbuModResources/shader/gray/fragShader.fsh"));
    }
    
    @SpirePatch(clz = AbstractDungeon.class, method = "render")
    public static class PatchRender
    {
        public static void Prefix(final AbstractDungeon dungeon, final SpriteBatch sb) {
            if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && AbstractDungeon.player.hasPower(MatchlessPower.POWER_ID) && LvbuModHelper.lvbuExtend()) {
                sb.setShader(RenderMatchlessRoomEffectPatch.silverShader);
                CardCrawlGame.psb.setShader(RenderMatchlessRoomEffectPatch.silverShader);
            }
        }
        
        @SpireInsertPatch(locator = Locator.class)
        public static void Insert(final AbstractDungeon dungeon, final SpriteBatch sb) {
            if (LvbuModHelper.lvbuExtend()) {
                sb.setShader((ShaderProgram)null);
                CardCrawlGame.psb.setShader((ShaderProgram)null);
            }
        }
        
        private static class Locator extends SpireInsertLocator
        {
            public int[] Locate(final CtBehavior ctBehavior) throws Exception {
                final Matcher.MethodCallMatcher methodCallMatcher = new Matcher.MethodCallMatcher((Class)AbstractRoom.class, "render");
                return LineFinder.findInOrder(ctBehavior, (List)new ArrayList(), (Matcher)methodCallMatcher);
            }
        }
    }
}
