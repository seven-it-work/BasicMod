package lvbumod.Relics;

import basemod.abstracts.*;
import com.megacrit.cardcrawl.relics.*;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Helpers.*;

public class JinJiaJinPao extends CustomRelic
{
    public static final String ID;
    private static final String IMG_PATH = "lvbuModResources/img/relics/JinJiaJinPao.png";
    private static final String IMG_PATH_O = "lvbuModResources/img/relics/JinJiaJinPaoO.png";
    private static final RelicTier RELIC_TIER;
    private static final LandingSound LANDING_SOUND;
    public static int JinJiaJinPaoAmount;
    
    public JinJiaJinPao() {
        super(JinJiaJinPao.ID, ImageMaster.loadImage("lvbuModResources/img/relics/JinJiaJinPao.png"), ImageMaster.loadImage("lvbuModResources/img/relics/JinJiaJinPaoO.png"), JinJiaJinPao.RELIC_TIER, JinJiaJinPao.LANDING_SOUND);
    }
    
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + JinJiaJinPao.JinJiaJinPaoAmount + this.DESCRIPTIONS[1];
    }
    
    public AbstractRelic makeCopy() {
        return (AbstractRelic)new JinJiaJinPao();
    }
    
    public void onEquip() {
        final EnergyManager energy = AbstractDungeon.player.energy;
        ++energy.energyMaster;
    }
    
    public void onUnequip() {
        final EnergyManager energy = AbstractDungeon.player.energy;
        --energy.energyMaster;
    }
    
    public boolean canSpawn() {
        return AbstractDungeon.floorNum < 27 || Settings.isEndless;
    }
    
    static {
        ID = LvbuModHelper.MakePath(JinJiaJinPao.class.getSimpleName());
        RELIC_TIER = RelicTier.BOSS;
        LANDING_SOUND = LandingSound.FLAT;
        JinJiaJinPao.JinJiaJinPaoAmount = 7;
    }
}
