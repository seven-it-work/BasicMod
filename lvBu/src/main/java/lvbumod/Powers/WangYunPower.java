package lvbumod.Powers;

import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.helpers.*;
import com.badlogic.gdx.graphics.g2d.*;
import lvbumod.Actions.*;
import com.megacrit.cardcrawl.actions.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;

public class WangYunPower extends AbstractPower
{
    public static final String POWER_ID;
    private static final PowerStrings powerStrings;
    private static final String NAME;
    private static final String[] DESCRIPTIONS;
    
    public WangYunPower(final AbstractCreature owner, final int amount) {
        this.name = WangYunPower.NAME;
        this.ID = WangYunPower.POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = amount;
        final String path128 = "lvbuModResources/img/powers/WangYunPower84.png";
        final String path129 = "lvbuModResources/img/powers/WangYunPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path129), 0, 0, 32, 32);
        this.updateDescription();
    }
    
    public void onSpecificTrigger() {
        super.onSpecificTrigger();
        this.addToBot((AbstractGameAction)new GetAngerFromDrawAction(this.amount));
    }
    
    public void updateDescription() {
        this.description = WangYunPower.DESCRIPTIONS[0] + this.amount + WangYunPower.DESCRIPTIONS[1];
    }
    
    static {
        POWER_ID = LvbuModHelper.MakePath(WangYunPower.class.getSimpleName());
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(WangYunPower.POWER_ID);
        NAME = WangYunPower.powerStrings.NAME;
        DESCRIPTIONS = WangYunPower.powerStrings.DESCRIPTIONS;
    }
}
