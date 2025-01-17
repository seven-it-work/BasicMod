package jinyongwuxia.cards;

import jinyongwuxia.JYWXMod;
import org.seven.util.CardStats;
import org.seven.util.QuickStartCard;
import org.seven.util.QuickStartMod;


public abstract class BaseCard extends QuickStartCard {
    public BaseCard(String ID, CardStats info) {
        super(ID, info, JYWXMod.MOD);
    }

    public BaseCard(String ID, CardStats info, String cardImage) {
        super(ID, info, cardImage);
    }

    public BaseCard(String ID, int cost, CardType cardType, CardTarget target, CardRarity rarity, CardColor color) {
        super(ID, cost, cardType, target, rarity, color, JYWXMod.MOD);
    }

    public BaseCard(String ID, int cost, CardType cardType, CardTarget target, CardRarity rarity, CardColor color, String cardImage) {
        super(ID, cost, cardType, target, rarity, color, cardImage);
    }

    @Override
    protected QuickStartMod quickStartMod() {
        return JYWXMod.MOD;
    }

}