package lvbumod.Cards.Attack;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.actions.*;
import lvbumod.Powers.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.dungeons.*;
import java.util.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class ZhanBaiYuHe extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 1;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public ZhanBaiYuHe() {
        super(ZhanBaiYuHe.ID, false, ZhanBaiYuHe.CARD_STRINGS, 1, ZhanBaiYuHe.TYPE, ZhanBaiYuHe.COLOR, ZhanBaiYuHe.RARITY, ZhanBaiYuHe.TARGET);
        this.setupDamage(9);
        this.setupMagicNumber(2);
        this.secondaryM = 1;
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeMagicNumber(1);
        this.upgradeDamage(1);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.damageToEnemy(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        if (this.magicNumber != 0) {
            this.applyToPlayer(new FightHardPower((AbstractCreature)p, this.magicNumber));
        }
        final AbstractCard card = (AbstractCard)this;
        final int increaseNum = this.secondaryM;
        final UUID uuid12 = this.uuid;
        this.addToBot((AbstractGameAction)new AbstractGameAction() {
            public void update() {
                final AbstractCard val$card = card;
                final AbstractCard val$card2 = card;
                final int n = val$card2.baseMagicNumber + increaseNum;
                val$card2.baseMagicNumber = n;
                val$card.magicNumber = n;
                card.applyPowers();
                for (final AbstractCard c : AbstractDungeon.player.discardPile.group) {
                    if (c.uuid == uuid12) {
                        final AbstractCard abstractCard = c;
                        final AbstractCard abstractCard2 = c;
                        final int n2 = abstractCard2.baseMagicNumber + increaseNum;
                        abstractCard2.baseMagicNumber = n2;
                        abstractCard.magicNumber = n2;
                        c.applyPowers();
                    }
                }
                for (final AbstractCard c : AbstractDungeon.player.drawPile.group) {
                    if (c.uuid == uuid12) {
                        final AbstractCard abstractCard3 = c;
                        final AbstractCard abstractCard4 = c;
                        final int n3 = abstractCard4.baseMagicNumber + increaseNum;
                        abstractCard4.baseMagicNumber = n3;
                        abstractCard3.magicNumber = n3;
                        c.applyPowers();
                    }
                }
                for (final AbstractCard c : AbstractDungeon.player.hand.group) {
                    if (c.uuid == uuid12) {
                        final AbstractCard abstractCard5 = c;
                        final AbstractCard abstractCard6 = c;
                        final int n4 = abstractCard6.baseMagicNumber + increaseNum;
                        abstractCard6.baseMagicNumber = n4;
                        abstractCard5.magicNumber = n4;
                        c.applyPowers();
                    }
                }
                this.isDone = true;
            }
        });
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new ZhanBaiYuHe();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(ZhanBaiYuHe.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ZhanBaiYuHe.ID);
        TYPE = CardType.ATTACK;
        RARITY = CardRarity.UNCOMMON;
        TARGET = CardTarget.ENEMY;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
