package lvbumod.Helpers;

import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.cards.*;
import lvbumod.Characters.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.rooms.*;
import com.megacrit.cardcrawl.monsters.*;
import java.util.*;
import lvbumod.Patches.*;
import lvbumod.Powers.*;

public class LvbuModHelper
{
    public static Color LVBU_CARD_BLUE;
    public static Color LVBU_CARD_RED;
    
    public static boolean lvbuExtend() {
        return false;
    }
    
    public static String MakePath(final String id) {
        return "lvbumod:" + id;
    }
    
    public static String getAssetPath(final String skinname, final String filename) {
        if (skinname.equals("")) {
            return "lvbuModResources/img/char/" + filename;
        }
        return "lvbuModResources/img/char/" + skinname + "/" + filename;
    }
    
    public static void setAnger(final AbstractCard c, final boolean b) {
        if (b && !(boolean)AbstractCardHasIsAngerPatch.IsAngerField.isAnger.get(c)) {
            if (c.color == lvbu.Enums.LVBU_CARD) {
                c.color = lvbu.Enums.LVBU_CARD2;
            }
            AbstractCardHasIsAngerPatch.IsAngerField.isAnger.set(c, true);
            return;
        }
        if (!b && AbstractCardHasIsAngerPatch.IsAngerField.isAnger.get(c)) {
            if (c.color == lvbu.Enums.LVBU_CARD2) {
                c.color = lvbu.Enums.LVBU_CARD;
            }
            AbstractCardHasIsAngerPatch.IsAngerField.isAnger.set(c, false);
        }
    }
    
    public static boolean getAnger(final AbstractCard c) {
        return AbstractCardHasIsAngerPatch.IsAngerField.isAnger.get(c);
    }
    
