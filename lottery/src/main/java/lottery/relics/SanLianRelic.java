package lottery.relics;

import cn.hutool.core.util.RandomUtil;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import lottery.LotteryMod;
import lottery.actions.PlayCardAction;
import lottery.cards.status.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class SanLianRelic extends BaseRelic {
    public static final String ID = LotteryMod.resourcePath.makeID(SanLianRelic.class.getSimpleName());
    private static final Set<String> NOT_IN_SAN_LIAN = new HashSet<>();
    public static List<AbstractRelic> THIS_BATTLE_ADD_RELICS = new ArrayList<>();

    static {
        NOT_IN_SAN_LIAN.add(SanLianReward.ID);
    }

    public SanLianRelic() {
        super(ID, SanLianRelic.class.getSimpleName(), RelicTier.STARTER, LandingSound.CLINK);
    }

    @Override
    public void atBattleStart() {
        super.atBattleStart();
        // 使用抽牌卡3张
        List<String> drawCardIds = Arrays.asList(LuckyDraw1.ID, LuckyDraw5.ID, LuckyDraw10.ID, SuperLuckyDraw1.ID,
                SuperLuckyDraw5.ID, SuperLuckyDraw10.ID, UnluckyDraw1.ID, UnluckyDraw5.ID, UnluckyDraw10.ID,
                UsuallyDraw1.ID, UsuallyDraw5.ID, UsuallyDraw10.ID);
        for (int i = 0; i < 3; i++) {
            String s = RandomUtil.randomEle(drawCardIds);
            this.addToTop(new PlayCardAction(CardLibrary.getCard(s).makeCopy(), null, true));
        }
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
    }

    @Override
    public void onRefreshHand() {
        super.onRefreshHand();
        AtomicBoolean changeHand = new AtomicBoolean(false);
        ArrayList<AbstractCard> newHand = new ArrayList<>();
        // 过滤掉不需要的
        List<AbstractCard> group = AbstractDungeon.player.hand.group.stream().filter(abstractCard -> {
            if (NOT_IN_SAN_LIAN.contains(abstractCard.cardID)) {
                newHand.add(abstractCard);
                return false;
            }
            return true;
        }).collect(Collectors.toList());
        // 已升级的
        List<AbstractCard> updradedList = group.stream().filter(abstractCard -> abstractCard.upgraded).collect(Collectors.toList());
        List<AbstractCard> notUpdradedList = group.stream().filter(abstractCard -> !abstractCard.upgraded).collect(Collectors.toList());
        if (updradedList.size() < 3 && notUpdradedList.size() < 3) {
            // 数量小于3不做处理了
            return;
        }
        updradedList.stream()
            .collect(Collectors.groupingBy(card -> card.cardID)).forEach((key, value) -> {
                if (value.size() >= 3) {
                    changeHand.set(true);
                    // 添加三连多余的
                    for (int i = 0; i < value.size() % 3; i++) {
                        newHand.add(value.get(i));
                    }
                    // 添加三连奖励
                    for (int i = 0; i < value.size() / 3; i++) {
                        AbstractCard abstractCard = value.get(i).makeCopy();
                        abstractCard.upgrade();
                        newHand.add(abstractCard);
                        SanLianReward sanLianReward = new SanLianReward(abstractCard.rarity);
                        sanLianReward.upgrade();
                        newHand.add(sanLianReward);
                    }
                } else {
                    newHand.addAll(value);
                }
            });
        notUpdradedList.stream()
            .collect(Collectors.groupingBy(card -> card.cardID)).forEach((key, value) -> {
                if (value.size() >= 3) {
                    changeHand.set(true);
                    // 添加三连多余的
                    for (int i = 0; i < value.size() % 3; i++) {
                        newHand.add(value.get(i));
                    }
                    // 添加三连奖励
                    for (int i = 0; i < value.size() / 3; i++) {
                        AbstractCard abstractCard = value.get(i).makeCopy();
                        abstractCard.upgrade();
                        newHand.add(abstractCard);
                        SanLianReward sanLianReward = new SanLianReward(abstractCard.rarity);
                        newHand.add(sanLianReward);
                    }
                } else {
                    newHand.addAll(value);
                }
            });
        if (changeHand.get()) {
            AbstractDungeon.player.hand.group = newHand;
        }
    }
}
