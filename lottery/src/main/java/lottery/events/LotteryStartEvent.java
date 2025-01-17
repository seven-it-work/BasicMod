
package lottery.events;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;

public class LotteryStartEvent extends AbstractImageEvent {
    public static final String ID = "My First Event";
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    //This text should be set up through loading an event localization json file
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String NAME = eventStrings.NAME;

    public LotteryStartEvent() {
        super(NAME, DESCRIPTIONS[0], "img/events/eventpicture.png");
    }

    @Override
    public void onEnterRoom() {
        super.onEnterRoom();
    }

    @Override
    protected void buttonEffect(int i) {

    }
}
