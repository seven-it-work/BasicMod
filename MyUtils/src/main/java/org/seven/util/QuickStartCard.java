package org.seven.util;

import basemod.BaseMod;
import basemod.abstracts.CustomCard;
import basemod.abstracts.DynamicVariable;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.seven.util.GeneralUtils.removePrefix;

public abstract class QuickStartCard extends CustomCard {
    final private static Map<String, DynamicVariable> customVars = new HashMap<>();
    private static final Logger logger = LogManager.getLogger(QuickStartCard.class);
    final protected Map<String, LocalVarInfo> cardVariables = new HashMap<>();
    protected CardStrings cardStrings;
    protected boolean upgradesDescription;
    protected int baseCost;
    protected boolean upgradeCost;
    protected int costUpgrade;
    protected boolean upgradeDamage;
    protected boolean upgradeBlock;
    protected boolean upgradeMagic;
    protected int damageUpgrade;

    protected int blockUpgrade;

    protected int magicUpgrade;

    protected boolean baseExhaust = false;

    protected boolean upgExhaust = false;

    protected boolean baseEthereal = false;

    protected boolean upgEthereal = false;

    protected boolean baseInnate = false;

    protected boolean upgInnate = false;

    protected boolean baseRetain = false;

    protected boolean upgRetain = false;

    protected String originalRawDescription = "";

    boolean inCalc = false;

    public QuickStartCard(String ID, CardStats info, QuickStartMod quickStartMod) {
        this(ID, info, quickStartMod.getCardTextureString(removePrefix(ID), info.cardType));
    }

    public QuickStartCard(String ID, CardStats info, String cardImage) {
        this(ID, info.baseCost, info.cardType, info.cardTarget, info.cardRarity, info.cardColor, cardImage);
    }

    public QuickStartCard(String ID, int cost, CardType cardType, CardTarget target, CardRarity rarity, CardColor color, QuickStartMod quickStartMod) {
        this(ID, cost, cardType, target, rarity, color, quickStartMod.getCardTextureString(removePrefix(ID), cardType));
    }

    public QuickStartCard(String ID, int cost, CardType cardType, CardTarget target, CardRarity rarity, CardColor color, String cardImage) {
        super(ID, getName(ID), cardImage, cost, getInitialDescription(ID), cardType, color, rarity, target);
        this.originalRawDescription = this.rawDescription;
        this.cardStrings = CardCrawlGame.languagePack.getCardStrings(cardID);
        this.originalName = cardStrings.NAME;

        this.baseCost = cost;

        this.upgradesDescription = cardStrings.UPGRADE_DESCRIPTION != null;
        this.upgradeCost = false;
        this.upgradeDamage = false;
        this.upgradeBlock = false;
        this.upgradeMagic = false;

        this.costUpgrade = cost;
        this.damageUpgrade = 0;
        this.blockUpgrade = 0;
        this.magicUpgrade = 0;
    }

    private static String getName(String ID) {
        return CardCrawlGame.languagePack.getCardStrings(ID).NAME;
    }

    private static String getInitialDescription(String ID) {
        return CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION;
    }

    @Override
    public void applyPowers() {
        if (!inCalc) {
            inCalc = true;
            for (LocalVarInfo var : cardVariables.values()) {
                var.value = var.calculation.apply(this, null, var.base);
            }
            if (isMultiDamage) {
                ArrayList<AbstractMonster> monsters = AbstractDungeon.getCurrRoom().monsters.monsters;
                AbstractMonster m;
                for (LocalVarInfo var : cardVariables.values()) {
                    if (var.aoeValue == null || var.aoeValue.length != monsters.size())
                        var.aoeValue = new int[monsters.size()];

                    for (int i = 0; i < monsters.size(); ++i) {
                        m = monsters.get(i);
                        var.aoeValue[i] = var.calculation.apply(this, m, var.base);
                    }
                }
            }
            inCalc = false;
        }

        super.applyPowers();
    }

    @Override
    public void calculateCardDamage(AbstractMonster m) {
        if (!inCalc) {
            inCalc = true;
            for (LocalVarInfo var : cardVariables.values()) {
                var.value = var.calculation.apply(this, m, var.base);
            }
            if (isMultiDamage) {
                ArrayList<AbstractMonster> monsters = AbstractDungeon.getCurrRoom().monsters.monsters;
                for (LocalVarInfo var : cardVariables.values()) {
                    if (var.aoeValue == null || var.aoeValue.length != monsters.size())
                        var.aoeValue = new int[monsters.size()];

                    for (int i = 0; i < monsters.size(); ++i) {
                        m = monsters.get(i);
                        var.aoeValue[i] = var.calculation.apply(this, m, var.base);
                    }
                }
            }
            inCalc = false;
        }

        super.calculateCardDamage(m);
    }

