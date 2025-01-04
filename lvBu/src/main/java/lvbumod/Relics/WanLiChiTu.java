package lvbumod.Relics;

import basemod.abstracts.*;
import com.megacrit.cardcrawl.relics.*;
import com.megacrit.cardcrawl.dungeons.*;
import lvbumod.Helpers.*;
import lvbumod.Actions.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.helpers.*;

public class WanLiChiTu extends CustomRelic
{
    public static final String ID;
    private static final String IMG_PATH = "lvbuModResources/img/relics/WanLiChiTu.png";
    private static final String IMG_PATH_O = "lvbuModResources/img/relics/WanLiChiTuO.png";
    private static final RelicTier RELIC_TIER;
    private static final LandingSound LANDING_SOUND;
    private int amount;
    private int start;
    
    public WanLiChiTu() {
        super(WanLiChiTu.ID, ImageMaster.loadImage("lvbuModResources/img/relics/WanLiChiTu.png"), ImageMaster.loadImage("lvbuModResources/img/relics/WanLiChiTuO.png"), WanLiChiTu.RELIC_TIER, WanLiChiTu.LANDING_SOUND);
        this.amount = 2;
        this.start = 0;
    }
    
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
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
    
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(LvbuModHelper.MakePath(ChiTu.class.getSimpleName()));
    }
    
    public AbstractRelic makeCopy() {
        return (AbstractRelic)new WanLiChiTu();
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
    }
    
    static {
        ID = LvbuModHelper.MakePath(WanLiChiTu.class.getSimpleName());
        RELIC_TIER = RelicTier.BOSS;
        LANDING_SOUND = LandingSound.FLAT;
    }
}
