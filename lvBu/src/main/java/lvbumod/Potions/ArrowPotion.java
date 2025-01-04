package lvbumod.Potions;

import com.megacrit.cardcrawl.potions.*;
import com.megacrit.cardcrawl.localization.*;
import com.badlogic.gdx.graphics.*;
import lvbumod.ModCore.*;
import com.megacrit.cardcrawl.helpers.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.*;

public class ArrowPotion extends AbstractPotion
{
    public static final String POTION_ID;
    private static final PotionStrings potionStrings;
    
    public ArrowPotion() {
        super(ArrowPotion.potionStrings.NAME, ArrowPotion.POTION_ID, PotionRarity.COMMON, PotionSize.S, PotionEffect.NONE, Color.BLACK, (Color)null, (Color)null);
        this.labOutlineColor = LvbuMod.MY_COLOR2_2;
        this.isThrown = false;
    }
    
    public void initializeData() {
        this.potency = this.getPotency();
        this.description = ArrowPotion.potionStrings.DESCRIPTIONS[0] + this.potency + ArrowPotion.potionStrings.DESCRIPTIONS[1];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }
    
    public void use(final AbstractCreature target) {
        for (int i = 0; i < LvbuModHelper.getEnemiesCount(); ++i) {
            this.addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)null, DamageInfo.createDamageMatrix(this.potency, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE));
        }
    }
    
    public int getPotency(final int ascensionLevel) {
        return 5;
    }
    
    public AbstractPotion makeCopy() {
        return new ArrowPotion();
    }
    
    static {
        POTION_ID = LvbuModHelper.MakePath(ArrowPotion.class.getSimpleName());
        potionStrings = CardCrawlGame.languagePack.getPotionString(LvbuModHelper.MakePath(ArrowPotion.class.getSimpleName()));
    }
}
