package lvbumod.Cards.Attack;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.vfx.combat.*;
import com.megacrit.cardcrawl.actions.animations.*;
import com.megacrit.cardcrawl.vfx.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class ZhangLiao extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 3;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public ZhangLiao() {
        super(ZhangLiao.ID, false, ZhangLiao.CARD_STRINGS, 3, ZhangLiao.TYPE, ZhangLiao.COLOR, ZhangLiao.RARITY, ZhangLiao.TARGET);
        this.setupDamage(28);
        this.isMultiDamage = true;
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(10);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        final int i = LvbuModHelper.getFarthestEnemy();
        if (i != -1) {
            final AbstractMonster mo = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            if (mo != null) {
                this.addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new WeightyImpactEffect(mo.hb.cX, mo.hb.cY)));
                this.addToBot((AbstractGameAction)new WaitAction(0.8f));
                this.addToBot((AbstractGameAction)new DamageAction((AbstractCreature)mo, new DamageInfo((AbstractCreature)AbstractDungeon.player, this.multiDamage[i]), AbstractGameAction.AttackEffect.NONE));
            }
        }
    }
    
    public boolean canUse(final AbstractPlayer p, final AbstractMonster m) {
        final boolean superCanUse = super.canUse(p, m);
        return (AbstractDungeon.player.currentHealth >= AbstractDungeon.player.maxHealth / 2.0f || !p.hand.group.contains(this)) && superCanUse;
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new ZhangLiao();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(ZhangLiao.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ZhangLiao.ID);
        TYPE = CardType.ATTACK;
        RARITY = CardRarity.UNCOMMON;
        TARGET = CardTarget.NONE;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
