package lvbumod.Powers;

import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.helpers.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.vfx.*;
import com.megacrit.cardcrawl.vfx.stance.*;
import lvbumod.Characters.*;
import lvbumod.Helpers.*;
import lvbumod.Patches.*;
import com.megacrit.cardcrawl.cards.*;
import lvbumod.Cards.Attack.*;
import java.util.*;

public class MatchlessPower extends AbstractPower
{
    public static final String POWER_ID;
    private static final PowerStrings powerStrings;
    private static final String NAME;
    private static final String[] DESCRIPTIONS;
    protected float particleTimer;
    protected float particleTimer2;
    
    public MatchlessPower(final AbstractCreature owner) {
        this.particleTimer = 0.0f;
        this.particleTimer2 = 0.0f;
        this.name = MatchlessPower.NAME;
        this.ID = MatchlessPower.POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = -1;
        final String path128 = "lvbuModResources/img/powers/MatchlessPower84.png";
        final String path129 = "lvbuModResources/img/powers/MatchlessPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path129), 0, 0, 32, 32);
        this.updateDescription();
        CardCrawlGame.sound.play("STANCE_ENTER_WRATH");
        AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.SCARLET, true));
        AbstractDungeon.effectsQueue.add(new StanceChangeParticleGenerator(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, "Wrath"));
        if (AbstractDungeon.player.chosenClass.equals((Object)lvbu.Enums.LVBU)) {
            if (AbstractDungeon.player.hasPower(LvbuModHelper.MakePath(FangTianHuaJiPower.class.getSimpleName()))) {
                AbstractDungeon.player.img = LvbuSelectScreenPatch.characters[0].skins[LvbuSelectScreenPatch.characters[0].reskinCount].orgLvbuMatchlessJi;
            }
            else {
                AbstractDungeon.player.img = LvbuSelectScreenPatch.characters[0].skins[LvbuSelectScreenPatch.characters[0].reskinCount].orgLvbuMatchless;
            }
        }
        LvbuModHelper.glowingRed();
    }
    
    public void atStartOfTurn() {
        super.atStartOfTurn();
        final ArrayList<AbstractCard> cardsToMove = new ArrayList<AbstractCard>();
        for (final AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c.cardID.equals(LvbuModHelper.MakePath(WuShuangNuJi.class.getSimpleName()))) {
                cardsToMove.add(c);
            }
        }
        for (final AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c.cardID.equals(LvbuModHelper.MakePath(WuShuangNuJi.class.getSimpleName()))) {
                cardsToMove.add(c);
            }
        }
        for (final AbstractCard c : cardsToMove) {
            if (AbstractDungeon.player.drawPile.group.contains(c)) {
                if (AbstractDungeon.player.hand.size() == 10) {
                    AbstractDungeon.player.drawPile.moveToDiscardPile(c);
                    AbstractDungeon.player.createHandIsFullDialog();
                }
                else {
                    AbstractDungeon.player.drawPile.moveToHand(c, AbstractDungeon.player.drawPile);
                }
            }
            else if (AbstractDungeon.player.hand.size() == 10) {
                AbstractDungeon.player.createHandIsFullDialog();
            }
            else {
                AbstractDungeon.player.discardPile.moveToHand(c, AbstractDungeon.player.discardPile);
            }
        }
    }
    
    public void onRemove() {
        super.onRemove();
        if (AbstractDungeon.player.chosenClass.equals((Object)lvbu.Enums.LVBU)) {
            if (AbstractDungeon.player.hasPower(LvbuModHelper.MakePath(FangTianHuaJiPower.class.getSimpleName()))) {
                AbstractDungeon.player.img = LvbuSelectScreenPatch.characters[0].skins[LvbuSelectScreenPatch.characters[0].reskinCount].orgLvbuJi;
            }
            else {
                AbstractDungeon.player.img = LvbuSelectScreenPatch.characters[0].skins[LvbuSelectScreenPatch.characters[0].reskinCount].orgLvbu;
            }
        }
        LvbuModHelper.glowingBlue();
    }
    
    public void updateDescription() {
        this.description = MatchlessPower.powerStrings.DESCRIPTIONS[0];
    }
    
    static {
        POWER_ID = LvbuModHelper.MakePath(MatchlessPower.class.getSimpleName());
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(MatchlessPower.POWER_ID);
        NAME = MatchlessPower.powerStrings.NAME;
        DESCRIPTIONS = MatchlessPower.powerStrings.DESCRIPTIONS;
    }
}
