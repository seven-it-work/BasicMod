package org.seven.util;

import basemod.abstracts.CustomRelic;
import basemod.helpers.RelicType;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.RelicStrings;

public abstract class QuickStartRelic extends CustomRelic {
    public AbstractCard.CardColor pool = null;

    public RelicType relicType = RelicType.SHARED;

    protected String imageName;

    //for character specific relics
    public QuickStartRelic(String id, String imageName, AbstractCard.CardColor pool, RelicTier tier, LandingSound sfx) {
        this(id, imageName, tier, sfx);

        setPool(pool);
    }


    public QuickStartRelic(String id, RelicTier tier, LandingSound sfx) {
        this(id, GeneralUtils.removePrefix(id), tier, sfx);
    }

    //To use a basegame relic image, just pass in the imagename used by a basegame relic instead of the ID.
    //eg. "calendar.png"
    public QuickStartRelic(String id, String imageName, RelicTier tier, LandingSound sfx) {
        super(testStrings(id), notPng(imageName) ? "" : imageName, tier, sfx);

        this.imageName = imageName;
        if (notPng(imageName)) {
            loadTexture();
        }
    }

    protected abstract QuickStartMod quickStartMod();


    @Override
    public void loadLargeImg() {
        if (notPng(imageName)) {
            if (largeImg == null) {
                this.largeImg = ImageMaster.loadImage(quickStartMod().relicPath("large/" + imageName + ".png"));
            }
        }
        else {
            super.loadLargeImg();
        }
    }

    @Override
    public String getUpdatedDescription() {
        if (DESCRIPTIONS == null) {
            return super.getUpdatedDescription();
        }
        if (DESCRIPTIONS.length < 1) {
            return super.getUpdatedDescription();
        }
        return DESCRIPTIONS[0].replace(quickStartMod().getModInfo().ID + ":",
                " #y" + quickStartMod().getModInfo().ID + ":");
    }



    protected void loadTexture() {
        this.img = quickStartMod().getTextureNull(quickStartMod().relicPath(imageName + ".png"), true);
        if (img != null) {
            outlineImg = quickStartMod().getTextureNull(quickStartMod().relicPath(imageName + "Outline.png"), true);
            if (outlineImg == null)
                outlineImg = img;
        }
        else {
            ImageMaster.loadRelicImg("Derp Rock", "derpRock.png");
            this.img = ImageMaster.getRelicImg("Derp Rock");
            this.outlineImg = ImageMaster.getRelicOutlineImg("Derp Rock");
        }
    }

    private void setPool(AbstractCard.CardColor pool) {
        switch (pool) { //Basegame pools are handled differently
            case RED:
                relicType = RelicType.RED;
                break;
            case GREEN:
                relicType = RelicType.GREEN;
                break;
            case BLUE:
                relicType = RelicType.BLUE;
                break;
            case PURPLE:
                relicType = RelicType.PURPLE;
                break;
            default:
                this.pool = pool;
                break;
        }
    }

    /**
     * Checks whether relic has localization set up correctly and gives a more accurate error message if it does not
     * @param ID the relic's ID
     * @return the relic's ID, to allow use in super constructor invocation
     */
    private static String testStrings(String ID) {
        RelicStrings text = CardCrawlGame.languagePack.getRelicStrings(ID);
        if (text == null) {
            throw new RuntimeException("The \"" + ID + "\" relic does not have associated text. Make sure " +
                    "there's no issue with the jywx-RelicStrings.json file, and that the ID in the json file matches the " +
                    "relic's ID. It should look like \"${modID}:" + GeneralUtils.removePrefix(ID) + "\".");
        }
        return ID;
    }

    protected static boolean notPng(String name) {
        return !name.endsWith(".png");
    }
}
