package lvbumod.Skins;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.assets.loaders.*;
import com.badlogic.gdx.assets.*;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.graphics.g2d.*;

public class AssetLoader
{
    private AssetManager assets;
    
    public AssetLoader() {
        this.assets = new AssetManager();
    }
    
    public Texture loadImage(final String fileName) {
        if (!this.assets.isLoaded(fileName, (Class)Texture.class)) {
            final TextureLoader.TextureParameter param = new TextureLoader.TextureParameter();
            param.minFilter = Texture.TextureFilter.Linear;
            param.magFilter = Texture.TextureFilter.Linear;
            this.assets.load(fileName, (Class)Texture.class, (AssetLoaderParameters)param);
            try {
                this.assets.finishLoadingAsset(fileName);
            }
            catch (GdxRuntimeException e) {
                return null;
            }
        }
        return (Texture)this.assets.get(fileName, (Class)Texture.class);
    }
    
    public TextureAtlas loadAtlas(final String fileName) {
        if (!this.assets.isLoaded(fileName, (Class)TextureAtlas.class)) {
            this.assets.load(fileName, (Class)TextureAtlas.class);
            this.assets.finishLoadingAsset(fileName);
        }
        return (TextureAtlas)this.assets.get(fileName, (Class)TextureAtlas.class);
    }
}
