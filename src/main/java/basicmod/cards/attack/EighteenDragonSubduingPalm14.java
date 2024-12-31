package basicmod.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;

import basicmod.util.CardStats;

public class EighteenDragonSubduingPalm14 extends AbstractEighteenDragonSubduingPalm {
    public static final String ID = makeID(EighteenDragonSubduingPalm14.class.getSimpleName());

    private static final CardStats info = new CardStats(CardColor.RED, CardType.ATTACK, CardRarity.BASIC,
        CardTarget.ENEMY, 1);

    private static final int DAMAGE = 10;

    private static final int BASE_MAGIC_NUMBER = 2;

    public EighteenDragonSubduingPalm14() {
        super(ID, info, 14);
        setDamage(DAMAGE, 0);
        this.baseMagicNumber = BASE_MAGIC_NUMBER;
        this.magicNumber = this.baseMagicNumber;
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
        this.addToBot(new ApplyPowerAction(abstractMonster, abstractPlayer,
            new FrailPower(abstractMonster, this.magicNumber, false), this.magicNumber));

    }

    @Override
    public AbstractCard makeCopy() {
        return new EighteenDragonSubduingPalm14();
    }

}