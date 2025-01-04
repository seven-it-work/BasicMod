package lvbumod.Cards.Attack;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import lvbumod.Characters.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.dungeons.*;
import lvbumod.Powers.*;
import lvbumod.Helpers.*;
import java.util.*;
import com.megacrit.cardcrawl.core.*;

public class WuShuangNuJi extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 1;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    private int extra;
    
    public WuShuangNuJi() {
        super(WuShuangNuJi.ID, false, WuShuangNuJi.CARD_STRINGS, 1, WuShuangNuJi.TYPE, WuShuangNuJi.COLOR, WuShuangNuJi.RARITY, WuShuangNuJi.TARGET);
        this.extra = 0;
        this.setupDamage(12);
        this.setupMagicNumber(10);
        this.tags.add(lvbu.Enums.JI_ATTACK_CARD);
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeMagicNumber(5);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.calculateCardDamage(m);
        this.damageToEnemy(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        this.useJi(m);
    }
    
    public void calculateCardDamage(final AbstractMonster mo) {
        this.extra = 0;
        if (AbstractDungeon.player.hasPower(LvbuModHelper.MakePath(MatchlessPower.class.getSimpleName()))) {
            for (final AbstractCard c : AbstractDungeon.player.drawPile.group) {
                if (LvbuModHelper.getAnger(c)) {
                    this.extra += this.magicNumber;
                }
            }
        }
        final int realBaseDamage = this.baseDamage;
        this.baseDamage += this.extra;
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = (this.damage != this.baseDamage);
        this.initializeDescription();
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new WuShuangNuJi();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(WuShuangNuJi.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(WuShuangNuJi.ID);
        TYPE = CardType.ATTACK;
        RARITY = CardRarity.RARE;
        TARGET = CardTarget.ENEMY;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
