package lvbumod.Skins;

import com.megacrit.cardcrawl.core.*;
import lvbumod.Helpers.*;
import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.helpers.*;

public class LvbuSkinOriginal extends AbstractLvbuSkin
{
    private String ds;
    
    public LvbuSkinOriginal() {
        this.ds = CardCrawlGame.languagePack.getUIString(LvbuModHelper.MakePath(LvbuSkinOriginal.class.getSimpleName())).EXTRA_TEXT[0];
        this.DESCRIPTION = this.ds;
        this.NAME = CardCrawlGame.languagePack.getUIString(LvbuModHelper.MakePath(LvbuSkinOriginal.class.getSimpleName())).TEXT[0];
        this.portraitStatic_IMG = new Texture(LvbuModHelper.getAssetPath("", "Character_Portrait.png"));
        this.orgLvbuPath = LvbuModHelper.getAssetPath("", "character_1.png");
        this.orgLvbu = ImageMaster.loadImage(this.orgLvbuPath);
        this.orgLvbuJi = ImageMaster.loadImage(LvbuModHelper.getAssetPath("", "character_1_ji.png"));
        this.orgLvbuMatchless = ImageMaster.loadImage(LvbuModHelper.getAssetPath("", "character_1_matchless.png"));
        this.orgLvbuMatchlessJi = ImageMaster.loadImage(LvbuModHelper.getAssetPath("", "character_1_matchless_ji.png"));
    }
    
    @Override
    public void refresh() {
        this.orgLvbuPath = LvbuModHelper.getAssetPath("", "character_1.png");
        this.orgLvbu = ImageMaster.loadImage(this.orgLvbuPath);
        this.orgLvbuJi = ImageMaster.loadImage(LvbuModHelper.getAssetPath("", "character_1_ji.png"));
        this.orgLvbuMatchless = ImageMaster.loadImage(LvbuModHelper.getAssetPath("", "character_1_matchless.png"));
        this.orgLvbuMatchlessJi = ImageMaster.loadImage(LvbuModHelper.getAssetPath("", "character_1_matchless_ji.png"));
    }
}
