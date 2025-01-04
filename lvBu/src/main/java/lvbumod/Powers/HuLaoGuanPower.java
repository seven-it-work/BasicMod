package lvbumod.Powers;

import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.helpers.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;

public class HuLaoGuanPower extends AbstractPower
{
    public static final String POWER_ID;
    private static final PowerStrings powerStrings;
    private static final String NAME;
    private static final String[] DESCRIPTIONS;
    
    public HuLaoGuanPower(final AbstractCreature owner, final int amount) {
        this.name = HuLaoGuanPower.NAME;
        this.ID = HuLaoGuanPower.POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = amount;
        final String path128 = "lvbuModResources/img/powers/HuLaoGuanPower84.png";
        final String path129 = "lvbuModResources/img/powers/HuLaoGuanPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path129), 0, 0, 32, 32);
        this.updateDescription();
    }
    
    public void atStartOfTurn() {
        super.atStartOfTurn();
        this.addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new FightHardPower((AbstractCreature)AbstractDungeon.player, this.amount), this.amount));
    }
    
    public void updateDescription() {
        this.description = HuLaoGuanPower.DESCRIPTIONS[0] + this.amount + HuLaoGuanPower.DESCRIPTIONS[1];
    }
    
    static {
        POWER_ID = LvbuModHelper.MakePath(HuLaoGuanPower.class.getSimpleName());
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(HuLaoGuanPower.POWER_ID);
        NAME = HuLaoGuanPower.powerStrings.NAME;
        DESCRIPTIONS = HuLaoGuanPower.powerStrings.DESCRIPTIONS;
    }
}
