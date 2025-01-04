package org.seven.jywx.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import org.seven.jywx.util.CardStats;

public class EighteenDragonSubduingPalm11 extends AbstractEighteenDragonSubduingPalm {
    public static final String ID = makeID(EighteenDragonSubduingPalm11.class.getSimpleName());

    private static final CardStats info = new CardStats(CardColor.COLORLESS, CardType.ATTACK, CardRarity.BASIC,
        CardTarget.ENEMY, 1);

    private static final int DAMAGE = 10;

    private static final int BASE_MAGIC_NUMBER = 1;

    public EighteenDragonSubduingPalm11() {
        super(ID, info, 11);
        setDamage(DAMAGE, 1);
        setMagic(BASE_MAGIC_NUMBER,1);
        this.exhaust = false;
    }

    @Override
    protected void runPlam(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL),
            AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        this.addToBot(
            new ApplyPowerAction(abstractPlayer, abstractPlayer, new StrengthPower(abstractPlayer, this.magicNumber),
                this.magicNumber));

    }

    @Override
    public AbstractCard makeCopy() {
        return new EighteenDragonSubduingPalm11();
    }

}
