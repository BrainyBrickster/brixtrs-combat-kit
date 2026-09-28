package net.brixtr.thormod.item;

import net.brixtr.thormod.ThorMod;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorMaterials;
import net.minecraft.world.item.equipment.EquipmentAsset;

public class ModArmorMaterials {
    public static final ResourceKey<? extends Registry<EquipmentAsset>> REGISTRY_KEY = ResourceKey.createRegistryKey(Identifier.withDefaultNamespace("equipment_asset"));
    public static final ResourceKey<EquipmentAsset> STEEL_KEY = ResourceKey.create(REGISTRY_KEY, Identifier.fromNamespaceAndPath(ThorMod.MOD_ID, "steel"));

    public static final ArmorMaterial STEEL_ARMOR_MATERIAL = new ArmorMaterial(750, ArmorMaterials.makeDefense(2, 6, 0, 4, 10),
    20, SoundEvents.ARMOR_EQUIP_CHAIN, 4, 2, ItemTags.REPAIRS_IRON_ARMOR, STEEL_KEY);

}
