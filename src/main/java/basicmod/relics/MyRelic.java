package basicmod.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.OnReceivePowerRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.*;

import basicmod.BasicMod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class MyRelic extends BaseRelic implements OnReceivePowerRelic {
    public static final Logger logger = LogManager.getLogger(BaseRelic.class); //Used to output to the console.

    private static final String NAME = "MyRelic";

    public static final String ID = BasicMod.makeID(NAME);

    private static final RelicTier RARITY = RelicTier.COMMON; //The relic's rarity.

    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.

    public MyRelic() {
        super(ID, NAME, RARITY, SOUND);
    }

    public static AbstractPower createByAbstractPower(AbstractPower abstractPower, Object... objects) {
        Class<? extends AbstractPower> aClass = abstractPower.getClass();
        if (FrailPower.class.equals(aClass)) {
            return new FrailPower((AbstractCreature) objects[0], (int) objects[1], false);
        }
        if (VulnerablePower.class.equals(aClass)) {
            return new VulnerablePower((AbstractCreature) objects[0], (int) objects[1], false);
        }
        if (WeakPower.class.equals(aClass)) {
            return new WeakPower((AbstractCreature) objects[0], (int) objects[1], false);
        }
        if (StrengthPower.class.equals(aClass)) {
            return new GainStrengthPower((AbstractCreature) objects[0], (int) objects[1]);
        }
        try {
            Constructor<? extends AbstractPower> constructor = aClass.getConstructor(AbstractCreature.class, int.class);
            return constructor.newInstance(objects);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            logger.error("暂时不支持这个负面影响：{}", aClass.getName());
            throw new RuntimeException(e);
        }
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void atBattleStart() {
    }

    @Override
    public boolean onReceivePower(AbstractPower abstractPower, AbstractCreature abstractCreature) {
        // 如果是负面影响，则给所有敌人来相同的负面影响
        if (abstractPower.type == AbstractPower.PowerType.DEBUFF) {
            try {
                AbstractPlayer p = AbstractDungeon.player;
                AbstractDungeon.getMonsters().monsters.forEach(m -> {
                    addToBot(new ApplyPowerAction(m, p, createByAbstractPower(abstractPower, m, abstractPower.amount)));
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public int onReceivePowerStacks(AbstractPower power, AbstractCreature source, int stackAmount) {
        return OnReceivePowerRelic.super.onReceivePowerStacks(power, source, stackAmount);
    }
}
