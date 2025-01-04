package lvbumod.Cards.Attack;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import lvbumod.Characters.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;

public class GaoShun extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 1;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public GaoShun() {
        super(GaoShun.ID, false, GaoShun.CARD_STRINGS, 1, GaoShun.TYPE, GaoShun.COLOR, GaoShun.RARITY, GaoShun.TARGET);
        this.setupDamage(14);
        this.isMultiDamage = true;
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(4);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (lvbu.GaoShunTarget == null) {
            lvbu.GaoShunTarget = m;
            this.target = CardTarget.NONE;
        }
        if (lvbu.GaoShunTarget != null && !lvbu.GaoShunTarget.isDeadOrEscaped() && lvbu.GaoShunTarget.currentHealth > 0) {
            final int temp = AbstractDungeon.getCurrRoom().monsters.monsters.size();
            int gaoshunI = -1;
            for (int i = 0; i < temp; ++i) {
                final AbstractMonster m2 = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                if (!m2.isDeadOrEscaped() && m2.currentHealth > 0 && m2.equals(lvbu.GaoShunTarget)) {
                    gaoshunI = i;
                    break;
                }
            }
            this.target = CardTarget.NONE;
            if (gaoshunI != -1) {
                this.addToBot((AbstractGameAction)new DamageAction((AbstractCreature)lvbu.GaoShunTarget, new DamageInfo((AbstractCreature)AbstractDungeon.player, this.multiDamage[gaoshunI]), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
            }
        }
    }
    
    public boolean canUse(final AbstractPlayer p, final AbstractMonster m) {
        final boolean superCanUse = super.canUse(p, m);
        if (lvbu.GaoShunTarget == null) {
            return superCanUse;
        }
        if (lvbu.GaoShunTarget.isDeadOrEscaped() || lvbu.GaoShunTarget.currentHealth <= 0) {
            this.cantUseMessage = GaoShun.CARD_STRINGS.EXTENDED_DESCRIPTION[0];
            return false;
        }
        return super.canUse(p, m);
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new GaoShun();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(GaoShun.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(GaoShun.ID);
        TYPE = CardType.ATTACK;
        RARITY = CardRarity.UNCOMMON;
        TARGET = CardTarget.ENEMY;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
