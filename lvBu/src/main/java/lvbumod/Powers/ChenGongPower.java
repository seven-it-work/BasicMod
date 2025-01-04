package lvbumod.Powers;

import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.helpers.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;

public class ChenGongPower extends AbstractPower
{
    public static final String POWER_ID;
    private static final PowerStrings powerStrings;
    private static final String NAME;
    private static final String[] DESCRIPTIONS;
    
    public ChenGongPower(final AbstractCreature owner) {
        this.name = ChenGongPower.NAME;
        this.ID = ChenGongPower.POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = -1;
        final String path128 = "lvbuModResources/img/powers/ChenGongPower84.png";
        final String path129 = "lvbuModResources/img/powers/ChenGongPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path129), 0, 0, 32, 32);
        this.updateDescription();
        this.changeStupid();
    }
    
    public void atEndOfTurn(final boolean isPlayer) {
        if (isPlayer) {
            this.addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, (AbstractPower)this));
        }
    }
    
    private void changeStupid() {
        if (this.owner.hasPower(LvbuModHelper.MakePath(DeprecatedStupidPower.class.getSimpleName()))) {
            this.owner.getPower(LvbuModHelper.MakePath(DeprecatedStupidPower.class.getSimpleName())).amount = 0;
            this.owner.getPower(LvbuModHelper.MakePath(DeprecatedStupidPower.class.getSimpleName())).updateDescription();
        }
    }
    
    public void updateDescription() {
        this.description = ChenGongPower.DESCRIPTIONS[0];
    }
    
    static {
        POWER_ID = LvbuModHelper.MakePath(ChenGongPower.class.getSimpleName());
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(ChenGongPower.POWER_ID);
        NAME = ChenGongPower.powerStrings.NAME;
        DESCRIPTIONS = ChenGongPower.powerStrings.DESCRIPTIONS;
    }
}
