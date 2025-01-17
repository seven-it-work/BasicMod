package jinyongwuxia.powers;


import jinyongwuxia.JYWXMod;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import org.seven.util.GeneralUtils;

public class SuoGuPower extends BasePower {
    public static final String ID = JYWXMod.MOD.makeID(SuoGuPower.class.getSimpleName());

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public SuoGuPower(AbstractCreature owner, int amount) {
        super(ID, PowerType.BUFF, false, owner, amount);
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        if (damageAmount < this.owner.currentHealth && damageAmount > 0 && info.owner != null) {
            this.flash();
            this.addToBot(new GainBlockAction(this.owner, this.owner, this.amount));
            this.addToBot(new ReducePowerAction(this.owner, this.owner, ID, 1));
        }
        return damageAmount;
    }

    @Override
    public void updateDescription() {
        description = GeneralUtils.tiHuan(DESCRIPTIONS[0], GeneralUtils.createByKeyValue("amount", this.amount));
    }
}
