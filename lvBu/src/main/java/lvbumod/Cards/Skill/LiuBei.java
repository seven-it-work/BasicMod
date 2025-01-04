package lvbumod.Cards.Skill;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import lvbumod.Actions.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.dungeons.*;
import lvbumod.Powers.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.*;
import java.util.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class LiuBei extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 0;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public LiuBei() {
        super(LiuBei.ID, false, LiuBei.CARD_STRINGS, 0, LiuBei.TYPE, LiuBei.COLOR, LiuBei.RARITY, LiuBei.TARGET);
        this.setupMagicNumber(2);
        this.secondaryM = 10;
        this.exhaust = true;
        this.selfRetain = true;
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeMagicNumber(3);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.addToBot((AbstractGameAction)new GetAngerFromDiscardAction(this.magicNumber, true));
        boolean hasDad = false;
        for (final AbstractMonster m2 : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!m2.isDeadOrEscaped() && m2.hasPower(LvbuModHelper.MakePath(DadPower.class.getSimpleName()))) {
                hasDad = true;
                break;
            }
        }
        if (!hasDad) {
            this.addToBot((AbstractGameAction)new LoseHPAction((AbstractCreature)p, (AbstractCreature)p, this.secondaryM, AbstractGameAction.AttackEffect.FIRE));
            if (LvbuModHelper.lvbuExtend()) {
                this.addToBot((AbstractGameAction)new SFXAction(LiuBei.ID));
            }
        }
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new LiuBei();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(LiuBei.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(LiuBei.ID);
        TYPE = CardType.SKILL;
        RARITY = CardRarity.UNCOMMON;
        TARGET = CardTarget.NONE;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
