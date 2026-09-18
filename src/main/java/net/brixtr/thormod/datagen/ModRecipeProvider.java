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


            }
        };
    }

    @Override
    public String getName() {
        return "Thor Mod Recipes";
    }
}
