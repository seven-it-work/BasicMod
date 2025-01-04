package lvbumod.ui;

import com.megacrit.cardcrawl.vfx.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.core.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.megacrit.cardcrawl.helpers.*;
import lvbumod.Skins.*;

public class ReskinUnlockedTextEffect extends AbstractGameEffect
{
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private static final float TEXT_DURATION = 3.0f;
    private static final float START_Y;
    private static final float TARGET_Y;
    private float y;
    private String msg;
    
    public ReskinUnlockedTextEffect(final int unlockValue) {
        CardCrawlGame.sound.play("UNLOCK_PING");
        this.duration = 3.0f;
        this.startingDuration = 3.0f;
        this.y = ReskinUnlockedTextEffect.START_Y;
        this.color = Settings.GREEN_TEXT_COLOR.cpy();
        this.msg = "ERROR";
        switch (unlockValue) {
            case 0: {
                this.msg = ReskinUnlockedTextEffect.TEXT[0];
                break;
            }
            case 1: {
                this.msg = ReskinUnlockedTextEffect.TEXT[1];
                break;
            }
            case 2: {
                this.msg = ReskinUnlockedTextEffect.TEXT[2];
                break;
            }
            case 3: {
                this.msg = ReskinUnlockedTextEffect.TEXT[3];
                break;
            }
        }
    }
    
    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0f) {
            this.isDone = true;
            this.duration = 0.0f;
        }
        if (this.duration > 2.5f) {
            this.y = Interpolation.elasticIn.apply(ReskinUnlockedTextEffect.TARGET_Y, ReskinUnlockedTextEffect.START_Y, (this.duration - 2.5f) * 2.0f);
            this.color.a = Interpolation.pow2In.apply(1.0f, 0.0f, (this.duration - 2.5f) * 2.0f);
        }
        else if (this.duration < 0.5f) {
            this.color.a = Interpolation.pow2In.apply(0.0f, 1.0f, this.duration * 2.0f);
        }
    }
    
    public void render(final SpriteBatch sb) {
        FontHelper.renderFontCentered(sb, FontHelper.buttonLabelFont, this.msg, Settings.WIDTH / 2.0f, this.y, this.color);
    }
    
    public void dispose() {
    }
    
    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(LvbuSkinAll.makeID("BetaArtUnlockEffect"));
        TEXT = ReskinUnlockedTextEffect.uiStrings.TEXT;
        START_Y = Settings.HEIGHT - 470.0f * Settings.scale;
        TARGET_Y = Settings.HEIGHT - 370.0f * Settings.scale;
    }
}
