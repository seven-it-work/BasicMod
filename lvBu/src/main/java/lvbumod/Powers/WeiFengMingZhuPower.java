package lvbumod.Powers;

import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.helpers.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.monsters.*;
import lvbumod.Helpers.*;
import com.badlogic.gdx.math.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.*;
import java.util.*;
import com.megacrit.cardcrawl.core.*;

public class WeiFengMingZhuPower extends AbstractPower
{
    public static final String POWER_ID;
    private static final PowerStrings powerStrings;
    private static final String NAME;
    private static final String[] DESCRIPTIONS;
    
    public WeiFengMingZhuPower(final AbstractCreature owner, final int amount) {
        this.name = WeiFengMingZhuPower.NAME;
        this.ID = WeiFengMingZhuPower.POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = amount;
        final String path128 = "lvbuModResources/img/powers/WeiFengMingZhuPower84.png";
        final String path129 = "lvbuModResources/img/powers/WeiFengMingZhuPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path129), 0, 0, 32, 32);
        this.updateDescription();
    }
    
    public void updateDescription() {
        this.description = WeiFengMingZhuPower.DESCRIPTIONS[0] + this.amount + WeiFengMingZhuPower.DESCRIPTIONS[1];
    }
    
    public void atEndOfTurn(final boolean isPlayer) {
        if (isPlayer) {
            boolean hasDad = false;
            for (final AbstractMonster m2 : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!m2.isDeadOrEscaped() && m2.hasPower(LvbuModHelper.MakePath(DadPower.class.getSimpleName())) && !m2.halfDead) {
                    hasDad = true;
                    break;
                }
            }
            if (!hasDad) {
                final ArrayList<Integer> monsterIndex = new ArrayList<Integer>();
                for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); ++i) {
                    if (!AbstractDungeon.getCurrRoom().monsters.monsters.get(i).isDeadOrEscaped() && AbstractDungeon.getCurrRoom().monsters.monsters.get(i).currentHealth > 0) {
                        monsterIndex.add(i);
                    }
                }
                if (monsterIndex.size() > 0) {
                    for (int i = 0; i < this.amount; ++i) {
                        final int monsterIndexI = MathUtils.random(0, monsterIndex.size() - 1);
                        final AbstractMonster m3 = AbstractDungeon.getCurrRoom().monsters.monsters.get(monsterIndex.get(monsterIndexI));
                        if (m3 == null) {
                            break;
                        }
                        this.addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m3, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new DadPower(m3)));
                        monsterIndex.remove(monsterIndexI);
                        if (monsterIndex.size() == 0) {
                            break;
                        }
                    }
                }
            }
        }
    }
    
    static {
        POWER_ID = LvbuModHelper.MakePath(WeiFengMingZhuPower.class.getSimpleName());
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(WeiFengMingZhuPower.POWER_ID);
        NAME = WeiFengMingZhuPower.powerStrings.NAME;
        DESCRIPTIONS = WeiFengMingZhuPower.powerStrings.DESCRIPTIONS;
    }
}
