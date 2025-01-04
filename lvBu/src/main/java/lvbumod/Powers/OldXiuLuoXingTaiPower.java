package lvbumod.Powers;

import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.helpers.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.megacrit.cardcrawl.actions.utility.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.cards.*;
import java.util.*;
import com.megacrit.cardcrawl.core.*;

public class OldXiuLuoXingTaiPower extends AbstractPower
{
    public static final String POWER_ID;
    private static final PowerStrings powerStrings;
    private static final String NAME;
    private static final String[] DESCRIPTIONS;
    
    public OldXiuLuoXingTaiPower(final AbstractCreature owner, final int amount) {
        this.name = OldXiuLuoXingTaiPower.NAME;
        this.ID = OldXiuLuoXingTaiPower.POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
        this.amount = amount;
        final String path128 = "lvbuModResources/img/powers/XiuLuoFormPower84.png";
        final String path129 = "lvbuModResources/img/powers/XiuLuoFormPower32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path129), 0, 0, 32, 32);
        this.updateDescription();
    }
    
    public void onUseCard(final AbstractCard card, final UseCardAction action) {
        if (LvbuModHelper.checkStupid() && card.type == AbstractCard.CardType.ATTACK) {
            this.flash();
            for (int i = 0; i < this.amount; ++i) {
                this.addToTop((AbstractGameAction)new AbstractGameAction() {
                    public void update() {
                        final ArrayList<AbstractCard> groupCopy = new ArrayList<AbstractCard>();
                        for (final AbstractCard abstractCard : AbstractDungeon.player.hand.group) {
                            if (abstractCard.cost > 0 && abstractCard.costForTurn > 0 && !abstractCard.freeToPlayOnce) {
                                groupCopy.add(abstractCard);
                            }
                        }
                        for (final CardQueueItem i : AbstractDungeon.actionManager.cardQueue) {
                            if (i.card != null) {
                                groupCopy.remove(i.card);
                            }
                        }
                        AbstractCard c = null;
                        if (!groupCopy.isEmpty()) {
                            c = groupCopy.get(AbstractDungeon.cardRandomRng.random(0, groupCopy.size() - 1));
                        }
                        if (c != null) {
                            c.setCostForTurn(0);
                        }
                        this.isDone = true;
                    }
                });
            }
        }
    }
    
    public void updateDescription() {
        this.description = OldXiuLuoXingTaiPower.DESCRIPTIONS[0] + this.amount + OldXiuLuoXingTaiPower.DESCRIPTIONS[1];
    }
    
    static {
        POWER_ID = LvbuModHelper.MakePath(OldXiuLuoXingTaiPower.class.getSimpleName());
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(OldXiuLuoXingTaiPower.POWER_ID);
        NAME = OldXiuLuoXingTaiPower.powerStrings.NAME;
        DESCRIPTIONS = OldXiuLuoXingTaiPower.powerStrings.DESCRIPTIONS;
    }
}
