package basicmod.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;

import basicmod.util.CardStats;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class EighteenDragonSubduingPalm17 extends AbstractEighteenDragonSubduingPalm {
    public static final String ID = makeID(EighteenDragonSubduingPalm17.class.getSimpleName());

    private static final CardStats info = new CardStats(CardColor.COLORLESS, CardType.ATTACK, CardRarity.BASIC,
        CardTarget.ENEMY, 1);

    private static final int DAMAGE = 10;

    private static final int BASE_MAGIC_NUMBER = 2;

    public EighteenDragonSubduingPalm17() {
        super(ID, info, 17);
        setDamage(DAMAGE, 1);
        setMagic(BASE_MAGIC_NUMBER,1);
    }



    @Override
    protected void runPlam(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL),
            AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        if (abstractMonster != null && !abstractMonster.hasPower("Artifact")) {
            this.addToBot(new ApplyPowerAction(abstractMonster, abstractPlayer,
                new StrengthPower(abstractMonster, -this.magicNumber), -this.magicNumber));
        }

    }

    @Override
    public AbstractCard makeCopy() {
        return new EighteenDragonSubduingPalm17();
    }

}
