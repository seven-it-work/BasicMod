package lottery.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import lottery.LotteryMod;
import org.seven.util.CardStats;

public class DrawCards extends BaseCard {
    public static final String ID = LotteryMod.resourcePath.makeID(DrawCards.class.getSimpleName());

    private static final CardStats info = new CardStats(LotteryMod.PlayerColorEnum.BASE_LU_SHI_PLAYER_CARD_COLOR, CardType.SKILL, CardRarity.COMMON,
            CardTarget.SELF, 1);


    public DrawCards() {
        super(ID, info);
        setMagic(1, 2);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DrawCardAction(abstractPlayer, 6 - this.magicNumber));
    }
}
