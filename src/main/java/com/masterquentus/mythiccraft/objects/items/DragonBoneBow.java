  
package com.masterquentus.mythiccraft.objects.items;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

import java.util.function.Predicate;

public class DragonBoneBow extends ModBow {
    public DragonBoneBow(Properties builder) {
        super(builder);
    }

    @Override
    protected AbstractArrowEntity createArrow(World worldIn, ItemStack ammoStack, PlayerEntity playerentity) {
        return new ArrowEntity(worldIn, playerentity);
    }

    @Override
    protected double getArrowBaseDamage(ItemStack bowStack, AbstractArrowEntity arrowEntity) {
        int powerLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, bowStack);

        return (double)powerLevel * 0.5D + 0.5D;
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return (ammoStack) -> {
            return ammoStack.getItem() == Items.ARROW;
        };
    }
}