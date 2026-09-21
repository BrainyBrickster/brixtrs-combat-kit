package net.brixtr.thormod.item;

import net.brixtr.thormod.ThorMod;
import net.brixtr.thormod.food.ModFoods;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.function.Function;

import static net.brixtr.thormod.item.ModToolMaterials.*;

public class ModItems {

    public static final Item SHAWARMA = registerItem("shawarma", properties -> new Item(properties.
            food(ModFoods.SHAWARMA, ModFoods.SHAWARMA_CONSUMABLE)));
    public static final Item MEAD = registerItem("mead", properties -> new Item(properties.food(ModFoods.MEAD, ModFoods.MEAD_CONSUMABLE)));

    public static final Item STORMBREAKER = registerItem("stormbreaker", properties -> new AxeItem(STAR, 10f, -3.0f, properties) {

        // Feature 1: The 10-Block Right-Click Cleave Attack
        @Override
        public net.minecraft.world.InteractionResult use(net.minecraft.world.level.Level level, net.minecraft.world.entity.player.Player player, net.minecraft.world.InteractionHand hand) {
            net.minecraft.world.item.ItemStack itemStack = player.getItemInHand(hand);

            if (!level.isClientSide()) {
                net.minecraft.world.phys.AABB area = player.getBoundingBox().inflate(10.0);

                level.getEntitiesOfClass(net.minecraft.world.entity.LivingEntity.class, area, entity -> entity != player).forEach(entity -> {
                    entity.hurt(level.damageSources().playerAttack(player), 10f); // Deals 5 hearts of damage
                });

                player.getCooldowns().addCooldown(this.getDefaultInstance(), 200);
            }

            return InteractionResult.SUCCESS;
        }

        // Feature 2: Force Sharpness 10 and Channeling when crafted
        @Override
        public void onCraftedBy(net.minecraft.world.item.ItemStack stack, net.minecraft.world.entity.player.Player player) {
            super.onCraftedBy(stack, player);

            // Dynamic look up from the modern 1.21+ enchantment registry data system
            var enchantmentRegistry = player.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT);

            // Safely applies the enchantments via their registry references
            stack.enchant(enchantmentRegistry.getOrThrow(Enchantments.SHARPNESS), 10);
            stack.enchant(enchantmentRegistry.getOrThrow(Enchantments.EFFICIENCY), 6);
        }
    });





    public static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(
                BuiltInRegistries.ITEM,
                Identifier.fromNamespaceAndPath(ThorMod.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(ThorMod.MOD_ID, name)))
        ));
    }


    public static void registerModItems(){

        ThorMod.LOGGER.info("Registering Mod Items " + ThorMod.MOD_ID);

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(output -> {
            output.accept(SHAWARMA);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(output -> {
            output.accept(MEAD);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT).register(output -> {
            output.accept(STORMBREAKER);
        });


    }



}

