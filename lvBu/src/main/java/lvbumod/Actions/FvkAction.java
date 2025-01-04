package lvbumod.Actions;

import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.dungeons.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.cards.*;
import java.util.*;
import com.megacrit.cardcrawl.monsters.*;
import lvbumod.Characters.*;
import lvbumod.Powers.*;
import com.megacrit.cardcrawl.ui.panels.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.actions.common.*;
import lvbumod.Cards.Attack.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.core.*;

public class FvkAction extends AbstractGameAction
{
    public static final String[] TEXT;
    private AbstractPlayer player;
    private int numberOfCards;
    private boolean optional;
    private boolean gainEnergy;
    private boolean upgraded;
    public ArrayList<AbstractCard> FvkGroup;
    
    public FvkAction(final int numberOfCards, final boolean optional, final boolean gainEnergy, final boolean upgraded) {
        this.FvkGroup = new ArrayList<AbstractCard>();
        this.actionType = ActionType.CARD_MANIPULATION;
        final float action_DUR_FAST = Settings.ACTION_DUR_FAST;
        this.startDuration = action_DUR_FAST;
        this.duration = action_DUR_FAST;
        this.player = AbstractDungeon.player;
        this.numberOfCards = numberOfCards;
        this.optional = optional;
        this.gainEnergy = gainEnergy;
        this.upgraded = upgraded;
    }
    
    public FvkAction(final int numberOfCards) {
        this(numberOfCards, false, false, false);
    }
    
    public FvkAction() {
        this(1, false, false, false);
    }
    
