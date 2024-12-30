package basicmod.cards.attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basicmod.util.CardStats;

public class EighteenDragonSubduingPalm6 extends AbstractEighteenDragonSubduingPalm {
    public static final String ID = makeID(EighteenDragonSubduingPalm6.class.getSimpleName());

    private static final CardStats info = new CardStats(CardColor.RED, CardType.ATTACK, CardRarity.BASIC,
        CardTarget.ENEMY, 1);

    private static final int DAMAGE = 6;

    private static final int BLOCK = 1;

    public EighteenDragonSubduingPalm6() {
        super(ID, info, 6);
        setDamage(DAMAGE, 0);
        setBlock(BLOCK, BLOCK);
        this.exhaust = true;
    }

    @Override
    protected void runPlam(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL),
            AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        this.addToBot(new GainBlockAction(abstractPlayer, abstractPlayer, this.block));

    }

    @Override
    public AbstractCard makeCopy() {
        return new EighteenDragonSubduingPalm6();
    }

}