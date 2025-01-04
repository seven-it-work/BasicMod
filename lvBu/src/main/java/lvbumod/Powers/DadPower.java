package lvbumod.Powers;

import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.helpers.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.dungeons.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.core.*;

public class DadPower extends AbstractPower
{
    public static final String POWER_ID;
    private static final PowerStrings powerStrings;
    private static final String NAME;
    private static final String[] DESCRIPTIONS;
    private final int strengthGet = 2;
    
    public DadPower(final AbstractMonster owner) {
        this.name = DadPower.NAME;
        this.ID = DadPower.POWER_ID;
        this.owner = (AbstractCreature)owner;
        this.type = PowerType.BUFF;
        this.amount = -1;
        final String path128 = "lvbuModResources/img/powers/DadPower84.png";
        final String path129 = "lvbuModResources/img/powers/DadPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path129), 0, 0, 32, 32);
        this.updateDescription();
        this.priority = 79;
    }
    
    public int onAttackedToChangeDamage(final DamageInfo info, final int damageAmount) {
        if (damageAmount > 0 && info.type == DamageInfo.DamageType.NORMAL && info.owner != null && info.owner.equals(AbstractDungeon.player)) {
            int extraAmount = 0;
            if (AbstractDungeon.player.hasPower(LvbuModHelper.MakePath(YinJiePower.class.getSimpleName()))) {
                extraAmount += AbstractDungeon.player.getPower(LvbuModHelper.MakePath(YinJiePower.class.getSimpleName())).amount;
            }
            this.addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new HatePower((AbstractMonster)this.owner, damageAmount + extraAmount), damageAmount + extraAmount));
            return 0;
        }
        return damageAmount;
    }
    
    public void updateDescription() {
        this.description = DadPower.powerStrings.DESCRIPTIONS[0];
    }
    
    static {
        POWER_ID = LvbuModHelper.MakePath(DadPower.class.getSimpleName());
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(DadPower.POWER_ID);
        NAME = DadPower.powerStrings.NAME;
        DESCRIPTIONS = DadPower.powerStrings.DESCRIPTIONS;
    }
}
