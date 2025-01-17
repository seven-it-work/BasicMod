package jinyongwuxia.powers;

import jinyongwuxia.JYWXMod;
import com.megacrit.cardcrawl.core.AbstractCreature;
import org.seven.util.QuickStartMod;
import org.seven.util.QuickStartPower;

public abstract class BasePower extends QuickStartPower {

    public BasePower(String id, PowerType powerType, boolean isTurnBased, AbstractCreature owner, int amount) {
        super(id, powerType, isTurnBased, owner, amount);
    }

    public BasePower(String id, PowerType powerType, boolean isTurnBased, AbstractCreature owner, AbstractCreature source, int amount) {
        super(id, powerType, isTurnBased, owner, source, amount);
    }

    public BasePower(String id, PowerType powerType, boolean isTurnBased, AbstractCreature owner, AbstractCreature source, int amount, boolean initDescription) {
        super(id, powerType, isTurnBased, owner, source, amount, initDescription);
    }

    public BasePower(String id, PowerType powerType, boolean isTurnBased, AbstractCreature owner, AbstractCreature source, int amount, boolean initDescription, boolean loadImage) {
        super(id, powerType, isTurnBased, owner, source, amount, initDescription, loadImage);
    }

    @Override
    protected QuickStartMod quickStartMod() {
        return JYWXMod.MOD;
    }
}