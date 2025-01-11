package lottery.relics;

import cn.hutool.json.JSONObject;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import lottery.LotteryMod;
import lottery.actions.PlayCardAction;
import org.seven.util.GeneralUtils;

public class YingShu extends BaseRelic {
    public static final String ID = LotteryMod.resourcePath.makeID(YingShu.class.getSimpleName());


    public YingShu() {
        super(ID, YingShu.class.getSimpleName(), RelicTier.SHOP, LandingSound.CLINK);
    }

    @Override
    public void atTurnStart() {
        super.atTurnStart();
    }

    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        super.onCardDraw(drawnCard);
        if (drawnCard.type.equals(AbstractCard.CardType.CURSE) ||  drawnCard.type.equals(AbstractCard.CardType.STATUS)) {
            addToBot(new GainBlockAction(AbstractDungeon.player,5));
        }
    }

    public String getUpdatedDescription() {
        JSONObject jsonObject = new JSONObject();
        return GeneralUtils.tiHuan(super.getUpdatedDescription(), jsonObject);
    }

}
