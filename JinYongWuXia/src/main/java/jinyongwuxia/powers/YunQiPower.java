package jinyongwuxia.powers;


import jinyongwuxia.JYWXMod;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import org.seven.util.GeneralUtils;

public class YunQiPower extends BasePower {
    public static final String ID = JYWXMod.MOD.makeID(YunQiPower.class.getSimpleName());

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public YunQiPower(AbstractCreature owner, int amount) {
        super(ID, PowerType.BUFF, true, owner, amount);
    }

    public void atEndOfRound() {
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, ID));
        this.addToBot(
                new ApplyPowerAction(this.owner, this.owner, new InfuriatedPower(this.owner, this.amount), this.amount));
    }

    @Override
    public void updateDescription() {
        description = GeneralUtils.tiHuan(DESCRIPTIONS[0], GeneralUtils.createByKeyValue("amount", this.amount));
    }
}
