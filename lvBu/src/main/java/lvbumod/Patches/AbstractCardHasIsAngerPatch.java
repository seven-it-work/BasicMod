package lvbumod.Patches;

import com.megacrit.cardcrawl.cards.*;
import com.evacipated.cardcrawl.modthespire.lib.*;

public class AbstractCardHasIsAngerPatch
{
    @SpirePatch(clz = AbstractCard.class, method = "<class>")
    public static class IsAngerField
    {
        public static SpireField<Boolean> isAnger;
        
        static {
            IsAngerField.isAnger = (SpireField<Boolean>)new SpireField(() -> false);
        }
    }
}
