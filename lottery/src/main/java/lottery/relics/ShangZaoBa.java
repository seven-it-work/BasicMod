package lottery.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;

import cn.hutool.json.JSONObject;
import lottery.LotteryMod;
import lottery.actions.PlayCardAction;

import org.seven.util.GeneralUtils;

public class ShangZaoBa extends BaseRelic {
    public static final String ID = LotteryMod.resourcePath.makeID(ShangZaoBa.class.getSimpleName());

    private boolean isPlay = false;

    public ShangZaoBa() {
        super(ID, ShangZaoBa.class.getSimpleName(), RelicTier.SHOP, LandingSound.CLINK);
    }

    @Override
    public void atTurnStart() {
        super.atTurnStart();
        this.isPlay = false;
    }

    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        super.onCardDraw(drawnCard);
        if (!this.isPlay && drawnCard.type.equals(AbstractCard.CardType.CURSE)) {
            addToBot(new PlayCardAction(drawnCard, null, true));
            this.isPlay = true;
        }
    }

    public String getUpdatedDescription() {
        JSONObject jsonObject = new JSONObject();
        return GeneralUtils.tiHuan(super.getUpdatedDescription(), jsonObject);
    }

}
