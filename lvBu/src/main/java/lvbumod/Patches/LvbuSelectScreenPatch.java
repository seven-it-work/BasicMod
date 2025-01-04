package lvbumod.Patches;

import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.vfx.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Skins.*;
import com.megacrit.cardcrawl.screens.charSelect.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.megacrit.cardcrawl.screens.*;
import javassist.*;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.helpers.*;
import java.util.*;
import com.megacrit.cardcrawl.helpers.input.*;
import com.megacrit.cardcrawl.helpers.controller.*;

public class LvbuSelectScreenPatch
{
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    public static Hitbox reskinRight;
    public static Hitbox reskinLeft;
    public static Hitbox portraitAnimationLeft;
    public static Hitbox portraitAnimationRight;
    private static float reskin_Text_W;
    private static float reskin_W;
    private static float reskinX_center;
    public static float allTextInfoX;
    public static float allTextInfoY;
    private static boolean bgIMGUpdate;
    public static ArrayList<AbstractGameEffect> char_effectsQueue;
    public static ArrayList<AbstractGameEffect> char_effectsQueue_toRemove;
    public static LvbuSkinCharacter[] characters;
    
    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(LvbuModHelper.MakePath("ReSkin"));
        TEXT = LvbuSelectScreenPatch.uiStrings.TEXT;
        LvbuSelectScreenPatch.reskin_Text_W = FontHelper.getSmartWidth(FontHelper.cardTitleFont, LvbuSelectScreenPatch.TEXT[1], 9999.0f, 0.0f);
        LvbuSelectScreenPatch.reskin_W = LvbuSelectScreenPatch.reskin_Text_W + 200.0f * Settings.scale;
        LvbuSelectScreenPatch.reskinX_center = 600.0f * Settings.scale;
        LvbuSelectScreenPatch.allTextInfoX = 0.0f;
        LvbuSelectScreenPatch.allTextInfoY = 0.0f;
        LvbuSelectScreenPatch.bgIMGUpdate = false;
        LvbuSelectScreenPatch.char_effectsQueue = new ArrayList<AbstractGameEffect>();
        LvbuSelectScreenPatch.char_effectsQueue_toRemove = new ArrayList<AbstractGameEffect>();
        LvbuSelectScreenPatch.characters = new LvbuSkinCharacter[] { new LvbuSkinCharacter("\u5415\u5e03", new AbstractLvbuSkin[] { new LvbuSkinOriginal(), new LvbuSkinGold() }) };
    }
    
    @SpirePatch(clz = CharacterSelectScreen.class, method = "initialize")
    public static class CharacterSelectScreenPatch_Initialize
    {
        @SpirePostfixPatch
        public static void Postfix(final CharacterSelectScreen __instance) {
            LvbuSkinAll.loadSettings();
            LvbuSelectScreenPatch.char_effectsQueue.clear();
            LvbuSelectScreenPatch.reskinRight = new Hitbox(80.0f * Settings.scale, 80.0f * Settings.scale);
            LvbuSelectScreenPatch.reskinLeft = new Hitbox(80.0f * Settings.scale, 80.0f * Settings.scale);
            LvbuSelectScreenPatch.portraitAnimationLeft = new Hitbox(80.0f * Settings.scale, 80.0f * Settings.scale);
            LvbuSelectScreenPatch.portraitAnimationRight = new Hitbox(80.0f * Settings.scale, 80.0f * Settings.scale);
            LvbuSelectScreenPatch.reskinRight.move(Settings.WIDTH / 2.0f + LvbuSelectScreenPatch.reskin_W / 2.0f - LvbuSelectScreenPatch.reskinX_center + LvbuSelectScreenPatch.allTextInfoX, LvbuSelectScreenPatch.allTextInfoY + 0.0f * Settings.scale);
            LvbuSelectScreenPatch.reskinLeft.move(Settings.WIDTH / 2.0f - LvbuSelectScreenPatch.reskin_W / 2.0f - LvbuSelectScreenPatch.reskinX_center + LvbuSelectScreenPatch.allTextInfoX, LvbuSelectScreenPatch.allTextInfoY + 0.0f * Settings.scale);
            LvbuSelectScreenPatch.portraitAnimationLeft.move(Settings.WIDTH / 2.0f - LvbuSelectScreenPatch.reskin_W / 2.0f - LvbuSelectScreenPatch.reskinX_center + LvbuSelectScreenPatch.allTextInfoX, LvbuSelectScreenPatch.allTextInfoY + 120.0f * Settings.scale);
            LvbuSelectScreenPatch.portraitAnimationRight.move(Settings.WIDTH / 2.0f + LvbuSelectScreenPatch.reskin_W / 2.0f - LvbuSelectScreenPatch.reskinX_center + LvbuSelectScreenPatch.allTextInfoX, LvbuSelectScreenPatch.allTextInfoY + 120.0f * Settings.scale);
        }
        
        @SpirePatch(clz = CharacterOption.class, method = "renderInfo")
        public static class CharacterOptionRenderInfoPatch
        {
            @SpireInsertPatch(locator = renderRelicsLocator.class, localvars = { "infoX", "infoY", "charInfo", "flavorText" })
            public static SpireReturn<Void> Insert(final CharacterOption _instance, final SpriteBatch sb, final float infoX, final float infoY, final CharSelectInfo charInfo, @ByRef final String[] flavorText) {
                LvbuSelectScreenPatch.allTextInfoX = infoX - 200.0f * Settings.scale;
                LvbuSelectScreenPatch.allTextInfoY = infoY + 250.0f * Settings.scale;
                for (final LvbuSkinCharacter character : LvbuSelectScreenPatch.characters) {
                    if (charInfo.name.equals(character.id)) {
                        flavorText[0] = character.skins[character.reskinCount].DESCRIPTION;
                        if (character.id.equals("\u5415\u5e03") && !character.reskinUnlockLvbu) {
                            flavorText[0] = CardCrawlGame.languagePack.getUIString(LvbuModHelper.MakePath(LvbuSkinOriginal.class.getSimpleName())).EXTRA_TEXT[1];
                        }
                    }
                }
                return SpireReturn.Continue();
            }
        }
        
        private static class renderRelicsLocator extends SpireInsertLocator
        {
            public int[] Locate(final CtBehavior ctMethodToPatch) throws Exception {
                final Matcher.MethodCallMatcher methodCallMatcher = new Matcher.MethodCallMatcher((Class)CharacterOption.class, "renderRelics");
                final int[] lines = LineFinder.findAllInOrder(ctMethodToPatch, (Matcher)methodCallMatcher);
                return lines;
            }
        }
        
        @SpirePatch(clz = CharacterSelectScreen.class, method = "render")
        public static class CharacterSelectScreenPatch_Render
        {
            @SpirePostfixPatch
            public static void Initialize(final CharacterSelectScreen __instance, final SpriteBatch sb) {
                for (final CharacterOption o : __instance.options) {
                    for (final LvbuSkinCharacter c : LvbuSelectScreenPatch.characters) {
                        c.InitializeReskinCount();
                        if (o.name.equals(c.id) && o.selected) {
                            LvbuSelectScreenPatch.reskinRight.render(sb);
                            LvbuSelectScreenPatch.reskinLeft.render(sb);
                            LvbuSelectScreenPatch.portraitAnimationLeft.render(sb);
                            LvbuSelectScreenPatch.portraitAnimationRight.render(sb);
                            c.skins[c.reskinCount].extraHitboxRender(sb);
                            if (c.skins[c.reskinCount].hasAnimation() && c.reskinUnlockLvbu) {
                                if (LvbuSelectScreenPatch.portraitAnimationLeft.hovered || Settings.isControllerMode) {
                                    sb.setColor(Color.WHITE.cpy());
                                }
                                else {
                                    sb.setColor(Color.LIGHT_GRAY.cpy());
                                }
                                sb.draw(ImageMaster.CF_LEFT_ARROW, Settings.WIDTH / 2.0f - LvbuSelectScreenPatch.reskin_W / 2.0f - LvbuSelectScreenPatch.reskinX_center - 36.0f * Settings.scale + LvbuSelectScreenPatch.allTextInfoX, LvbuSelectScreenPatch.allTextInfoY + 84.0f * Settings.scale, 0.0f, 0.0f, 48.0f, 48.0f, Settings.scale * 1.5f, Settings.scale * 1.5f, 0.0f, 0, 0, 48, 48, false, false);
                                if (LvbuSelectScreenPatch.portraitAnimationRight.hovered || Settings.isControllerMode) {
                                    sb.setColor(Color.WHITE.cpy());
                                }
                                else {
                                    sb.setColor(Color.LIGHT_GRAY.cpy());
                                }
                                sb.draw(ImageMaster.CF_RIGHT_ARROW, Settings.WIDTH / 2.0f + LvbuSelectScreenPatch.reskin_W / 2.0f - LvbuSelectScreenPatch.reskinX_center - 36.0f * Settings.scale + LvbuSelectScreenPatch.allTextInfoX, LvbuSelectScreenPatch.allTextInfoY + 84.0f * Settings.scale, 0.0f, 0.0f, 48.0f, 48.0f, Settings.scale * 1.5f, Settings.scale * 1.5f, 0.0f, 0, 0, 48, 48, false, false);
                            }
                            if (c.reskinUnlockLvbu) {
                                if (LvbuSelectScreenPatch.reskinRight.hovered || Settings.isControllerMode) {
                                    sb.setColor(Color.WHITE.cpy());
                                }
                                else {
                                    sb.setColor(Color.LIGHT_GRAY.cpy());
                                }
                                sb.draw(ImageMaster.CF_RIGHT_ARROW, Settings.WIDTH / 2.0f + LvbuSelectScreenPatch.reskin_W / 2.0f - LvbuSelectScreenPatch.reskinX_center - 36.0f * Settings.scale + LvbuSelectScreenPatch.allTextInfoX, LvbuSelectScreenPatch.allTextInfoY - 36.0f * Settings.scale, 0.0f, 0.0f, 48.0f, 48.0f, Settings.scale * 1.5f, Settings.scale * 1.5f, 0.0f, 0, 0, 48, 48, false, false);
                                if (LvbuSelectScreenPatch.reskinLeft.hovered || Settings.isControllerMode) {
                                    sb.setColor(Color.WHITE.cpy());
                                }
                                else {
                                    sb.setColor(Color.LIGHT_GRAY.cpy());
                                }
                                sb.draw(ImageMaster.CF_LEFT_ARROW, Settings.WIDTH / 2.0f - LvbuSelectScreenPatch.reskin_W / 2.0f - LvbuSelectScreenPatch.reskinX_center - 36.0f * Settings.scale + LvbuSelectScreenPatch.allTextInfoX, LvbuSelectScreenPatch.allTextInfoY - 36.0f * Settings.scale, 0.0f, 0.0f, 48.0f, 48.0f, Settings.scale * 1.5f, Settings.scale * 1.5f, 0.0f, 0, 0, 48, 48, false, false);
                            }
                            FontHelper.cardTitleFont.getData().setScale(1.0f);
                            FontHelper.losePowerFont.getData().setScale(0.8f);
                            if (c.skins[c.reskinCount].hasAnimation() && c.reskinUnlockLvbu) {
                                FontHelper.renderFontCentered(sb, FontHelper.cardTitleFont, CardCrawlGame.languagePack.getUIString(LvbuSkinAll.makeID("PortraitAnimationType")).TEXT[c.skins[c.reskinCount].portraitAnimationType], Settings.WIDTH / 2.0f - LvbuSelectScreenPatch.reskinX_center + LvbuSelectScreenPatch.allTextInfoX, LvbuSelectScreenPatch.allTextInfoY + 120.0f * Settings.scale, Settings.GOLD_COLOR.cpy());
                                FontHelper.renderFontCentered(sb, FontHelper.losePowerFont, CardCrawlGame.languagePack.getUIString(LvbuSkinAll.makeID("PortraitAnimation")).TEXT[0], Settings.WIDTH / 2.0f - LvbuSelectScreenPatch.reskinX_center + LvbuSelectScreenPatch.allTextInfoX, LvbuSelectScreenPatch.allTextInfoY + 170.0f * Settings.scale, Settings.GOLD_COLOR);
                            }
                            if (c.reskinUnlockLvbu) {
                                FontHelper.renderFontCentered(sb, FontHelper.losePowerFont, LvbuSelectScreenPatch.TEXT[0], Settings.WIDTH / 2.0f - LvbuSelectScreenPatch.reskinX_center + LvbuSelectScreenPatch.allTextInfoX, LvbuSelectScreenPatch.allTextInfoY + 50.0f * Settings.scale, Settings.GOLD_COLOR.cpy());
                                FontHelper.renderFontCentered(sb, FontHelper.cardTitleFont, c.skins[c.reskinCount].NAME, Settings.WIDTH / 2.0f - LvbuSelectScreenPatch.reskinX_center + LvbuSelectScreenPatch.allTextInfoX, LvbuSelectScreenPatch.allTextInfoY + 0.0f * Settings.scale, Settings.GOLD_COLOR.cpy());
                            }
                        }
                    }
                }
            }
        }
        
        @SpirePatch(clz = CharacterSelectScreen.class, method = "render")
        public static class CharacterSelectScreenPatch_portraitSkeleton
        {
            @SpireInsertPatch(rloc = 62)
            public static void Insert(final CharacterSelectScreen __instance, final SpriteBatch sb) {
                for (final CharacterOption o : __instance.options) {
                    for (final LvbuSkinCharacter c : LvbuSelectScreenPatch.characters) {
                        c.InitializeReskinCount();
                        if (o.name.equals(c.id) && o.selected && c.skins[c.reskinCount].portraitAnimationType != 0) {
                            c.skins[c.reskinCount].render(sb);
                            if (LvbuSelectScreenPatch.char_effectsQueue.size() > 0) {
                                for (int k = 0; k < LvbuSelectScreenPatch.char_effectsQueue.size(); ++k) {
                                    if (!LvbuSelectScreenPatch.char_effectsQueue.get(k).isDone) {
                                        LvbuSelectScreenPatch.char_effectsQueue.get(k).update();
                                        LvbuSelectScreenPatch.char_effectsQueue.get(k).render(sb);
                                    }
                                    else {
                                        if (LvbuSelectScreenPatch.char_effectsQueue_toRemove == null) {
                                            LvbuSelectScreenPatch.char_effectsQueue_toRemove = new ArrayList<AbstractGameEffect>();
                                        }
                                        if (!LvbuSelectScreenPatch.char_effectsQueue_toRemove.contains(LvbuSelectScreenPatch.char_effectsQueue.get(k))) {
                                            LvbuSelectScreenPatch.char_effectsQueue_toRemove.add(LvbuSelectScreenPatch.char_effectsQueue.get(k));
                                        }
                                    }
                                }
                                if (LvbuSelectScreenPatch.char_effectsQueue_toRemove != null) {
                                    LvbuSelectScreenPatch.char_effectsQueue.removeAll(LvbuSelectScreenPatch.char_effectsQueue_toRemove);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    @SpirePatch(clz = CharacterOption.class, method = "updateHitbox")
    public static class CharacterOptionPatch_reloadAnimation
    {
        @SpireInsertPatch(rloc = 56)
        public static void Insert(final CharacterOption __instance) {
            LvbuSelectScreenPatch.char_effectsQueue.clear();
            LvbuSelectScreenPatch.bgIMGUpdate = false;
            for (final LvbuSkinCharacter c : LvbuSelectScreenPatch.characters) {
                c.InitializeReskinCount();
                if (__instance.name.equals(c.id) && c.skins[c.reskinCount].portraitAnimationType != 0) {
                    c.skins[c.reskinCount].clearWhenClick();
                    if (c.skins[c.reskinCount].hasAnimation()) {
                        c.skins[c.reskinCount].loadPortraitAnimation();
                    }
                    System.out.println("\u7acb\u7ed8\u8f7d\u51652");
                }
            }
        }
    }
    
    @SpirePatch(clz = CharacterSelectScreen.class, method = "update")
    public static class CharacterSelectScreenPatch_Update
    {
        @SpirePostfixPatch
        public static void Postfix(final CharacterSelectScreen __instance) {
            for (final CharacterOption o : __instance.options) {
                for (final LvbuSkinCharacter c : LvbuSelectScreenPatch.characters) {
                    c.InitializeReskinCount();
                    if (o.name.equals(c.id) && o.selected && c.reskinUnlockLvbu) {
                        if (!LvbuSelectScreenPatch.bgIMGUpdate) {
                            __instance.bgCharImg = c.skins[c.reskinCount].updateBgImg();
                            LvbuSelectScreenPatch.bgIMGUpdate = true;
                        }
                        if (InputHelper.justClickedLeft && LvbuSelectScreenPatch.reskinLeft.hovered) {
                            LvbuSelectScreenPatch.reskinLeft.clickStarted = true;
                            CardCrawlGame.sound.play("UI_CLICK_1");
                        }
                        if (InputHelper.justClickedLeft && LvbuSelectScreenPatch.reskinRight.hovered) {
                            LvbuSelectScreenPatch.reskinRight.clickStarted = true;
                            CardCrawlGame.sound.play("UI_CLICK_1");
                        }
                        if (InputHelper.justClickedLeft && LvbuSelectScreenPatch.portraitAnimationLeft.hovered && c.reskinCount > 0) {
                            LvbuSelectScreenPatch.portraitAnimationLeft.clickStarted = true;
                            CardCrawlGame.sound.play("UI_CLICK_1");
                        }
                        if (InputHelper.justClickedLeft && LvbuSelectScreenPatch.portraitAnimationRight.hovered && c.reskinCount > 0) {
                            LvbuSelectScreenPatch.portraitAnimationRight.clickStarted = true;
                            CardCrawlGame.sound.play("UI_CLICK_1");
                        }
                        if (LvbuSelectScreenPatch.reskinLeft.justHovered || LvbuSelectScreenPatch.reskinRight.justHovered) {
                            CardCrawlGame.sound.playV("UI_HOVER", 0.75f);
                        }
                        if ((LvbuSelectScreenPatch.portraitAnimationLeft.justHovered || LvbuSelectScreenPatch.portraitAnimationRight.justHovered) && c.reskinCount > 0) {
                            CardCrawlGame.sound.playV("UI_HOVER", 0.75f);
                        }
                        LvbuSelectScreenPatch.reskinRight.move(Settings.WIDTH / 2.0f + LvbuSelectScreenPatch.reskin_W / 2.0f - LvbuSelectScreenPatch.reskinX_center + LvbuSelectScreenPatch.allTextInfoX, LvbuSelectScreenPatch.allTextInfoY + 0.0f * Settings.scale);
                        LvbuSelectScreenPatch.reskinLeft.move(Settings.WIDTH / 2.0f - LvbuSelectScreenPatch.reskin_W / 2.0f - LvbuSelectScreenPatch.reskinX_center + LvbuSelectScreenPatch.allTextInfoX, LvbuSelectScreenPatch.allTextInfoY + 0.0f * Settings.scale);
                        LvbuSelectScreenPatch.portraitAnimationLeft.move(Settings.WIDTH / 2.0f - LvbuSelectScreenPatch.reskin_W / 2.0f - LvbuSelectScreenPatch.reskinX_center + LvbuSelectScreenPatch.allTextInfoX, LvbuSelectScreenPatch.allTextInfoY + 120.0f * Settings.scale);
                        LvbuSelectScreenPatch.portraitAnimationRight.move(Settings.WIDTH / 2.0f + LvbuSelectScreenPatch.reskin_W / 2.0f - LvbuSelectScreenPatch.reskinX_center + LvbuSelectScreenPatch.allTextInfoX, LvbuSelectScreenPatch.allTextInfoY + 120.0f * Settings.scale);
                        LvbuSelectScreenPatch.reskinLeft.update();
                        LvbuSelectScreenPatch.reskinRight.update();
                        if (c.reskinCount > 0) {
                            LvbuSelectScreenPatch.portraitAnimationLeft.update();
                            LvbuSelectScreenPatch.portraitAnimationRight.update();
                        }
                        if (LvbuSelectScreenPatch.reskinRight.clicked || CInputActionSet.pageRightViewExhaust.isJustPressed()) {
                            LvbuSelectScreenPatch.reskinRight.clicked = false;
                            c.skins[c.reskinCount].clearWhenClick();
                            LvbuSelectScreenPatch.char_effectsQueue.clear();
                            if (c.reskinCount < c.skins.length - 1) {
                                final LvbuSkinCharacter lvbuSkinCharacter = c;
                                ++lvbuSkinCharacter.reskinCount;
                            }
                            else {
                                c.reskinCount = 0;
                            }
                            c.skins[c.reskinCount].loadPortraitAnimation();
                            __instance.bgCharImg = c.skins[c.reskinCount].updateBgImg();
                        }
                        if (LvbuSelectScreenPatch.reskinLeft.clicked || CInputActionSet.pageRightViewExhaust.isJustPressed()) {
                            LvbuSelectScreenPatch.reskinLeft.clicked = false;
                            c.skins[c.reskinCount].clearWhenClick();
                            LvbuSelectScreenPatch.char_effectsQueue.clear();
                            if (c.reskinCount > 0) {
                                final LvbuSkinCharacter lvbuSkinCharacter2 = c;
                                --lvbuSkinCharacter2.reskinCount;
                            }
                            else {
                                c.reskinCount = c.skins.length - 1;
                            }
                            c.skins[c.reskinCount].loadPortraitAnimation();
                            __instance.bgCharImg = c.skins[c.reskinCount].updateBgImg();
                        }
                        if (LvbuSelectScreenPatch.portraitAnimationLeft.clicked || CInputActionSet.pageRightViewExhaust.isJustPressed()) {
                            LvbuSelectScreenPatch.portraitAnimationLeft.clicked = false;
                            c.skins[c.reskinCount].clearWhenClick();
                            LvbuSelectScreenPatch.char_effectsQueue.clear();
                            if (c.skins[c.reskinCount].portraitAnimationType <= 0) {
                                c.skins[c.reskinCount].portraitAnimationType = 2;
                            }
                            else {
                                final AbstractLvbuSkin abstractLvbuSkin = c.skins[c.reskinCount];
                                --abstractLvbuSkin.portraitAnimationType;
                            }
                            c.skins[c.reskinCount].loadPortraitAnimation();
                            __instance.bgCharImg = c.skins[c.reskinCount].updateBgImg();
                        }
                        if (LvbuSelectScreenPatch.portraitAnimationRight.clicked || CInputActionSet.pageRightViewExhaust.isJustPressed()) {
                            LvbuSelectScreenPatch.portraitAnimationRight.clicked = false;
                            c.skins[c.reskinCount].clearWhenClick();
                            LvbuSelectScreenPatch.char_effectsQueue.clear();
                            if (c.skins[c.reskinCount].portraitAnimationType >= 2) {
                                c.skins[c.reskinCount].portraitAnimationType = 0;
                            }
                            else {
                                final AbstractLvbuSkin abstractLvbuSkin2 = c.skins[c.reskinCount];
                                ++abstractLvbuSkin2.portraitAnimationType;
                            }
                            c.skins[c.reskinCount].loadPortraitAnimation();
                            __instance.bgCharImg = c.skins[c.reskinCount].updateBgImg();
                        }
                        c.skins[c.reskinCount].update();
                        if (c.skins[c.reskinCount].extraHitboxClickCheck()) {
                            __instance.bgCharImg = c.skins[c.reskinCount].updateBgImg();
                        }
                        c.InitializeReskinCount();
                    }
                }
            }
        }
    }
}
