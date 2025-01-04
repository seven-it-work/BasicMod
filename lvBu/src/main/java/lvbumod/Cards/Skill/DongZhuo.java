package lvbumod.Cards.Skill;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import lvbumod.Powers.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.vfx.*;
import com.megacrit.cardcrawl.actions.common.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class DongZhuo extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 2;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public DongZhuo() {
        super(DongZhuo.ID, false, DongZhuo.CARD_STRINGS, 2, DongZhuo.TYPE, DongZhuo.COLOR, DongZhuo.RARITY, DongZhuo.TARGET);
        this.setupMagicNumber(15);
        this.exhaust = true;
        this.tags.add(CardTags.HEALING);
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeMagicNumber(10);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)p, (AbstractPower)new DadPower(m)));
        AbstractDungeon.effectList.add(new RainingGoldEffect(this.magicNumber * 2, true));
        AbstractDungeon.effectsQueue.add(new SpotlightPlayerEffect());
        this.addToBot((AbstractGameAction)new GainGoldAction(this.magicNumber));
        if (LvbuModHelper.lvbuExtend()) {
            this.addToBot((AbstractGameAction)new WaitAction(0.1f));
            this.addToBot((AbstractGameAction)new SFXAction(DongZhuo.ID));
        }
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new DongZhuo();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(DongZhuo.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(DongZhuo.ID);
        TYPE = CardType.SKILL;
        RARITY = CardRarity.UNCOMMON;
        TARGET = CardTarget.ENEMY;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
