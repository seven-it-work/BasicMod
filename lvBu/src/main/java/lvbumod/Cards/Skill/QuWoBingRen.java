package lvbumod.Cards.Skill;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.dungeons.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.actions.common.*;
import lvbumod.Powers.*;
import java.util.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class QuWoBingRen extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 0;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public QuWoBingRen() {
        super(QuWoBingRen.ID, false, QuWoBingRen.CARD_STRINGS, 0, QuWoBingRen.TYPE, QuWoBingRen.COLOR, QuWoBingRen.RARITY, QuWoBingRen.TARGET);
        this.setupMagicNumber(2);
        this.exhaust = true;
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeMagicNumber(1);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)p, (AbstractPower)new WeakPower((AbstractCreature)m, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        final ArrayList<AbstractCard> qCardList = new ArrayList<AbstractCard>();
        for (final AbstractCard r : CardLibrary.getAllCards()) {
            if (r.color == AbstractDungeon.player.getCardColor() && r.type.equals((Object) CardType.ATTACK) && !r.hasTag(CardTags.HEALING) && !r.rarity.equals((Object) CardRarity.BASIC) && r.cost >= this.magicNumber) {
                qCardList.add(r);
            }
        }
        final AbstractCard l = qCardList.get(AbstractDungeon.cardRandomRng.random(qCardList.size() - 1)).makeCopy();
        this.addToBot((AbstractGameAction)new MakeTempCardInDrawPileAction(l, 1, false, false, true));
        if (p.hasPower(LvbuModHelper.MakePath(ZhouXuanPower.class.getSimpleName()))) {
            this.addToTop((AbstractGameAction)new GainBlockAction((AbstractCreature)p, p.getPower(LvbuModHelper.MakePath(ZhouXuanPower.class.getSimpleName())).amount));
            if (p.hasPower(LvbuModHelper.MakePath(MatchlessPower.class.getSimpleName()))) {
                this.addToTop((AbstractGameAction)new GainBlockAction((AbstractCreature)p, p.getPower(LvbuModHelper.MakePath(ZhouXuanPower.class.getSimpleName())).amount));
            }
        }
        LvbuModHelper.setAnger(l, true);
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new QuWoBingRen();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(QuWoBingRen.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(QuWoBingRen.ID);
        TYPE = CardType.SKILL;
        RARITY = CardRarity.UNCOMMON;
        TARGET = CardTarget.ENEMY;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
