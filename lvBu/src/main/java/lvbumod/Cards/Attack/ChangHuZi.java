package lvbumod.Cards.Attack;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.vfx.combat.*;
import com.megacrit.cardcrawl.actions.animations.*;
import com.megacrit.cardcrawl.vfx.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class ChangHuZi extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 1;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public ChangHuZi() {
        super(ChangHuZi.ID, false, ChangHuZi.CARD_STRINGS, 1, ChangHuZi.TYPE, ChangHuZi.COLOR, ChangHuZi.RARITY, ChangHuZi.TARGET);
        this.setupDamage(5);
        this.isMultiDamage = true;
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(2);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        for (int i = 0; i < LvbuModHelper.getEnemiesCount(); ++i) {
            this.addToBot((AbstractGameAction)new SFXAction("ATTACK_HEAVY"));
            this.addToBot((AbstractGameAction)new VFXAction((AbstractCreature)p, (AbstractGameEffect)new CleaveEffect(), 0.1f));
            this.damageToAllEnemies(AbstractGameAction.AttackEffect.NONE);
        }
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new ChangHuZi();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(ChangHuZi.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ChangHuZi.ID);
        TYPE = CardType.ATTACK;
        RARITY = CardRarity.UNCOMMON;
        TARGET = CardTarget.ALL_ENEMY;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
