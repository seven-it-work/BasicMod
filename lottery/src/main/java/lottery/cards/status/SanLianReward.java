package lottery.cards.status;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONObject;
import lottery.LotteryMod;
import lottery.cards.BaseCard;
import lottery.relics.SanLianRelic;

import org.seven.util.CardStats;
import org.seven.util.GeneralUtils;

import java.util.List;
import java.util.stream.Collectors;

public class SanLianReward extends BaseCard {
    public static final String ID = LotteryMod.resourcePath.makeID(SanLianReward.class.getSimpleName());

    private static final CardStats info = new CardStats(CardColor.COLORLESS, CardType.STATUS, CardRarity.CURSE,
        CardTarget.SELF, 0);

    private boolean used = false;

    public SanLianReward() {
        super(ID, info);
    }

    private static final int maxRepeatTimes = 200;

    private int repeatTimes = 0;

    public SanLianReward(CardRarity cardRarity) {
        super(ID, info);
        this.rarity = cardRarity;
        this.exhaust = true;
        setMagic(1, 1);
    }

    @Override
    public void initializeDescription() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("rarity", " " + LotteryMod.getKeywordsTranslation(this.rarity.name()) + " ");
        this.rawDescription = GeneralUtils.tiHuan(this.rawDescription, jsonObject);
        super.initializeDescription();
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return super.canUse(p, m) && !used;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
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
        AbstractRelic abstractRelic = RandomUtil.randomEle(baseList);
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
        abstractRelicCurse.atPreBattle();
        abstractRelicCurse.atBattleStartPreDraw();
        abstractRelicCurse.atBattleStart();
    }
}
