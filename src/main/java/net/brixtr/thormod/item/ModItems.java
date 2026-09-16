package net.brixtr.thormod.item;

import net.brixtr.thormod.food.ModFoods;
import net.minecraft.world.item.Item;

import java.rmi.registry.Registry;

public class ModItems {
    public static final Item SHAWARMA = ("shawarma", properties -> new Item(properties
            .food(ModFoods.SHAWARMA, ModFoods.SHAWARMA_CONSUMABLE)));


}
