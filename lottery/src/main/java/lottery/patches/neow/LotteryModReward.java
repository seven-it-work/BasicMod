package lottery.patches.neow;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;

import lottery.LotteryMod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class LotteryModReward {
    public static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(
        LotteryMod.resourcePath.makeID("Blessings"));

    private static final List<LotteryModReward> commonRewardList = new ArrayList<>();

    private static final List<LotteryModReward> miniRewardList = new ArrayList<>();

    private static final Map<AbstractPlayer.PlayerClass, List<LotteryModReward>> characterRewardList = new HashMap<>();

    public LotteryModReward() {
    }

    public static void initializeRewards() {
        commonRewardList.add(new SanLianModReward(false));
        // miniRewardList.add(new JustRelax());
        characterRewardList.put(PlayerClass.IRONCLAD, new ArrayList());
        characterRewardList.put(PlayerClass.THE_SILENT, new ArrayList());
        characterRewardList.put(PlayerClass.DEFECT, new ArrayList());
        characterRewardList.put(PlayerClass.WATCHER, new ArrayList());
        // ((List)characterRewardList.get(PlayerClass.IRONCLAD)).add(new IroncladReward());
    }

    public static void addCharacterRewards(AbstractPlayer.PlayerClass playerClass, final Runnable activateEffect,
        final String description, final String selectedSpeech) {
        if (!characterRewardList.containsKey(playerClass)) {
            characterRewardList.put(playerClass, new ArrayList<>());
        }
        characterRewardList.get(playerClass).add(new LotteryModReward() {
            public String getDescription() {
                return description;
            }

            public String getSelectedSpeech() {
                return selectedSpeech;
            }

            public void activate() {
                activateEffect.run();
            }
        });
    }

    public abstract String getDescription();

    public abstract String getSelectedSpeech();

    public abstract void activate();
}
