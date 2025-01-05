package JinYongWuXia.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import JinYongWuXia.cards.BaseCard;
import JinYongWuXia.powers.InfuriatedPower;
import JinYongWuXia.powers.SuoGuPower;
import JinYongWuXia.util.CardStats;
import cn.hutool.core.collection.CollUtil;

import java.util.List;
import java.util.stream.Collectors;

public class ShouJinSuoGuFa extends BaseCard {

    public static final String ID = makeID(ShouJinSuoGuFa.class.getSimpleName());

    private static final CardStats info = new CardStats(CardColor.COLORLESS, CardType.SKILL, CardRarity.BASIC,
        CardTarget.SELF, 1);

    public ShouJinSuoGuFa() {
        super(ID, info);
        setMagic(5, 5);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        int magnification = 1;
        List<AbstractPower> abstractPowers = abstractPlayer.powers.stream()
            .filter(abstractPower -> abstractPower instanceof InfuriatedPower)
            .collect(Collectors.toList());
        if (CollUtil.isNotEmpty(abstractPowers)) {
            AbstractPower abstractPower = abstractPowers.get(0);
            if (abstractPower != null) {
                if (abstractPower.amount >= 2) {
                    this.addToTop(new ReducePowerAction(abstractPlayer, abstractPlayer, InfuriatedPower.ID, 2));
                    magnification = 3;
                }
            }
        }
        this.addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer,
            new SuoGuPower(abstractPlayer, this.magicNumber * magnification), this.magicNumber * magnification));
    }
}
