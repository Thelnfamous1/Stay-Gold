package com.infamous.stay_gold.mixin;

import com.infamous.stay_gold.ModEnchantmentHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {

    @Inject(at = @At("RETURN"), method = "getEnchantmentDatas")
    private static void getEnchantmentDatas(int level, ItemStack stack, boolean allowTreasure, CallbackInfoReturnable callbackInfoReturnable){
        List<EnchantmentData> enchantmentDataList = (List<EnchantmentData>) callbackInfoReturnable.getReturnValue();
        for(int i = 0; i < enchantmentDataList.size(); i++){
            EnchantmentData enchantmentData = enchantmentDataList.get(i);
            Enchantment enchantment = enchantmentData.enchantment;
            int enchantmentLevel = enchantmentData.enchantmentLevel;
            boolean shouldIncreaseEnchantmentCap = ModEnchantmentHelper.shouldIncreaseEnchantmentCap(enchantment, stack.getItem());
            if(shouldIncreaseEnchantmentCap){
                EnchantmentData replacement = new EnchantmentData(enchantment, enchantmentLevel * 2);
                enchantmentDataList.set(i, replacement);
            }
        }
    }
}
