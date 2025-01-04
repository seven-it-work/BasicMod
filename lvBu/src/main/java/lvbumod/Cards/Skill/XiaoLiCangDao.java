package lvbumod.Cards.Skill;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.dungeons.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.actions.utility.*;
import java.util.*;
import lvbumod.Powers.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class XiaoLiCangDao extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 1;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public XiaoLiCangDao() {
        super(XiaoLiCangDao.ID, false, XiaoLiCangDao.CARD_STRINGS, 1, XiaoLiCangDao.TYPE, XiaoLiCangDao.COLOR, XiaoLiCangDao.RARITY, XiaoLiCangDao.TARGET);
        this.setupMagicNumber(21);
        this.secondaryM = 4;
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeMagicNumber(4);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        boolean playSound = false;
        for (final AbstractMonster m2 : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!m2.isDeadOrEscaped() && m2.hasPower(LvbuModHelper.MakePath(DadPower.class.getSimpleName()))) {
                this.addToBot((AbstractGameAction)new HealAction((AbstractCreature)m2, (AbstractCreature)p, this.secondaryM));
                this.applyPowers();
                this.addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m2, (AbstractCreature)p, (AbstractPower)new HatePower(m2, this.magicNumber), this.magicNumber));
                playSound = true;
            }
        }
        if (LvbuModHelper.lvbuExtend() && playSound) {
            this.addToBot((AbstractGameAction)new SFXAction(XiaoLiCangDao.ID));
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
        this.rawDescription = XiaoLiCangDao.CARD_STRINGS.DESCRIPTION;
        this.initializeDescription();
    }
    
    public void onMoveToDiscard() {
        this.refreshDescription();
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new XiaoLiCangDao();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(XiaoLiCangDao.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(XiaoLiCangDao.ID);
        TYPE = CardType.SKILL;
        RARITY = CardRarity.COMMON;
        TARGET = CardTarget.ALL_ENEMY;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
