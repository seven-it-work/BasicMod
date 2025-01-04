package lvbumod.Skins;

import com.megacrit.cardcrawl.dungeons.*;
import lvbumod.Characters.*;
import lvbumod.ui.*;
import com.megacrit.cardcrawl.core.*;
import lvbumod.Helpers.*;

public class LvbuSkinCharacter
{
    public String id;
    public boolean reskinUnlockLvbu;
    public int reskinCount;
    public AbstractLvbuSkin[] skins;
    public static final String ID;
    
    public LvbuSkinCharacter(final String id, final AbstractLvbuSkin[] skins) {
        this.reskinUnlockLvbu = false;
        this.reskinCount = 0;
        this.id = id;
        this.skins = skins;
    }
    
    public void InitializeReskinCount() {
        if (this.reskinCount < 0) {
            this.reskinCount = 0;
        }
    }
    
    public Boolean isOriginal() {
        return this.reskinCount <= 0;
    }
    
    public void checkUnlock() {
        if (AbstractDungeon.player.chosenClass == lvbu.Enums.LVBU && !this.reskinUnlockLvbu) {
            AbstractDungeon.topLevelEffects.add(new ReskinUnlockedTextEffect(0));
            this.reskinUnlockLvbu = true;
        }
    }
    
    static {
        ID = CardCrawlGame.languagePack.getCharacterString(LvbuModHelper.MakePath("Lvbu")).NAMES[0];
    }
}
