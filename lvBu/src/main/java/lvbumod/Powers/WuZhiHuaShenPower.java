package lvbumod.Powers;

import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.helpers.*;
import com.badlogic.gdx.graphics.g2d.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;

public class WuZhiHuaShenPower extends AbstractPower
{
    public static final String POWER_ID;
    private static final PowerStrings powerStrings;
    private static final String NAME;
    private static final String[] DESCRIPTIONS;
    
    public WuZhiHuaShenPower(final AbstractCreature owner, final int amount) {
        this.name = WuZhiHuaShenPower.NAME;
        this.ID = WuZhiHuaShenPower.POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = amount;
        final String path128 = "lvbuModResources/img/powers/WuZhiHuaShenPower84.png";
        final String path129 = "lvbuModResources/img/powers/WuZhiHuaShenPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path129), 0, 0, 32, 32);
        this.updateDescription();
    }
    
    public void updateDescription() {
        this.description = WuZhiHuaShenPower.DESCRIPTIONS[0] + this.amount + WuZhiHuaShenPower.DESCRIPTIONS[1];
    }
    
    static {
        POWER_ID = LvbuModHelper.MakePath(WuZhiHuaShenPower.class.getSimpleName());
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(WuZhiHuaShenPower.POWER_ID);
        NAME = WuZhiHuaShenPower.powerStrings.NAME;
        DESCRIPTIONS = WuZhiHuaShenPower.powerStrings.DESCRIPTIONS;
    }
}
