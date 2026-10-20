package net.brixtr.thormod;

import net.brixtr.thormod.datagen.ModAdvancementsProvider;
import net.brixtr.thormod.datagen.ModEquipmentAssetProvider;
import net.brixtr.thormod.datagen.ModModelProvider;
import net.brixtr.thormod.datagen.ModRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class ThorModDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		var pack = fabricDataGenerator.createPack();

		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(ModAdvancementsProvider::new);

		pack.addProvider(ModEquipmentAssetProvider::new);

	}
}
