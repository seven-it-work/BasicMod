package basicmod.cards.attack;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basicmod.cards.BaseCard;
import basicmod.util.CardStats;

public abstract class AbstractEighteenDragonSubduingPalm extends BaseCard {

    // 是否执行上一掌
    protected boolean isRunPrePalm = true;

    // 从1开始，所以小标要-1
    protected int index = -1;

    public AbstractEighteenDragonSubduingPalm(String ID, CardStats info, int index) {
        super(ID, info);
        this.index = index;
    }

    private static void runPrePlam(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster,
        AbstractEighteenDragonSubduingPalm current) {
        while (current.isRunPrePalm) {
            AbstractEighteenDragonSubduingPalm preCard = current.getPreCard();
            if (preCard == null) {
                return;
            }
            if (current.upgraded) {
                preCard.upgrade();
            }
            preCard.runPlam(abstractPlayer, abstractMonster);
            current = preCard;
        }
    }

    protected abstract void runPlam(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster);

    private AbstractEighteenDragonSubduingPalm getNextCard() {
        try {
            return EighteenDragonSubduingPalmConstant.ABSTRACT_EIGHTEEN_DRAGON_SUBDUING_PALM_LIST.get(this.index);
        } catch (Exception e) {
            return null;
        }
    }

    private AbstractEighteenDragonSubduingPalm getPreCard() {
        try {
            return EighteenDragonSubduingPalmConstant.ABSTRACT_EIGHTEEN_DRAGON_SUBDUING_PALM_LIST.get(this.index - 2);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractEighteenDragonSubduingPalm nextCard = this.getNextCard();
        if (nextCard != null) {
            if (this.upgraded) {
                nextCard.upgrade();
            }
            this.addToBot(new MakeTempCardInDiscardAction(nextCard, 1));
        }
        this.runPlam(abstractPlayer, abstractMonster);
        AbstractEighteenDragonSubduingPalm current = this;
        runPrePlam(abstractPlayer, abstractMonster, current);
    }
}
