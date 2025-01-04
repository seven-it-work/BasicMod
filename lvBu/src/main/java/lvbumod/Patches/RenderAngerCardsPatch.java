package lvbumod.Patches;

import com.megacrit.cardcrawl.ui.panels.*;
import java.lang.reflect.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.cards.*;
import lvbumod.Helpers.*;
import java.util.*;
import com.megacrit.cardcrawl.core.*;
import com.badlogic.gdx.graphics.*;
import javassist.*;
import com.megacrit.cardcrawl.helpers.*;
import com.evacipated.cardcrawl.modthespire.lib.*;

@SpirePatch(clz = DrawPilePanel.class, method = "render")
public class RenderAngerCardsPatch
{
    private static final float HB_W;
    private static final float HB_H;
    private static Field frameShadowColorField;
    
    @SpireInsertPatch(locator = Locator.class)
    public static void Insert(final DrawPilePanel __instance, final SpriteBatch sb) {
        if (AbstractDungeon.isScreenUp) {
            return;
        }
        AbstractCard hovered = null;
        int hoveredIndex = -1;
        int i = 0;
        for (final AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (LvbuModHelper.getAnger(c)) {
                ++i;
            }
        }
        for (final AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (LvbuModHelper.getAnger(c)) {
                --i;
                final AbstractCard ret = renderCard(__instance, sb, c, i, 0.45f, true);
                if (ret == null) {
                    continue;
                }
                hovered = ret;
                hoveredIndex = i;
            }
        }
        if (hovered != null) {
            renderCard(__instance, sb, hovered, hoveredIndex, 0.8f, false);
        }
    }
    
    private static boolean isMintySpireExists() {
        try {
            Class.forName("mintySpire.utility.StsLibChecker");
            return true;
        }
        catch (ClassNotFoundException e) {
            return false;
        }
    }
    
    private static AbstractCard renderCard(final DrawPilePanel __instance, final SpriteBatch sb, final AbstractCard card, final int i, final float scale, final boolean hitbox) {
        AbstractCard hovered = null;
        final float prev_current_x = card.current_x;
        final float prev_current_y = card.current_y;
        final float prev_drawScale = card.drawScale;
        final float prev_angle = card.angle;
        card.current_x = __instance.current_x + (hitbox ? 75 : 245) * Settings.scale;
        card.current_y = __instance.current_y + (220 + i * 27) * Settings.scale;
        if (isMintySpireExists() && (AbstractDungeon.player.hasRelic("Frozen Eye") || AbstractDungeon.isScreenUp)) {
            card.current_y += 310.0f;
        }
        card.drawScale = scale;
        card.angle = 0.0f;
        card.lighten(true);
        if (hitbox) {
            card.hb.move(card.current_x, card.current_y);
            card.hb.resize(RenderAngerCardsPatch.HB_W * card.drawScale, RenderAngerCardsPatch.HB_H * card.drawScale);
            card.hb.update();
            if (card.hb.hovered) {
                hovered = card;
            }
        }
        Color frameShadowColor = null;
        float prev_frameShadow_a = 0.0f;
        if (hitbox) {
            try {
                if (RenderAngerCardsPatch.frameShadowColorField == null) {
                    (RenderAngerCardsPatch.frameShadowColorField = AbstractCard.class.getDeclaredField("frameShadowColor")).setAccessible(true);
                }
                frameShadowColor = (Color)RenderAngerCardsPatch.frameShadowColorField.get(card);
                prev_frameShadow_a = frameShadowColor.a;
                frameShadowColor.a = 0.0f;
            }
            catch (IllegalAccessException | NoSuchFieldException ex2) {
                ex2.printStackTrace();
            }
        }
        card.render(sb);
        if (hitbox) {
            frameShadowColor.a = prev_frameShadow_a;
        }
        card.current_x = prev_current_x;
        card.current_y = prev_current_y;
        card.drawScale = prev_drawScale;
        card.angle = prev_angle;
        return hovered;
    }
    
    static {
        HB_W = 300.0f * Settings.scale;
        HB_H = 420.0f * Settings.scale;
        RenderAngerCardsPatch.frameShadowColorField = null;
    }
    
    public static class Locator extends SpireInsertLocator
    {
        public int[] Locate(final CtBehavior ctBehavior) throws Exception {
            final Matcher.MethodCallMatcher methodCallMatcher = new Matcher.MethodCallMatcher((Class)Hitbox.class, "render");
            return LineFinder.findInOrder(ctBehavior, (Matcher)methodCallMatcher);
        }
    }
}
