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
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class HengSaoQianJun extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 2;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public HengSaoQianJun() {
        super(HengSaoQianJun.ID, false, HengSaoQianJun.CARD_STRINGS, 2, HengSaoQianJun.TYPE, HengSaoQianJun.COLOR, HengSaoQianJun.RARITY, HengSaoQianJun.TARGET);
        this.misc = 16;
        this.baseDamage = this.misc;
        final int n = 10;
        this.baseMagicNumber = n;
        this.magicNumber = n;
        this.exhaust = true;
        this.isMultiDamage = true;
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeMagicNumber(5);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.addToBot((AbstractGameAction)new SFXAction("ATTACK_HEAVY"));
        this.addToBot((AbstractGameAction)new VFXAction((AbstractCreature)p, (AbstractGameEffect)new CleaveEffect(), 0.0f));
        this.damageToAllEnemies(AbstractGameAction.AttackEffect.NONE);
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new HengSaoQianJun();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(HengSaoQianJun.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(HengSaoQianJun.ID);
        TYPE = CardType.ATTACK;
        RARITY = CardRarity.RARE;
        TARGET = CardTarget.ALL_ENEMY;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
