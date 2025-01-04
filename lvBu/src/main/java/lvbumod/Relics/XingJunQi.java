package lvbumod.Relics;

import basemod.abstracts.*;
import com.megacrit.cardcrawl.relics.*;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.actions.*;
import java.util.*;
import lvbumod.Helpers.*;

public class XingJunQi extends CustomRelic
{
    public static final String ID;
    private static final String IMG_PATH = "lvbuModResources/img/relics/XingJunQi.png";
    private static final String IMG_PATH_O = "lvbuModResources/img/relics/XingJunQiO.png";
    private static final RelicTier RELIC_TIER;
    private static final LandingSound LANDING_SOUND;
    private int start;
    private List<String> powerList;
    
    public XingJunQi() {
        super(XingJunQi.ID, ImageMaster.loadImage("lvbuModResources/img/relics/XingJunQi.png"), ImageMaster.loadImage("lvbuModResources/img/relics/XingJunQiO.png"), XingJunQi.RELIC_TIER, XingJunQi.LANDING_SOUND);
        this.start = 0;
        this.powerList = Arrays.asList("Strength", "Artifact", "Metallicize", "Plated Armor");
    }
    
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
    
    public AbstractRelic makeCopy() {
        return (AbstractRelic)new XingJunQi();
    }
    
    public void atTurnStartPostDraw() {
        super.atTurnStartPostDraw();
        if (this.start == 0) {
            for (int i = 0; i < this.powerList.size(); ++i) {
                for (final AbstractMonster m3 : AbstractDungeon.getCurrRoom().monsters.monsters) {
                    if (!m3.isDeadOrEscaped() && m3.currentHealth > 0 && m3.hasPower((String)this.powerList.get(i))) {
                        this.addToTop((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)m3, (AbstractCreature)AbstractDungeon.player, (String)this.powerList.get(i)));
                    }
                }
            }
        }
        ++this.start;
    }
    
    public void atBattleStart() {
        this.flash();
        this.start = 0;
        for (int i = 0; i < this.powerList.size(); ++i) {
            for (final AbstractMonster m3 : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!m3.isDeadOrEscaped() && m3.currentHealth > 0 && m3.hasPower((String)this.powerList.get(i))) {
                    this.addToTop((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)m3, (AbstractCreature)AbstractDungeon.player, (String)this.powerList.get(i)));
                }
            }
        }
    }
    
    static {
        ID = LvbuModHelper.MakePath(XingJunQi.class.getSimpleName());
        RELIC_TIER = RelicTier.SHOP;
        LANDING_SOUND = LandingSound.FLAT;
    }
}
