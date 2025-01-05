package JinYongWuXia.cards.attack;

import basemod.BaseMod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class EighteenDragonSubduingPalmConstant {

    public static final List<AbstractEighteenDragonSubduingPalm> ABSTRACT_EIGHTEEN_DRAGON_SUBDUING_PALM_LIST
        = new ArrayList<>();

    private static final Logger logger = LogManager.getLogger(EighteenDragonSubduingPalmConstant.class);

    static {
        ABSTRACT_EIGHTEEN_DRAGON_SUBDUING_PALM_LIST.add(new EighteenDragonSubduingPalm1());
        ABSTRACT_EIGHTEEN_DRAGON_SUBDUING_PALM_LIST.add(new EighteenDragonSubduingPalm2());
        ABSTRACT_EIGHTEEN_DRAGON_SUBDUING_PALM_LIST.add(new EighteenDragonSubduingPalm3());
        ABSTRACT_EIGHTEEN_DRAGON_SUBDUING_PALM_LIST.add(new EighteenDragonSubduingPalm4());
        ABSTRACT_EIGHTEEN_DRAGON_SUBDUING_PALM_LIST.add(new EighteenDragonSubduingPalm5());
        ABSTRACT_EIGHTEEN_DRAGON_SUBDUING_PALM_LIST.add(new EighteenDragonSubduingPalm6());
        ABSTRACT_EIGHTEEN_DRAGON_SUBDUING_PALM_LIST.add(new EighteenDragonSubduingPalm7());
        ABSTRACT_EIGHTEEN_DRAGON_SUBDUING_PALM_LIST.add(new EighteenDragonSubduingPalm8());
        ABSTRACT_EIGHTEEN_DRAGON_SUBDUING_PALM_LIST.add(new EighteenDragonSubduingPalm9());
        ABSTRACT_EIGHTEEN_DRAGON_SUBDUING_PALM_LIST.add(new EighteenDragonSubduingPalm10());
        ABSTRACT_EIGHTEEN_DRAGON_SUBDUING_PALM_LIST.add(new EighteenDragonSubduingPalm11());
        ABSTRACT_EIGHTEEN_DRAGON_SUBDUING_PALM_LIST.add(new EighteenDragonSubduingPalm12());
        ABSTRACT_EIGHTEEN_DRAGON_SUBDUING_PALM_LIST.add(new EighteenDragonSubduingPalm13());
        ABSTRACT_EIGHTEEN_DRAGON_SUBDUING_PALM_LIST.add(new EighteenDragonSubduingPalm14());
        ABSTRACT_EIGHTEEN_DRAGON_SUBDUING_PALM_LIST.add(new EighteenDragonSubduingPalm15());
        ABSTRACT_EIGHTEEN_DRAGON_SUBDUING_PALM_LIST.add(new EighteenDragonSubduingPalm16());
        ABSTRACT_EIGHTEEN_DRAGON_SUBDUING_PALM_LIST.add(new EighteenDragonSubduingPalm17());
        ABSTRACT_EIGHTEEN_DRAGON_SUBDUING_PALM_LIST.add(new EighteenDragonSubduingPalm18());
    }

    public static void initAbstractEighteenDragonSubduingPalm() {
        logger.info("降龙十八掌初始化成功");
        EighteenDragonSubduingPalmConstant.ABSTRACT_EIGHTEEN_DRAGON_SUBDUING_PALM_LIST.forEach(BaseMod::addCard);
    }
}
