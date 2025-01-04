package lvbumod.Potions;

import com.megacrit.cardcrawl.potions.*;
import com.megacrit.cardcrawl.localization.*;
import com.badlogic.gdx.graphics.*;
import lvbumod.ModCore.*;
import lvbumod.Helpers.*;
import basemod.*;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.dungeons.*;
import lvbumod.Powers.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.actions.*;
import java.util.*;
import com.badlogic.gdx.math.*;
import lvbumod.Actions.*;
import com.megacrit.cardcrawl.core.*;

public class PitchPotion extends AbstractPotion
{
    public static final String POTION_ID;
    private static final PotionStrings potionStrings;
    
    public PitchPotion() {
        super(PitchPotion.potionStrings.NAME, PitchPotion.POTION_ID, PotionRarity.UNCOMMON, PotionSize.FAIRY, PotionEffect.NONE, Color.PINK, (Color)null, (Color)null);
        this.labOutlineColor = LvbuMod.MY_COLOR2_2;
        this.isThrown = true;
        this.targetRequired = true;
    }
    
    public void initializeData() {
        this.potency = this.getPotency();
        this.description = PitchPotion.potionStrings.DESCRIPTIONS[0] + this.potency + PitchPotion.potionStrings.DESCRIPTIONS[1];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(TipHelper.capitalize(BaseMod.getKeywordTitle(LvbuModHelper.MakePath("AdoptiveFather"))), BaseMod.getKeywordDescription(LvbuModHelper.MakePath("AdoptiveFather"))));
        this.tips.add(new PowerTip(TipHelper.capitalize(BaseMod.getKeywordTitle(LvbuModHelper.MakePath("Fvk"))), BaseMod.getKeywordDescription(LvbuModHelper.MakePath("Fvk"))));
    }
    
    public void use(final AbstractCreature target) {
        final AbstractMonster targetM = (AbstractMonster)target;
        this.addToBot((AbstractGameAction)new ApplyPowerAction(target, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new DadPower(targetM)));
        if (this.potency > 1) {
            final ArrayList<Integer> monsterIndex = new ArrayList<Integer>();
            for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); ++i) {
                if (!AbstractDungeon.getCurrRoom().monsters.monsters.get(i).isDeadOrEscaped() && AbstractDungeon.getCurrRoom().monsters.monsters.get(i).currentHealth > 0 && !AbstractDungeon.getCurrRoom().monsters.monsters.get(i).hasPower(LvbuModHelper.MakePath(DadPower.class.getSimpleName())) && !AbstractDungeon.getCurrRoom().monsters.monsters.get(i).equals(targetM)) {
                    monsterIndex.add(i);
                }
            }
            if (monsterIndex.size() > 0) {
                final int monsterIndexI = MathUtils.random(0, monsterIndex.size() - 1);
                final AbstractMonster m3 = AbstractDungeon.getCurrRoom().monsters.monsters.get(monsterIndex.get(monsterIndexI));
                if (m3 != null) {
                    this.addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m3, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new DadPower(m3)));
                }
            }
        }
        this.addToBot((AbstractGameAction)new FvkAction());
    }
    
    public int getPotency(final int ascensionLevel) {
        return 1;
    }
    
    public AbstractPotion makeCopy() {
        return new PitchPotion();
    }
    
    static {
        POTION_ID = LvbuModHelper.MakePath(PitchPotion.class.getSimpleName());
        potionStrings = CardCrawlGame.languagePack.getPotionString(LvbuModHelper.MakePath(PitchPotion.class.getSimpleName()));
    }
}
