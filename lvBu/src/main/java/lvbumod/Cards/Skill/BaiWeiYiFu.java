package lvbumod.Cards.Skill;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import lvbumod.Powers.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.powers.*;
import lvbumod.Actions.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class BaiWeiYiFu extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 1;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public BaiWeiYiFu() {
        super(BaiWeiYiFu.ID, false, BaiWeiYiFu.CARD_STRINGS, 1, BaiWeiYiFu.TYPE, BaiWeiYiFu.COLOR, BaiWeiYiFu.RARITY, BaiWeiYiFu.TARGET);
        this.setupMagicNumber(1);
        this.setupBlock(4);
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeBlock(2);
        this.upgradeMagicNumber(1);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (!m.hasPower(LvbuModHelper.MakePath(DadPower.class.getSimpleName()))) {
            this.addToBot((AbstractGameAction)new SFXAction(LvbuModHelper.MakePath("BaiWeiYiFu")));
        }
        this.gainBlock();
        this.addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)p, (AbstractPower)new DadPower(m)));
        this.addToBot((AbstractGameAction)new GetAngerFromDrawAction(this.magicNumber));
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new BaiWeiYiFu();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(BaiWeiYiFu.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(BaiWeiYiFu.ID);
        TYPE = CardType.SKILL;
        RARITY = CardRarity.BASIC;
        TARGET = CardTarget.ENEMY;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
