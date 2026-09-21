package net.brixtr.thormod.datagen;

import net.brixtr.thormod.ThorMod;
import net.brixtr.thormod.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(ModItems.SHAWARMA, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.MEAD, ModelTemplates.FLAT_ITEM);

        itemModelGenerators.generateFlatItem(ModItems.STORMBREAKER, ModelTemplates.FLAT_HANDHELD_ITEM);

    }
}
