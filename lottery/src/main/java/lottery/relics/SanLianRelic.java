package lottery.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import lottery.LotteryMod;

import java.util.ArrayList;
import java.util.List;

public class SanLianRelic extends BaseRelic {
    public static final String ID = LotteryMod.resourcePath.makeID(SanLianRelic.class.getSimpleName());
    public static List<AbstractRelic> THIS_BATTLE_ADD_RELICS = new ArrayList<>();

    public SanLianRelic() {
        super(ID, SanLianRelic.class.getSimpleName(), RelicTier.STARTER, LandingSound.CLINK);
    }

    @Override
    public void atBattleStart() {
        THIS_BATTLE_ADD_RELICS.clear();
    }

    @Override
    public void justEnteredRoom(AbstractRoom room) {
        super.justEnteredRoom(room);
        // 移除上次加载的遗物
        // 清空list
        THIS_BATTLE_ADD_RELICS.clear();
        System.out.println("justEnteredRoom");
    }

    @Override
    public void onRefreshHand() {
        super.onRefreshHand();
        System.out.println("onRefreshHand");
    }
}
