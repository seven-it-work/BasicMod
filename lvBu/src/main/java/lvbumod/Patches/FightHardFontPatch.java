package lvbumod.Patches;

import com.megacrit.cardcrawl.helpers.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.files.*;
import com.badlogic.gdx.graphics.g2d.freetype.*;
import java.util.*;
import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.core.*;
import com.evacipated.cardcrawl.modthespire.lib.*;

public class FightHardFontPatch
{
    @SpirePatch(clz = FontHelper.class, method = "<class>")
    public static class FightHardFontField
    {
        public static StaticSpireField<BitmapFont> FightHardFont;
        
        static {
            FightHardFontField.FightHardFont = (StaticSpireField<BitmapFont>)new StaticSpireField(() -> null);
        }
    }
    
    @SpirePatch(clz = FontHelper.class, method = "initialize", paramtypez = {})
    public static class FontPatchLvbu
    {
        @SpireInsertPatch(rloc = 280)
        public static void Insert(final FileHandle ___fontFile, final FreeTypeFontGenerator.FreeTypeFontParameter ___param, final float ___fontScale, final HashMap<String, FreeTypeFontGenerator> ___generators) {
            ___param.shadowOffsetX = 2;
            ___param.shadowOffsetY = 2;
            ___param.shadowColor = new Color(0.0f, 0.0f, 0.0f, 0.33f);
            ___param.gamma = 2.0f;
            ___param.borderGamma = 2.0f;
            ___param.borderStraight = true;
            ___param.borderColor = new Color(0.32156864f, 0.31764707f, 0.32156864f, 0.8f);
            ___param.borderWidth = 3.0f * Settings.scale;
            ___param.shadowOffsetX = 1;
            ___param.shadowOffsetY = 1;
            final BitmapFont a = FontHelper.prepFont(39.0f, false);
            FightHardFontField.FightHardFont.set(a);
        }
    }
}
