package lvbumod.DynamicVariables;

import basemod.abstracts.*;
import com.megacrit.cardcrawl.cards.*;
import lvbumod.Cards.Abstract.*;

public class LvbuSecondaryMagicVariable extends DynamicVariable
{
    public String key() {
        return "lvbudmg";
    }
    
    public boolean isModified(final AbstractCard card) {
        if (card instanceof LvbuAllCards) {
            final LvbuAllCards asc = (LvbuAllCards)card;
            return asc.isSecondaryMModified;
        }
        return false;
    }
    
    public int value(final AbstractCard card) {
        if (card instanceof LvbuAllCards) {
            final LvbuAllCards asc = (LvbuAllCards)card;
            return asc.secondaryM;
        }
        return 0;
    }
    
    public int baseValue(final AbstractCard card) {
        if (card instanceof LvbuAllCards) {
            final LvbuAllCards asc = (LvbuAllCards)card;
            return asc.secondaryM;
        }
        return 0;
    }
    
    public boolean upgraded(final AbstractCard card) {
        if (card instanceof LvbuAllCards) {
            final LvbuAllCards asc = (LvbuAllCards)card;
            return asc.isSecondaryMModified;
        }
        return false;
    }
}
