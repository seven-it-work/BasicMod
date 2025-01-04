package lvbumod.Patches;

import com.megacrit.cardcrawl.cards.purple.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import java.util.*;
import com.evacipated.cardcrawl.modthespire.lib.*;

@SpirePatch(clz = BowlingBash.class, method = "use", paramtypez = { AbstractPlayer.class, AbstractMonster.class })
public class BowlingBashPatch
{
    @SpirePrefixPatch
    public static void BowlingBashPatch(final BowlingBash __instance, final AbstractPlayer p, final AbstractMonster m) {
        int newCount = LvbuModHelper.getEnemiesCount();
        for (final AbstractMonster m2 : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!m2.isDeadOrEscaped()) {
                --newCount;
            }
        }
        if (newCount > 0) {
            for (int i = 0; i < newCount; ++i) {
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, __instance.damage, __instance.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            }
        }
    }
}
