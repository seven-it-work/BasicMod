package lottery.cards.status;

import lottery.LotteryMod;
import org.seven.util.CardStats;

public class SuperLuckyDraw1 extends BaseDraw {
    public static final String ID = LotteryMod.MOD.makeID(SuperLuckyDraw1.class.getSimpleName());

    private static final CardStats info = new CardStats(CardColor.COLORLESS, CardType.STATUS, CardRarity.CURSE,
        CardTarget.SELF, 0);

    public SuperLuckyDraw1() {
        super(ID, info, Probability.builder().basic(20).common(20).uncommon(20).rare(20).special(10).curse(10).build());
        setMagic(1, 1);
    }
}
