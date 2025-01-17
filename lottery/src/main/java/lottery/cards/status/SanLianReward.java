package lottery.cards.status;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONObject;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.*;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import kobting.friendlyminions.characters.AbstractPlayerWithMinions;
import lottery.LotteryMod;
import lottery.cards.BaseCard;
import lottery.characters.TestMinion;
import lottery.relics.SanLianRelic;
import org.seven.util.CardStats;
import org.seven.util.GeneralUtils;

import java.util.*;
import java.util.stream.Collectors;

public class SanLianReward extends BaseCard {
    public static final String ID = LotteryMod.MOD.makeID(SanLianReward.class.getSimpleName());

    private static final CardStats info = new CardStats(CardColor.COLORLESS, CardType.STATUS, CardRarity.CURSE,
        CardTarget.SELF, 0);

    private boolean used = false;

    public SanLianReward() {
        this(CardRarity.CURSE);
    }

    private static final int maxRepeatTimes = 200;

    private static final Set<String> NOT_GENERATE_IDS = new HashSet<>(Arrays.asList(
            SanLianRelic.ID,
            BottledLightning.ID,
            BottledFlame.ID,
            BottledTornado.ID,
            Astrolabe.ID,
            Orrery.ID,
            EmptyCage.ID,
            DollysMirror.ID,
            Cauldron.ID,
            TinyHouse.ID,
            "Guardian:BottledAnomaly",
            "Guardian:BottledStasis",
            "bronze:BottledCode",
            ".*?Bottled.*?"
    ));

    private int repeatTimes = 0;

    public SanLianReward(CardRarity cardRarity) {
        super(ID, info);
        this.rarity=cardRarity;
        setEthereal(true);
        setExhaust(true);
        setMagic(1, 1);
        this.reDescription();
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return super.canUse(p, m) && !used;
    }

