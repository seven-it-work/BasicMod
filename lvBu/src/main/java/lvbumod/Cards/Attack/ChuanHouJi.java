package lvbumod.Cards.Attack;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import lvbumod.Characters.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.actions.*;
import lvbumod.Helpers.*;
import lvbumod.Powers.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.core.*;

public class ChuanHouJi extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 1;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public ChuanHouJi() {
        super(ChuanHouJi.ID, false, ChuanHouJi.CARD_STRINGS, 1, ChuanHouJi.TYPE, ChuanHouJi.COLOR, ChuanHouJi.RARITY, ChuanHouJi.TARGET);
        this.setupDamage(10);
        this.setupMagicNumber(6);
        this.tags.add(lvbu.Enums.JI_ATTACK_CARD);
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(2);
        this.upgradeMagicNumber(2);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.damageToEnemy(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        if (m.hasPower(LvbuModHelper.MakePath(DadPower.class.getSimpleName()))) {
            this.addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)p, (AbstractPower)new HatePower(m, this.magicNumber), this.magicNumber));
        }
        this.useJi(m);
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new ChuanHouJi();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(ChuanHouJi.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ChuanHouJi.ID);
        TYPE = CardType.ATTACK;
        RARITY = CardRarity.COMMON;
        TARGET = CardTarget.ENEMY;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
