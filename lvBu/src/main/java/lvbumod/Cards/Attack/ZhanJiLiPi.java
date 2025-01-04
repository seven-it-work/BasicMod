package lvbumod.Cards.Attack;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import lvbumod.Characters.*;
import com.megacrit.cardcrawl.dungeons.*;
import java.util.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.actions.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;

public class ZhanJiLiPi extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 2;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public ZhanJiLiPi() {
        super(ZhanJiLiPi.ID, false, ZhanJiLiPi.CARD_STRINGS, 2, ZhanJiLiPi.TYPE, ZhanJiLiPi.COLOR, ZhanJiLiPi.RARITY, ZhanJiLiPi.TARGET);
        this.setupDamage(10);
        this.setupMagicNumber(5);
        this.tags.add(lvbu.Enums.JI_ATTACK_CARD);
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeMagicNumber(3);
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new ZhanJiLiPi();
        return c;
    }
    
    public int countCards() {
        int count = 0;
        for (final AbstractCard c : AbstractDungeon.player.hand.group) {
            if (isJi(c) && c.uuid != this.uuid) {
                ++count;
            }
        }
        for (final AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (isJi(c) && c.uuid != this.uuid) {
                ++count;
            }
        }
        for (final AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (isJi(c) && c.uuid != this.uuid) {
                ++count;
            }
        }
        return count;
    }
    
    public static boolean isJi(final AbstractCard c) {
        return c.hasTag(lvbu.Enums.JI_ATTACK_CARD);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.damageToEnemy(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        this.useJi(m);
    }
    
    public void calculateCardDamage(final AbstractMonster mo) {
        final int realBaseDamage = this.baseDamage;
        this.baseDamage += this.magicNumber * (this.countCards() + 1);
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = (this.damage != this.baseDamage);
    }
    
    public void applyPowers() {
        final int realBaseDamage = this.baseDamage;
        this.baseDamage += this.magicNumber * (this.countCards() + 1);
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = (this.damage != this.baseDamage);
    }
    
    static {
        ID = LvbuModHelper.MakePath(ZhanJiLiPi.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ZhanJiLiPi.ID);
        TYPE = CardType.ATTACK;
        RARITY = CardRarity.COMMON;
        TARGET = CardTarget.ENEMY;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
