package lottery.cards.status;

import lottery.LotteryMod;

import org.seven.util.CardStats;

public class UnluckyDraw5 extends BaseDraw {
    public static final String ID = LotteryMod.resourcePath.makeID(UnluckyDraw5.class.getSimpleName());

    private static final CardStats info = new CardStats(CardColor.COLORLESS, CardType.STATUS, CardRarity.CURSE,
        CardTarget.SELF, 0);

    public UnluckyDraw5() {
        super(ID, info, Probability.builder().basic(5).common(5).uncommon(5).rare(5).special(5).curse(75).build());
        setMagic(5, 2);
    }
}