    private void reDescription() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("rarity", " " + LotteryMod.MOD.getProperNameByWordName(this.rarity.name()) + " ");
        jsonObject.set("relicRarity", " " + LotteryMod.MOD.getProperNameByWordName(getRelicTier().name()) + " ");
        this.rawDescription = GeneralUtils.tiHuan(this.originalRawDescription, jsonObject);
        super.initializeDescription();
    }

    @Override
    public List<String> getCardDescriptors() {
        // 这个是放在状态（属性里面的文本）
        return Arrays.asList("奖励");
    }

    private AbstractRelic.RelicTier getRelicTier() {
        switch (this.rarity) {
            case RARE:
                return AbstractRelic.RelicTier.RARE;
            case BASIC:
                return AbstractRelic.RelicTier.STARTER;
            case SPECIAL:
                return AbstractRelic.RelicTier.SPECIAL;
            case COMMON:
                return AbstractRelic.RelicTier.COMMON;
            case UNCOMMON:
                return AbstractRelic.RelicTier.UNCOMMON;
            case CURSE:
            default:
                return AbstractRelic.RelicTier.SHOP;
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if(abstractPlayer instanceof AbstractPlayerWithMinions) {
            ArrayList<AbstractCard> temp = new ArrayList<>();
            temp.add(new SanLianReward());
            temp.add(new SanLianReward());
            temp.add(new SanLianReward());
            temp.add(new SanLianReward());
            // 这是选卡，选了后在AbstractDungeon.cardRewardScreen.discoveryCard里面
            AbstractDungeon.cardRewardScreen.customCombatOpen(temp, CardRewardScreen.TEXT[1], false);
            AbstractDungeon.cardRewardScreen.confirmButton.show();
            if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
                AbstractCard abstractCard = AbstractDungeon.cardRewardScreen.discoveryCard.makeCopy();
            }
            AbstractPlayerWithMinions player = (AbstractPlayerWithMinions) abstractPlayer;
            TestMinion minion = new TestMinion(0,0);
            minion.drawX=abstractPlayer.drawX+100;
            minion.drawY=abstractPlayer.drawY+100;
            player.addMinion(minion);
        }
        for (int i = 0; i < this.magicNumber; i++) {
            // RelicLibrary 中获取
            switch (this.rarity) {
                case RARE:
                    addTempRelic(abstractPlayer, randomRelic(RelicLibrary.rareList).makeCopy());
                    break;
                case BASIC:
                    addTempRelic(abstractPlayer, randomRelic(RelicLibrary.starterList).makeCopy());
                    break;
                case SPECIAL:
                    addTempRelic(abstractPlayer, randomRelic(RelicLibrary.specialList).makeCopy());
                    break;
                case COMMON:
                    addTempRelic(abstractPlayer, randomRelic(RelicLibrary.commonList).makeCopy());
                    break;
                case UNCOMMON:
                    addTempRelic(abstractPlayer, randomRelic(RelicLibrary.uncommonList).makeCopy());
                    break;
                case CURSE:
                default:
                    addTempRelic(abstractPlayer, randomRelic(RelicLibrary.shopList).makeCopy());
                    break;
            }
        }
        this.used = true;
    }

    private AbstractRelic randomRelic(List<AbstractRelic> baseList) {
        repeatTimes++;
        AbstractRelic abstractRelic = RandomUtil.randomEle(
            baseList.stream().filter(temp -> {
                boolean contains = NOT_GENERATE_IDS.stream().anyMatch(temp.relicId::matches);
                if (!contains){
                    LotteryMod.logger.info("包含。onEquip 遗物类名:{}，遗物id：{}，遗物名称:{}",
                            temp.getClass().getName(), temp.relicId, temp.name);

                }
                return !contains;
            }).collect(Collectors.toList()));
        List<String> hasRelic = AbstractDungeon.player.relics.stream()
            .filter(temp -> temp.relicId.equals(abstractRelic.relicId))
            .map(temp -> temp.relicId)
            .collect(Collectors.toList());
        if (hasRelic.isEmpty()) {
            repeatTimes = 0;
            return abstractRelic;
        } else {
            // 存在遗物 则通过1/已有相同遗物数量
            if (RandomUtil.randomInt(hasRelic.size() + 1) == 0) {
                repeatTimes = 0;
                return abstractRelic;
            } else {
                if (repeatTimes > maxRepeatTimes) {
                    repeatTimes = 0;
                    return abstractRelic;
                } else {
                    return randomRelic(baseList);
                }
            }
        }
    }

    private void addTempRelic(AbstractPlayer abstractPlayer, AbstractRelic abstractRelicCurse) {
        abstractPlayer.relics.add(abstractRelicCurse);
        SanLianRelic.THIS_BATTLE_ADD_RELICS.add(abstractRelicCurse);
        abstractRelicCurse.playLandingSFX();
        AbstractDungeon.player.reorganizeRelics();
        AbstractRelic.relicPage = 0;
        AbstractDungeon.topPanel.adjustRelicHbs();
        this.addToTop(new RelicAboveCreatureAction(abstractPlayer, abstractPlayer.getRelic(SanLianRelic.ID)));
        // abstractRelicCurse的开始回合效果重新触发
        try {
            abstractRelicCurse.atPreBattle();
        } catch (Exception e) {
            LotteryMod.logger.error("三连遗物错误。atPreBattle 遗物类名:{}，遗物id：{}，遗物名称:{}",
                abstractRelicCurse.getClass().getName(), abstractRelicCurse.relicId, abstractRelicCurse.name);
        }
        try {
            abstractRelicCurse.atBattleStartPreDraw();
        } catch (Exception e) {
            LotteryMod.logger.error("三连遗物错误。atBattleStartPreDraw 遗物类名:{}，遗物id：{}，遗物名称:{}",
                abstractRelicCurse.getClass().getName(), abstractRelicCurse.relicId, abstractRelicCurse.name);
        }
        try {
            abstractRelicCurse.atBattleStart();
        } catch (Exception e) {
            LotteryMod.logger.error("三连遗物错误。atBattleStart 遗物类名:{}，遗物id：{}，遗物名称:{}",
                abstractRelicCurse.getClass().getName(), abstractRelicCurse.relicId, abstractRelicCurse.name);
        }
        try {
            abstractRelicCurse.onEquip();
        } catch (Exception e) {
            LotteryMod.logger.error("三连遗物错误。onEquip 遗物类名:{}，遗物id：{}，遗物名称:{}",
                abstractRelicCurse.getClass().getName(), abstractRelicCurse.relicId, abstractRelicCurse.name);
        }
    }
}
