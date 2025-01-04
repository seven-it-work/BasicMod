package lvbumod.Cards.Attack;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import lvbumod.Characters.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.badlogic.gdx.math.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.helpers.*;
import java.util.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;

public class XuanZhuanFeiJi extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 2;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public XuanZhuanFeiJi() {
        super(XuanZhuanFeiJi.ID, false, XuanZhuanFeiJi.CARD_STRINGS, 2, XuanZhuanFeiJi.TYPE, XuanZhuanFeiJi.COLOR, XuanZhuanFeiJi.RARITY, XuanZhuanFeiJi.TARGET);
        this.setupDamage(21);
        this.setupMagicNumber(1);
        this.tags.add(lvbu.Enums.JI_ATTACK_CARD);
        this.isMultiDamage = true;
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(6);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        final ArrayList<Integer> monsterIndex = new ArrayList<Integer>();
        for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); ++i) {
            if (!AbstractDungeon.getCurrRoom().monsters.monsters.get(i).isDeadOrEscaped() && AbstractDungeon.getCurrRoom().monsters.monsters.get(i).currentHealth > 0) {
                monsterIndex.add(i);
            }
        }
        if (monsterIndex.size() > 0) {
            final int monsterIndexI = MathUtils.random(0, monsterIndex.size() - 1);
            final AbstractMonster m2 = AbstractDungeon.getCurrRoom().monsters.monsters.get(monsterIndex.get(monsterIndexI));
            if (m2 != null) {
                this.addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m2, new DamageInfo((AbstractCreature)AbstractDungeon.player, this.multiDamage[monsterIndex.get(monsterIndexI)]), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                this.useJi(m2);
            }
        }
        final UUID uuid12 = this.uuid;
        final int magic = this.magicNumber;
        this.addToBot((AbstractGameAction)new AbstractGameAction() {
            public void update() {
                for (final AbstractCard abstractCard : GetAllInBattleInstances.get(uuid12)) {
                    final AbstractCard c = abstractCard;
                    abstractCard.costForTurn += magic;
                    if (c.cost != c.costForTurn) {
                        c.isCostModified = true;
                    }
                    c.cost = c.costForTurn;
                }
                this.isDone = true;
            }
        });
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new XuanZhuanFeiJi();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(XuanZhuanFeiJi.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(XuanZhuanFeiJi.ID);
        TYPE = CardType.ATTACK;
        RARITY = CardRarity.UNCOMMON;
        TARGET = CardTarget.ALL_ENEMY;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