    protected void calculateVarAsBlock(String key) {
        setVarCalculation(key, (c, m, base) -> {
            int origBase = c.baseBlock, origVal = c.block;

            c.baseBlock = base;
            if (m != null)
                c.calculateCardDamage(m);
            else
                c.applyPowers();

            c.baseBlock = origBase;
            int result = c.block;
            c.block = origVal;
            return result;
        });
    }

    protected void calculateVarAsDamage(String key) {
        setVarCalculation(key, (c, m, base) -> {
            boolean wasMultiDamage = c.isMultiDamage;
            c.isMultiDamage = false;

            int origBase = c.baseDamage, origVal = c.damage;

            c.baseDamage = base;
            if (m != null)
                c.calculateCardDamage(m);
            else
                c.applyPowers();

            c.baseDamage = origBase;
            c.isMultiDamage = wasMultiDamage;

            int result = c.damage;
            c.damage = origVal;

            return result;
        });
    }

    protected final void colorCustomVar(String key, Color normalColor) {
        colorCustomVar(key, normalColor, Settings.GREEN_TEXT_COLOR, Settings.RED_TEXT_COLOR, Settings.GREEN_TEXT_COLOR);
    }

    protected final void colorCustomVar(String key, Color normalColor, Color increasedColor, Color decreasedColor) {
        colorCustomVar(key, normalColor, increasedColor, decreasedColor, increasedColor);
    }

    protected final void colorCustomVar(String key, Color normalColor, Color increasedColor, Color decreasedColor, Color upgradedColor) {
        LocalVarInfo var = getCustomVar(key);
        if (var == null) {
            throw new IllegalArgumentException("Attempted to set color of variable that hasn't been registered.");
        }

        var.normalColor = normalColor;
        var.increasedColor = increasedColor;
        var.decreasedColor = decreasedColor;
        var.upgradedColor = upgradedColor;
    }

    public int customVar(String key) {
        LocalVarInfo var = cardVariables == null ? null : cardVariables.get(key); //Prevents crashing when used with dynamic text
        if (var == null)
            return -1;
        return var.value;
    }

    public int customVarBase(String key) {
        LocalVarInfo var = cardVariables.get(key);
        if (var == null)
            return -1;
        return var.base;
    }

    public int[] customVarMulti(String key) {
        LocalVarInfo var = cardVariables.get(key);
        if (var == null)
            return null;
        return var.aoeValue;
    }

    public boolean customVarUpgraded(String key) {
        LocalVarInfo var = cardVariables.get(key);
        if (var == null)
            return false;
        return var.upgraded;
    }

    private LocalVarInfo getCustomVar(String key) {
        return cardVariables.get(key);
    }

    @Override
    public void initializeDescription() {
        if (this.exhaust) {
            String name = GameDictionary.EXHAUST.NAMES[0];
            if (!this.rawDescription.contains(name)) {
                this.rawDescription += "| " + name + " ";
            }
        }
        if (this.isEthereal) {
            String name = GameDictionary.ETHEREAL.NAMES[0];
            if (!this.rawDescription.contains(name)) {
                this.rawDescription += "| " + name + " ";
            }
        }
        super.initializeDescription();
    }

    public boolean isCustomVarModified(String key) {
        LocalVarInfo var = cardVariables.get(key);
        if (var == null)
            return false;
        return var.isModified();
    }

