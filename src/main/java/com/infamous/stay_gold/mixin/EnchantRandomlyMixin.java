package com.infamous.stay_gold.mixin;

import com.infamous.stay_gold.ModEnchantmentHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.functions.EnchantRandomly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Random;

@Mixin(EnchantRandomly.class)
public class EnchantRandomlyMixin {

    @ModifyVariable(at = @At("STORE"), method = "func_237420_a_")
    private static int enchant(int enchantmentLevel, ItemStack stack, Enchantment enchantment, Random random){
        return ModEnchantmentHelper.shouldIncreaseEnchantmentCap(enchantment, stack.getItem()) ?
                enchantmentLevel * 2 :
                enchantmentLevel;
    }
}
