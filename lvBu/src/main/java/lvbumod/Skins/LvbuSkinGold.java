package lvbumod.Skins;

import com.megacrit.cardcrawl.core.*;
import lvbumod.Helpers.*;
import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.helpers.*;

public class LvbuSkinGold extends AbstractLvbuSkin
{
    public LvbuSkinGold() {
        this.DESCRIPTION = CardCrawlGame.languagePack.getUIString(LvbuModHelper.MakePath(LvbuSkinGold.class.getSimpleName())).EXTRA_TEXT[0];
        this.NAME = CardCrawlGame.languagePack.getUIString(LvbuModHelper.MakePath(LvbuSkinGold.class.getSimpleName())).TEXT[0];
        this.portraitStatic_IMG = new Texture(LvbuModHelper.getAssetPath(LvbuSkinGold.class.getSimpleName(), "Character_Portrait_2.png"));
        this.orgLvbuPath = LvbuModHelper.getAssetPath(LvbuSkinGold.class.getSimpleName(), "character_2.png");
        this.orgLvbu = ImageMaster.loadImage(this.orgLvbuPath);
        this.orgLvbuJi = ImageMaster.loadImage(LvbuModHelper.getAssetPath(LvbuSkinGold.class.getSimpleName(), "character_2_ji.png"));
        this.orgLvbuMatchless = ImageMaster.loadImage(LvbuModHelper.getAssetPath(LvbuSkinGold.class.getSimpleName(), "character_2_matchless.png"));
        this.orgLvbuMatchlessJi = ImageMaster.loadImage(LvbuModHelper.getAssetPath(LvbuSkinGold.class.getSimpleName(), "character_2_matchless_ji.png"));
    }
    
    @Override
    public void refresh() {
        this.orgLvbuPath = LvbuModHelper.getAssetPath(LvbuSkinGold.class.getSimpleName(), "character_2.png");
        this.orgLvbu = ImageMaster.loadImage(this.orgLvbuPath);
        this.orgLvbuJi = ImageMaster.loadImage(LvbuModHelper.getAssetPath(LvbuSkinGold.class.getSimpleName(), "character_2_ji.png"));
        this.orgLvbuMatchless = ImageMaster.loadImage(LvbuModHelper.getAssetPath(LvbuSkinGold.class.getSimpleName(), "character_2_matchless.png"));
        this.orgLvbuMatchlessJi = ImageMaster.loadImage(LvbuModHelper.getAssetPath(LvbuSkinGold.class.getSimpleName(), "character_2_matchless_ji.png"));
    }
}
