package jinyongwuxia.cards.attack;

import jinyongwuxia.JYWXMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import org.seven.util.CardStats;

public class EighteenDragonSubduingPalm13 extends AbstractEighteenDragonSubduingPalm {
    public static final String ID = JYWXMod.MOD.makeID(EighteenDragonSubduingPalm13.class.getSimpleName());

    private static final CardStats info = new CardStats(CardColor.COLORLESS, CardType.ATTACK, CardRarity.BASIC,
            CardTarget.ENEMY, 1);

    private static final int DAMAGE = 10;

    private static final int BASE_MAGIC_NUMBER = 2;

    public EighteenDragonSubduingPalm13() {
        super(ID, info, 13);
        setDamage(DAMAGE, 1);
        setMagic(BASE_MAGIC_NUMBER, 1);
    }

    @Override
    public AbstractCard makeCopy() {
        return new EighteenDragonSubduingPalm13();
    }

    @Override
    protected void runPlam(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL),
                AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        this.addToBot(new ApplyPowerAction(abstractMonster, abstractPlayer,
                new WeakPower(abstractMonster, this.magicNumber, false), this.magicNumber));

    }

}
