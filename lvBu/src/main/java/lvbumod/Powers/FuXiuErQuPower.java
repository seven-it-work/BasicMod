package lvbumod.Powers;

import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.helpers.*;
import com.badlogic.gdx.graphics.g2d.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.cards.*;
import java.util.*;
import com.megacrit.cardcrawl.core.*;

public class FuXiuErQuPower extends AbstractPower
{
    public static final String POWER_ID;
    private static final PowerStrings powerStrings;
    private static final String NAME;
    private static final String[] DESCRIPTIONS;
    
    public FuXiuErQuPower(final AbstractCreature owner, final int amount) {
        this.name = FuXiuErQuPower.NAME;
        this.ID = FuXiuErQuPower.POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = amount;
        final String path128 = "lvbuModResources/img/powers/FuXiuErQuPower84.png";
        final String path129 = "lvbuModResources/img/powers/FuXiuErQuPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path129), 0, 0, 32, 32);
        this.updateDescription();
    }
    
    public void atEndOfTurnPreEndTurnCards(final boolean isPlayer) {
        super.atEndOfTurnPreEndTurnCards(isPlayer);
        if (this.owner.hasPower(LvbuModHelper.MakePath(MatchlessPower.class.getSimpleName()))) {
            this.onSpecificTrigger();
        }
    }
    
    public void onSpecificTrigger() {
        int playAmount = this.amount;
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() < this.amount) {
            playAmount = AbstractDungeon.actionManager.cardsPlayedThisTurn.size();
        }
        final ArrayList<AbstractCard> prepareToPlay = new ArrayList<AbstractCard>();
        for (int i = 0; i < playAmount; ++i) {
            prepareToPlay.add(AbstractDungeon.actionManager.cardsPlayedThisTurn.get(AbstractDungeon.actionManager.cardsPlayedThisTurn.size() - i - 1));
        }
        for (final AbstractCard card : prepareToPlay) {
            if (!card.purgeOnUse && this.amount > 0) {
                this.flash();
                AbstractMonster m = null;
                if (card.target == AbstractCard.CardTarget.ENEMY) {
                    m = AbstractDungeon.getCurrRoom().monsters.getRandomMonster((AbstractMonster)null, true, AbstractDungeon.cardRandomRng);
                }
                final AbstractCard tmp = card.makeSameInstanceOf();
                AbstractDungeon.player.limbo.addToBottom(tmp);
                tmp.current_x = card.current_x;
                tmp.current_y = card.current_y;
                tmp.target_x = Settings.WIDTH / 2.0f - 300.0f * Settings.scale;
                tmp.target_y = Settings.HEIGHT / 2.0f;
                if (m != null) {
                    tmp.calculateCardDamage(m);
                }
                tmp.purgeOnUse = true;
                AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, card.energyOnUse, true, true), true);
            }
        }
    }
    
    public void updateDescription() {
        if (this.amount == 1) {
            this.description = FuXiuErQuPower.DESCRIPTIONS[0];
        }
        else {
            this.description = FuXiuErQuPower.DESCRIPTIONS[1] + this.amount + FuXiuErQuPower.DESCRIPTIONS[2];
        }
    }
    
    static {
        POWER_ID = LvbuModHelper.MakePath(FuXiuErQuPower.class.getSimpleName());
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(FuXiuErQuPower.POWER_ID);
        NAME = FuXiuErQuPower.powerStrings.NAME;
        DESCRIPTIONS = FuXiuErQuPower.powerStrings.DESCRIPTIONS;
    }
}
