package lottery.cards.status;

import lottery.LotteryMod;
import org.seven.util.CardStats;

public class LuckyDraw1 extends BaseDraw {
    public static final String ID = LotteryMod.MOD.makeID(LuckyDraw1.class.getSimpleName());

    private static final CardStats info = new CardStats(CardColor.COLORLESS, CardType.STATUS, CardRarity.CURSE,
        CardTarget.SELF, 0);

    public LuckyDraw1() {
        super(ID, info,
            BaseDraw.Probability.builder().basic(30).common(30).uncommon(15).rare(15).special(5).curse(5).build());
        setMagic(1, 1);
    }
}
