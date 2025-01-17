package lottery.cards.status;

import cn.hutool.core.util.RandomUtil;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.daily.mods.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import lombok.Builder;
import lottery.cards.BaseCard;
import org.seven.util.CardStats;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseDraw extends BaseCard {
    private boolean used = false;

    private Probability probability = null;

    public BaseDraw(String ID, CardStats info) {
        super(ID, info);
    }

    public BaseDraw(String ID, CardStats info, Probability probability) {
        super(ID, info);
        this.exhaust = true;
        setEthereal(true);
        setExhaust(true);
        this.exhaustOnUseOnce = true;
        this.probability = probability;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return !used;
    }


    public List<String> getCardDescriptors() {
        return Arrays.asList("抽卡器");
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.used = true;
        if (this.probability != null) {
            List<AbstractCard> card = new ArrayList<>();
            try {
                card = this.probability.getCard(this.magicNumber);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            int currentHandSize = abstractPlayer.hand.group.size();
            int gameHandSize = abstractPlayer.gameHandSize;
            for (AbstractCard abstractCard : card) {
                if (currentHandSize <= gameHandSize) {
                    currentHandSize++;
                    this.addToBot(new MakeTempCardInHandAction(abstractCard));
                } else {
                    this.addToBot(new MakeTempCardInDrawPileAction(abstractCard, 1, true, true));
                }
            }
        }
        // 清理使用的牌记录（这里是无奈之举，因为有个遗物 Normality 他会记录打出牌的数量，导致这个回合打不了任何牌了）
        AbstractDungeon.actionManager.cardsPlayedThisTurn.clear();
    }

    @Builder
    public static class Probability {
        private static final List<AbstractCard> ALL_CARDS = new ArrayList<>(CardLibrary.cards.values());

        private static final List<String> NOT_GENERATE_CARD_IDS = Arrays.asList(
                SanLianReward.ID,
                LuckyDraw1.ID,
                LuckyDraw5.ID,
                LuckyDraw10.ID,
                SanLianReward.ID,
                SuperLuckyDraw1.ID,
                SuperLuckyDraw5.ID,
                SuperLuckyDraw10.ID,
                UnluckyDraw1.ID,
                UnluckyDraw5.ID,
                UnluckyDraw10.ID,
                UsuallyDraw1.ID,
                UsuallyDraw5.ID,
                UsuallyDraw10.ID
        );

        private int basic;

        private int common;

        private int uncommon;

        private int rare;

        private int special;

        private int curse;

        public AbstractCard getAnyCard(AbstractCard.CardRarity rarity) {
            return RandomUtil.randomEle(CardLibrary.cards.values()
                    .stream()
                    .filter(c -> c.type != CardType.CURSE)
                    .filter(c -> c.type != CardType.STATUS)
                    .filter(c -> (!UnlockTracker.isCardLocked(c.cardID) || Settings.treatEverythingAsUnlocked()))
                    .filter(c -> !NOT_GENERATE_CARD_IDS.contains(c.cardID))
                    .filter(c -> rarity.equals(c.rarity))
                    .filter(c -> {
                        if (ModHelper.isModEnabled(ColorlessCards.ID) && CardColor.COLORLESS.equals(c.color)) {
                            return true;
                        }
                        if (ModHelper.isModEnabled(Diverse.ID)) {
                            return true;
                        } else {
                            if (ModHelper.isModEnabled(RedCards.ID) && CardColor.RED.equals(c.color)) {
                                return true;
                            }
                            if (ModHelper.isModEnabled(GreenCards.ID) && CardColor.GREEN.equals(c.color)) {
                                return true;
                            }
                            if (ModHelper.isModEnabled(PurpleCards.ID) && CardColor.PURPLE.equals(c.color)) {
                                return true;
                            }
                            if (ModHelper.isModEnabled(BlueCards.ID) && CardColor.BLUE.equals(c.color)) {
                                return true;
                            }
                            return AbstractDungeon.player.getCardColor().equals(c.color);
                        }
                    })
                    .collect(Collectors.toList()));
        }

        public List<AbstractCard> getCard(int size) {
            int sum = this.basic + this.common + this.uncommon + this.rare + this.special + this.curse;
            ArrayList<AbstractCard> abstractCards = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                int result = RandomUtil.randomInt(sum);
                int temp = basic;
                if (result < temp) {
                    abstractCards.add(getAnyCard(CardRarity.BASIC));
                    continue;
                }
                temp += common;
                if (result < temp) {
                    abstractCards.add(getAnyCard(CardRarity.COMMON));
                    continue;
                }
                temp += uncommon;
                if (result < temp) {
                    abstractCards.add(getAnyCard(CardRarity.UNCOMMON));
                    continue;
                }
                temp += rare;
                if (result < temp) {
                    abstractCards.add(getAnyCard(CardRarity.RARE));
                    continue;
                }
                temp += special;
                if (result < temp) {
                    abstractCards.add(CardLibrary.getAnyColorCard(CardRarity.SPECIAL));
                    continue;
                }
                temp += curse;
                if (result < temp) {
                    abstractCards.add(CardLibrary.getCurse());
                    continue;
                }
                throw new RuntimeException("没有对应概率，判断逻辑错误了");
            }
            return abstractCards;
        }
    }
}
