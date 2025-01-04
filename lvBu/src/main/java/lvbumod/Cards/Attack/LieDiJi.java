package lvbumod.Cards.Attack;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import lvbumod.Characters.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import java.util.*;
import com.megacrit.cardcrawl.dungeons.*;
import lvbumod.Actions.*;
import com.megacrit.cardcrawl.actions.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;

public class LieDiJi extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = -1;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public LieDiJi() {
        super(LieDiJi.ID, false, LieDiJi.CARD_STRINGS, -1, LieDiJi.TYPE, LieDiJi.COLOR, LieDiJi.RARITY, LieDiJi.TARGET);
        this.setupDamage(24);
        this.setupMagicNumber(2);
        this.tags.add(lvbu.Enums.JI_ATTACK_CARD);
        this.isMultiDamage = true;
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(6);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        int targetI = -1;
        final ArrayList<Integer> maxHpMonsterIndexList = new ArrayList<Integer>();
        final int temp = AbstractDungeon.getCurrRoom().monsters.monsters.size();
        final ArrayList<AbstractMonster> MaxHpMonster = new ArrayList<AbstractMonster>();
        for (int i = 0; i < temp; ++i) {
            final AbstractMonster m2 = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            if (!m2.isDeadOrEscaped() && m2.currentHealth > 0) {
                if (MaxHpMonster.size() == 0) {
                    MaxHpMonster.add(m2);
                    maxHpMonsterIndexList.add(i);
                }
                else {
                    final AbstractMonster chosenM = MaxHpMonster.get(0);
                    if (m2.currentHealth > chosenM.currentHealth) {
                        maxHpMonsterIndexList.clear();
                        maxHpMonsterIndexList.add(i);
                        MaxHpMonster.clear();
                        MaxHpMonster.add(m2);
                    }
                    else if (m2.currentHealth == chosenM.currentHealth) {
                        maxHpMonsterIndexList.add(i);
                        MaxHpMonster.add(m2);
                    }
                }
            }
        }
        if (maxHpMonsterIndexList.size() > 1) {
            targetI = maxHpMonsterIndexList.get(AbstractDungeon.cardRandomRng.random(0, maxHpMonsterIndexList.size() - 1));
        }
        else if (maxHpMonsterIndexList.size() == 1) {
            targetI = maxHpMonsterIndexList.get(0);
        }
        if (targetI != -1) {
            this.addToBot((AbstractGameAction)new LieDiJiAction(p, this.multiDamage[targetI], this.damageTypeForTurn, this.freeToPlayOnce, this.energyOnUse, targetI));
        }
        else {
            this.addToBot((AbstractGameAction)new LieDiJiAction(p, 0, this.damageTypeForTurn, this.freeToPlayOnce, this.energyOnUse, targetI));
        }
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new LieDiJi();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(LieDiJi.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(LieDiJi.ID);
        TYPE = CardType.ATTACK;
        RARITY = CardRarity.RARE;
        TARGET = CardTarget.NONE;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
