package lvbumod.Relics;

import basemod.abstracts.*;
import com.megacrit.cardcrawl.relics.*;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.dungeons.*;
import lvbumod.Powers.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.actions.*;
import lvbumod.Helpers.*;

public class XiaoShou extends CustomRelic
{
    public static final String ID;
    private static final String IMG_PATH = "lvbuModResources/img/relics/XiaoShou.png";
    private static final String IMG_PATH_O = "lvbuModResources/img/relics/XiaoShouO.png";
    private static final RelicTier RELIC_TIER;
    private static final LandingSound LANDING_SOUND;
    
    public XiaoShou() {
        super(XiaoShou.ID, ImageMaster.loadImage("lvbuModResources/img/relics/XiaoShou.png"), ImageMaster.loadImage("lvbuModResources/img/relics/XiaoShouO.png"), XiaoShou.RELIC_TIER, XiaoShou.LANDING_SOUND);
    }
    
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
    
    public AbstractRelic makeCopy() {
        return (AbstractRelic)new XiaoShou();
    }
    
    public void onMonsterDeath(final AbstractMonster m) {
        if (this.pulse && !this.grayscale && m.currentHealth == 0 && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();
            this.grayscale = true;
            this.stopPulse();
            this.pulse = false;
            this.addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new GuanWaiZhuHouPower((AbstractCreature)AbstractDungeon.player, 2), 2));
        }
    }
    
    public void atBattleStart() {
        this.beginPulse();
        this.pulse = true;
        this.grayscale = false;
    }
    
    public void onVictory() {
        super.onVictory();
        this.grayscale = false;
        this.stopPulse();
        this.pulse = false;
    }
    
    static {
        ID = LvbuModHelper.MakePath(XiaoShou.class.getSimpleName());
        RELIC_TIER = RelicTier.RARE;
        LANDING_SOUND = LandingSound.FLAT;
    }
}
