package basicmod.relics;

import basemod.ReflectionHacks;
import basemod.abstracts.CustomRelic;
import basicmod.BasicMod;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.util.HashMap;
import java.util.Map;


public class MyRelic extends BaseRelic {
    private static final String NAME = "MyRelic";
    public static final String ID = BasicMod.makeID(NAME);
    private static final RelicTier RARITY = RelicTier.COMMON; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.

    private long previousDebuffCount;
    private Map<String, Integer> debuffMap = new HashMap<>();

    public MyRelic() {
        super(ID, NAME, RARITY, SOUND);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void update() {
        super.update();
        if (AbstractDungeon.player != null && AbstractDungeon.getCurrMapNode() != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            // 这里就是获取当前的状态，但是不一定是玩家的
            try {
                // 当前玩家的负面影响数量
                long sum = AbstractDungeon.player.powers.stream().filter(item -> item.type == AbstractPower.PowerType.DEBUFF).mapToLong(temp -> temp.amount).sum();
                if (sum > this.previousDebuffCount) {
                    if (AbstractDungeon.actionManager.currentAction instanceof ApplyPowerAction) {
                        flash();
                        AbstractPlayer p = AbstractDungeon.player;
                        addToBot(new RelicAboveCreatureAction(p, this));
                        AbstractPower powerToApply = ReflectionHacks.getPrivate(AbstractDungeon.actionManager.currentAction, ApplyPowerAction.class, "powerToApply");
                        if (powerToApply.type == AbstractPower.PowerType.DEBUFF) {
                            AbstractDungeon.getMonsters().monsters.forEach(m -> {
                                addToBot(new ApplyPowerAction(m, p, powerToApply));
                            });
                        }
                    }
                    this.previousDebuffCount = sum;
                }

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public void atBattleStart() {
        this.debuffMap = new HashMap<>();
        this.previousDebuffCount = -1;
    }

}
