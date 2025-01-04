package lvbumod.Interface;

import basemod.interfaces.*;
import com.megacrit.cardcrawl.core.*;

public interface SaveLoadSubscriber extends StartGameSubscriber
{
    default void receiveStartGame() {
        if (CardCrawlGame.loadingSave) {
            this.onLoadGame();
        }
    }
    
    void onLoadGame();
}
