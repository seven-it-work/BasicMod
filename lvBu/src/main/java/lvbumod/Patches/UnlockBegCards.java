package lvbumod.Patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import lvbumod.Helpers.*;
import lvbumod.Cards.Special.*;
import basemod.*;
import basemod.interfaces.*;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.unlock.*;
import java.util.*;
import lvbumod.Skins.*;

@SpireInitializer
public class UnlockBegCards implements PostInitializeSubscriber
{
    private List<String> begCardsList;
    
    public UnlockBegCards() {
        this.begCardsList = Arrays.asList(LvbuModHelper.MakePath(beg1.class.getSimpleName()), LvbuModHelper.MakePath(beg2.class.getSimpleName()), LvbuModHelper.MakePath(beg3.class.getSimpleName()), LvbuModHelper.MakePath(beg4.class.getSimpleName()));
    }
    
    public static void initialize() {
        BaseMod.subscribe((ISubscriber)new UnlockBegCards());
    }
    
    public void receivePostInitialize() {
        for (final AbstractCard c : CardLibrary.getAllCards()) {
            if (this.begCardsList.contains(c.cardID)) {
                final String id = c.cardID;
                UnlockTracker.unlockPref.putInteger(id, 2);
                UnlockTracker.lockedCards.remove(id);
                if (c.isSeen) {
                    continue;
                }
                c.isSeen = true;
                c.unlock();
                UnlockTracker.seenPref.putInteger(id, 1);
            }
        }
        UnlockTracker.seenPref.flush();
        UnlockTracker.unlockPref.flush();
        if (LvbuModHelper.lvbuExtend()) {
            for (final LvbuSkinCharacter c2 : LvbuSelectScreenPatch.characters) {
                c2.reskinUnlockLvbu = true;
            }
            LvbuSkinAll.saveSettings();
        }
    }
}
