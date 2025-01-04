package lvbumod.Powers;

import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.helpers.*;
import com.badlogic.gdx.graphics.g2d.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.core.*;

public class XiuLuoFormPower extends AbstractPower
{
    public static final String POWER_ID;
    private static final PowerStrings powerStrings;
    private static final String NAME;
    private static final String[] DESCRIPTIONS;
    
    public XiuLuoFormPower(final AbstractCreature owner, final int amount) {
        this.name = XiuLuoFormPower.NAME;
        this.ID = XiuLuoFormPower.POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = amount;
        final String path128 = "lvbuModResources/img/powers/XiuLuoFormPower84.png";
        final String path129 = "lvbuModResources/img/powers/XiuLuoFormPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path129), 0, 0, 32, 32);
        this.updateDescription();
    }
    
    public void atStartOfTurn() {
        super.atStartOfTurn();
        if (this.owner.hasPower(LvbuModHelper.MakePath(MatchlessPower.class.getSimpleName())) && this.owner.hasPower("Strength") && this.owner.getPower("Strength").amount >= 10) {
            this.addToTop((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new LoseStrengthPower(this.owner, 10 * this.amount), 10 * this.amount));
        }
    }
    
    public void onApplyPower(final AbstractPower power, final AbstractCreature target, final AbstractCreature source) {
        super.onApplyPower(power, target, source);
        if (power.ID.equals(LvbuModHelper.MakePath(FightHardPower.class.getSimpleName())) && power.amount > 0) {
            this.addToTop((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new StrengthPower(this.owner, power.amount * this.amount), power.amount * this.amount));
            this.addToTop((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new LoseStrengthPower(this.owner, power.amount * this.amount), power.amount * this.amount));
        }
        if (power.ID.equals(LvbuModHelper.MakePath(FightHardPower.class.getSimpleName())) && power.amount < 0) {
            this.addToTop((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new StrengthPower(this.owner, -power.amount * this.amount), -power.amount * this.amount));
            this.addToTop((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new LoseStrengthPower(this.owner, -power.amount * this.amount), -power.amount * this.amount));
        }
    }
    
    public void updateDescription() {
        if (this.amount == 1) {
            this.description = XiuLuoFormPower.DESCRIPTIONS[0];
        }
        else {
            this.description = XiuLuoFormPower.DESCRIPTIONS[1] + this.amount + XiuLuoFormPower.DESCRIPTIONS[2];
        }
    }
    
    static {
        POWER_ID = LvbuModHelper.MakePath(XiuLuoFormPower.class.getSimpleName());
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(XiuLuoFormPower.POWER_ID);
        NAME = XiuLuoFormPower.powerStrings.NAME;
        DESCRIPTIONS = XiuLuoFormPower.powerStrings.DESCRIPTIONS;
    }
}
