/*
 * Copyright (c) Huawei Technologies Co., Lq. 2023-2023. All rights reserved
 */

package jinyongwuxia.potions;


import com.badlogic.gdx.graphics.Color;
import org.seven.util.QuickStartPotion;

public abstract class BasePotion extends QuickStartPotion {
    public BasePotion(String id, int potency, PotionRarity rarity, PotionSize shape, Color liquidColor, Color hybridColor, Color spotsColor) {
        super(id, potency, rarity, shape, liquidColor, hybridColor, spotsColor);
    }

    public BasePotion(String id, int potency, PotionRarity rarity, PotionSize size, PotionColor color) {
        super(id, potency, rarity, size, color);
    }
}