    @Override
    public AbstractCard makeStatEquivalentCopy() {
        AbstractCard card = super.makeStatEquivalentCopy();

        if (card instanceof QuickStartCard) {
            card.rawDescription = this.rawDescription;
            ((QuickStartCard) card).upgradesDescription = this.upgradesDescription;

            ((QuickStartCard) card).baseCost = this.baseCost;

            ((QuickStartCard) card).upgradeCost = this.upgradeCost;
            ((QuickStartCard) card).upgradeDamage = this.upgradeDamage;
            ((QuickStartCard) card).upgradeBlock = this.upgradeBlock;
            ((QuickStartCard) card).upgradeMagic = this.upgradeMagic;

            ((QuickStartCard) card).costUpgrade = this.costUpgrade;
            ((QuickStartCard) card).damageUpgrade = this.damageUpgrade;
            ((QuickStartCard) card).blockUpgrade = this.blockUpgrade;
            ((QuickStartCard) card).magicUpgrade = this.magicUpgrade;

            ((QuickStartCard) card).baseExhaust = this.baseExhaust;
            ((QuickStartCard) card).upgExhaust = this.upgExhaust;
            ((QuickStartCard) card).baseEthereal = this.baseEthereal;
            ((QuickStartCard) card).upgEthereal = this.upgEthereal;
            ((QuickStartCard) card).baseInnate = this.baseInnate;
            ((QuickStartCard) card).upgInnate = this.upgInnate;
            ((QuickStartCard) card).baseRetain = this.baseRetain;
            ((QuickStartCard) card).upgRetain = this.upgRetain;

            for (Map.Entry<String, LocalVarInfo> varEntry : cardVariables.entrySet()) {
                LocalVarInfo target = ((QuickStartCard) card).getCustomVar(varEntry.getKey()),
                        current = varEntry.getValue();
                if (target == null) {
                    ((QuickStartCard) card).setCustomVar(varEntry.getKey(), current.base, current.upgrade);
                    target = ((QuickStartCard) card).getCustomVar(varEntry.getKey());
                }
                target.base = current.base;
                target.value = current.value;
                target.aoeValue = current.aoeValue;
                target.upgrade = current.upgrade;
                target.calculation = current.calculation;
            }
        }

        return card;
    }

    protected abstract QuickStartMod quickStartMod();

    @Override
    public void resetAttributes() {
        super.resetAttributes();

        for (LocalVarInfo var : cardVariables.values()) {
            var.value = var.base;
        }
    }

    protected final void setBlock(int block) {
        this.setBlock(block, 0);
    }

    protected final void setBlock(int block, int blockUpgrade) {
        this.baseBlock = this.block = block;
        if (blockUpgrade != 0) {
            this.upgradeBlock = true;
            this.blockUpgrade = blockUpgrade;
        }
    }

    protected final void setCostUpgrade(int costUpgrade) {
        this.costUpgrade = costUpgrade;
        this.upgradeCost = true;
    }

    protected final void setCustomVar(String key, int base) {
        this.setCustomVar(key, base, 0);
    }

    protected final void setCustomVar(String key, int base, int upgrade) {
        setCustomVarValue(key, base, upgrade);

        if (!customVars.containsKey(key)) {
            QuickDynamicVariable var = new QuickDynamicVariable(key, quickStartMod());
            customVars.put(key, var);
            BaseMod.addDynamicVariable(var);
            initializeDescription();
        }
    }

    protected final void setCustomVar(String key, VariableType type, int base) {
        setCustomVar(key, type, base, 0);
    }

    protected final void setCustomVar(String key, VariableType type, int base, int upgrade) {
        setCustomVarValue(key, base, upgrade);

        switch (type) {
            case DAMAGE:
                calculateVarAsDamage(key);
                break;
            case BLOCK:
                calculateVarAsBlock(key);
                break;
        }

        if (!customVars.containsKey(key)) {
            QuickDynamicVariable var = new QuickDynamicVariable(key, quickStartMod());
            customVars.put(key, var);
            BaseMod.addDynamicVariable(var);
            initializeDescription();
        }
    }

    protected final void setCustomVar(String key, VariableType type, int base, TriFunction<QuickStartCard, AbstractMonster, Integer, Integer> preCalc) {
        setCustomVar(key, type, base, 0, preCalc);
    }

    protected final void setCustomVar(String key, VariableType type, int base, int upgrade, TriFunction<QuickStartCard, AbstractMonster, Integer, Integer> preCalc) {
        setCustomVar(key, type, base, upgrade, preCalc, LocalVarInfo::noCalc);
    }

    protected final void setCustomVar(String key, VariableType type, int base, TriFunction<QuickStartCard, AbstractMonster, Integer, Integer> preCalc, TriFunction<QuickStartCard, AbstractMonster, Integer, Integer> postCalc) {
        setCustomVar(key, type, base, 0, preCalc, postCalc);
    }

