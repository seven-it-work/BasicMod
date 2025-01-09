
package lottery.patches.neow;

import lottery.relics.SanLianLucky;
import lottery.relics.SanLianRelic;

public class SanLianModReward extends LotteryModReward {
    private boolean miniBlessing=false;
    public SanLianModReward(boolean miniBlessing) {
        this.miniBlessing=miniBlessing;
    }

    public String getDescription() {
        return uiStrings.EXTRA_TEXT[0];
    }

    public String getSelectedSpeech() {
        return uiStrings.TEXT[0];
    }

    public void activate() {
        if (miniBlessing){
            (new SanLianLucky()).instantObtain();
        }
        (new SanLianRelic()).instantObtain();
    }
}
