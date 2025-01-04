package lvbumod.Cards.Attack;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.vfx.combat.*;
import com.megacrit.cardcrawl.actions.animations.*;
import com.megacrit.cardcrawl.vfx.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.helpers.*;
import java.util.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class LiZhenQianKun extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 5;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public LiZhenQianKun() {
        super(LiZhenQianKun.ID, false, LiZhenQianKun.CARD_STRINGS, 5, LiZhenQianKun.TYPE, LiZhenQianKun.COLOR, LiZhenQianKun.RARITY, LiZhenQianKun.TARGET);
        this.setupDamage(20);
        this.misc = 0;
        this.exhaust = true;
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(8);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        if (m != null) {
            this.addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new WeightyImpactEffect(m.hb.cX, m.hb.cY)));
            this.addToBot((AbstractGameAction)new WaitAction(0.8f));
        }
        final UUID uuidTemp = this.uuid;
        this.addToBot((AbstractGameAction)new AbstractGameAction() {
            public void update() {
                for (final AbstractCard c : AbstractDungeon.player.masterDeck.group) {
                    if (c.uuid.equals(uuidTemp)) {
                        if (c.misc < 5) {
                            final AbstractCard abstractCard = c;
                            ++abstractCard.misc;
                        }
                        c.updateCost(-1);
                    }
                }
                for (final AbstractCard c : GetAllInBattleInstances.get(uuidTemp)) {
                    if (c.misc < 5) {
                        final AbstractCard abstractCard2 = c;
                        ++abstractCard2.misc;
                    }
                    c.updateCost(-1);
                }
                this.isDone = true;
            }
        });
        this.damageToEnemy(m, AbstractGameAction.AttackEffect.NONE);
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new LiZhenQianKun();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(LiZhenQianKun.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(LiZhenQianKun.ID);
        TYPE = CardType.ATTACK;
        RARITY = CardRarity.UNCOMMON;
        TARGET = CardTarget.ENEMY;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
