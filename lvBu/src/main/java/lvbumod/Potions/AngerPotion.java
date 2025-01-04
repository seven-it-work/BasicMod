package lvbumod.Potions;

import com.megacrit.cardcrawl.potions.*;
import com.megacrit.cardcrawl.localization.*;
import com.badlogic.gdx.graphics.*;
import lvbumod.ModCore.*;
import lvbumod.Helpers.*;
import basemod.*;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.dungeons.*;
import lvbumod.Actions.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.core.*;

public class AngerPotion extends AbstractPotion
{
    public static final String POTION_ID;
    private static final PotionStrings potionStrings;
    
    public AngerPotion() {
        super(AngerPotion.potionStrings.NAME, AngerPotion.POTION_ID, PotionRarity.RARE, PotionSize.SPIKY, PotionEffect.NONE, Color.FIREBRICK, (Color)null, (Color)null);
        this.labOutlineColor = LvbuMod.MY_COLOR2_2;
        this.isThrown = false;
    }
    
    public void initializeData() {
        this.potency = this.getPotency();
        this.description = AngerPotion.potionStrings.DESCRIPTIONS[0];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(TipHelper.capitalize(BaseMod.getKeywordTitle(LvbuModHelper.MakePath("GetAnger"))), BaseMod.getKeywordDescription(LvbuModHelper.MakePath("GetAnger"))));
    }
    
    public void use(final AbstractCreature target) {
        this.addToBot((AbstractGameAction)new GetAngerAllAction(AbstractDungeon.player, true));
    }
    
    public int getPotency(final int ascensionLevel) {
        return 1;
    }
    
    public AbstractPotion makeCopy() {
        return new AngerPotion();
    }
    
    static {
        POTION_ID = LvbuModHelper.MakePath(AngerPotion.class.getSimpleName());
        potionStrings = CardCrawlGame.languagePack.getPotionString(LvbuModHelper.MakePath(AngerPotion.class.getSimpleName()));
    }
}
