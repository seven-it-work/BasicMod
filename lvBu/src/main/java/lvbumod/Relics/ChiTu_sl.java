package lvbumod.Relics;

import basemod.abstracts.*;
import com.megacrit.cardcrawl.relics.*;
import lvbumod.Actions.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.dungeons.*;
import lvbumod.Helpers.*;

public class ChiTu_sl extends CustomRelic
{
    public static final String ID;
    private static final String IMG_PATH = "lvbuModResources/img/relics/ChiTu_sl.png";
    private static final String IMG_PATH_O = "lvbuModResources/img/relics/ChiTuO_sl.png";
    private static final RelicTier RELIC_TIER;
    private static final LandingSound LANDING_SOUND;
    
    public ChiTu_sl() {
        super(ChiTu_sl.ID, ImageMaster.loadImage("lvbuModResources/img/relics/ChiTu_sl.png"), ImageMaster.loadImage("lvbuModResources/img/relics/ChiTuO_sl.png"), ChiTu_sl.RELIC_TIER, ChiTu_sl.LANDING_SOUND);
    }
    
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
    
    public AbstractRelic makeCopy() {
        return (AbstractRelic)new ChiTu_sl();
    }
    
    public void atBattleStart() {
        this.flash();
        this.addToTop((AbstractGameAction)new GetAngerFromDrawAction(2, true));
    }
    
    protected void initializeTips() {
        super.initializeTips();
        this.tips.add(new PowerTip("\u79ef\u6012", "\u5c06\u724c\u8fd4\u56de\u62bd\u724c\u5806\u5e95\u90e8\u3002\u8fd9\u4e9b\u724c\u4f1a\u6539\u53d8\u989c\u8272\u5e76\u4e14\u5728\u62bd\u724c\u5806\u5904\u6709\u989d\u5916\u5c55\u793a\u3002"));
        this.tips.add(new PowerTip("SL", "\u6307\u4fdd\u5b58\u5e76\u9000\u51fa\u540e\u91cd\u65b0\u8fdb\u5165\u6e38\u620f\u7684\u884c\u4e3a\u3002"));
    }
    
    public void obtain() {
        if (AbstractDungeon.player.hasRelic(LvbuModHelper.MakePath(ChiTu.class.getSimpleName()))) {
            for (int i = 0; i < AbstractDungeon.player.relics.size(); ++i) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals(ChiTu.ID)) {
                    this.instantObtain(AbstractDungeon.player, i, true);
                    break;
                }
            }
        }
        else {
            super.obtain();
        }
    }
    
    static {
        ID = LvbuModHelper.MakePath(ChiTu_sl.class.getSimpleName());
        RELIC_TIER = RelicTier.STARTER;
        LANDING_SOUND = LandingSound.FLAT;
    }
}
