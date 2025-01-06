package lottery.cards.status;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.devcommands.relic.RelicAdd;
import cn.hutool.core.util.RandomUtil;
import lottery.LotteryMod;
import lottery.cards.BaseCard;
import lottery.relics.SanLianRelic;

import org.seven.util.CardStats;

public class SanLianReward extends BaseCard {
    public static final String ID = LotteryMod.resourcePath.makeID(SanLianReward.class.getSimpleName());

    private static final CardStats info = new CardStats(CardColor.COLORLESS, CardType.STATUS, CardRarity.CURSE,
        CardTarget.SELF, 0);

    private boolean used = false;

    public SanLianReward() {
        super(ID, info);
    }

    public SanLianReward(CardRarity cardRarity) {
        super(ID, info);
        this.rarity = cardRarity;
        this.exhaust = true;
        setMagic(1,1);
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return super.canUse(p, m) && !used;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        for (int i = 0; i < this.magicNumber; i++) {
            // RelicLibrary 中获取
            switch (this.rarity) {
                case RARE:
                    addTempRelic(abstractPlayer, RandomUtil.randomEle(RelicLibrary.rareList).makeCopy());
                    break;
                case BASIC:
                    addTempRelic(abstractPlayer, RandomUtil.randomEle(RelicLibrary.starterList).makeCopy());
                    break;
                case SPECIAL:
                    addTempRelic(abstractPlayer, RandomUtil.randomEle(RelicLibrary.specialList).makeCopy());
                    break;
                case COMMON:
                    addTempRelic(abstractPlayer, RandomUtil.randomEle(RelicLibrary.commonList).makeCopy());
                    break;
                case UNCOMMON:
                    addTempRelic(abstractPlayer, RandomUtil.randomEle(RelicLibrary.uncommonList).makeCopy());
                    break;
                case CURSE:
                default:
                    addTempRelic(abstractPlayer, RandomUtil.randomEle(RelicLibrary.shopList).makeCopy());
                    break;
            }
        }
        this.used = true;
    }

    private void addTempRelic(AbstractPlayer abstractPlayer, AbstractRelic abstractRelicCurse) {
        abstractPlayer.relics.add(abstractRelicCurse);
        SanLianRelic.THIS_BATTLE_ADD_RELICS.add(abstractRelicCurse);
        abstractRelicCurse.playLandingSFX();
        AbstractDungeon.player.reorganizeRelics();
        AbstractRelic.relicPage = 0;
        AbstractDungeon.topPanel.adjustRelicHbs();
        this.addToTop(new RelicAboveCreatureAction(abstractPlayer, abstractPlayer.getRelic(SanLianRelic.ID)));
    }
}
