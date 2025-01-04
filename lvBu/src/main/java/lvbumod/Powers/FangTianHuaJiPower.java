package lvbumod.Powers;

import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.helpers.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.megacrit.cardcrawl.dungeons.*;
import lvbumod.Characters.*;
import lvbumod.Helpers.*;
import lvbumod.Patches.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.core.*;

public class FangTianHuaJiPower extends AbstractPower
{
    public static final String POWER_ID;
    private static final PowerStrings powerStrings;
    private static final String NAME;
    private static final String[] DESCRIPTIONS;
    
    public FangTianHuaJiPower(final AbstractCreature owner, final int amount) {
        this.name = FangTianHuaJiPower.NAME;
        this.ID = FangTianHuaJiPower.POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = amount;
        final String path128 = "lvbuModResources/img/powers/FangTianHuaJiPower84.png";
        final String path129 = "lvbuModResources/img/powers/FangTianHuaJiPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path129), 0, 0, 32, 32);
        this.updateDescription();
        if (AbstractDungeon.player.chosenClass.equals((Object)lvbu.Enums.LVBU)) {
            if (AbstractDungeon.player.hasPower(LvbuModHelper.MakePath(MatchlessPower.class.getSimpleName()))) {
                AbstractDungeon.player.img = LvbuSelectScreenPatch.characters[0].skins[LvbuSelectScreenPatch.characters[0].reskinCount].orgLvbuMatchlessJi;
            }
            else {
                AbstractDungeon.player.img = LvbuSelectScreenPatch.characters[0].skins[LvbuSelectScreenPatch.characters[0].reskinCount].orgLvbuJi;
            }
        }
    }
    
    public void onRemove() {
        super.onRemove();
        LvbuModHelper.resetCharacter();
    }
    
    public void updateDescription() {
        if (this.amount >= 0) {
            this.description = FangTianHuaJiPower.DESCRIPTIONS[0] + this.amount + FangTianHuaJiPower.DESCRIPTIONS[1];
            this.type = PowerType.BUFF;
        }
        else {
            this.type = PowerType.DEBUFF;
        }
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
    
    static {
        POWER_ID = LvbuModHelper.MakePath(FangTianHuaJiPower.class.getSimpleName());
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(FangTianHuaJiPower.POWER_ID);
        NAME = FangTianHuaJiPower.powerStrings.NAME;
        DESCRIPTIONS = FangTianHuaJiPower.powerStrings.DESCRIPTIONS;
    }
}
