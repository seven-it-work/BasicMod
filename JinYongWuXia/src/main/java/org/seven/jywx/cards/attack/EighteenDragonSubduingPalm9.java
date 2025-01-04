package org.seven.jywx.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import org.seven.jywx.util.CardStats;

public class EighteenDragonSubduingPalm9 extends AbstractEighteenDragonSubduingPalm {
    public static final String ID = makeID(EighteenDragonSubduingPalm9.class.getSimpleName());

    private static final CardStats info = new CardStats(CardColor.COLORLESS, CardType.ATTACK, CardRarity.BASIC,
        CardTarget.ENEMY, 1);

    private static final int DAMAGE = 9;

    private static final int BLOCK = 4;

    public EighteenDragonSubduingPalm9() {
        super(ID, info, 9);
        setDamage(DAMAGE, 1);
        setBlock(BLOCK, 1);
    }

    @Override
    protected void runPlam(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL),
            AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        this.addToBot(new GainBlockAction(abstractPlayer, abstractPlayer, this.block));

    }

    @Override
    public AbstractCard makeCopy() {
        return new EighteenDragonSubduingPalm9();
    }

}
