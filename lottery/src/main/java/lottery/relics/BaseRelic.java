package lottery.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import lottery.LotteryMod;
import org.seven.util.QuickStartMod;
import org.seven.util.QuickStartRelic;


public abstract class BaseRelic extends QuickStartRelic {

    public BaseRelic(String id, String imageName, AbstractCard.CardColor pool, RelicTier tier, LandingSound sfx) {
        super(id, imageName, pool, tier, sfx);
    }

    public BaseRelic(String id, RelicTier tier, LandingSound sfx) {
        super(id, tier, sfx);
    }

    public BaseRelic(String id, String imageName, RelicTier tier, LandingSound sfx) {
        super(id, imageName, tier, sfx);
    }

    @Override
    protected QuickStartMod quickStartMod() {
        return LotteryMod.MOD;
    }
}
