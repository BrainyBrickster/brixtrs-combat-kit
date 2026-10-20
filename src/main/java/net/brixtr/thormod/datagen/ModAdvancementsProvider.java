package net.brixtr.thormod.datagen;

import net.brixtr.thormod.ThorMod;
import net.brixtr.thormod.item.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementsProvider extends AdvancementProvider {
    public ModAdvancementsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, List.of(new ThorModAdvancements()));
    }

    public static class ThorModAdvancements implements AdvancementSubProvider{

        @Override
        public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> output) {
            var items = registries.lookupOrThrow(Registries.ITEM);


            Advancement.Builder advancement = Advancement.Builder.advancement();
            advancement.display(
                    ModItems.SHAWARMA,
                    Component.translatable("advancements.thormod.base.title"),
                    Component.translatable("advancements.thormod.base.description"),
                    Identifier.parse("minecraft:textures/gui/advancement/backgrounds/stone.png"),
                    AdvancementType.CHALLENGE,
                    true,
                    true,
                    false

            );
            advancement.addCriterion("has_food", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.SHAWARMA)));
            AdvancementHolder base = advancement
                    .save(output, ThorMod.MOD_ID + ":thormod/base");

            AdvancementHolder mead = Advancement.Builder.advancement()
                    .parent(base)
                    .display(
                            ModItems.MEAD,
                            Component.translatable("advancements.thormod.mead.title"),
                            Component.translatable("advancements.thormod.mead.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false

                    )
                    .addCriterion("has_fod", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.MEAD)))
                    .save(output, ThorMod.MOD_ID + ":thormod/mead");

            AdvancementHolder jumper = Advancement.Builder.advancement()
                    .parent(base)
                    .display(
                            ModItems.BREECHES,
                            Component.translatable("advancements.thormod.jumper.title"),
                            Component.translatable("advancements.thormod.jumper.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false

                    )
                    .addCriterion("has_breech", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.BREECHES)))
                    .save(output, ThorMod.MOD_ID + ":thormod/jumper");

            AdvancementHolder helmet = Advancement.Builder.advancement()
                    .parent(jumper)
                    .display(
                            ModItems.WINGED_HELMET,
                            Component.translatable("advancements.thormod.helmet.title"),
                            Component.translatable("advancements.thormod.helmet.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false

                    )
                    .addCriterion("has_breech", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.WINGED_HELMET)))
                    .save(output, ThorMod.MOD_ID + ":thormod/helmet");

            AdvancementHolder stealthy = Advancement.Builder.advancement()
                    .parent(base)
                    .display(
                            ModItems.FISTS,
                            Component.translatable("advancements.thormod.stealthy.title"),
                            Component.translatable("advancements.thormod.steatlhy.description"),
                            null,
                            AdvancementType.CHALLENGE,
                            true,
                            true,
                            false

                    )
                    .addCriterion("has_karambit", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.FISTS)))
                    .save(output, ThorMod.MOD_ID + ":thormod/stealthy");

            AdvancementHolder heavenly = Advancement.Builder.advancement()
                    .parent(stealthy)
                    .display(
                            ModItems.STORMBREAKER,
                            Component.translatable("advancements.thormod.heavenly.title"),
                            Component.translatable("advancements.thormod.heavenly.description"),
                            null,
                            AdvancementType.CHALLENGE,
                            true,
                            true,
                            false

                    )
                    .addCriterion("has_stormbreaker", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.STORMBREAKER)))
                    .save(output, ThorMod.MOD_ID + ":thormod/heavenly");

            AdvancementHolder root = Advancement.Builder.advancement()
                    .parent(heavenly)
                    .display(
                            ModItems.MJOLNIR,
                            Component.translatable("advancements.thormod.root.title"),
                            Component.translatable("advancements.thormod.root.description"),
                            null,
                            AdvancementType.CHALLENGE,
                            true,
                            true,
                            false

                    )
                    .addCriterion("has_mjolnir", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.MJOLNIR)))
                    .save(output, ThorMod.MOD_ID + ":thormod/root");


        }
    }
}
