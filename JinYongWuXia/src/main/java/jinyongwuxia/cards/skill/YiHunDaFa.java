package jinyongwuxia.cards.skill;

import jinyongwuxia.JYWXMod;
import jinyongwuxia.cards.BaseCard;
import jinyongwuxia.powers.InfuriatedPower;
import jinyongwuxia.powers.ShiHunPower;
import cn.hutool.core.collection.CollUtil;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.seven.util.CardStats;

import java.util.List;
import java.util.stream.Collectors;

public class YiHunDaFa extends BaseCard {

    public static final String ID = JYWXMod.MOD.makeID(YiHunDaFa.class.getSimpleName());

    private static final CardStats info = new CardStats(CardColor.COLORLESS, CardType.SKILL, CardRarity.BASIC,
            CardTarget.ENEMY, 2);

    public YiHunDaFa() {
        super(ID, info);
        setMagic(1, 0);
        setCostUpgrade(1);
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return super.canUse(p, m) && AbstractDungeon.player.powers.stream()
                .anyMatch(abstractPower -> abstractPower instanceof InfuriatedPower);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        // 扣除一点真气
        List<AbstractPower> abstractPowers = abstractPlayer.powers.stream()
                .filter(abstractPower -> abstractPower instanceof InfuriatedPower)
                .collect(Collectors.toList());
        if (CollUtil.isNotEmpty(abstractPowers)) {
            AbstractPower abstractPower = abstractPowers.get(0);
            if (abstractPower != null) {
                if (abstractPower.amount >= 1) {
                    this.addToTop(new ReducePowerAction(abstractPlayer, abstractPlayer, InfuriatedPower.ID, 1));
                }
            }
        }
        // 给monster加入 失魂
        this.addToBot(
                new ApplyPowerAction(abstractMonster, abstractPlayer, new ShiHunPower(abstractMonster, this.magicNumber),
                        this.magicNumber));
    }
}
