package lvbumod.Cards.Attack;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import lvbumod.Characters.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.actions.*;
import lvbumod.Powers.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.*;

public class WuShuangTuCiJi extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 1;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public WuShuangTuCiJi() {
        super(WuShuangTuCiJi.ID, false, WuShuangTuCiJi.CARD_STRINGS, 1, WuShuangTuCiJi.TYPE, WuShuangTuCiJi.COLOR, WuShuangTuCiJi.RARITY, WuShuangTuCiJi.TARGET);
        this.setupDamage(8);
        this.setupMagicNumber(3);
        this.tags.add(lvbu.Enums.JI_ATTACK_CARD);
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(2);
        this.upgradeMagicNumber(1);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.damageToEnemy(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        if (p.hasPower(LvbuModHelper.MakePath(MatchlessPower.class.getSimpleName()))) {
            this.addToBot((AbstractGameAction)new DrawCardAction(this.magicNumber));
        }
        this.useJi(m);
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new WuShuangTuCiJi();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(WuShuangTuCiJi.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(WuShuangTuCiJi.ID);
        TYPE = CardType.ATTACK;
        RARITY = CardRarity.COMMON;
        TARGET = CardTarget.ENEMY;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
