package lvbumod.Powers;

import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.helpers.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.dungeons.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;

public class ShaShenPower extends AbstractPower
{
    public static final String POWER_ID;
    private static final PowerStrings powerStrings;
    private static final String NAME;
    private static final String[] DESCRIPTIONS;
    
    public ShaShenPower(final AbstractCreature owner, final int amount) {
        this.name = ShaShenPower.NAME;
        this.ID = ShaShenPower.POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = amount;
        final String path128 = "lvbuModResources/img/powers/ShaShenPower84.png";
        final String path129 = "lvbuModResources/img/powers/ShaShenPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path129), 0, 0, 32, 32);
        this.updateDescription();
    }
    
    public void updateDescription() {
        if (this.amount == 1) {
            this.description = ShaShenPower.DESCRIPTIONS[0];
        }
        else {
            this.description = ShaShenPower.DESCRIPTIONS[1] + this.amount + ShaShenPower.DESCRIPTIONS[2];
        }
    }
    
    public float atDamageGive(final float damage, final DamageInfo.DamageType type) {
        final float amount1 = (float)this.amount;
        if (AbstractDungeon.player.hasPower(LvbuModHelper.MakePath(MatchlessPower.class.getSimpleName())) && type == DamageInfo.DamageType.NORMAL) {
            return damage * (amount1 + 1.0f);
        }
        return damage;
    }
    
    static {
        POWER_ID = LvbuModHelper.MakePath(ShaShenPower.class.getSimpleName());
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(ShaShenPower.POWER_ID);
        NAME = ShaShenPower.powerStrings.NAME;
        DESCRIPTIONS = ShaShenPower.powerStrings.DESCRIPTIONS;
    }
}
