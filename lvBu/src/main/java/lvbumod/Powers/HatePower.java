package lvbumod.Powers;

import com.megacrit.cardcrawl.powers.*;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.helpers.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.dungeons.*;
import lvbumod.Relics.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.core.*;

public class HatePower extends AbstractPower implements HealthBarRenderPower
{
    public static final String POWER_ID;
    private static final PowerStrings powerStrings;
    private static final String NAME;
    private static final String[] DESCRIPTIONS;
    
    public HatePower(final AbstractMonster owner, final int amount) {
        this.name = HatePower.NAME;
        this.ID = HatePower.POWER_ID;
        this.owner = (AbstractCreature)owner;
        this.amount = amount;
        final String path128 = "lvbuModResources/img/powers/HatePower84.png";
        final String path129 = "lvbuModResources/img/powers/HatePower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path129), 0, 0, 32, 32);
        this.updateDescription();
    }
    
    public void reducePower(final int reduceAmount) {
        this.fontScale = 8.0f;
        this.amount -= reduceAmount;
        this.updateDescription();
        if (this.amount <= 0) {
            this.addToTop((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, (AbstractPower)this));
        }
        if (this.amount <= -999) {
            this.amount = -999;
        }
        if (this.amount >= 999) {
            this.amount = 999;
        }
    }
    
    public void stackPower(final int stackAmount) {
        this.fontScale = 8.0f;
        this.amount += stackAmount;
        this.updateDescription();
        if (this.amount <= 0) {
            this.addToTop((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, (AbstractPower)this));
        }
        if (this.amount >= 999) {
            this.amount = 999;
        }
        if (this.amount <= -999) {
            this.amount = -999;
        }
    }
    
    public void updateDescription() {
        this.description = HatePower.DESCRIPTIONS[0];
        if (this.amount >= 0) {
            this.type = PowerType.DEBUFF;
            this.description = HatePower.DESCRIPTIONS[0];
        }
        else {
            this.type = PowerType.BUFF;
        }
    }
    
    public void onSpecificTrigger() {
        super.onSpecificTrigger();
        if (AbstractDungeon.player.hasRelic(LvbuModHelper.MakePath(JinJiaJinPao.class.getSimpleName()))) {
            if (this.amount <= 5) {
                this.addToTop((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, (AbstractPower)this));
            }
            else {
                this.addToTop((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new HatePower((AbstractMonster)this.owner, -JinJiaJinPao.JinJiaJinPaoAmount), -JinJiaJinPao.JinJiaJinPaoAmount));
            }
        }
    }
    
    public int getHealthBarAmount() {
        return this.amount;
    }
    
    public Color getColor() {
        return new Color(0.9764706f, 0.48235294f, 0.043137256f, 1.0f);
    }
    
    static {
        POWER_ID = LvbuModHelper.MakePath(HatePower.class.getSimpleName());
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(HatePower.POWER_ID);
        NAME = HatePower.powerStrings.NAME;
        DESCRIPTIONS = HatePower.powerStrings.DESCRIPTIONS;
    }
}
