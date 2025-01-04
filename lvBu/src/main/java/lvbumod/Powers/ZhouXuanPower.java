package lvbumod.Powers;

import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.helpers.*;
import com.badlogic.gdx.graphics.g2d.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;

public class ZhouXuanPower extends AbstractPower
{
    public static final String POWER_ID;
    private static final PowerStrings powerStrings;
    private static final String NAME;
    private static final String[] DESCRIPTIONS;
    
    public ZhouXuanPower(final AbstractCreature owner, final int amount) {
        this.name = ZhouXuanPower.NAME;
        this.ID = ZhouXuanPower.POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = amount;
        final String path128 = "lvbuModResources/img/powers/ZhouXuanPower84.png";
        final String path129 = "lvbuModResources/img/powers/ZhouXuanPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path129), 0, 0, 32, 32);
        this.updateDescription();
    }
    
    public void updateDescription() {
        this.description = ZhouXuanPower.DESCRIPTIONS[0] + this.amount + ZhouXuanPower.DESCRIPTIONS[1];
    }
    
    static {
        POWER_ID = LvbuModHelper.MakePath(ZhouXuanPower.class.getSimpleName());
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(ZhouXuanPower.POWER_ID);
        NAME = ZhouXuanPower.powerStrings.NAME;
        DESCRIPTIONS = ZhouXuanPower.powerStrings.DESCRIPTIONS;
    }
}
