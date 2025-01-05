package JinYongWuXia.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.OnReceivePowerRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;

import JinYongWuXia.JYWXMod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class MyRelic extends BaseRelic implements OnReceivePowerRelic {
    public static final Logger logger = LogManager.getLogger(BaseRelic.class); //Used to output to the console.

    private static final String NAME = "MyRelic";

    public static final String ID = JYWXMod.makeID(NAME);

    private static final RelicTier RARITY = RelicTier.COMMON; //The relic's rarity.

    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.

    public MyRelic() {
        super(ID, NAME, RARITY, SOUND);
    }

    public static List<AbstractPower> createByAbstractPower(AbstractPower abstractPower, AbstractMonster abstractMonster, int count) {
        Class<? extends AbstractPower> aClass = abstractPower.getClass();
        ArrayList<AbstractPower> result = new ArrayList<>();
        if (FrailPower.class.equals(aClass)) {
            result.add(new FrailPower(abstractMonster, count, false));
        }
        if (VulnerablePower.class.equals(aClass)) {
            result.add(new VulnerablePower(abstractMonster, count, false));
        }
        if (WeakPower.class.equals(aClass)) {
            result.add(new WeakPower(abstractMonster, count, false));
        }
        if (StrengthPower.class.equals(aClass)) {
            int tempCount = Math.abs(count);
            result.add(new GainStrengthPower(abstractMonster, tempCount));
            result.add(new StrengthPower(abstractMonster, -tempCount));
        }
        return result;
//        try {
//            Constructor<? extends AbstractPower> constructor = aClass.getConstructor(AbstractCreature.class, int.class);
//            return constructor.newInstance(objects);
//        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
//                 IllegalAccessException e) {
//            logger.error("暂时不支持这个负面影响：{}", aClass.getName());
//            throw new RuntimeException(e);
//        }
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
            AbstractPlayer p = AbstractDungeon.player;
            AbstractDungeon.getMonsters().monsters
                    .forEach(m -> createByAbstractPower(abstractPower, m, abstractPower.amount)
                            .forEach(temp -> addToBot(new ApplyPowerAction(m, p, temp))));
        }
        return true;
    }

    @Override
    public int onReceivePowerStacks(AbstractPower power, AbstractCreature source, int stackAmount) {
        return OnReceivePowerRelic.super.onReceivePowerStacks(power, source, stackAmount);
    }
}
