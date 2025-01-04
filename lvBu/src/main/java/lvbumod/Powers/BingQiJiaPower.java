package lvbumod.Powers;

import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.helpers.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.megacrit.cardcrawl.dungeons.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.*;

public class BingQiJiaPower extends AbstractPower
{
    public static final String POWER_ID;
    private static final PowerStrings powerStrings;
    private static final String NAME;
    private static final String[] DESCRIPTIONS;
    
    public BingQiJiaPower(final AbstractCreature owner, final int amount) {
        this.name = BingQiJiaPower.NAME;
        this.ID = BingQiJiaPower.POWER_ID;
        this.owner = owner;
        this.type = PowerType.DEBUFF;
        this.amount = amount;
        final String path128 = "lvbuModResources/img/powers/BingQiJiaPower84.png";
        final String path129 = "lvbuModResources/img/powers/BingQiJiaPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path129), 0, 0, 32, 32);
        this.updateDescription();
    }
    
    public void updateDescription() {
        this.description = BingQiJiaPower.DESCRIPTIONS[0] + this.amount + BingQiJiaPower.DESCRIPTIONS[1];
    }
    
    public void atEndOfTurn(final boolean isPlayer) {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();
            if (AbstractDungeon.player.hasPower(LvbuModHelper.MakePath(FangTianHuaJiPower.class.getSimpleName()))) {
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new FangTianHuaJiPower(this.owner, -this.amount), -this.amount));
            }
            this.addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, (AbstractPower)this));
        }
    }
    
    static {
        POWER_ID = LvbuModHelper.MakePath(BingQiJiaPower.class.getSimpleName());
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(BingQiJiaPower.POWER_ID);
        NAME = BingQiJiaPower.powerStrings.NAME;
        DESCRIPTIONS = BingQiJiaPower.powerStrings.DESCRIPTIONS;
    }
}
