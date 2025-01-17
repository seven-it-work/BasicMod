package lottery.cards;

import cn.hutool.core.util.RandomUtil;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import lottery.LotteryMod;
import org.seven.util.QuickStartCard;
import org.seven.util.CardStats;

public class DefaultDefault extends BaseCard {
    public static final String ID = LotteryMod.MOD.makeID(DefaultDefault.class.getSimpleName());

    private static final CardStats info = new CardStats(LotteryMod.BASE_LU_SHI_PLAYER_CARD_COLOR, CardType.SKILL, CardRarity.BASIC,
            CardTarget.SELF, 1);


    public DefaultDefault() {
        super(ID, info);
        setBlock(RandomUtil.randomInt(5, 10), RandomUtil.randomInt(0, 5));
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToTop(new GainBlockAction(abstractPlayer, this.block));
    }
}
