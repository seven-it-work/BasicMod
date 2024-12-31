package basicmod.cards.skill;

import basicmod.cards.BaseCard;
import basicmod.cards.attack.EighteenDragonSubduingPalm1;
import basicmod.util.CardStats;
import cn.hutool.core.util.RandomUtil;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.DarkShackles;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.powers.watcher.BlockReturnPower;
import com.megacrit.cardcrawl.powers.watcher.FreeAttackPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import java.util.Arrays;
import java.util.List;

public class NineYangsScripture extends BaseCard {

    public static final String ID = makeID(EighteenDragonSubduingPalm1.class.getSimpleName());

    private static final CardStats info = new CardStats(CardColor.COLORLESS, CardType.SKILL, CardRarity.BASIC,
            CardTarget.ENEMY, 1);

    private static final List<Class> EFFECT = Arrays.asList(
            // 力量
            StrengthPower.class,
            // 下回合格挡
            NextTurnBlockPower.class,
            // 多重护甲
            PlatedArmorPower.class,
            // 敏捷
            DexterityPower.class,
            // 活力
            VigorPower.class,
            // 格挡返还
            BlockReturnPower.class,
            // 人工制品
            ArtifactPower.class,
            // 缓冲
            BufferPower.class,
            // 保留
            ConservePower.class,
            // 双倍伤害
            DoubleDamagePower.class,
            // 免费攻击
            FreeAttackPower.class
    );


    public NineYangsScripture() {
        super(ID, info);
        setMagic(2, 3);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        RandomUtil.randomEleList(EFFECT, this.baseMagicNumber).forEach(aclass -> {
            AbstractPower power = null;
            if (aclass.equals(StrengthPower.class)) {
                power = new FrailPower(abstractMonster, 9, false);
            } else if (aclass.equals(NextTurnBlockPower.class)) {
                power = new NextTurnBlockPower(abstractMonster, 9);
            } else if (aclass.equals(PlatedArmorPower.class)) {
                power = new PlatedArmorPower(abstractMonster, 9);
            } else if (aclass.equals(DexterityPower.class)) {
                power = new DexterityPower(abstractMonster, 9);
            } else if (aclass.equals(VigorPower.class)) {
                power = new VigorPower(abstractMonster, 9);
            } else if (aclass.equals(BlockReturnPower.class)) {
                power = new BlockReturnPower(abstractMonster, 9);
            } else if (aclass.equals(ArtifactPower.class)) {
                power = new ArtifactPower(abstractMonster, 9);
            } else if (aclass.equals(BufferPower.class)) {
                power = new BufferPower(abstractMonster, 9);
            } else if (aclass.equals(ConservePower.class)) {
                power = new ConservePower(abstractMonster, 9);
            } else if (aclass.equals(DoubleDamagePower.class)) {
                power = new DoubleDamagePower(abstractMonster, 9, false);
            } else if (aclass.equals(FreeAttackPower.class)) {
                power = new FreeAttackPower(abstractMonster, 9);
            } else {
                // 不支持请继续添加
            }
            if (power != null) {
                this.addToBot(new ApplyPowerAction(abstractPlayer, abstractPlayer, power, 9));
            }
        });
    }


    @Override
    public AbstractCard makeCopy() {
        return new NineYangsScripture();
    }
}
