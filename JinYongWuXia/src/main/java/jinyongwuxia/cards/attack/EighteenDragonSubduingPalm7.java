package jinyongwuxia.cards.attack;

import jinyongwuxia.JYWXMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.seven.util.CardStats;

public class EighteenDragonSubduingPalm7 extends AbstractEighteenDragonSubduingPalm {
    public static final String ID = JYWXMod.MOD.makeID(EighteenDragonSubduingPalm7.class.getSimpleName());

    private static final CardStats info = new CardStats(CardColor.COLORLESS, CardType.ATTACK, CardRarity.BASIC,
            CardTarget.ENEMY, 1);

    private static final int DAMAGE = 7;

    private static final int BLOCK = 2;

    public EighteenDragonSubduingPalm7() {
        super(ID, info, 7);
        setDamage(DAMAGE, 1);
        setBlock(BLOCK, 1);
    }

    @Override
    public AbstractCard makeCopy() {
        return new EighteenDragonSubduingPalm7();
    }

    @Override
    protected void runPlam(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, damage, DamageInfo.DamageType.NORMAL),
                AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        this.addToBot(new GainBlockAction(abstractPlayer, abstractPlayer, this.block));

    }

}
