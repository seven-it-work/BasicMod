package lvbumod.Powers;

import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.helpers.*;
import com.badlogic.gdx.graphics.g2d.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;

public class ShenWeiPower extends AbstractPower
{
    public static final String POWER_ID;
    private static final PowerStrings powerStrings;
    private static final String NAME;
    private static final String[] DESCRIPTIONS;
    
    public ShenWeiPower(final AbstractCreature owner, final int amount) {
        this.name = ShenWeiPower.NAME;
        this.ID = ShenWeiPower.POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = amount;
        final String path128 = "lvbuModResources/img/powers/ShenWeiPower84.png";
        final String path129 = "lvbuModResources/img/powers/ShenWeiPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path129), 0, 0, 32, 32);
        this.updateDescription();
    }
    
    public void updateDescription() {
        this.description = ShenWeiPower.DESCRIPTIONS[0] + this.amount + ShenWeiPower.DESCRIPTIONS[1] + this.amount + ShenWeiPower.DESCRIPTIONS[2];
    }
    
    static {
        POWER_ID = LvbuModHelper.MakePath(ShenWeiPower.class.getSimpleName());
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(ShenWeiPower.POWER_ID);
        NAME = ShenWeiPower.powerStrings.NAME;
        DESCRIPTIONS = ShenWeiPower.powerStrings.DESCRIPTIONS;
    }
}
