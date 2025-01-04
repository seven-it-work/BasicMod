package org.seven.jywx.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import org.seven.jywx.util.CardStats;

public class EighteenDragonSubduingPalm1 extends AbstractEighteenDragonSubduingPalm {
    public static final String ID = makeID(EighteenDragonSubduingPalm1.class.getSimpleName());

    private static final CardStats info = new CardStats(CardColor.COLORLESS, CardType.ATTACK, CardRarity.BASIC,
        CardTarget.ENEMY, 1);

    private static final int DAMAGE = 1;

    public EighteenDragonSubduingPalm1() {
        super(ID, info, 1);
        setDamage(DAMAGE,1);
    }

    @Override
    public AbstractCard makeCopy() {
        return new EighteenDragonSubduingPalm1();
    }

    @Override
    protected void runPlam(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL),
            AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }

}
