package lvbumod.Relics;

import basemod.abstracts.*;
import com.megacrit.cardcrawl.relics.*;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.actions.utility.*;
import lvbumod.Characters.*;
import com.megacrit.cardcrawl.dungeons.*;
import lvbumod.Powers.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.characters.*;

public class BingQiJia extends CustomRelic
{
    public static final String ID;
    private static final String IMG_PATH = "lvbuModResources/img/relics/BingQiJia.png";
    private static final String IMG_PATH_O = "lvbuModResources/img/relics/BingQiJiaO.png";
    private static final RelicTier RELIC_TIER;
    private static final LandingSound LANDING_SOUND;
    private int needCount;
    private final int amount = 2;
    
    public BingQiJia() {
        super(BingQiJia.ID, ImageMaster.loadImage("lvbuModResources/img/relics/BingQiJia.png"), ImageMaster.loadImage("lvbuModResources/img/relics/BingQiJiaO.png"), BingQiJia.RELIC_TIER, BingQiJia.LANDING_SOUND);
        this.needCount = 3;
    }
    
    public String getUpdatedDescription() {
        final StringBuilder append = new StringBuilder().append(this.DESCRIPTIONS[0]);
        this.getClass();
        return append.append(2).append(this.DESCRIPTIONS[1]).toString();
    }
    
    public AbstractRelic makeCopy() {
        return (AbstractRelic)new BingQiJia();
    }
    
    public void onEquip() {
        this.counter = 0;
    }
    
    public void onUseCard(final AbstractCard card, final UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK && card.hasTag(lvbu.Enums.JI_ATTACK_CARD)) {
            ++this.counter;
            if (this.counter == this.needCount) {
                this.counter = 0;
                this.flash();
                this.pulse = false;
                if (AbstractDungeon.player.hasPower(LvbuModHelper.MakePath(FangTianHuaJiPower.class.getSimpleName()))) {
                    final AbstractPlayer player = AbstractDungeon.player;
                    final AbstractPlayer player2 = AbstractDungeon.player;
                    final AbstractPlayer player3 = AbstractDungeon.player;
                    this.getClass();
                    final FangTianHuaJiPower fangTianHuaJiPower = new FangTianHuaJiPower((AbstractCreature)player3, -2);
                    this.getClass();
                    this.addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)player, (AbstractCreature)player2, (AbstractPower)fangTianHuaJiPower, -2, true));
                }
            }
            else if (this.counter == this.needCount - 1) {
                this.beginPulse();
                this.pulse = true;
                AbstractDungeon.player.hand.refreshHandLayout();
                this.addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, (AbstractRelic)this));
                final AbstractPlayer player4 = AbstractDungeon.player;
                final AbstractPlayer player5 = AbstractDungeon.player;
                final AbstractPlayer player6 = AbstractDungeon.player;
                this.getClass();
                final FangTianHuaJiPower fangTianHuaJiPower2 = new FangTianHuaJiPower((AbstractCreature)player6, 2);
                this.getClass();
                this.addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)player4, (AbstractCreature)player5, (AbstractPower)fangTianHuaJiPower2, 2, true));
            }
        }
    }
    
    public void atBattleStart() {
        if (this.counter == this.needCount - 1) {
            this.beginPulse();
            this.pulse = true;
            final AbstractPlayer player = AbstractDungeon.player;
            final AbstractPlayer player2 = AbstractDungeon.player;
            final AbstractPlayer player3 = AbstractDungeon.player;
            this.getClass();
            final FangTianHuaJiPower fangTianHuaJiPower = new FangTianHuaJiPower((AbstractCreature)player3, 2);
            this.getClass();
            this.addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)player, (AbstractCreature)player2, (AbstractPower)fangTianHuaJiPower, 2, true));
        }
    }
    
    static {
        ID = LvbuModHelper.MakePath(BingQiJia.class.getSimpleName());
        RELIC_TIER = RelicTier.COMMON;
        LANDING_SOUND = LandingSound.FLAT;
    }
}
