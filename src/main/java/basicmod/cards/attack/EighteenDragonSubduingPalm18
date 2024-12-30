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

    private static final CardStats info = new CardStats(CardColor.RED, CardType.ATTACK, CardRarity.BASIC,
        CardTarget.ENEMY, 1);

    private static final int DAMAGE = 10;

    public EighteenDragonSubduingPalm18() {
        super(ID, info, 18);
        setDamage(DAMAGE, 0);
        this.exhaust = true;
        this.isRunPrePalm = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
        this.runPlam(p, m);
    }

    @Override
    protected void runPlam(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL),
            AbstractGameAction.AttackEffect.SLASH_VERTICAL));

        List<AbstractPower> abstractPowers = abstractMonster.powers.stream()
            .filter(abstractPower -> abstractPower.type == AbstractPower.PowerType.BUFF)
            .collect(Collectors.toList());
        abstractPowers.forEach(abstractPower -> {
            this.addToBot(new RemoveSpecificPowerAction(abstractMonster, abstractMonster, abstractPower));
        });

    }

    @Override
    public AbstractCard makeCopy() {
        return new EighteenDragonSubduingPalm18();
    }

}
