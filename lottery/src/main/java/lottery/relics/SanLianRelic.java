package lottery.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.RelicAboveCreatureEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONObject;
import lottery.LotteryMod;
import lottery.cards.status.LuckyDraw1;
import lottery.cards.status.LuckyDraw10;
import lottery.cards.status.LuckyDraw5;
import lottery.cards.status.SanLianReward;
import lottery.cards.status.SuperLuckyDraw1;
import lottery.cards.status.SuperLuckyDraw10;
import lottery.cards.status.SuperLuckyDraw5;
import lottery.cards.status.UnluckyDraw1;
import lottery.cards.status.UnluckyDraw10;
import lottery.cards.status.UnluckyDraw5;
import lottery.cards.status.UsuallyDraw1;
import lottery.cards.status.UsuallyDraw10;
import lottery.cards.status.UsuallyDraw5;

import org.seven.util.GeneralUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SanLianRelic extends BaseRelic {
    public static final String ID = LotteryMod.resourcePath.makeID(SanLianRelic.class.getSimpleName());
    private static final Set<String> NOT_IN_SAN_LIAN = new HashSet<>();
    public static List<AbstractRelic> THIS_BATTLE_ADD_RELICS = new ArrayList<>();

    static {
        NOT_IN_SAN_LIAN.add(SanLianReward.ID);
    }

    private int gameHandSize;

    public SanLianRelic() {
        super(ID, SanLianRelic.class.getSimpleName(), RelicTier.STARTER, LandingSound.CLINK);
    }

    private static final int ADD_HAND_SIZE = 5;

    private static final int OPEN_SIZE = 3;

    @Override
    public String getUpdatedDescription() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("handSize", ADD_HAND_SIZE);
        jsonObject.set("openSize", OPEN_SIZE);
        String updatedDescription = super.getUpdatedDescription();
        return GeneralUtils.tiHuan(updatedDescription, jsonObject);
    }

    @Override
    public void atBattleStart() {
        AbstractDungeon.player.gameHandSize += ADD_HAND_SIZE;
        super.atBattleStart();
        // 使用抽牌卡3张
        List<String> drawCardIds = Arrays.asList(LuckyDraw1.ID, LuckyDraw5.ID, LuckyDraw10.ID, SuperLuckyDraw1.ID,
            SuperLuckyDraw5.ID, SuperLuckyDraw10.ID, UnluckyDraw1.ID, UnluckyDraw5.ID, UnluckyDraw10.ID,
            UsuallyDraw1.ID, UsuallyDraw5.ID, UsuallyDraw10.ID);
        for (int i = 0; i < OPEN_SIZE; i++) {
            String s = RandomUtil.randomEle(drawCardIds);
            AbstractDungeon.actionManager.addCardQueueItem(
                new CardQueueItem(CardLibrary.getCard(s).makeCopy(), true, EnergyPanel.getCurrentEnergy(), false, true),
                true);
        }
        this.gameHandSize = AbstractDungeon.player.gameHandSize;
    }

    @Override
    public void onVictory() {
        this.flash();
        // 移除上次加载的遗物
        AbstractDungeon.player.relics = (ArrayList<AbstractRelic>) AbstractDungeon.player.relics.stream()
                .filter(abstractRelic -> !THIS_BATTLE_ADD_RELICS.contains(abstractRelic))
                .collect(Collectors.toList());
        AbstractDungeon.player.reorganizeRelics();
        AbstractRelic.relicPage = 0;
        AbstractDungeon.topPanel.adjustRelicHbs();
        // 清空list
        THIS_BATTLE_ADD_RELICS.clear();
        AbstractDungeon.player.gameHandSize = this.gameHandSize;
    }

    @Override
    public void onRefreshHand() {
        super.onRefreshHand();
        ArrayList<AbstractCard> tempHand = new ArrayList<>(AbstractDungeon.player.hand.group);
        HashMap<String, List<AbstractCard>> countMap = new HashMap<>();
        tempHand.forEach(abstractCard -> {
            if (NOT_IN_SAN_LIAN.contains(abstractCard.cardID)) {
                return;
            }
            String key = abstractCard.cardID + abstractCard.timesUpgraded;
            List<AbstractCard> orDefault = countMap.getOrDefault(key, new ArrayList<>());
            orDefault.add(abstractCard);
            if (orDefault.size() == 3) {
                AbstractDungeon.effectList.add(
                    new RelicAboveCreatureEffect(Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH / 2.0F,
                        Settings.HEIGHT / 2.0F, this));
                orDefault.forEach(temp -> AbstractDungeon.player.hand.removeCard(temp));
                orDefault.clear();
                AbstractCard newCard = abstractCard.makeCopy();
                SanLianReward sanLianReward = new SanLianReward(abstractCard.rarity);
                if (newCard.upgraded) {
                    sanLianReward.upgrade();
                }
                newCard.upgrade();
                AbstractDungeon.effectList.add(
                    new ShowCardAndAddToHandEffect(newCard, Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH / 2.0F,
                        Settings.HEIGHT / 2.0F));
                AbstractDungeon.effectList.add(
                    new ShowCardAndAddToHandEffect(sanLianReward, Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH / 2.0F,
                        Settings.HEIGHT / 2.0F));
            }
            countMap.put(key, orDefault);
        });
    }
}
