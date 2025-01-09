package lottery.relics;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import cn.hutool.json.JSONObject;
import lottery.LotteryMod;

import org.seven.util.GeneralUtils;

public class JinShenHuTi extends BaseRelic {
    public static final String ID = LotteryMod.resourcePath.makeID(JinShenHuTi.class.getSimpleName());

    public JinShenHuTi() {
        super(ID, JinShenHuTi.class.getSimpleName(), RelicTier.SHOP, LandingSound.CLINK);
    }

    @Override
    public void onPlayerEndTurn() {
        AbstractPlayer player = AbstractDungeon.player;
        long count = player.drawPile.group.stream()
            .filter(abstractCard -> abstractCard.type.equals(AbstractCard.CardType.CURSE))
            .count();
        if (count > 0) {
            addToBot(new GainBlockAction(player, (int) count));
        }
    }

    public String getUpdatedDescription() {
        JSONObject jsonObject = new JSONObject();
        return GeneralUtils.tiHuan(super.getUpdatedDescription(), jsonObject);
    }

}
