package lvbumod.Cards.Attack;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import lvbumod.Characters.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.*;

public class ThrowJi extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 3;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public ThrowJi() {
        super(ThrowJi.ID, false, ThrowJi.CARD_STRINGS, 3, ThrowJi.TYPE, ThrowJi.COLOR, ThrowJi.RARITY, ThrowJi.TARGET);
        this.setupDamage(18);
        this.setupMagicNumber(1);
        this.tags.add(lvbu.Enums.JI_ATTACK_CARD);
        this.isMultiDamage = true;
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeMagicNumber(1);
        this.upgradeDescription(ThrowJi.CARD_STRINGS);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        final int i = LvbuModHelper.getFarthestEnemy();
        if (i != -1) {
            final AbstractMonster mo = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
            this.addToBot((AbstractGameAction)new DamageAction((AbstractCreature)mo, new DamageInfo((AbstractCreature)AbstractDungeon.player, this.multiDamage[i]), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            this.useJi(mo);
        }
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new ThrowJi();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(ThrowJi.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ThrowJi.ID);
        TYPE = CardType.ATTACK;
        RARITY = CardRarity.UNCOMMON;
        TARGET = CardTarget.NONE;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
