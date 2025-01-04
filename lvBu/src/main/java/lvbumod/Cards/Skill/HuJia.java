package lvbumod.Cards.Skill;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.dungeons.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import lvbumod.Powers.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.actions.*;
import java.util.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class HuJia extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 0;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public HuJia() {
        super(HuJia.ID, false, HuJia.CARD_STRINGS, 0, HuJia.TYPE, HuJia.COLOR, HuJia.RARITY, HuJia.TARGET);
        this.secondaryM = 6;
        this.setupMagicNumber(7);
    }
    
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(2);
        }
    }
    
    public void applyPowers() {
        super.applyPowers();
        this.magicNumber = this.baseMagicNumber;
        if (AbstractDungeon.player.hasPower(LvbuModHelper.MakePath(YinJiePower.class.getSimpleName()))) {
            this.magicNumber += AbstractDungeon.player.getPower(LvbuModHelper.MakePath(YinJiePower.class.getSimpleName())).amount;
            this.isMagicNumberModified = (this.magicNumber != this.baseMagicNumber);
        }
        this.refreshDescription();
    }
    
    private void refreshDescription() {
        this.rawDescription = HuJia.CARD_STRINGS.DESCRIPTION;
        this.initializeDescription();
    }
    
    public void onMoveToDiscard() {
        this.refreshDescription();
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.applyPowers();
        boolean hasNoDadAttack = false;
        for (final AbstractMonster m2 : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!m2.isDeadOrEscaped() && !m2.hasPower(LvbuModHelper.MakePath(DadPower.class.getSimpleName())) && m2.getIntentBaseDmg() >= 0) {
                hasNoDadAttack = true;
                break;
            }
        }
        if (hasNoDadAttack) {
            for (final AbstractMonster m3 : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!m3.isDeadOrEscaped() && m3.hasPower(LvbuModHelper.MakePath(DadPower.class.getSimpleName()))) {
                    for (int i = 0; i < this.secondaryM; ++i) {
                        this.addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m3, (AbstractCreature)p, (AbstractPower)new HatePower(m3, this.magicNumber), this.magicNumber));
                    }
                }
            }
        }
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new HuJia();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(HuJia.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(HuJia.ID);
        TYPE = CardType.SKILL;
        RARITY = CardRarity.RARE;
        TARGET = CardTarget.ALL_ENEMY;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
