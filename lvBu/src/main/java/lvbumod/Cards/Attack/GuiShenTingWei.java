package lvbumod.Cards.Attack;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.vfx.combat.*;
import com.megacrit.cardcrawl.actions.animations.*;
import com.megacrit.cardcrawl.vfx.*;
import lvbumod.Powers.*;
import com.megacrit.cardcrawl.powers.*;
import java.util.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class GuiShenTingWei extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 5;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public GuiShenTingWei() {
        super(GuiShenTingWei.ID, false, GuiShenTingWei.CARD_STRINGS, 5, GuiShenTingWei.TYPE, GuiShenTingWei.COLOR, GuiShenTingWei.RARITY, GuiShenTingWei.TARGET);
        this.setupDamage(21);
        this.setupMagicNumber(4);
        this.isMultiDamage = true;
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(6);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.addToBot((AbstractGameAction)new SFXAction("THUNDERCLAP", 0.05f));
        for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!mo.isDeadOrEscaped()) {
                this.addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new LightningEffect(mo.drawX, mo.drawY), 0.05f));
            }
        }
        this.damageToAllEnemies(AbstractGameAction.AttackEffect.NONE);
        this.applyToPlayer(new FightHardPower((AbstractCreature)p, this.magicNumber));
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new GuiShenTingWei();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(GuiShenTingWei.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(GuiShenTingWei.ID);
        TYPE = CardType.ATTACK;
        RARITY = CardRarity.RARE;
        TARGET = CardTarget.ALL_ENEMY;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
