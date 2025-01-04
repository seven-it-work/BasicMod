package lvbumod.Relics;

import basemod.abstracts.*;
import com.evacipated.cardcrawl.mod.stslib.relics.*;
import com.megacrit.cardcrawl.relics.*;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.*;
import java.util.*;
import lvbumod.Helpers.*;

public class TongJing extends CustomRelic implements OnReceivePowerRelic
{
    public static final String ID;
    private static final String IMG_PATH = "lvbuModResources/img/relics/TongJing.png";
    private static final String IMG_PATH_O = "lvbuModResources/img/relics/TongJingO.png";
    private static final RelicTier RELIC_TIER;
    private static final LandingSound LANDING_SOUND;
    private List<String> stringList;
    private static boolean canClean;
    private static int artNum;
    
    public TongJing() {
        super(TongJing.ID, ImageMaster.loadImage("lvbuModResources/img/relics/TongJing.png"), ImageMaster.loadImage("lvbuModResources/img/relics/TongJingO.png"), TongJing.RELIC_TIER, TongJing.LANDING_SOUND);
        this.stringList = Arrays.asList("Weakened", "Frail", "Vulnerable");
    }
    
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
    
    public AbstractRelic makeCopy() {
        return (AbstractRelic)new TongJing();
    }
    
    public boolean onReceivePower(final AbstractPower power, final AbstractCreature abstractCreature) {
        if (power.owner.equals(AbstractDungeon.player)) {
            String nowPower = null;
            boolean washAll = true;
            for (final String powerName : this.stringList) {
                if (AbstractDungeon.player.hasPower(powerName)) {
                    nowPower = powerName;
                    washAll = false;
                    break;
                }
            }
            if (washAll && TongJing.canClean) {
                if (AbstractDungeon.player.hasPower("Artifact")) {
                    TongJing.artNum += AbstractDungeon.player.getPower("Artifact").amount;
                }
                if (power.type == AbstractPower.PowerType.DEBUFF) {
                    if (TongJing.artNum > 0) {
                        --TongJing.artNum;
                    }
                    else if (this.stringList.contains(power.ID)) {
                        for (final String powerName : this.stringList) {
                            if (!powerName.equals(power.ID)) {
                                this.addToBot((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, powerName));
                            }
                        }
                        TongJing.canClean = false;
                    }
                }
            }
            else if (!washAll && this.stringList.contains(power.ID) && !Objects.equals(nowPower, power.ID)) {
                this.addToBot((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, power));
                this.flash();
            }
        }
        this.addToBot((AbstractGameAction)new AbstractGameAction() {
            public void update() {
                TongJing.canClean = (this.isDone = true);
                TongJing.artNum = 0;
            }
        });
        return true;
    }
    
    static {
        ID = LvbuModHelper.MakePath(TongJing.class.getSimpleName());
        RELIC_TIER = RelicTier.COMMON;
        LANDING_SOUND = LandingSound.FLAT;
        TongJing.canClean = true;
        TongJing.artNum = 0;
    }
}
