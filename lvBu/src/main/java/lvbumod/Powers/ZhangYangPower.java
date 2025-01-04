package lvbumod.Powers;

import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.helpers.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.rooms.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.*;

public class ZhangYangPower extends AbstractPower
{
    public static final String POWER_ID;
    private static final PowerStrings powerStrings;
    private static final String NAME;
    private static final String[] DESCRIPTIONS;
    
    public ZhangYangPower(final AbstractCreature owner, final int amount) {
        this.name = ZhangYangPower.NAME;
        this.ID = ZhangYangPower.POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = amount;
        final String path128 = "lvbuModResources/img/powers/ZhangYangPower84.png";
        final String path129 = "lvbuModResources/img/powers/ZhangYangPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path129), 0, 0, 32, 32);
        this.updateDescription();
    }
    
    public void updateDescription() {
        this.description = ZhangYangPower.DESCRIPTIONS[0] + this.amount + ZhangYangPower.DESCRIPTIONS[1];
    }
    
    public void wasHPLost(final DamageInfo info, final int damageAmount) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && damageAmount > 0) {
            this.flash();
            for (int i = 0; i < LvbuModHelper.getEnemiesCount(); ++i) {
                this.addToTop((AbstractGameAction)new DamageRandomEnemyAction(new DamageInfo(this.owner, this.amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
            }
        }
    }
    
    static {
        POWER_ID = LvbuModHelper.MakePath(ZhangYangPower.class.getSimpleName());
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(ZhangYangPower.POWER_ID);
        NAME = ZhangYangPower.powerStrings.NAME;
        DESCRIPTIONS = ZhangYangPower.powerStrings.DESCRIPTIONS;
    }
}
