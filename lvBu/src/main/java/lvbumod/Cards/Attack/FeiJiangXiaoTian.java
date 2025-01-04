package lvbumod.Cards.Attack;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import lvbumod.ModCore.*;
import com.megacrit.cardcrawl.vfx.combat.*;
import com.megacrit.cardcrawl.actions.animations.*;
import com.megacrit.cardcrawl.vfx.*;
import com.megacrit.cardcrawl.actions.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.actions.utility.*;
import lvbumod.Actions.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class FeiJiangXiaoTian extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 1;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public FeiJiangXiaoTian() {
        super(FeiJiangXiaoTian.ID, false, FeiJiangXiaoTian.CARD_STRINGS, 1, FeiJiangXiaoTian.TYPE, FeiJiangXiaoTian.COLOR, FeiJiangXiaoTian.RARITY, FeiJiangXiaoTian.TARGET);
        this.setupDamage(5);
        this.setupMagicNumber(1);
        this.isMultiDamage = true;
        this.exhaust = true;
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.exhaust = false;
        this.upgradeDescription(FeiJiangXiaoTian.CARD_STRINGS);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (Settings.FAST_MODE) {
            this.addToBot((AbstractGameAction)new VFXAction((AbstractCreature)p, (AbstractGameEffect)new ShockWaveEffect(p.hb.cX, p.hb.cY, LvbuMod.MY_COLOR_1_1, ShockWaveEffect.ShockWaveType.CHAOTIC), 0.3f));
        }
        else {
            this.addToBot((AbstractGameAction)new VFXAction((AbstractCreature)p, (AbstractGameEffect)new ShockWaveEffect(p.hb.cX, p.hb.cY, LvbuMod.MY_COLOR_1_1, ShockWaveEffect.ShockWaveType.CHAOTIC), 1.5f));
        }
        if (LvbuModHelper.lvbuExtend()) {
            this.addToBot((AbstractGameAction)new SFXAction(FeiJiangXiaoTian.ID));
        }
        this.damageToAllEnemies(AbstractGameAction.AttackEffect.NONE);
        this.addToBot((AbstractGameAction)new FvkAction(this.magicNumber));
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new FeiJiangXiaoTian();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(FeiJiangXiaoTian.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(FeiJiangXiaoTian.ID);
        TYPE = CardType.ATTACK;
        RARITY = CardRarity.COMMON;
        TARGET = CardTarget.ALL_ENEMY;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
