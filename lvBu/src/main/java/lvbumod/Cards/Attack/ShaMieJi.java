package lvbumod.Cards.Attack;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import lvbumod.Characters.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.vfx.combat.*;
import com.megacrit.cardcrawl.actions.animations.*;
import com.megacrit.cardcrawl.vfx.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.utility.*;
import lvbumod.Actions.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;

public class ShaMieJi extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 0;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public ShaMieJi() {
        super(ShaMieJi.ID, false, ShaMieJi.CARD_STRINGS, 0, ShaMieJi.TYPE, ShaMieJi.COLOR, ShaMieJi.RARITY, ShaMieJi.TARGET);
        this.setupDamage(6);
        this.setupMagicNumber(999);
        this.tags.add(lvbu.Enums.JI_ATTACK_CARD);
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(3);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (m != null && m.hasPower("Minion")) {
            this.addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new WeightyImpactEffect(m.hb.cX, m.hb.cY, Color.GOLD.cpy())));
            this.addToBot((AbstractGameAction)new WaitAction(0.8f));
            this.addToBot((AbstractGameAction)new ShaMieJiAction((AbstractCreature)m, this.magicNumber));
        }
        else {
            this.damageToEnemy(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        }
        this.useJi(m);
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new ShaMieJi();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(ShaMieJi.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ShaMieJi.ID);
        TYPE = CardType.ATTACK;
        RARITY = CardRarity.COMMON;
        TARGET = CardTarget.ENEMY;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
