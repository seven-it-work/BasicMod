package lvbumod.Monsters;

import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.badlogic.gdx.math.*;
import com.esotericsoftware.spine.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.actions.common.*;
import lvbumod.Powers.*;
import java.util.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;

public class JiFromYuanMen extends AbstractMonster
{
    public static final String ID;
    private static final MonsterStrings monsterStrings;
    public static final String NAME;
    public static final String[] MOVES;
    public static final String[] DIALOG;
    public static final int HP_MIN = 1;
    public static final int HP_MAX = 1;
    public static final int A_2_HP_MIN = 1;
    public static final int A_2_HP_MAX = 3;
    public static final int TACKLE_DAMAGE = 0;
    public static final int A_2_TACKLE_DAMAGE = 0;
    private static final byte TACKLE = 1;
    
    public JiFromYuanMen(final float x, final float y, final int poisonAmount) {
        super(JiFromYuanMen.NAME, JiFromYuanMen.ID, 1, 0.0f, -24.0f, 130.0f, 100.0f, (String)null, x, y);
        this.setHp(1, 1);
        this.damage.add(new DamageInfo((AbstractCreature)this, 0));
        if (poisonAmount >= 1) {
            this.powers.add(new PoisonPower((AbstractCreature)this, (AbstractCreature)this, poisonAmount));
        }
        this.loadAnimation("images/monsters/theForest/mage_dagger/skeleton.atlas", "images/monsters/theForest/mage_dagger/skeleton.json", 1.0f);
        final AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
    }
    
    public void usePreBattleAction() {
        this.addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new JiShangXiaoZhiPower((AbstractCreature)this)));
        this.addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)this, -999)));
    }
    
    public void takeTurn() {
        switch (this.nextMove) {
            case 1: {
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction((AbstractMonster)this));
                break;
            }
        }
    }
    
    protected void getMove(final int num) {
        this.setMove((byte)1, Intent.ATTACK, this.damage.get(0).base);
    }
    
    public void die() {
        super.die();
        for (final AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!m.isDeadOrEscaped() && m.currentHealth > 0) {
                this.addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)this, (AbstractPower)new YuanMenSheJiPower((AbstractCreature)m, 1), 1));
            }
        }
        this.addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new YuanMenSheJiPower((AbstractCreature)AbstractDungeon.player, 1), 1));
    }
    
    static {
        ID = LvbuModHelper.MakePath(JiFromYuanMen.class.getSimpleName());
        monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(JiFromYuanMen.ID);
        NAME = JiFromYuanMen.monsterStrings.NAME;
        MOVES = JiFromYuanMen.monsterStrings.MOVES;
        DIALOG = JiFromYuanMen.monsterStrings.DIALOG;
    }
}
