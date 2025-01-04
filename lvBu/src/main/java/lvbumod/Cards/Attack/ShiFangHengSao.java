package lvbumod.Cards.Attack;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.vfx.combat.*;
import com.megacrit.cardcrawl.actions.animations.*;
import com.megacrit.cardcrawl.vfx.*;
import com.megacrit.cardcrawl.actions.common.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class ShiFangHengSao extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 2;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public ShiFangHengSao() {
        super(ShiFangHengSao.ID, false, ShiFangHengSao.CARD_STRINGS, 2, ShiFangHengSao.TYPE, ShiFangHengSao.COLOR, ShiFangHengSao.RARITY, ShiFangHengSao.TARGET);
        this.setupDamage(12);
        this.setupMagicNumber(2);
        this.isMultiDamage = true;
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(3);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.addToBot((AbstractGameAction)new SFXAction("ATTACK_HEAVY"));
        this.addToBot((AbstractGameAction)new VFXAction((AbstractCreature)p, (AbstractGameEffect)new CleaveEffect(), 0.1f));
        this.damageToAllEnemies(AbstractGameAction.AttackEffect.NONE);
        this.addToBot((AbstractGameAction)new DrawCardAction(this.magicNumber));
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new ShiFangHengSao();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(ShiFangHengSao.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ShiFangHengSao.ID);
        TYPE = CardType.ATTACK;
        RARITY = CardRarity.COMMON;
        TARGET = CardTarget.ALL_ENEMY;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