    public void update() {
        this.FvkGroup.clear();
        for (final AbstractCard c : this.player.drawPile.group) {
            if (LvbuModHelper.getAnger(c)) {
                this.FvkGroup.add(c);
            }
        }
        if (this.duration != this.startDuration) {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                if (this.player.hasPower(LvbuModHelper.MakePath(ShenWeiPower.class.getSimpleName()))) {
                    final int drawNum = this.player.getPower(LvbuModHelper.MakePath(ShenWeiPower.class.getSimpleName())).amount * (this.player.hasPower(LvbuModHelper.MakePath(MatchlessPower.class.getSimpleName())) ? 2 : 1);
                    this.addToBot((AbstractGameAction)new DrawCardAction(drawNum));
                    this.player.getPower(LvbuModHelper.MakePath(ShenWeiPower.class.getSimpleName())).flash();
                }
                Collections.reverse(AbstractDungeon.gridSelectScreen.selectedCards);
                for (final AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                    this.playCard(c);
                }
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                AbstractDungeon.player.hand.refreshHandLayout();
            }
            this.tickDuration();
            return;
        }
        if (this.FvkGroup.size() == 0 || this.numberOfCards <= 0) {
            this.isDone = true;
            return;
        }
        if (this.FvkGroup.size() == 1 && !this.optional) {
            if (this.player.hasPower(LvbuModHelper.MakePath(ShenWeiPower.class.getSimpleName()))) {
                final int drawNum = this.player.getPower(LvbuModHelper.MakePath(ShenWeiPower.class.getSimpleName())).amount * (this.player.hasPower(LvbuModHelper.MakePath(MatchlessPower.class.getSimpleName())) ? 2 : 1);
                this.addToBot((AbstractGameAction)new DrawCardAction(drawNum));
                this.player.getPower(LvbuModHelper.MakePath(ShenWeiPower.class.getSimpleName())).flash();
            }
            final ArrayList<AbstractCard> cardsToUse = new ArrayList<AbstractCard>(this.FvkGroup);
            for (final AbstractCard c2 : cardsToUse) {
                this.playCard(c2);
            }
            this.isDone = true;
            return;
        }
        if (this.FvkGroup.size() < this.numberOfCards) {
            this.numberOfCards = this.FvkGroup.size();
        }
        final CardGroup temp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (final AbstractCard c2 : this.FvkGroup) {
            temp.addToTop(c2);
        }
        temp.sortAlphabetically(true);
        temp.sortByRarityPlusStatusCardType(false);
        if (this.numberOfCards == 1) {
            if (this.optional) {
                AbstractDungeon.gridSelectScreen.open(temp, this.numberOfCards, true, FvkAction.TEXT[0]);
            }
            else {
                AbstractDungeon.gridSelectScreen.open(temp, this.numberOfCards, FvkAction.TEXT[0], false);
            }
        }
        else if (this.optional) {
            AbstractDungeon.gridSelectScreen.open(temp, this.numberOfCards, true, FvkAction.TEXT[1] + this.numberOfCards + FvkAction.TEXT[2]);
        }
        else {
            AbstractDungeon.gridSelectScreen.open(temp, this.numberOfCards, FvkAction.TEXT[1] + this.numberOfCards + FvkAction.TEXT[2], false);
        }
        this.tickDuration();
    }
    
    private void playCard(final AbstractCard card) {
        LvbuModHelper.setAnger(card, false);
        if (card.target == AbstractCard.CardTarget.ENEMY || card.target == AbstractCard.CardTarget.SELF_AND_ENEMY) {
            boolean ifContinue = false;
            for (final AbstractMonster m2 : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!m2.isDeadOrEscaped() && m2.currentHealth > 0) {
                    ifContinue = true;
                    break;
                }
            }
            if (ifContinue) {
                if (card.target == AbstractCard.CardTarget.ENEMY && card.hasTag(lvbu.Enums.BETRAY_CARD)) {
                    final ArrayList<AbstractMonster> chosenList = new ArrayList<AbstractMonster>();
                    for (final AbstractMonster m3 : AbstractDungeon.getCurrRoom().monsters.monsters) {
                        if (!m3.isDeadOrEscaped() && m3.currentHealth > 0 && m3.hasPower(DadPower.POWER_ID)) {
                            if (chosenList.size() == 0) {
                                chosenList.add(m3);
                            }
                            else {
                                final AbstractMonster chosenM = chosenList.get(0);
                                final int chosenMHHate = chosenM.hasPower(HatePower.POWER_ID) ? chosenM.getPower(HatePower.POWER_ID).amount : 0;
                                final int m2Hate = m3.hasPower(HatePower.POWER_ID) ? m3.getPower(HatePower.POWER_ID).amount : 0;
                                if (chosenM.hasPower(DadPower.POWER_ID) && chosenMHHate < m2Hate) {
                                    chosenList.clear();
                                    chosenList.add(m3);
                                }
                                else {
                                    if (!chosenM.hasPower(DadPower.POWER_ID) || chosenMHHate != m2Hate) {
                                        continue;
                                    }
                                    chosenList.add(m3);
                                }
                            }
                        }
                    }
                    AbstractMonster chosenMLast = null;
                    if (chosenList.size() == 1) {
                        chosenMLast = chosenList.get(0);
                    }
                    else if (chosenList.size() > 1) {
                        chosenMLast = chosenList.get(AbstractDungeon.cardRandomRng.random(0, chosenList.size() - 1));
                    }
                    if (chosenMLast == null) {
                        this.target = (AbstractCreature)AbstractDungeon.getCurrRoom().monsters.getRandomMonster((AbstractMonster)null, true, AbstractDungeon.cardRandomRng);
                    }
                    else {
                        this.target = (AbstractCreature)chosenMLast;
                    }
                }
                else {
                    this.target = (AbstractCreature)AbstractDungeon.getCurrRoom().monsters.getRandomMonster((AbstractMonster)null, true, AbstractDungeon.cardRandomRng);
                }
            }
        }
        if (card.target == AbstractCard.CardTarget.ENEMY && card.cardID.equals(LvbuModHelper.MakePath(GaoShun.class.getSimpleName())) && lvbu.GaoShunTarget != null) {
            this.target = (AbstractCreature)lvbu.GaoShunTarget;
        }
        AbstractDungeon.player.drawPile.group.remove(card);
        AbstractDungeon.getCurrRoom().souls.remove(card);
        AbstractDungeon.player.limbo.group.add(card);
        card.current_y = -200.0f * Settings.scale;
        card.target_x = Settings.WIDTH / 2.0f + 200.0f * Settings.xScale;
        card.target_y = Settings.HEIGHT / 2.0f;
        card.targetAngle = 0.0f;
        card.lighten(false);
        card.drawScale = 0.12f;
        card.targetDrawScale = 0.75f;
        card.applyPowers();
        if (this.gainEnergy && card.costForTurn != -2) {
            final int gainAmount = ((card.costForTurn == -1) ? EnergyPanel.getCurrentEnergy() : card.costForTurn) + (this.upgraded ? 1 : 0);
            this.addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this.player, (AbstractCreature)this.player, (AbstractPower)new EnergizedPower((AbstractCreature)this.player, gainAmount), gainAmount));
        }
        if (card.cardID.equals(LvbuModHelper.MakePath(HengSaoQianJun.class.getSimpleName())) && this.player.hasPower(LvbuModHelper.MakePath(MatchlessPower.class.getSimpleName()))) {
            this.addToTop((AbstractGameAction)new AbstractGameAction() {
                public void update() {
                    for (final AbstractCard c : AbstractDungeon.player.masterDeck.group) {
                        if (!c.uuid.equals(card.uuid)) {
                            continue;
                        }
                        final AbstractCard abstractCard = c;
                        abstractCard.misc += card.magicNumber;
                        c.applyPowers();
                        c.baseDamage = c.misc;
                        c.isDamageModified = false;
                    }
                    for (final AbstractCard abstractCard2 : GetAllInBattleInstances.get(card.uuid)) {
                        final AbstractCard c = abstractCard2;
                        abstractCard2.misc += card.magicNumber;
                        c.applyPowers();
                        c.baseDamage = c.misc;
                    }
                    this.isDone = true;
                }
            });
        }
        if (card.cardID.equals(LvbuModHelper.MakePath(ThrowJi.class.getSimpleName()))) {
            this.addToTop((AbstractGameAction)new GainEnergyAction(card.magicNumber));
        }
        if (card.cardID.equals(LvbuModHelper.MakePath(XuanZhuanFeiJi.class.getSimpleName()))) {
            if (card.costForTurn > 0) {
                card.costForTurn += -card.magicNumber * 2;
                if (card.costForTurn < -1) {
                    card.costForTurn = -1;
                }
                if (card.cost != card.costForTurn) {
                    card.isCostModified = true;
                }
                card.cost = card.costForTurn;
            }
            else if (card.cost >= 0) {
                card.cost += -card.magicNumber * 2;
                if (card.cost < -1) {
                    card.cost = -1;
                }
                card.costForTurn = -1;
                if (card.cost != card.costForTurn) {
                    card.isCostModified = true;
                }
            }
        }
        this.addToTop((AbstractGameAction)new NewQueueCardAction(card, this.target, false, true));
        this.addToTop((AbstractGameAction)new UnlimboAction(card));
        if (!Settings.FAST_MODE) {
            this.addToTop((AbstractGameAction)new WaitAction(Settings.ACTION_DUR_MED));
        }
        else {
            this.addToTop((AbstractGameAction)new WaitAction(Settings.ACTION_DUR_FASTER));
        }
    }
    
    static {
        TEXT = CardCrawlGame.languagePack.getUIString(LvbuModHelper.MakePath("FvkAction")).TEXT;
    }
}