    protected final void setCustomVar(String key, VariableType type, int base, int upgrade, TriFunction<QuickStartCard, AbstractMonster, Integer, Integer> preCalc, TriFunction<QuickStartCard, AbstractMonster, Integer, Integer> postCalc) {
        setCustomVarValue(key, base, upgrade);

        switch (type) {
            case DAMAGE:
                setVarCalculation(key, (c, m, baseVal) -> {
                    boolean wasMultiDamage = c.isMultiDamage;
                    c.isMultiDamage = false;

                    int origBase = c.baseDamage, origVal = c.damage;

                    c.baseDamage = preCalc.apply(c, m, baseVal);

                    if (m != null)
                        c.calculateCardDamage(m);
                    else
                        c.applyPowers();

                    c.damage = postCalc.apply(c, m, c.damage);

                    c.baseDamage = origBase;
                    c.isMultiDamage = wasMultiDamage;

                    int result = c.damage;
                    c.damage = origVal;

                    return result;
                });
                break;
            case BLOCK:
                setVarCalculation(key, (c, m, baseVal) -> {
                    int origBase = c.baseBlock, origVal = c.block;

                    c.baseBlock = preCalc.apply(c, m, baseVal);

                    if (m != null)
                        c.calculateCardDamage(m);
                    else
                        c.applyPowers();

                    c.block = postCalc.apply(c, m, c.block);

                    c.baseBlock = origBase;
                    int result = c.block;
                    c.block = origVal;
                    return result;
                });
                break;
            default:
                setVarCalculation(key, (c, m, baseVal) -> {
                    int tmp = baseVal;

                    tmp = preCalc.apply(c, m, tmp);
                    tmp = postCalc.apply(c, m, tmp);

                    return tmp;
                });
                break;
        }

        if (!customVars.containsKey(key)) {
            QuickDynamicVariable var = new QuickDynamicVariable(key, quickStartMod());
            customVars.put(key, var);
            BaseMod.addDynamicVariable(var);
            initializeDescription();
        }
    }

    private void setCustomVarValue(String key, int base, int upg) {
        cardVariables.compute(key, (k, old) -> {
            if (old == null) {
                return new LocalVarInfo(base, upg);
            } else {
                old.base = base;
                old.upgrade = upg;
                return old;
            }
        });
    }

    //Methods meant for constructor use
    protected final void setDamage(int damage) {
        this.setDamage(damage, 0);
    }

    protected final void setDamage(int damage, int damageUpgrade) {
        this.baseDamage = this.damage = damage;
        if (damageUpgrade != 0) {
            this.upgradeDamage = true;
            this.damageUpgrade = damageUpgrade;
        }
    }

    protected final void setEthereal(boolean ethereal) {
        this.setEthereal(ethereal, ethereal);
    }

    protected final void setEthereal(boolean baseEthereal, boolean upgEthereal) {
        this.baseEthereal = baseEthereal;
        this.upgEthereal = upgEthereal;
        this.isEthereal = baseEthereal;
    }

    protected final void setExhaust(boolean exhaust) {
        this.setExhaust(exhaust, exhaust);
    }

    protected final void setExhaust(boolean baseExhaust, boolean upgExhaust) {
        this.baseExhaust = baseExhaust;
        this.upgExhaust = upgExhaust;
        this.exhaust = baseExhaust;
    }

    protected final void setInnate(boolean innate) {
        this.setInnate(innate, innate);
    }

    protected void setInnate(boolean baseInnate, boolean upgInnate) {
        this.baseInnate = baseInnate;
        this.upgInnate = upgInnate;
        this.isInnate = baseInnate;
    }

    protected final void setMagic(int magic) {
        this.setMagic(magic, 0);
    }

    protected final void setMagic(int magic, int magicUpgrade) {
        this.baseMagicNumber = this.magicNumber = magic;
        if (magicUpgrade != 0) {
            this.upgradeMagic = true;
            this.magicUpgrade = magicUpgrade;
        }
    }

    protected final void setSelfRetain(boolean retain) {
        this.setSelfRetain(retain, retain);
    }

    protected void setSelfRetain(boolean baseRetain, boolean upgRetain) {
        this.baseRetain = baseRetain;
        this.upgRetain = upgRetain;
        this.selfRetain = baseRetain;
    }

