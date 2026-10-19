package net.brixtr.thormod.item;

import net.brixtr.thormod.ThorMod;
import net.brixtr.thormod.food.ModFoods;
import net.brixtr.thormod.item.StormbreakerItem;

import net.brixtr.thormod.weapons.MjolnirItem;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.function.Consumer;
import java.util.function.Function;

import static net.brixtr.thormod.item.ModToolMaterials.*;

@SuppressWarnings("deprecation")
public class ModItems {


    //My Food stuffs and all
    public static final Item SHAWARMA = registerItem("shawarma", properties -> new Item(properties.
            food(ModFoods.SHAWARMA, ModFoods.SHAWARMA_CONSUMABLE)));
    public static final Item MEAD = registerItem("mead", properties -> new Item(properties.food(ModFoods.MEAD, ModFoods.MEAD_CONSUMABLE)));

    //My weapons
    public static final Item STORMBREAKER = registerItem("stormbreaker", properties -> new StormbreakerItem(ModToolMaterials.STAR, 10.0f, -3.0f, properties) {
        @Override
        public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
            builder.accept(Component.translatable("tooltip.thormod.stormbreaker"));
        }
    });

    public static final Item FISTS = registerItem("fists", properties -> new Item(
            properties.component(DataComponents.ATTRIBUTE_MODIFIERS,
                    ItemAttributeModifiers.builder()
                            .add(Attributes.BLOCK_INTERACTION_RANGE,
                                    new AttributeModifier(Identifier.fromNamespaceAndPath("thormod", "fists_block_reach"), -2.5f, AttributeModifier.Operation.ADD_VALUE),
                                    EquipmentSlotGroup.MAINHAND)
                            .add(Attributes.ENTITY_INTERACTION_RANGE,
                                    new AttributeModifier(Identifier.fromNamespaceAndPath("thormod", "fists_entity_reach"), -1.0f, AttributeModifier.Operation.ADD_VALUE),
                                    EquipmentSlotGroup.MAINHAND)
                            .build()
            )
    ));
    public static final Item MJOLNIR = registerItem("mjolnir", properties -> new MjolnirItem(
            properties.component(DataComponents.ATTRIBUTE_MODIFIERS,
                    ItemAttributeModifiers.builder()
                            .add(Attributes.ATTACK_DAMAGE,
                                    new AttributeModifier(Identifier.fromNamespaceAndPath("thormod", "damage"), 12.0f, AttributeModifier.Operation.ADD_VALUE),
                                    EquipmentSlotGroup.MAINHAND)
                            .add(Attributes.ATTACK_SPEED,
                                    new AttributeModifier(Identifier.fromNamespaceAndPath("thormod", "speed"), 12.0f, AttributeModifier.Operation.ADD_VALUE),
                                    EquipmentSlotGroup.MAINHAND)
                            .add(Attributes.ENTITY_INTERACTION_RANGE,
                                    new AttributeModifier(Identifier.fromNamespaceAndPath("thormod", "mjolnir_entity_reach"), 2f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                            .build()
            )
    ) {
        @Override
        public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
            builder.accept(Component.translatable("tooltip.thormod.mjolnir"));
            super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
        }
    });

    //My aarmour
    public static final Item WINGED_HELMET = registerItem("winged_battle_helmet", properties -> new Item(properties.humanoidArmor(ModArmorMaterials.STEEL_ARMOR_MATERIAL, ArmorType.HELMET)) {
        @Override
        public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
            builder.accept(Component.translatable("tooltip.thormod.helmet"));
            super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
        }
    });
    public static final Item BREECHES = registerItem("asguardian_breech", properties -> new Item(properties.humanoidArmor(ModArmorMaterials.STEEL_ARMOR_MATERIAL, ArmorType.LEGGINGS)){
        @Override
        public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
            builder.accept(Component.translatable("tooltip.thormod.breech"));
            super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
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
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT).register(output -> {
            output.accept(MJOLNIR);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT).register(output -> {
            output.accept(WINGED_HELMET);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT).register(output -> {
            output.accept(BREECHES);
        });
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT).register(output -> {
            output.accept(FISTS);
        });



    }



}

