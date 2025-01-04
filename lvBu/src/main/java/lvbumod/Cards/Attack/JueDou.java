package lvbumod.Cards.Attack;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import basemod.*;
import com.megacrit.cardcrawl.relics.*;
import com.megacrit.cardcrawl.powers.*;
import com.badlogic.gdx.math.*;
import java.util.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class JueDou extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 1;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public JueDou() {
        super(JueDou.ID, false, JueDou.CARD_STRINGS, 1, JueDou.TYPE, JueDou.COLOR, JueDou.RARITY, JueDou.TARGET);
        this.setupDamage(10);
        this.setupBlock(5);
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeBlock(5);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)AbstractDungeon.player, this.damage), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        if (this.calJueDouDamage(m) <= this.calIntentDamage(m)) {
            this.gainBlock();
        }
    }
    
    public int calIntentDamage(final AbstractMonster m) {
        if (m == null) {
            return 0;
        }
        int dmg = 0;
        if (!m.isDeadOrEscaped() && m.getIntentBaseDmg() >= 0) {
            int multiAmt = 0;
            multiAmt = (int)ReflectionHacks.getPrivate((Object)m, (Class)AbstractMonster.class, "intentMultiAmt");
            dmg = m.getIntentDmg();
            if (multiAmt > 1) {
                dmg *= multiAmt;
            }
        }
        return dmg;
    }
    
    public int calJueDouDamage(final AbstractMonster mo) {
        this.applyPowersToBlock();
        final AbstractPlayer player = AbstractDungeon.player;
        if (mo == null) {
            return 0;
        }
        float tmp = (float)this.baseDamage;
        for (final AbstractRelic r : player.relics) {
            tmp = r.atDamageModify(tmp, (AbstractCard)this);
        }
        for (final AbstractPower p : player.powers) {
            tmp = p.atDamageGive(tmp, this.damageTypeForTurn, (AbstractCard)this);
        }
        tmp = player.stance.atDamageGive(tmp, this.damageTypeForTurn, (AbstractCard)this);
        for (final AbstractPower p : mo.powers) {
            tmp = p.atDamageReceive(tmp, this.damageTypeForTurn, (AbstractCard)this);
        }
        for (final AbstractPower p : player.powers) {
            tmp = p.atDamageFinalGive(tmp, this.damageTypeForTurn, (AbstractCard)this);
        }
        for (final AbstractPower p : mo.powers) {
            tmp = p.atDamageFinalReceive(tmp, this.damageTypeForTurn, (AbstractCard)this);
        }
        if (tmp < 0.0f) {
            tmp = 0.0f;
        }
        return MathUtils.floor(tmp);
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new JueDou();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(JueDou.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(JueDou.ID);
        TYPE = CardType.ATTACK;
        RARITY = CardRarity.COMMON;
        TARGET = CardTarget.ENEMY;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
