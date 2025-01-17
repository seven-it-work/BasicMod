package lottery.cards;

import cn.hutool.core.util.RandomUtil;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kobting.friendlyminions.characters.AbstractPlayerWithMinions;
import lottery.LotteryMod;
import lottery.characters.TestMinion;
import org.seven.util.QuickStartCard;
import org.seven.util.CardStats;

public class DefaultAttack extends BaseCard {
    public static final String ID = LotteryMod.MOD.makeID(DefaultAttack.class.getSimpleName());

    private static final CardStats info = new CardStats(LotteryMod.BASE_LU_SHI_PLAYER_CARD_COLOR, CardType.ATTACK, CardRarity.BASIC,
            CardTarget.ENEMY, 1);


    public DefaultAttack() {
        super(ID, info);
        setDamage(RandomUtil.randomInt(5, 10), RandomUtil.randomInt(0, 5));
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToTop(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage)));

        if(abstractPlayer instanceof AbstractPlayerWithMinions) {
            AbstractPlayerWithMinions player = (AbstractPlayerWithMinions) abstractPlayer;
            TestMinion minion = new TestMinion(0,0);
            minion.drawX=abstractPlayer.drawX+100;
            minion.drawY=abstractPlayer.drawY+100;
            player.addMinion(minion);
        }
    }
}
