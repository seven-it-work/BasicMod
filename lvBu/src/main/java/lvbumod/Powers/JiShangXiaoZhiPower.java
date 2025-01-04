package lvbumod.Powers;

import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.helpers.*;
import com.badlogic.gdx.graphics.g2d.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;

public class JiShangXiaoZhiPower extends AbstractPower
{
    public static final String POWER_ID;
    private static final PowerStrings powerStrings;
    private static final String NAME;
    private static final String[] DESCRIPTIONS;
    
    public JiShangXiaoZhiPower(final AbstractCreature owner) {
        this.name = JiShangXiaoZhiPower.NAME;
        this.ID = JiShangXiaoZhiPower.POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = -1;
        final String path128 = "lvbuModResources/img/powers/JiShangXiaoZhiPower84.png";
        final String path129 = "lvbuModResources/img/powers/JiShangXiaoZhiPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path129), 0, 0, 32, 32);
        this.updateDescription();
    }
    
    public void onDeath() {
        super.onDeath();
    }
    
    public void updateDescription() {
        this.description = JiShangXiaoZhiPower.DESCRIPTIONS[0];
    }
    
    static {
        POWER_ID = LvbuModHelper.MakePath(JiShangXiaoZhiPower.class.getSimpleName());
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(JiShangXiaoZhiPower.POWER_ID);
        NAME = JiShangXiaoZhiPower.powerStrings.NAME;
        DESCRIPTIONS = JiShangXiaoZhiPower.powerStrings.DESCRIPTIONS;
    }
}
