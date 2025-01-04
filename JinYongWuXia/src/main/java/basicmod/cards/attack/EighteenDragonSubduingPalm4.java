package basicmod.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basicmod.util.CardStats;

public class EighteenDragonSubduingPalm4 extends AbstractEighteenDragonSubduingPalm {
    public static final String ID = makeID(EighteenDragonSubduingPalm4.class.getSimpleName());

    private static final CardStats info = new CardStats(CardColor.COLORLESS, CardType.ATTACK, CardRarity.BASIC,
        CardTarget.ENEMY, 1);

    private static final int DAMAGE = 4;

    public EighteenDragonSubduingPalm4() {
        super(ID, info, 4);
        setDamage(DAMAGE, 1);
    }

    @Override
    protected void runPlam(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL),
            AbstractGameAction.AttackEffect.SLASH_VERTICAL));

    }

    @Override
    public AbstractCard makeCopy() {
        return new EighteenDragonSubduingPalm4();
    }

}
