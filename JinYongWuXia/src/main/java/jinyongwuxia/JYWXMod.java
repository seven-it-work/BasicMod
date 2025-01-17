package jinyongwuxia;

import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import org.seven.util.QuickStartMod;


@SpireInitializer
public class JYWXMod extends QuickStartMod {

    public static JYWXMod MOD;

    public JYWXMod() {
        super(loadModInfo(JYWXMod.class));
        MOD = this;
        logger.info(this.getModId() + " subscribed to QuickStartMod.");
    }

    public static void initialize() {
        new JYWXMod();
    }
}
