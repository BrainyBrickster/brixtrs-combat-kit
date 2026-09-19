package net.brixtr.thormod.food;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

import java.util.List;

public class ModFoods {

    public static final FoodProperties SHAWARMA = new FoodProperties.Builder().nutrition(8).saturationModifier(0.8F).alwaysEdible().build();

    public static final Consumable SHAWARMA_CONSUMABLE = Consumables.defaultFood()
            .consumeSeconds(0.5f).onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 400, 10), 0.5f)).build();

    public static final FoodProperties MEAD = (new FoodProperties.Builder()).nutrition(6).saturationModifier(0.1F).alwaysEdible().build();

    public static final Consumable MEAD_CONSUMABLE = Consumables.defaultDrink().consumeSeconds(1f).onConsume(
            new ApplyStatusEffectsConsumeEffect(
                    List.of(
                            new MobEffectInstance(MobEffects.STRENGTH, 3600, 1),
                            new MobEffectInstance(MobEffects.SPEED, 3600, 1),
                            new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 3600, 1)
                    )
            )
    )
    .build();
}
