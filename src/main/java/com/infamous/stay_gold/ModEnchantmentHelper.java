package com.infamous.stay_gold;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class ModEnchantmentHelper {

    public static final Tags.IOptionalNamedTag<Item> GOLD_TIER_ITEMS = ItemTags.createOptional(new ResourceLocation(StayGold.MODID, "gold_tier_items"));

    public static boolean shouldIncreaseEnchantmentCap(Enchantment enchantment, Item item) {
        boolean isGoldTool = item instanceof TieredItem && ((TieredItem) item).getTier() == ItemTier.GOLD;
        boolean isGoldArmor = item instanceof ArmorItem && ((ArmorItem) item).getArmorMaterial() == ArmorMaterial.GOLD;
        boolean increaseEnchantmentCap = isGoldTool || isGoldArmor || item.isIn(GOLD_TIER_ITEMS);
        return increaseEnchantmentCap && enchantment.getMaxLevel() > 1;
    }
}
