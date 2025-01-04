package lvbumod.Powers;

import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.helpers.*;
import com.badlogic.gdx.graphics.g2d.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.core.*;

public class ZhanSanYingPower extends AbstractPower
{
    public static final String POWER_ID;
    private static final PowerStrings powerStrings;
    private static final String NAME;
    private static final String[] DESCRIPTIONS;
    
    public ZhanSanYingPower(final AbstractCreature owner, final int amount) {
        this.name = ZhanSanYingPower.NAME;
        this.ID = ZhanSanYingPower.POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = amount;
        final String path128 = "lvbuModResources/img/powers/ZhanSanYingPower84.png";
        final String path129 = "lvbuModResources/img/powers/ZhanSanYingPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path129), 0, 0, 32, 32);
        this.updateDescription();
    }
    
    public void updateDescription() {
        this.description = ZhanSanYingPower.DESCRIPTIONS[0] + this.amount + ZhanSanYingPower.DESCRIPTIONS[1];
    }
    
    public void atEndOfTurn(final boolean isPlayer) {
        super.atEndOfTurn(isPlayer);
        if (isPlayer && LvbuModHelper.getEnemiesCount() > 0 && AbstractDungeon.player.hasPower(LvbuModHelper.MakePath(MatchlessPower.class.getSimpleName()))) {
            this.addToTop((AbstractGameAction)new GainBlockAction((AbstractCreature)AbstractDungeon.player, this.amount * LvbuModHelper.getEnemiesCount()));
        }
    }
    
    static {
        POWER_ID = LvbuModHelper.MakePath(ZhanSanYingPower.class.getSimpleName());
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(ZhanSanYingPower.POWER_ID);
        NAME = ZhanSanYingPower.powerStrings.NAME;
        DESCRIPTIONS = ZhanSanYingPower.powerStrings.DESCRIPTIONS;
    }
}
