package lvbumod.Panels;

import com.megacrit.cardcrawl.ui.panels.*;
import com.megacrit.cardcrawl.localization.*;
import com.badlogic.gdx.graphics.*;
import lvbumod.Patches.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.rooms.*;
import com.megacrit.cardcrawl.helpers.input.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Powers.*;
import com.megacrit.cardcrawl.helpers.*;

public class FightHardPanel extends AbstractPanel
{
    public static float AnimaP_locx;
    public static float AnimaP_locy;
    public static float hide_x;
    public static float shadow_offset;
    public static int totalCount;
    private Hitbox tipHitbox;
    private static final Color ANIMA_TEXT_COLOR;
    public static final String MSG;
    public static final String NAME;
    private static final PowerStrings tutorialStrings;
    private static final int Max_Anima = 999;
    public static Texture blueTexture;
    public static Texture redTexture;
    public static Texture blueTextureF;
    public static Texture redTextureF;
    
    public FightHardPanel() {
        super(FightHardPanel.AnimaP_locx, FightHardPanel.AnimaP_locy, FightHardPanel.hide_x, FightHardPanel.AnimaP_locy, FightHardPanel.shadow_offset, -FightHardPanel.shadow_offset, (Texture)null, true);
        this.tipHitbox = new Hitbox(FightHardPanel.AnimaP_locx, FightHardPanel.AnimaP_locy, 170.0f * Settings.scale, 170.0f * Settings.scale);
    }
    
    public static void setAnima(final int anima) {
        FightHardPanel.totalCount = anima;
        if (FightHardPanel.totalCount > 999) {
            FightHardPanel.totalCount = 999;
        }
    }
    
    public static void increaseAnima(final int e) {
        FightHardPanel.totalCount += e;
        if (FightHardPanel.totalCount > 999) {
            FightHardPanel.totalCount = 999;
        }
    }
    
    public static void decreaseAnima(final int e) {
        FightHardPanel.totalCount -= e;
        if (FightHardPanel.totalCount < 0) {
            FightHardPanel.totalCount = 0;
        }
    }
    
    public static int getAnima() {
        return FightHardPanel.totalCount;
    }
    
    public void updatePositions() {
        super.updatePositions();
        if (!this.isHidden) {
            this.tipHitbox.update();
        }
    }
    
    private BitmapFont getPanelFont() {
        return (BitmapFont)FightHardFontPatch.FightHardFontField.FightHardFont.get();
    }
    
    public void render(final SpriteBatch sb) {
        sb.setColor(Color.WHITE);
        if (!this.isHidden) {
            this.tipHitbox.render(sb);
            final float extendMove = AbstractDungeon.player.drawX / Settings.scale;
            if (FightHardPanel.totalCount >= 10 && AbstractDungeon.player.hasPower(MatchlessPower.POWER_ID)) {
                sb.draw(FightHardPanel.redTextureF, (this.tipHitbox.x + extendMove) * Settings.xScale, this.tipHitbox.y, this.tipHitbox.width, this.tipHitbox.height);
            }
            else if (FightHardPanel.totalCount >= 10 && !AbstractDungeon.player.hasPower(MatchlessPower.POWER_ID)) {
                sb.draw(FightHardPanel.blueTextureF, (this.tipHitbox.x + extendMove) * Settings.xScale, this.tipHitbox.y, this.tipHitbox.width, this.tipHitbox.height);
            }
            else if (FightHardPanel.totalCount < 10 && AbstractDungeon.player.hasPower(MatchlessPower.POWER_ID)) {
                sb.draw(FightHardPanel.redTexture, (this.tipHitbox.x + extendMove) * Settings.xScale, this.tipHitbox.y, this.tipHitbox.width, this.tipHitbox.height);
            }
            else if (FightHardPanel.totalCount < 10 && !AbstractDungeon.player.hasPower(MatchlessPower.POWER_ID)) {
                sb.draw(FightHardPanel.blueTexture, (this.tipHitbox.x + extendMove) * Settings.xScale, this.tipHitbox.y, this.tipHitbox.width, this.tipHitbox.height);
            }
            final String animaMsg = " " + FightHardPanel.totalCount + " ";
            final BitmapFont f = this.getPanelFont();
            FontHelper.renderRotatedText(sb, f, animaMsg, (this.current_x + extendMove) * Settings.xScale, this.current_y, 85.0f * Settings.scale, 65.0f * Settings.scale, 0.0f, false, Color.WHITE);
            if (this.tipHitbox.hovered && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.isScreenUp) {
                TipHelper.renderGenericTip(InputHelper.mX + 60.0f * Settings.scale, InputHelper.mY + 100.0f * Settings.scale, FightHardPanel.NAME, FightHardPanel.MSG);
            }
        }
    }
    
    static {
        FightHardPanel.AnimaP_locx = -90.0f;
        FightHardPanel.AnimaP_locy = 830.0f * Settings.yScale;
        FightHardPanel.hide_x = -480.0f * Settings.scale;
        FightHardPanel.shadow_offset = 12.0f * Settings.scale;
        ANIMA_TEXT_COLOR = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        tutorialStrings = CardCrawlGame.languagePack.getPowerStrings(FightHardPower.POWER_ID);
        MSG = FightHardPanel.tutorialStrings.DESCRIPTIONS[0] + FightHardPanel.totalCount + FightHardPanel.tutorialStrings.DESCRIPTIONS[1] + FightHardPanel.totalCount + FightHardPanel.tutorialStrings.DESCRIPTIONS[2];
        NAME = FightHardPanel.tutorialStrings.NAME;
        FightHardPanel.blueTexture = ImageMaster.loadImage("lvbuModResources/img/UI/Panel/FightHardPanel_blue.png");
        FightHardPanel.redTexture = ImageMaster.loadImage("lvbuModResources/img/UI/Panel/FightHardPanel_red.png");
        FightHardPanel.blueTextureF = ImageMaster.loadImage("lvbuModResources/img/UI/Panel/FightHardPanel_blueFire.png");
        FightHardPanel.redTextureF = ImageMaster.loadImage("lvbuModResources/img/UI/Panel/FightHardPanel_redFire.png");
    }
}
