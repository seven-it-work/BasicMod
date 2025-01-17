package jinyongwuxia.powers;


import jinyongwuxia.JYWXMod;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class InfuriatedPower extends BasePower {
    public static final String ID = JYWXMod.MOD.makeID(InfuriatedPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public InfuriatedPower(AbstractCreature owner, int amount) {
        super(ID, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}
