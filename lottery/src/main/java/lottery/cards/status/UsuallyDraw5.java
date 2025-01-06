package lottery.cards.status;

import lottery.LotteryMod;

import org.seven.util.CardStats;

public class UsuallyDraw5 extends BaseDraw {
    public static final String ID = LotteryMod.resourcePath.makeID(UsuallyDraw5.class.getSimpleName());

    private static final CardStats info = new CardStats(CardColor.COLORLESS, CardType.STATUS, CardRarity.CURSE,
        CardTarget.SELF, 0);

    public UsuallyDraw5() {
        super(ID, info, Probability.builder().basic(35).common(35).uncommon(10).rare(10).special(5).curse(5).build());
        setMagic(5, 2);
    }
}
