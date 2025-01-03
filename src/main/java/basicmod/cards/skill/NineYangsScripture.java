package basicmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.powers.ConservePower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.DoubleDamagePower;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.watcher.BlockReturnPower;
import com.megacrit.cardcrawl.powers.watcher.FreeAttackPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import basicmod.cards.BaseCard;
import basicmod.util.CardStats;
import basicmod.util.GeneralUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class NineYangsScripture extends BaseCard {

    public static final String ID = makeID(NineYangsScripture.class.getSimpleName());

    private static final CardStats info = new CardStats(CardColor.COLORLESS, CardType.SKILL, CardRarity.BASIC,
            CardTarget.SELF, 1);

    private static final ArrayList<Class> EFFECT = new ArrayList<>(Arrays.asList(
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
    ));


    public NineYangsScripture() {
        super(ID, info);
        setMagic(2, 1);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        GeneralUtils.getRandomElementsUsingSet(EFFECT, this.baseMagicNumber).forEach(aclass -> {
            AbstractPower power = null;
            if (aclass.equals(StrengthPower.class)) {
                power = new StrengthPower(abstractPlayer, 9);
            } else if (aclass.equals(NextTurnBlockPower.class)) {
                power = new NextTurnBlockPower(abstractPlayer, 9);
            } else if (aclass.equals(PlatedArmorPower.class)) {
                power = new PlatedArmorPower(abstractPlayer, 9);
            } else if (aclass.equals(DexterityPower.class)) {
                power = new DexterityPower(abstractPlayer, 9);
            } else if (aclass.equals(VigorPower.class)) {
                power = new VigorPower(abstractPlayer, 9);
            } else if (aclass.equals(BlockReturnPower.class)) {
                power = new BlockReturnPower(abstractPlayer, 9);
            } else if (aclass.equals(ArtifactPower.class)) {
                power = new ArtifactPower(abstractPlayer, 9);
            } else if (aclass.equals(BufferPower.class)) {
                power = new BufferPower(abstractPlayer, 9);
            } else if (aclass.equals(ConservePower.class)) {
                power = new ConservePower(abstractPlayer, 9);
            } else if (aclass.equals(DoubleDamagePower.class)) {
                power = new DoubleDamagePower(abstractPlayer, 9, false);
            } else if (aclass.equals(FreeAttackPower.class)) {
                power = new FreeAttackPower(abstractPlayer, 9);
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
