package lottery.patches.neow;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.neow.NeowEvent;
import com.megacrit.cardcrawl.vfx.InfiniteSpeechBubble;
import javassist.CtBehavior;
import lottery.LotteryMod;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class NeowEventPatch {
    private static final float DIALOG_X = 1100.0F * Settings.xScale;

    private static final float DIALOG_Y = AbstractDungeon.floorY + 60.0F * Settings.yScale;

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(
        LotteryMod.MOD.makeID("Blessings"));

    public NeowEventPatch() {
    }

    @SpirePatch(clz = NeowEvent.class, method = "buttonEffect")
    public static class PatchButtonEffect {
        private static final List<LotteryModReward> rewards = new ArrayList<>();

        public static SpireReturn<Void> Prefix(NeowEvent neowEvent, int buttonPressed) {
            int screenNum = ReflectionHacks.getPrivate(neowEvent, NeowEvent.class, "screenNum");
            switch (screenNum) {
                case 7780:
                    try {
                        ReflectionHacks.getCachedMethod(NeowEvent.class, "dismissBubble", new Class[0])
                            .invoke(neowEvent);
                    } catch (InvocationTargetException | IllegalAccessException var6) {
                        throw new RuntimeException(var6);
                    }
                    // miniBlessing标识击败过boss。奖励不同而已
                    boolean miniBlessing =
                        (Integer) ReflectionHacks.getPrivate(neowEvent, NeowEvent.class, "bossCount") == 0
                            && !Settings.isTestingNeow;
                    rewards.clear();
                    neowEvent.roomEventText.clearRemainingOptions();
                    AbstractDungeon.effectList.add(
                        new InfiniteSpeechBubble(DIALOG_X, DIALOG_Y, uiStrings.TEXT[miniBlessing ? 1 : 0]));
                    rewards.add(new SanLianModReward(miniBlessing));
                    neowEvent.roomEventText.updateDialogOption(0, rewards.get(0).getDescription());
                    neowEvent.roomEventText.addDialogOption(uiStrings.EXTRA_TEXT[1]);

                    ReflectionHacks.setPrivate(neowEvent, NeowEvent.class, "screenNum", 7781);
                    return SpireReturn.Return(null);
                case 7781:
                    try {
                        ReflectionHacks.getCachedMethod(NeowEvent.class, "dismissBubble", new Class[0])
                            .invoke(neowEvent);
                    } catch (InvocationTargetException | IllegalAccessException var5) {
                        throw new RuntimeException(var5);
                    }
                    neowEvent.roomEventText.clearRemainingOptions();
                    switch (buttonPressed) {
                        case 0:
                            LotteryModReward reward = rewards.get(buttonPressed);
                            reward.activate();
                            AbstractDungeon.effectList.add(
                                new InfiniteSpeechBubble(DIALOG_X, DIALOG_Y, reward.getSelectedSpeech()));
                        case 1:
                            // 拒绝了什么都不做
                    }
                    neowEvent.roomEventText.updateDialogOption(0, NeowEvent.OPTIONS[1]);
                    ReflectionHacks.setPrivate(neowEvent, NeowEvent.class, "screenNum", 1);
                    return SpireReturn.Return(null);
                default:
                    return SpireReturn.Continue();
            }
        }
    }

    @SpirePatch(clz = NeowEvent.class, method = "<ctor>", paramtypez = {boolean.class})
    public static class PatchConstructor {
        @SpireInsertPatch(locator = Locator.class)
        public static void Insert(NeowEvent neowEvent, boolean isDone) {
            // 切换选中是否三连模式
            ReflectionHacks.setPrivate(neowEvent, NeowEvent.class, "screenNum", 7780);
        }

        private static final class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractDungeon.class, "topLevelEffects");
                int[] lines = LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher);
                return new int[] {lines[lines.length - 1] - 2};
            }
        }
    }
}