    public static boolean checkThreeEnemies() {
        boolean b = false;
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            int count = 0;
            if (AbstractDungeon.player != null && AbstractDungeon.player.hasPower(MakePath(HuLaoGuanPower.class.getSimpleName()))) {
                return true;
            }
            if (AbstractDungeon.player != null && AbstractDungeon.player.hasPower(MakePath(GuanWaiZhuHouPower.class.getSimpleName()))) {
                count += AbstractDungeon.player.getPower(MakePath(GuanWaiZhuHouPower.class.getSimpleName())).amount;
            }
            for (final AbstractMonster m2 : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!m2.isDeadOrEscaped()) {
                    ++count;
                }
            }
            b = (count >= 3);
        }
        return b;
    }
    
    public static boolean checkHasDad() {
        boolean hasDad = false;
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            for (final AbstractMonster m2 : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!m2.isDeadOrEscaped() && m2.hasPower(MakePath(DadPower.class.getSimpleName()))) {
                    hasDad = true;
                    break;
                }
            }
        }
        return hasDad;
    }
    
    public static int getEnemiesCount() {
        int count = 0;
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            if (AbstractDungeon.player != null && AbstractDungeon.player.hasPower(MakePath(GuanWaiZhuHouPower.class.getSimpleName()))) {
                count += AbstractDungeon.player.getPower(MakePath(GuanWaiZhuHouPower.class.getSimpleName())).amount;
            }
            for (final AbstractMonster m2 : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!m2.isDeadOrEscaped() && m2.currentHealth > 0) {
                    ++count;
                }
            }
            if (AbstractDungeon.player != null && AbstractDungeon.player.hasPower(MakePath(HuLaoGuanPower.class.getSimpleName())) && count < 3) {
                return 3;
            }
        }
        return count;
    }
    
    public static int getFarthestEnemy() {
        final int temp = AbstractDungeon.getCurrRoom().monsters.monsters.size();
        int FarthestIndex = -1;
        float FarthestX = 999.99f;
        boolean hasShield = false;
        boolean hasSpear = false;
        int ShieldI = -1;
        for (int i = 0; i < temp; ++i) {
            final AbstractMonster m = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            if (!m.halfDead && !m.isDying && !m.isEscaping && m.currentHealth > 0) {
                if (FarthestX == 999.99f) {
                    FarthestX = m.drawX;
                    FarthestIndex = i;
                }
                else if (FarthestX < m.drawX) {
                    FarthestX = m.drawX;
                    FarthestIndex = i;
                }
                if (m.id.equals("SpireShield")) {
                    hasShield = true;
                    ShieldI = i;
                }
                if (m.id.equals("SpireSpear")) {
                    hasSpear = true;
                }
            }
        }
        if (!hasSpear && hasShield && ShieldI != -1) {
            return ShieldI;
        }
        return FarthestIndex;
    }
    
    public static boolean checkStupid() {
        if (AbstractDungeon.player.hasPower(MakePath(DeprecatedStupidPower.class.getSimpleName()))) {
            return AbstractDungeon.player.getPower(MakePath(DeprecatedStupidPower.class.getSimpleName())).amount == 0;
        }
        if (AbstractDungeon.player.hasPower(MakePath(ChenGongPower.class.getSimpleName()))) {
            return true;
        }
        boolean hasUse = false;
        if (!AbstractDungeon.actionManager.cardsPlayedThisTurn.isEmpty()) {
            for (int i = 0; i < AbstractDungeon.actionManager.cardsPlayedThisTurn.size() - 1; ++i) {
                if (AbstractDungeon.actionManager.cardsPlayedThisTurn.get(i).type == AbstractCard.CardType.SKILL) {
                    hasUse = true;
                    break;
                }
            }
        }
        return !hasUse;
    }
    
    public static void resetCharacter() {
        if (AbstractDungeon.player.chosenClass.equals(lvbu.Enums.LVBU)) {
            AbstractDungeon.player.img = LvbuSelectScreenPatch.characters[0].skins[LvbuSelectScreenPatch.characters[0].reskinCount].orgLvbu;
        }
    }
    
    public static void glowingBlue() {
        for (final AbstractCard c : AbstractDungeon.player.hand.group) {
            c.glowColor = LvbuModHelper.LVBU_CARD_BLUE.cpy();
        }
        for (final AbstractCard c : AbstractDungeon.player.drawPile.group) {
            c.glowColor = LvbuModHelper.LVBU_CARD_BLUE.cpy();
        }
        for (final AbstractCard c : AbstractDungeon.player.discardPile.group) {
            c.glowColor = LvbuModHelper.LVBU_CARD_BLUE.cpy();
        }
        for (final AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
            c.glowColor = LvbuModHelper.LVBU_CARD_BLUE.cpy();
        }
    }
    
    public static void glowingRed() {
        for (final AbstractCard c : AbstractDungeon.player.hand.group) {
            c.glowColor = LvbuModHelper.LVBU_CARD_RED.cpy();
        }
        for (final AbstractCard c : AbstractDungeon.player.drawPile.group) {
            c.glowColor = LvbuModHelper.LVBU_CARD_RED.cpy();
        }
        for (final AbstractCard c : AbstractDungeon.player.discardPile.group) {
            c.glowColor = LvbuModHelper.LVBU_CARD_RED.cpy();
        }
        for (final AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
            c.glowColor = LvbuModHelper.LVBU_CARD_RED.cpy();
        }
    }
    
    public static void resetLvbuCardColor(final AbstractCard c) {
        if (AbstractDungeon.getCurrRoom().phase != AbstractRoom.RoomPhase.COMBAT) {
            return;
        }
        if (AbstractDungeon.player.hasPower(MakePath(MatchlessPower.class.getSimpleName()))) {
            c.glowColor = LvbuModHelper.LVBU_CARD_RED;
        }
        else {
            c.glowColor = LvbuModHelper.LVBU_CARD_BLUE;
        }
    }
    
    static {
        LvbuModHelper.LVBU_CARD_BLUE = new Color(0.2f, 0.9f, 1.0f, 0.25f);
        LvbuModHelper.LVBU_CARD_RED = new Color(0.8156863f, 0.011764706f, 0.09019608f, 0.25f);
    }
}
