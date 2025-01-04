package lvbumod.Relics;

import basemod.abstracts.*;
import com.megacrit.cardcrawl.relics.*;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Helpers.*;

public class DianTianDeng extends CustomRelic
{
    public static final String ID;
    private static final String IMG_PATH = "lvbuModResources/img/relics/DianTianDeng.png";
    private static final String IMG_PATH_O = "lvbuModResources/img/relics/DianTianDengO.png";
    private static final RelicTier RELIC_TIER;
    private static final LandingSound LANDING_SOUND;
    public static int DianTianDengHeal;
    
    public DianTianDeng() {
        super(DianTianDeng.ID, ImageMaster.loadImage("lvbuModResources/img/relics/DianTianDeng.png"), ImageMaster.loadImage("lvbuModResources/img/relics/DianTianDengO.png"), DianTianDeng.RELIC_TIER, DianTianDeng.LANDING_SOUND);
    }
    
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + DianTianDeng.DianTianDengHeal + this.DESCRIPTIONS[1];
    }
    
    public AbstractRelic makeCopy() {
        return (AbstractRelic)new DianTianDeng();
    }
    
    public boolean canSpawn() {
        return AbstractDungeon.floorNum < 48 || Settings.isEndless;
    }
    
    static {
        ID = LvbuModHelper.MakePath(DianTianDeng.class.getSimpleName());
        RELIC_TIER = RelicTier.UNCOMMON;
        LANDING_SOUND = LandingSound.FLAT;
        DianTianDeng.DianTianDengHeal = 4;
    }
}
