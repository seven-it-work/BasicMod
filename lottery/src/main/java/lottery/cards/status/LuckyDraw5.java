package lottery.cards.status;

import lottery.LotteryMod;
import org.seven.util.CardStats;

public class LuckyDraw5 extends BaseDraw {
    public static final String ID = LotteryMod.MOD.makeID(LuckyDraw5.class.getSimpleName());

    private static final CardStats info = new CardStats(CardColor.COLORLESS, CardType.STATUS, CardRarity.CURSE,
        CardTarget.SELF, 0);

    public LuckyDraw5() {
        super(ID, info, Probability.builder().basic(30).common(30).uncommon(15).rare(15).special(5).curse(5).build());
        setMagic(5, 2);
    }
}
