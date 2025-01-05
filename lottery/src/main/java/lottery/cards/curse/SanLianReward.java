package lottery.cards.curse;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import lottery.LotteryMod;
import lottery.cards.BaseCard;
import org.seven.util.CardStats;

public class SanLianReward extends BaseCard {
    public static final String ID = LotteryMod.resourcePath.makeID(SanLianReward.class.getSimpleName());

    private static final CardStats info = new CardStats(CardColor.COLORLESS, CardType.CURSE, CardRarity.CURSE,
            CardTarget.SELF, 0);

    public SanLianReward() {
        super(ID, info);
    }



    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }
}
