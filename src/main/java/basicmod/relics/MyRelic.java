package basicmod.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import demoMod.aberration.Aberration;
import demoMod.aberration.characters.LagavulinCharacter;


public class MyRelic extends CustomRelic {
    public static final String ID = Aberration.makeID("MyRelic");
    public static final Texture IMG = new Texture(Aberration.getResourcePath("relics/MyRelic.png"));
    public static final Texture IMG_OUTLINE = new Texture(Aberration.getResourcePath("relics/MyRelic_outline.png"));
    private int previousDebuffCount;

    public MyRelic() {
        super(ID, IMG, IMG_OUTLINE, AbstractRelic.RelicTier.BOSS, AbstractRelic.LandingSound.MAGICAL);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void update() {
        update();
        if (AbstractDungeon.player != null && AbstractDungeon.getCurrMapNode() != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            if (this.previousDebuffCount >= 0 && LagavulinCharacter.gainDebuffCount != this.previousDebuffCount) {
                flash();
                AbstractPlayer p = AbstractDungeon.player;
                addToBot(new RelicAboveCreatureAction(p, this));
                if (AbstractDungeon.cardRandomRng.randomBoolean()) {
                    addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 1)));
                } else {
                    addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, 1)));
                }
            }
            this.previousDebuffCount = LagavulinCharacter.gainDebuffCount;
        }
    }

    public void atBattleStart() {
        this.previousDebuffCount = -1;
    }

}
