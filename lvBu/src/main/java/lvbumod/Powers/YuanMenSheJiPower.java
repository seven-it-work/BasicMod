package lvbumod.Powers;

import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.helpers.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.cards.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;

public class YuanMenSheJiPower extends AbstractPower
{
    public static final String POWER_ID;
    private static final PowerStrings powerStrings;
    private static final String NAME;
    private static final String[] DESCRIPTIONS;
    
    public YuanMenSheJiPower(final AbstractCreature owner, final int amount) {
        this.name = YuanMenSheJiPower.NAME;
        this.ID = YuanMenSheJiPower.POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = amount;
        final String path128 = "lvbuModResources/img/powers/YuanMenSheJiPower84.png";
        final String path129 = "lvbuModResources/img/powers/YuanMenSheJiPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path129), 0, 0, 32, 32);
        this.updateDescription();
        this.priority = 1;
    }
    
    public void updateDescription() {
        if (this.amount == 1) {
            this.description = YuanMenSheJiPower.DESCRIPTIONS[0];
        }
        else {
            this.description = YuanMenSheJiPower.DESCRIPTIONS[1] + this.amount + YuanMenSheJiPower.DESCRIPTIONS[2];
        }
    }
    
    public void atEndOfRound() {
        super.atEndOfRound();
        this.addToBot((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, (AbstractPower)this, 1));
    }
    
    public int onAttackedToChangeDamage(final DamageInfo info, final int damageAmount) {
        return 0;
    }
    
    static {
        POWER_ID = LvbuModHelper.MakePath(YuanMenSheJiPower.class.getSimpleName());
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(YuanMenSheJiPower.POWER_ID);
        NAME = YuanMenSheJiPower.powerStrings.NAME;
        DESCRIPTIONS = YuanMenSheJiPower.powerStrings.DESCRIPTIONS;
    }
}
