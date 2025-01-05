package JinYongWuXia.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BufferPower;

import JinYongWuXia.cards.BaseCard;
import JinYongWuXia.powers.YunQiPower;
import JinYongWuXia.util.CardStats;

public class BiQiMiJue extends BaseCard {
    public static final String ID = makeID(BiQiMiJue.class.getSimpleName());

    private static final CardStats info = new CardStats(CardColor.COLORLESS, CardType.SKILL, CardRarity.BASIC,
        CardTarget.SELF, 2);

    public BiQiMiJue() {
        super(ID, info);
        setMagic(3, 6);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBot(
            new ApplyPowerAction(abstractPlayer, abstractPlayer, new YunQiPower(abstractPlayer, this.magicNumber),
                this.magicNumber));
        this.addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, new BufferPower(abstractPlayer, 1), 1));
    }
}
