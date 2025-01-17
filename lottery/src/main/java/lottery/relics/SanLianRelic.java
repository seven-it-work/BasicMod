package lottery.relics;

import basemod.cardmods.EtherealMod;
import basemod.cardmods.ExhaustMod;
import basemod.helpers.CardModifierManager;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONObject;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.RelicAboveCreatureEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
import lottery.LotteryMod;
import lottery.cards.status.*;
import org.seven.util.QuickStartRelic;
import org.seven.util.GeneralUtils;

import java.util.*;
import java.util.stream.Collectors;

public class SanLianRelic extends BaseRelic {
    public static final String ID = LotteryMod.MOD.makeID(SanLianRelic.class.getSimpleName());
    private static final Set<String> NOT_IN_SAN_LIAN = new HashSet<>();
    public static List<AbstractRelic> THIS_BATTLE_ADD_RELICS = new ArrayList<>();

    static {
        NOT_IN_SAN_LIAN.add(SanLianReward.ID);
    }

    private static final int ADD_HAND_SIZE = 5;

    public SanLianRelic() {
        super(ID, SanLianRelic.class.getSimpleName(), RelicTier.STARTER, LandingSound.CLINK);
    }

    private int masterHandSize;

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
        this.masterHandSize = AbstractDungeon.player.masterHandSize;
        AbstractDungeon.player.masterHandSize += ADD_HAND_SIZE;
        super.atBattleStart();
        // 使用抽牌卡3张
        List<String> drawCardIds = Arrays.asList(LuckyDraw1.ID, LuckyDraw5.ID, LuckyDraw10.ID, SuperLuckyDraw1.ID,
            SuperLuckyDraw5.ID, SuperLuckyDraw10.ID, UnluckyDraw1.ID, UnluckyDraw5.ID, UnluckyDraw10.ID,
            UsuallyDraw1.ID, UsuallyDraw5.ID, UsuallyDraw10.ID);
        for (int i = 0; i < OPEN_SIZE; i++) {
            String s = RandomUtil.randomEle(drawCardIds);
            AbstractCard card = CardLibrary.getCard(s).makeCopy();
            CardModifierManager.addModifier(card, new EtherealMod());
            CardModifierManager.addModifier(card, new ExhaustMod());
            AbstractDungeon.actionManager.addCardQueueItem(
                new CardQueueItem(card, true, EnergyPanel.getCurrentEnergy(), false, true), true);
        }
    }

    @Override
    public void onPlayerEndTurn() {
        super.onPlayerEndTurn();
        AbstractDungeon.player.masterHandSize = this.masterHandSize;
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
        THIS_BATTLE_ADD_RELICS.forEach(abstractRelic -> {
            try {
                abstractRelic.onUnequip();
            } catch (Exception e) {
                LotteryMod.logger.error("三连遗物错误。onUnequip 遗物类名:{}，遗物id：{}，遗物名称:{}",
                        abstractRelic.getClass().getName(), abstractRelic.relicId, abstractRelic.name);
            }
        });
        // 清空list
        THIS_BATTLE_ADD_RELICS.clear();
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
                CardModifierManager.addModifier(sanLianReward, new EtherealMod());
                CardModifierManager.addModifier(sanLianReward, new ExhaustMod());
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
