package lvbumod.Cards.Abstract;

import basemod.abstracts.*;
import com.megacrit.cardcrawl.localization.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.actions.common.*;
import lvbumod.Powers.*;
import com.megacrit.cardcrawl.powers.*;

public abstract class LvbuAllCards extends CustomCard
{
    public int baseSelfDamage;
    private int baseSelfBlock;
    public int selfDamage;
    public int selfBlock;
    private boolean isSelfDamageModified;
    private boolean isSelfBlockModified;
    public boolean isSecondaryMModified;
    public boolean upgradesecondaryM;
    public int secondaryM;
    
    public LvbuAllCards(final String ID, final boolean useTmpArt, final CardStrings strings, final int COST, final CardType TYPE, final CardColor color, final CardRarity RARITY, final CardTarget TARGET) {
        super(ID, strings.NAME, useTmpArt ? GetTmpImgPath(TYPE) : GetImgPath(TYPE, ID), COST, strings.DESCRIPTION, TYPE, color, RARITY, TARGET);
    }
    
    private static String GetTmpImgPath(final CardType t) {
        String type = null;
        switch (t) {
            case ATTACK: {
                type = "attack";
                break;
            }
            case POWER: {
                type = "power";
                break;
            }
            case STATUS: {
                type = "skill";
                break;
            }
            case CURSE: {
                type = "skill";
                break;
            }
            case SKILL: {
                type = "skill";
                break;
            }
            default: {
                throw new IllegalStateException("Unexpected value: " + t);
            }
        }
        return String.format("lvbuModResources/img/cards/test_%s.png", type);
    }
    
    private static String GetImgPath(final CardType t, final String name) {
        String type = null;
        switch (t) {
            case ATTACK: {
                type = "Attack";
                break;
            }
            case POWER: {
                type = "Power";
                break;
            }
            case CURSE: {
                type = "Curse";
                break;
            }
            case SKILL: {
                type = "Skill";
                break;
            }
            default: {
                type = "Special";
                break;
            }
        }
        return String.format("lvbuModResources/img/cards/%s/%s.png", type, name.replace(LvbuModHelper.MakePath(""), ""));
    }
    
    protected void setupDamage(final int amt) {
        this.baseDamage = amt;
        this.damage = amt;
    }
    
    protected void setupBlock(final int amt) {
        this.baseBlock = amt;
        this.block = amt;
    }
    
    protected void setupMagicNumber(final int amt) {
        this.baseMagicNumber = amt;
        this.magicNumber = amt;
    }
    
    protected void upgradeDescription(final CardStrings cardStrings) {
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        this.initializeDescription();
    }
    
    public void damageToEnemy(final AbstractMonster m, final AbstractGameAction.AttackEffect effect) {
        this.addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)AbstractDungeon.player, this.damage), effect));
    }
    
    public void damageToAllEnemies(final AbstractGameAction.AttackEffect effect) {
        this.addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)AbstractDungeon.player, this.multiDamage, this.damageTypeForTurn, effect));
    }
    
    public boolean checkThreeEnemiesFromCard(final AbstractPlayer p, final AbstractMonster m) {
        if (LvbuModHelper.checkThreeEnemies()) {
            if (p.hasPower(LvbuModHelper.MakePath(WuZhiHuaShenPower.class.getSimpleName()))) {
                this.applyToPlayer(new FightHardPower((AbstractCreature)p, p.getPower(LvbuModHelper.MakePath(WuZhiHuaShenPower.class.getSimpleName())).amount));
            }
            return true;
        }
        return false;
    }
    
    public void gainBlock() {
        this.addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)AbstractDungeon.player, this.block));
    }
    
    public void gainBlock(final int amt) {
        this.addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)AbstractDungeon.player, amt));
    }
    
    public void drawCards(final int amt) {
        this.addToBot((AbstractGameAction)new DrawCardAction(amt));
    }
    
    public void applyToPlayer(final AbstractPower power) {
        this.addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, power));
    }
    
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.limitedUpgrade();
        }
    }
    
    public void limitedUpgrade() {
    }
    
    public void useJi(final AbstractMonster m) {
        if (AbstractDungeon.player.hasPower(LvbuModHelper.MakePath(FangTianHuaJiPower.class.getSimpleName()))) {
            this.addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new VulnerablePower((AbstractCreature)m, AbstractDungeon.player.getPower(LvbuModHelper.MakePath(FangTianHuaJiPower.class.getSimpleName())).amount, false), AbstractDungeon.player.getPower(LvbuModHelper.MakePath(FangTianHuaJiPower.class.getSimpleName())).amount));
        }
    }
    
    protected void upgradeSecondaryM(final int amount) {
        this.secondaryM += amount;
        this.upgradesecondaryM = true;
    }
}
