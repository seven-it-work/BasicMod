package lvbumod.Skins;

import com.badlogic.gdx.*;
import com.esotericsoftware.spine.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.megacrit.cardcrawl.core.*;

public abstract class AbstractLvbuSkin
{
    public Texture portraitStatic_IMG;
    public Texture portraitAnimation_IMG;
    public String orgLvbuPath;
    public Texture orgLvbu;
    public Texture orgLvbuMatchless;
    public Texture orgLvbuJi;
    public Texture orgLvbuMatchlessJi;
    public TextureAtlas portraitAtlas;
    public Skeleton portraitSkeleton;
    public AnimationState portraitState;
    public AnimationStateData portraitStateData;
    public SkeletonData portraitData;
    public String portraitAtlasPath;
    public String NAME;
    public int portraitAnimationType;
    public boolean forcePortraitAnimationType;
    public String SHOULDER1;
    public String SHOULDER2;
    public String CORPSE;
    public String atlasURL;
    public String jsonURL;
    public float renderscale;
    public String DESCRIPTION;
    
    public AbstractLvbuSkin() {
        this.portraitAtlas = null;
        this.portraitAtlasPath = null;
        this.portraitAnimationType = 0;
        this.forcePortraitAnimationType = false;
        this.DESCRIPTION = null;
    }
    
    public void loadPortraitAnimation() {
        if (this.hasAnimation()) {
            this.loadAnimation();
            this.setAnimation();
            this.InitializeStaticPortraitVar();
        }
    }
    
    public Boolean hasAnimation() {
        return this.portraitAtlasPath != null;
    }
    
    public void loadAnimation() {
        this.portraitAtlas = new TextureAtlas(Gdx.files.internal(this.portraitAtlasPath + ".atlas"));
        final SkeletonJson json = new SkeletonJson(this.portraitAtlas);
        json.setScale(Settings.scale / 1.0f);
        this.portraitData = json.readSkeletonData(Gdx.files.internal(this.portraitAtlasPath + ".json"));
        (this.portraitSkeleton = new Skeleton(this.portraitData)).setColor(Color.WHITE);
        this.portraitStateData = new AnimationStateData(this.portraitData);
        this.portraitState = new AnimationState(this.portraitStateData);
        this.portraitStateData.setDefaultMix(0.2f);
        this.portraitState.setTimeScale(1.0f);
    }
    
    public void setAnimation() {
        this.portraitState.setAnimation(1, "fade_in", false);
        this.portraitState.addAnimation(0, "idle", true, 0.0f);
        this.InitializeStaticPortraitVar();
    }
    
    public void InitializeStaticPortraitVar() {
    }
    
    public Texture updateBgImg() {
        LvbuSkinAll.saveSettings();
        if (this.portraitAnimationType == 0 || !this.hasAnimation()) {
            return this.portraitStatic_IMG;
        }
        return this.portraitAnimation_IMG;
    }
    
    public void render(final SpriteBatch sb) {
        if (this.hasAnimation() && this.portraitAnimationType > 0) {
            this.portraitState.update(Gdx.graphics.getDeltaTime());
            this.portraitState.apply(this.portraitSkeleton);
            this.portraitSkeleton.updateWorldTransform();
            this.setPos();
            this.skeletonRenderUpdate(sb);
            this.portraitSkeleton.setColor(Color.WHITE.cpy());
            this.portraitSkeleton.setFlip(false, false);
            sb.end();
            CardCrawlGame.psb.begin();
            this.skeletonRender(sb);
        }
    }
    
    public void setPos() {
    }
    
    public void skeletonRenderUpdate(final SpriteBatch sb) {
    }
    
    public void skeletonRender(final SpriteBatch sb) {
        if (this.hasAnimation()) {
            AbstractCreature.sr.draw(CardCrawlGame.psb, this.portraitSkeleton);
            CardCrawlGame.psb.end();
            sb.begin();
        }
    }
    
    public void update() {
    }
    
    public void clearWhenClick() {
    }
    
    public void extraHitboxRender(final SpriteBatch sb) {
    }
    
    public Boolean extraHitboxClickCheck() {
        return false;
    }
    
    public String getNewCharDescription() {
        if (this.DESCRIPTION != null) {
            return this.DESCRIPTION;
        }
        return "";
    }
    
    public String getAtlasURL() {
        return this.atlasURL;
    }
    
    public String getJsonURL() {
        return this.jsonURL;
    }
    
    public String getSHOULDER1() {
        return this.SHOULDER1;
    }
    
    public String getSHOULDER2() {
        return this.SHOULDER2;
    }
    
    public String getCORPSE() {
        return this.CORPSE;
    }
    
    public void dispose() {
        if (this.portraitAtlas != null) {
            this.portraitAtlas.dispose();
        }
    }
    
    public void refresh() {
    }
}
