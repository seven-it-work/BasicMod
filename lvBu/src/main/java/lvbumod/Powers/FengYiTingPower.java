package lvbumod.Powers;

import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.helpers.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.megacrit.cardcrawl.actions.*;
import lvbumod.Helpers.*;
import lvbumod.Actions.*;
import com.megacrit.cardcrawl.core.*;

public class FengYiTingPower extends AbstractPower
{
    public static final String POWER_ID;
    private static final PowerStrings powerStrings;
    private static final String NAME;
    private static final String[] DESCRIPTIONS;
    
    public FengYiTingPower(final AbstractCreature owner, final int amount) {
        this.name = FengYiTingPower.NAME;
        this.ID = FengYiTingPower.POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = amount;
        final String path128 = "lvbuModResources/img/powers/FengYiTingPower84.png";
        final String path129 = "lvbuModResources/img/powers/FengYiTingPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path129), 0, 0, 32, 32);
        this.updateDescription();
    }
    
    public void atStartOfTurnPostDraw() {
        this.addToBot((AbstractGameAction)new FvkAction(this.amount));
        if (LvbuModHelper.checkHasDad()) {
            this.addToBot((AbstractGameAction)new GetAngerFromDrawAction(this.amount));
        }
    }
    
    public void updateDescription() {
        this.description = FengYiTingPower.DESCRIPTIONS[0] + this.amount + FengYiTingPower.DESCRIPTIONS[1];
    }
    
    static {
        POWER_ID = LvbuModHelper.MakePath(FengYiTingPower.class.getSimpleName());
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(FengYiTingPower.POWER_ID);
        NAME = FengYiTingPower.powerStrings.NAME;
        DESCRIPTIONS = FengYiTingPower.powerStrings.DESCRIPTIONS;
    }
}
