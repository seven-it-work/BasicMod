package lottery.relics;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONObject;
import lottery.LotteryMod;
import lottery.cards.status.BaseDraw;
import lottery.cards.status.SanLianReward;

import org.seven.util.GeneralUtils;

public class SanLianLucky extends BaseRelic {
    public static final String ID = LotteryMod.resourcePath.makeID(SanLianLucky.class.getSimpleName());

    private static final int COUNT_MAX = 20;

    public SanLianLucky() {
        super(ID, SanLianLucky.class.getSimpleName(), RelicTier.SPECIAL, LandingSound.CLINK);
        this.counter = 0;
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        super.onUseCard(targetCard, useCardAction);
        if (targetCard instanceof BaseDraw) {
            this.counter += targetCard.magicNumber;
        }
        if (this.counter >= COUNT_MAX) {
            flash();
            this.pulse = false;
            addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            addToBot(new MakeTempCardInHandAction(
                new SanLianReward(RandomUtil.randomEle(AbstractCard.CardRarity.values()))));
            this.counter -= COUNT_MAX;
            return;
        }
        if (this.counter == COUNT_MAX - 1) {
            beginPulse();
            this.pulse = true;
        }
    }

    public String getUpdatedDescription() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("max", COUNT_MAX);
        return GeneralUtils.tiHuan(super.getUpdatedDescription(), jsonObject);
    }

}
