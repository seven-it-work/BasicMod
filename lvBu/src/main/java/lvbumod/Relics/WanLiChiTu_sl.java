package lvbumod.Relics;

import basemod.abstracts.*;
import com.megacrit.cardcrawl.relics.*;
import com.megacrit.cardcrawl.dungeons.*;
import lvbumod.Helpers.*;
import lvbumod.Actions.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.helpers.*;

public class WanLiChiTu_sl extends CustomRelic
{
    public static final String ID;
    private static final String IMG_PATH = "lvbuModResources/img/relics/WanLiChiTu_sl.png";
    private static final String IMG_PATH_O = "lvbuModResources/img/relics/WanLiChiTuO_sl.png";
    private static final RelicTier RELIC_TIER;
    private static final LandingSound LANDING_SOUND;
    private int amount;
    private int start;
    
    public WanLiChiTu_sl() {
        super(WanLiChiTu_sl.ID, ImageMaster.loadImage("lvbuModResources/img/relics/WanLiChiTu_sl.png"), ImageMaster.loadImage("lvbuModResources/img/relics/WanLiChiTuO_sl.png"), WanLiChiTu_sl.RELIC_TIER, WanLiChiTu_sl.LANDING_SOUND);
        this.amount = 2;
        this.start = 0;
    }
    
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
    
    public void obtain() {
        if (AbstractDungeon.player.hasRelic(LvbuModHelper.MakePath(ChiTu_sl.class.getSimpleName()))) {
            for (int i = 0; i < AbstractDungeon.player.relics.size(); ++i) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals(ChiTu_sl.ID)) {
                    this.instantObtain(AbstractDungeon.player, i, true);
                    break;
                }
            }
        }
        else if (AbstractDungeon.player.hasRelic(LvbuModHelper.MakePath(WanLiChiTu.class.getSimpleName()))) {
            for (int i = 0; i < AbstractDungeon.player.relics.size(); ++i) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals(WanLiChiTu.ID)) {
                    this.instantObtain(AbstractDungeon.player, i, true);
                    break;
                }
            }
        }
        else {
            super.obtain();
        }
    }
    
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(LvbuModHelper.MakePath(ChiTu_sl.class.getSimpleName()));
    }
    
    public AbstractRelic makeCopy() {
        return (AbstractRelic)new WanLiChiTu_sl();
    }
    
    public void atTurnStartPostDraw() {
        super.atTurnStartPostDraw();
        if (this.start > 0) {
            this.addToBot((AbstractGameAction)new GetAngerFromDrawAction(this.amount));
        }
        ++this.start;
    }
    
    public void atBattleStart() {
        this.flash();
        this.start = 0;
        this.addToTop((AbstractGameAction)new GetAngerFromDrawAction(this.amount, true));
    }
    
    protected void initializeTips() {
        super.initializeTips();
        this.tips.add(new PowerTip("\u79ef\u6012", "\u5c06\u724c\u8fd4\u56de\u62bd\u724c\u5806\u5e95\u90e8\u3002\u8fd9\u4e9b\u724c\u4f1a\u6539\u53d8\u989c\u8272\u5e76\u4e14\u5728\u62bd\u724c\u5806\u5904\u6709\u989d\u5916\u5c55\u793a\u3002"));
        this.tips.add(new PowerTip("SL", "\u6307\u4fdd\u5b58\u5e76\u9000\u51fa\u540e\u91cd\u65b0\u8fdb\u5165\u6e38\u620f\u7684\u884c\u4e3a\u3002"));
    }
    
    static {
        ID = LvbuModHelper.MakePath(WanLiChiTu_sl.class.getSimpleName());
        RELIC_TIER = RelicTier.BOSS;
        LANDING_SOUND = LandingSound.FLAT;
    }
}
