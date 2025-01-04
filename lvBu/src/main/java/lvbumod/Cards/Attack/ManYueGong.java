package lvbumod.Cards.Attack;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class ManYueGong extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 1;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public ManYueGong() {
        super(ManYueGong.ID, false, ManYueGong.CARD_STRINGS, 1, ManYueGong.TYPE, ManYueGong.COLOR, ManYueGong.RARITY, ManYueGong.TARGET);
        this.setupDamage(7);
        this.isMultiDamage = true;
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(3);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        final int i = LvbuModHelper.getFarthestEnemy();
        if (i != -1) {
            final AbstractMonster mo = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            if (LvbuModHelper.getEnemiesCount() > 1) {
                this.addToBot((AbstractGameAction)new DamageAction((AbstractCreature)mo, new DamageInfo((AbstractCreature)AbstractDungeon.player, this.multiDamage[i] * ((LvbuModHelper.getEnemiesCount() == 0) ? 1 : LvbuModHelper.getEnemiesCount())), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            }
            else {
                this.addToBot((AbstractGameAction)new DamageAction((AbstractCreature)mo, new DamageInfo((AbstractCreature)AbstractDungeon.player, this.multiDamage[i] * ((LvbuModHelper.getEnemiesCount() == 0) ? 1 : LvbuModHelper.getEnemiesCount())), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
            }
        }
    }
    
    public void calculateCardDamage(final AbstractMonster mo) {
        super.calculateCardDamage(mo);
        this.damage *= ((LvbuModHelper.getEnemiesCount() == 0) ? 1 : LvbuModHelper.getEnemiesCount());
        this.isDamageModified = (this.damage != this.baseDamage);
        this.initializeDescription();
    }
    
    public void applyPowers() {
        super.applyPowers();
        this.damage *= ((LvbuModHelper.getEnemiesCount() == 0) ? 1 : LvbuModHelper.getEnemiesCount());
        this.isDamageModified = (this.damage != this.baseDamage);
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new ManYueGong();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(ManYueGong.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ManYueGong.ID);
        TYPE = CardType.ATTACK;
        RARITY = CardRarity.COMMON;
        TARGET = CardTarget.NONE;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
