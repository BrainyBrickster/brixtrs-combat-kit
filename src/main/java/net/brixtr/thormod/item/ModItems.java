package net.brixtr.thormod.item;

import net.brixtr.thormod.ThorMod;
import net.brixtr.thormod.food.ModFoods;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public class ModItems {

    public static final Item SHAWARMA = registerItem("shawarma", properties -> new Item(properties.
            food(ModFoods.SHAWARMA, ModFoods.SHAWARMA_CONSUMABLE)));
    public static final Item MEAD = registerItem("mead", properties -> new Item(properties.food(ModFoods.MEAD, ModFoods.MEAD_CONSUMABLE)));


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


    }



}

