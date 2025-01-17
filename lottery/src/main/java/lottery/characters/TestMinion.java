package lottery.characters;

import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kobting.friendlyminions.monsters.AbstractFriendlyMonster;

public class TestMinion extends AbstractFriendlyMonster {
    
    private static String NAME = "打我呀";
    private static String ID = "TestingMinion";
    private AbstractMonster target;

    public TestMinion(int offsetX, int offsetY) {
        super(NAME, ID, 20, -8.0F, 10.0F, 230.0F, 240.0F, "lottery/images/relics/example.png", offsetX, offsetY);
    }
}