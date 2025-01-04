package lvbumod.Powers;

import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.helpers.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.megacrit.cardcrawl.dungeons.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.core.*;

public class MaShuPower extends AbstractPower
{
    public static final String POWER_ID;
    private static final PowerStrings powerStrings;
    private static final String NAME;
    private static final String[] DESCRIPTIONS;
    
    public MaShuPower(final AbstractCreature owner, final int amount) {
        this.name = MaShuPower.NAME;
        this.ID = MaShuPower.POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = amount;
        if (this.amount >= 999) {
            this.amount = 999;
        }
        final String path128 = "lvbuModResources/img/powers/MaShuPower84.png";
        final String path129 = "lvbuModResources/img/powers/MaShuPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path129), 0, 0, 32, 32);
        this.updateDescription();
    }
    
    public void updateDescription() {
        final StringBuilder sb = new StringBuilder();
        sb.append(MaShuPower.powerStrings.DESCRIPTIONS[0]);
        for (int i = 0; i < this.amount; ++i) {
            sb.append("[E] ");
        }
        sb.append(MaShuPower.powerStrings.DESCRIPTIONS[1]);
        this.description = sb.toString();
    }
    
    public void stackPower(final int stackAmount) {
        super.stackPower(stackAmount);
        if (this.amount >= 999) {
            this.amount = 999;
        }
    }
    
    public void onEnergyRecharge() {
        if (AbstractDungeon.player.hasPower(LvbuModHelper.MakePath(MatchlessPower.class.getSimpleName()))) {
            this.flash();
            AbstractDungeon.player.gainEnergy(this.amount);
            this.addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, (AbstractPower)this));
        }
    }
    
    static {
        POWER_ID = LvbuModHelper.MakePath(MaShuPower.class.getSimpleName());
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(MaShuPower.POWER_ID);
        NAME = MaShuPower.powerStrings.NAME;
        DESCRIPTIONS = MaShuPower.powerStrings.DESCRIPTIONS;
    }
}
