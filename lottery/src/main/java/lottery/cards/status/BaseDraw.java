package lottery.cards.status;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import cn.hutool.core.util.RandomUtil;
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
        this.probability = probability;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return !used;
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
    }

    @Builder
    public static class Probability {
        private static final List<AbstractCard> ALL_CARDS = new ArrayList<>(CardLibrary.cards.values());

        private static final List<String> NOT_GENERATE_CARD_IDS = Arrays.asList(SanLianReward.ID);

        private int basic;

        private int common;

        private int uncommon;

        private int rare;

        private int special;

        private int curse;

        private AbstractCard randomByType(CardRarity cardRarity) {
            return RandomUtil.randomEle(CardLibrary.cards.values()
                .stream()
                .filter(abstractCard -> !NOT_GENERATE_CARD_IDS.contains(abstractCard.cardID))
                .filter(abstractCard -> cardRarity.equals(abstractCard.rarity))
                .collect(Collectors.toList()));
        }

        public List<AbstractCard> getCard(int size) {
            int sum = this.basic + this.common + this.uncommon + this.rare + this.special + this.curse;
            ArrayList<AbstractCard> abstractCards = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                int result = RandomUtil.randomInt(sum);
                int temp = basic;
                if (result < temp) {
                    abstractCards.add(randomByType(CardRarity.BASIC));
                    continue;
                }
                temp += common;
                if (result < temp) {
                    abstractCards.add(randomByType(CardRarity.COMMON));
                    continue;
                }
                temp += uncommon;
                if (result < temp) {
                    abstractCards.add(randomByType(CardRarity.UNCOMMON));
                    continue;
                }
                temp += rare;
                if (result < temp) {
                    abstractCards.add(randomByType(CardRarity.RARE));
                    continue;
                }
                temp += special;
                if (result < temp) {
                    abstractCards.add(randomByType(CardRarity.SPECIAL));
                    continue;
                }
                temp += curse;
                if (result < temp) {
                    abstractCards.add(randomByType(CardRarity.CURSE));
                    continue;
                }
                throw new RuntimeException("没有对应概率，判断逻辑错误了");
            }
            return abstractCards;
        }
    }
}