    protected void setVarCalculation(String key, TriFunction<QuickStartCard, AbstractMonster, Integer, Integer> calculation) {
        cardVariables.get(key).calculation = calculation;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            this.upgradeName();

            if (this.upgradesDescription) {
                if (cardStrings.UPGRADE_DESCRIPTION == null) {
                    logger.error("Card " + cardID + " upgrades description and has null upgrade description.");
                } else {
                    this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
                }
            }

            if (upgradeCost) {
                if (isCostModified && this.cost < this.baseCost && this.cost >= 0) {
                    int diff = this.costUpgrade - this.baseCost; //how the upgrade alters cost
                    this.upgradeBaseCost(this.cost + diff);
                    if (this.cost < 0)
                        this.cost = 0;
                } else {
                    upgradeBaseCost(costUpgrade);
                }
            }

            if (upgradeDamage)
                this.upgradeDamage(damageUpgrade);

            if (upgradeBlock)
                this.upgradeBlock(blockUpgrade);

            if (upgradeMagic)
                this.upgradeMagicNumber(magicUpgrade);

            for (LocalVarInfo var : cardVariables.values()) {
                if (var.upgrade != 0) {
                    var.base += var.upgrade;
                    var.value = var.base;
                    var.upgraded = true;
                }
            }

            if (baseExhaust ^ upgExhaust)
                this.exhaust = upgExhaust;

            if (baseInnate ^ upgInnate)
                this.isInnate = upgInnate;

            if (baseEthereal ^ upgEthereal)
                this.isEthereal = upgEthereal;

            if (baseRetain ^ upgRetain)
                this.selfRetain = upgRetain;


            this.initializeDescription();
        }
    }

    protected enum VariableType {
        DAMAGE,
        BLOCK,
        MAGIC
    }

    private static class QuickDynamicVariable extends DynamicVariable {
        final String localKey, key;

        private QuickStartCard current = null;

        public QuickDynamicVariable(String key, QuickStartMod quickStartMod) {
            this.localKey = key;
            this.key = quickStartMod.makeID(key);
        }

        @Override
        public int baseValue(AbstractCard c) {
            return c instanceof QuickStartCard ? ((QuickStartCard) c).customVarBase(localKey) : 0;
        }

        public Color getDecreasedValueColor() {
            LocalVarInfo var;
            if (current == null || (var = current.getCustomVar(localKey)) == null)
                return Settings.RED_TEXT_COLOR;

            return var.decreasedColor;
        }

        public Color getIncreasedValueColor() {
            LocalVarInfo var;
            if (current == null || (var = current.getCustomVar(localKey)) == null)
                return Settings.GREEN_TEXT_COLOR;

            return var.increasedColor;
        }

        public Color getNormalColor() {
            LocalVarInfo var;
            if (current == null || (var = current.getCustomVar(localKey)) == null)
                return Settings.CREAM_COLOR;

            return var.normalColor;
        }

        public Color getUpgradedColor() {
            LocalVarInfo var;
            if (current == null || (var = current.getCustomVar(localKey)) == null)
                return Settings.GREEN_TEXT_COLOR;

            return var.upgradedColor;
        }

        @Override
        public boolean isModified(AbstractCard c) {
            return c instanceof QuickStartCard && (current = (QuickStartCard) c).isCustomVarModified(localKey);
        }

        @Override
        public String key() {
            return key;
        }

        @Override
        public void setIsModified(AbstractCard c, boolean v) {
            if (c instanceof QuickStartCard) {
                LocalVarInfo var = ((QuickStartCard) c).getCustomVar(localKey);
                if (var != null)
                    var.forceModified = v;
            }
        }

        @Override
        public boolean upgraded(AbstractCard c) {
            return c instanceof QuickStartCard && ((QuickStartCard) c).customVarUpgraded(localKey);
        }

        @Override
        public int value(AbstractCard c) {
            return c instanceof QuickStartCard ? ((QuickStartCard) c).customVar(localKey) : 0;
        }
    }

    protected static class LocalVarInfo {
        int base, value, upgrade;
        int[] aoeValue = null;
        boolean upgraded = false;
        boolean forceModified = false;
        Color normalColor = Settings.CREAM_COLOR;
        Color upgradedColor = Settings.GREEN_TEXT_COLOR;
        Color increasedColor = Settings.GREEN_TEXT_COLOR;
        Color decreasedColor = Settings.RED_TEXT_COLOR;

        TriFunction<QuickStartCard, AbstractMonster, Integer, Integer> calculation = LocalVarInfo::noCalc;

        public LocalVarInfo(int base, int upgrade) {
            this.base = this.value = base;
            this.upgrade = upgrade;
        }

        private static int noCalc(QuickStartCard c, AbstractMonster m, int base) {
            return base;
        }

        public boolean isModified() {
            return forceModified || base != value;
        }
    }
}
