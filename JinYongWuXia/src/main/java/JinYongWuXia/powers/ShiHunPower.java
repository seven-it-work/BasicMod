package JinYongWuXia.powers;

import static JinYongWuXia.JYWXMod.makeID;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import JinYongWuXia.util.GeneralUtils;

public class ShiHunPower extends BasePower {
    public static final String ID = makeID(ShiHunPower.class.getSimpleName());

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private boolean isItEffective = true;

    public ShiHunPower(AbstractCreature owner, int amount) {
        super(ID, PowerType.DEBUFF, false, owner, amount);
    }

    @Override
    public void atEndOfRound() {
        super.atEndOfRound();
        addToBot(new ReducePowerAction(this.owner, this.owner, this, 1));
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        super.atEndOfTurn(isPlayer);
        isItEffective = true;
    }

    @Override
    public int onAttackToChangeDamage(DamageInfo info, int damageAmount) {
        if (!isItEffective) {
            // 执行过就不会执行了
            return damageAmount;
        }
        isItEffective = false;
        if (info.owner.isPlayer) {
            // 打自己人
            return 0;
        } else {
            // 将这个伤害转移到敌人
            AbstractMonster randomMonster = AbstractDungeon.getRandomMonster();
            addToBot(new DamageAction(randomMonster, info, damageAmount));
            return 0;
        }
    }

    @Override
    public void updateDescription() {
        description = GeneralUtils.tiHuan(DESCRIPTIONS[0], GeneralUtils.createByKeyValue("amount", this.amount));
    }
}
