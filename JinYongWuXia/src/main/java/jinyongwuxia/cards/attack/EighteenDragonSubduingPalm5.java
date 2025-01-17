package jinyongwuxia.cards.attack;

import jinyongwuxia.JYWXMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.seven.util.CardStats;

public class EighteenDragonSubduingPalm5 extends AbstractEighteenDragonSubduingPalm {
    public static final String ID = JYWXMod.MOD.makeID(EighteenDragonSubduingPalm5.class.getSimpleName());

    private static final CardStats info = new CardStats(CardColor.COLORLESS, CardType.ATTACK, CardRarity.BASIC,
            CardTarget.ENEMY, 1);

    private static final int DAMAGE = 5;

    public EighteenDragonSubduingPalm5() {
        super(ID, info, 5);
        setDamage(DAMAGE, 1);
    }

    @Override
    public AbstractCard makeCopy() {
        return new EighteenDragonSubduingPalm5();
    }

    @Override
    protected void runPlam(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL),
                AbstractGameAction.AttackEffect.SLASH_VERTICAL));

    }

}
