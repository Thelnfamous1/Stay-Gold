package com.infamous.stay_gold.mixin;

import com.infamous.stay_gold.ModEnchantmentHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.AbstractRepairContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.RepairContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IWorldPosCallable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import javax.annotation.Nullable;

@Mixin(RepairContainer.class)
public abstract class RepairContainerMixin extends AbstractRepairContainer {

    public RepairContainerMixin(@Nullable ContainerType<?> containerType, int windowId, PlayerInventory playerInventory, IWorldPosCallable worldPosCallable) {
        super(containerType, windowId, playerInventory, worldPosCallable);
    }

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/Enchantment;getMaxLevel()I"), method = "updateRepairOutput")
    private int getMaxLevel(Enchantment enchantment){
        ItemStack stack = this.field_234643_d_.getStackInSlot(0);
        boolean increaseEnchantmentCap = ModEnchantmentHelper.shouldIncreaseEnchantmentCap(enchantment, stack.getItem());
        return increaseEnchantmentCap ?
                enchantment.getMaxLevel() * 2 :
                enchantment.getMaxLevel();
    }
}
