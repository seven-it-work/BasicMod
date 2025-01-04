package lvbumod.Actions;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.badlogic.gdx.math.*;
import lvbumod.Monsters.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.actions.common.*;
import java.util.*;

public class NewEnemyAction extends AbstractGameAction
{
    public AbstractPlayer p;
    public AbstractMonster m;
    
    public NewEnemyAction(final AbstractPlayer p, final AbstractMonster m) {
        this.actionType = ActionType.SPECIAL;
        this.p = p;
        this.m = m;
    }
    
    public void update() {
        final int temp = AbstractDungeon.getCurrRoom().monsters.monsters.size();
        int NearbyIndex = -1;
        float NearbyX = -999.99f;
        final List<String> MonsterL = Arrays.asList("Dagger", "BronzeOrb", "TorchHead", "GremlinFat", "GremlinNob", "GremlinThief", "GremlinTsundere", "GremlinWarrior", "GremlinWizard");
        for (int i = 0; i < temp; ++i) {
            final AbstractMonster m = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            if (!m.hasPower("Minion") && (m.currentHealth > 0 || m.id.equals("AwakenedOne") || m.id.equals("SpireSpear")) && !m.id.equals("SpireShield")) {
                if (NearbyX == -999.99f) {
                    NearbyX = m.drawX;
                    NearbyIndex = i;
                }
                else if (NearbyX > m.drawX) {
                    NearbyX = m.drawX;
                    NearbyIndex = i;
                }
            }
        }
        if (AbstractDungeon.player.drawX < NearbyX) {
            this.m.drawX = MathUtils.random(AbstractDungeon.player.drawX + AbstractDungeon.player.hb_w, NearbyX - AbstractDungeon.getCurrRoom().monsters.monsters.get(NearbyIndex).hb_w);
        }
        else {
            this.m.drawX = MathUtils.random(AbstractDungeon.player.drawX - AbstractDungeon.player.hb_w, NearbyX + AbstractDungeon.getCurrRoom().monsters.monsters.get(NearbyIndex).hb_w);
        }
        if (this.m.id.equals(LvbuModHelper.MakePath(JiFromYuanMen.class.getSimpleName()))) {
            this.m.drawY = AbstractDungeon.floorY + MathUtils.random(500.0f, 550.0f) * Settings.yScale;
        }
        else {
            this.m.drawY = AbstractDungeon.floorY + MathUtils.random(0.0f, 450.0f) * Settings.yScale;
        }
        this.m.usePreBattleAction();
        final AbstractMonster tempM = this.m;
        this.addToTop((AbstractGameAction)new AbstractGameAction() {
            public void update() {
                tempM.createIntent();
                this.isDone = true;
            }
        });
        this.addToTop((AbstractGameAction)new SpawnMonsterAction(this.m, true, 0));
        this.isDone = true;
    }
}
