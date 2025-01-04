package lvbumod.Powers;

import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.helpers.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.cards.*;
import java.util.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;

public class ShanZhanWuQianPower extends AbstractPower
{
    public static final String POWER_ID;
    private static final PowerStrings powerStrings;
    private static final String NAME;
    private static final String[] DESCRIPTIONS;
    
    public ShanZhanWuQianPower(final AbstractCreature owner, final int amount) {
        this.name = ShanZhanWuQianPower.NAME;
        this.ID = ShanZhanWuQianPower.POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = amount;
        final String path128 = "lvbuModResources/img/powers/ShanZhanWuQianPower84.png";
        final String path129 = "lvbuModResources/img/powers/ShanZhanWuQianPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path129), 0, 0, 32, 32);
        this.updateDescription();
    }
    
    public void atStartOfTurn() {
        this.addToBot((AbstractGameAction)new GainEnergyAction(this.amount));
        this.flash();
    }
    
    public void onSpecificTrigger() {
        super.onSpecificTrigger();
        this.flash();
        for (final AbstractCard c : AbstractDungeon.player.hand.group) {
            if (!c.isEthereal) {
                c.retain = true;
            }
        }
    }
    
    public void updateDescription() {
        final StringBuilder sb = new StringBuilder();
        sb.append(ShanZhanWuQianPower.powerStrings.DESCRIPTIONS[0]);
        for (int i = 0; i < this.amount; ++i) {
            sb.append("[E] ");
        }
        sb.append(ShanZhanWuQianPower.powerStrings.DESCRIPTIONS[1]);
        this.description = sb.toString();
    }
    
    static {
        POWER_ID = LvbuModHelper.MakePath(ShanZhanWuQianPower.class.getSimpleName());
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(ShanZhanWuQianPower.POWER_ID);
        NAME = ShanZhanWuQianPower.powerStrings.NAME;
        DESCRIPTIONS = ShanZhanWuQianPower.powerStrings.DESCRIPTIONS;
    }
}
