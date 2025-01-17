package lottery.relics;

import cn.hutool.json.JSONObject;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import lottery.LotteryMod;
import org.seven.util.QuickStartRelic;
import org.seven.util.GeneralUtils;

public class DaoDanGui extends BaseRelic {
    public static final String ID = LotteryMod.MOD.makeID(DaoDanGui.class.getSimpleName());


    public DaoDanGui() {
        super(ID, DaoDanGui.class.getSimpleName(), RelicTier.SHOP, LandingSound.CLINK);
    }

    @Override
    public void atTurnStart() {
        AbstractCard curse = CardLibrary.getCurse();
        for (int i = 0; i < 3; i++) {
            AbstractDungeon.player.hand.group.add(curse.makeCopy());
        }
    }

    public String getUpdatedDescription() {
        JSONObject jsonObject = new JSONObject();
        return GeneralUtils.tiHuan(super.getUpdatedDescription(), jsonObject);
    }

}
