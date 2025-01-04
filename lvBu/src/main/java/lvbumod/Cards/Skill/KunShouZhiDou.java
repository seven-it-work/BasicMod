package lvbumod.Cards.Skill;

import lvbumod.Cards.Abstract.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import lvbumod.Actions.*;
import com.megacrit.cardcrawl.actions.*;
import lvbumod.Helpers.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Characters.*;

public class KunShouZhiDou extends LvbuAllCards
{
    public static final String ID;
    private static final CardStrings CARD_STRINGS;
    private static final int COST = 1;
    private static final CardType TYPE;
    private static final CardRarity RARITY;
    private static final CardTarget TARGET;
    private static final CardColor COLOR;
    
    public KunShouZhiDou() {
        super(KunShouZhiDou.ID, false, KunShouZhiDou.CARD_STRINGS, 1, KunShouZhiDou.TYPE, KunShouZhiDou.COLOR, KunShouZhiDou.RARITY, KunShouZhiDou.TARGET);
        this.setupBlock(5);
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeBlock(2);
    }
    
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        this.addToBot((AbstractGameAction)new KunShouZhiDouAction(this.block));
    }
    
    public AbstractCard makeCopy() {
        final AbstractCard c = (AbstractCard)new KunShouZhiDou();
        return c;
    }
    
    static {
        ID = LvbuModHelper.MakePath(KunShouZhiDou.class.getSimpleName());
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(KunShouZhiDou.ID);
        TYPE = CardType.SKILL;
        RARITY = CardRarity.UNCOMMON;
        TARGET = CardTarget.SELF;
        COLOR = lvbu.Enums.LVBU_CARD;
    }
}
