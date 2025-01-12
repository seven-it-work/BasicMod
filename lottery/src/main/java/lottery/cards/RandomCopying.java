package lottery.cards;

import cn.hutool.core.util.RandomUtil;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import lottery.LotteryMod;
import org.seven.util.CardStats;

public class RandomCopying extends BaseCard {
    public static final String ID = LotteryMod.resourcePath.makeID(RandomCopying.class.getSimpleName());

    private static final CardStats info = new CardStats(LotteryMod.PlayerColorEnum.BASE_LU_SHI_PLAYER_CARD_COLOR, CardType.ATTACK, CardRarity.COMMON,
            CardTarget.SELF, 1);


    public RandomCopying() {
        super(ID, info);
        setDamage(RandomUtil.randomInt(1, 5), RandomUtil.randomInt(0, 5));
        setBlock(RandomUtil.randomInt(1, 5), RandomUtil.randomInt(0, 5));
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToTop(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage)));
        addToTop(new GainBlockAction(abstractPlayer, this.block));
        for (int i = 0; i < RandomUtil.randomInt(0, 4); i++) {
            RandomCopying card = new RandomCopying();
            if (this.upgraded){
                card.upgrade();
            }
            addToTop(new MakeTempCardInDiscardAction(card, 1));
        }
    }
}
