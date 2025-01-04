package basicmod.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import basicmod.util.CardStats;

import java.util.List;
import java.util.stream.Collectors;

public class EighteenDragonSubduingPalm18 extends AbstractEighteenDragonSubduingPalm {
    public static final String ID = makeID(EighteenDragonSubduingPalm18.class.getSimpleName());

    private static final CardStats info = new CardStats(CardColor.COLORLESS, CardType.ATTACK, CardRarity.BASIC,
            CardTarget.ENEMY, 1);

    private static final int DAMAGE = 10;

    public EighteenDragonSubduingPalm18() {
        super(ID, info, 18);
        setDamage(DAMAGE, 1);
        setMagic(2, 0);
        this.exhaust = true;
        this.isRunPrePalm = true;
    }

    @Override
    protected void runPlam(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (this.upgraded) {
            this.exhaust = false;
            setMagic(this.baseMagicNumber - 1, 0);
        }
        if (this.baseMagicNumber == 0) {
            this.exhaust = true;
        }
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL),
                AbstractGameAction.AttackEffect.SLASH_VERTICAL));

        List<AbstractPower> abstractPowers = abstractMonster.powers.stream()
                .filter(abstractPower -> abstractPower.type == AbstractPower.PowerType.BUFF)
                .collect(Collectors.toList());
        abstractPowers.forEach(abstractPower -> this.addToBot(new RemoveSpecificPowerAction(abstractMonster, abstractMonster, abstractPower)));

    }

    @Override
    public AbstractCard makeCopy() {
        return new EighteenDragonSubduingPalm18();
    }

}
