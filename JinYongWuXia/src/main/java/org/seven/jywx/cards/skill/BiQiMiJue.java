package org.seven.jywx.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BufferPower;

import org.seven.jywx.cards.BaseCard;
import org.seven.jywx.powers.YunQiPower;
import org.seven.jywx.util.CardStats;

public class BiQiMiJue extends BaseCard {
    public static final String ID = makeID(BiQiMiJue.class.getSimpleName());

    private static final CardStats info = new CardStats(CardColor.COLORLESS, CardType.SKILL, CardRarity.BASIC,
        CardTarget.SELF, 2);

    public BiQiMiJue() {
        super(ID, info);
        setMagic(3, 6);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(
            new ApplyPowerAction(abstractPlayer, abstractPlayer, new YunQiPower(abstractPlayer, this.magicNumber),
                this.magicNumber));
        this.addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new BufferPower(abstractPlayer, 1), 1));
    }
}
