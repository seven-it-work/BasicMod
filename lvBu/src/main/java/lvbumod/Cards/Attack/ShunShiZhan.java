package lvbumod.Cards.Attack;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.animations.*;
import com.megacrit.cardcrawl.vfx.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.vfx.combat.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class ShunShiZhan extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 1;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public ShunShiZhan() {
        super(ShunShiZhan.ID, false, ShunShiZhan.CARD_STRINGS, 1, ShunShiZhan.TYPE, ShunShiZhan.COLOR, ShunShiZhan.RARITY, ShunShiZhan.TARGET);
        this.setupDamage(7);
        this.setupMagicNumber(2);
        this.isMultiDamage = true;
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(3);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (LvbuModHelper.getEnemiesCount() > 1) {
            this.addToBot((AbstractGameAction)new SFXAction("ATTACK_HEAVY"));
            this.addToBot((AbstractGameAction)new VFXAction((AbstractCreature)p, (AbstractGameEffect)new CleaveEffect(), 0.1f));
            final int i = LvbuModHelper.getFarthestEnemy();
            if (i != -1) {
                final AbstractMonster FarthestM = AbstractDungeon.getCurrRoom().monsters.monsters.get(i);
                this.addToBot((AbstractGameAction)new SFXAction("THUNDERCLAP", 0.05f));
                this.addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new LightningEffect(FarthestM.drawX, FarthestM.drawY), 0.05f));
                this.damageToAllEnemies(AbstractGameAction.AttackEffect.NONE);
                this.addToBot((AbstractGameAction)new DamageAction((AbstractCreature)FarthestM, new DamageInfo((AbstractCreature)AbstractDungeon.player, this.multiDamage[i]), AbstractGameAction.AttackEffect.NONE));
            }
            LvbuModHelper.resetLvbuCardColor((AbstractCard)this);
        }
        else {
            this.addToBot((AbstractGameAction)new SFXAction("ATTACK_HEAVY"));
            this.addToBot((AbstractGameAction)new VFXAction((AbstractCreature)p, (AbstractGameEffect)new CleaveEffect(), 0.1f));
            this.damageToAllEnemies(AbstractGameAction.AttackEffect.NONE);
        }
    }
    
    public void triggerOnGlowCheck() {
        if (LvbuModHelper.getEnemiesCount() > 1) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
        else {
            LvbuModHelper.resetLvbuCardColor((AbstractCard)this);
        }
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new ShunShiZhan();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(ShunShiZhan.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ShunShiZhan.ID);
        TYPE = CardType.ATTACK;
        RARITY = CardRarity.COMMON;
        TARGET = CardTarget.ALL_ENEMY;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
