package net.brixtr.thormod.datagen;

import net.brixtr.thormod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {

                shapeless(RecipeCategory.FOOD, ModItems.SHAWARMA, 1)
                        .requires(Items.COOKED_CHICKEN)
                        .requires(Items.BREAD)
                        .unlockedBy(getHasName(Items.COOKED_CHICKEN), has(Items.BREAD))
                        .group("Asguardian Foods")
                        .save(output);

                shapeless(RecipeCategory.FOOD, ModItems.MEAD, 1)
                        .requires(Items.HONEY_BOTTLE)
                        .requires(Items.SUGAR)
                        .requires(Items.WHEAT)
                        .unlockedBy(getHasName(Items.HONEY_BOTTLE), has(Items.SUGAR))
                        .group("Asguardian Foods")
                        .save(output);

                shaped(RecipeCategory.COMBAT, ModItems.STORMBREAKER)
                        .pattern("INI")
                        .pattern(" B ")
                        .pattern(" B ")
                        .define('B', Items.BREEZE_ROD)
                        .define('I', Items.IRON_BLOCK)
                        .define('N', Items.NETHERITE_BLOCK)
                        .unlockedBy(getHasName(Items.NETHERITE_BLOCK), has(Items.NETHERITE_BLOCK))
                        .unlockedBy(getHasName(Items.IRON_BLOCK), has(Items.IRON_BLOCK))
                        .unlockedBy(getHasName(Items.BREEZE_ROD), has(Items.BREEZE_ROD))
                        .group("Thor Weapons")
                        .save(output);

                shaped(RecipeCategory.COMBAT, ModItems.WINGED_HELMET)
                        .pattern("P P")
                        .pattern("III")
                        .pattern("I I")
                        .define('P', Items.PHANTOM_MEMBRANE)
                        .define('I', Items.IRON_INGOT)
                        .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                        .unlockedBy(getHasName(Items.PHANTOM_MEMBRANE), has(Items.PHANTOM_MEMBRANE))
                        .group("Thor Armor")
                        .save(output);

                shaped(RecipeCategory.COMBAT, ModItems.BREECHES)
                        .pattern("LIL")
                        .pattern("I I")
                        .pattern("I I")
                        .define('L', Items.LEATHER)
                        .define('I', Items.IRON_INGOT)
                        .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                        .unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER))
                        .group("Thor Armor")
                        .save(output);


            }
        };
    }

    @Override
    public String getName() {
        return "Thor Mod Recipes";
    }
}
