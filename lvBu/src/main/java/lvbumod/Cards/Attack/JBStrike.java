package lvbumod.Cards.Attack;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import lvbumod.Characters.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.actions.*;
import lvbumod.Helpers.*;
import lvbumod.Actions.*;
import com.megacrit.cardcrawl.core.*;

public class JBStrike extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 1;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public JBStrike() {
        super(JBStrike.ID, false, JBStrike.CARD_STRINGS, 1, JBStrike.TYPE, JBStrike.COLOR, JBStrike.RARITY, JBStrike.TARGET);
        this.setupDamage(12);
        this.setupMagicNumber(1);
        this.tags.add(CardTags.STRIKE);
        this.tags.add(lvbu.Enums.JI_ATTACK_CARD);
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(3);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.damageToEnemy(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        if (LvbuModHelper.checkHasDad()) {
            this.addToBot((AbstractGameAction)new GetAngerFromHandAction(this.magicNumber, false));
        }
        this.useJi(m);
        LvbuModHelper.resetLvbuCardColor((AbstractCard)this);
    }
    
    public void triggerOnGlowCheck() {
        if (LvbuModHelper.checkHasDad()) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
        else {
            LvbuModHelper.resetLvbuCardColor((AbstractCard)this);
        }
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new JBStrike();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(JBStrike.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(JBStrike.ID);
        TYPE = CardType.ATTACK;
        RARITY = CardRarity.COMMON;
        TARGET = CardTarget.ENEMY;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
