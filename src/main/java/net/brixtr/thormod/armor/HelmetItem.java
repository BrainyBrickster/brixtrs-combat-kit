package net.brixtr.thormod.armor;

import net.brixtr.thormod.item.ModItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

public class HelmetItem extends Item {

    public HelmetItem(Properties properties) {
        super(properties);
    }

    /**
     * Call this method from your main mod's onInitialize() method.
     * Runs on the server side to safely apply potion effects without desyncs.
     */
    public static void initializeAbilityTick() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            // Loop through all online players on the server
            for (var player : server.getPlayerList().getPlayers()) {
                if (player == null) continue;

                Level level = player.level();

                // 1. Check if the player is wearing this item in the HEAD slot
                ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
                boolean hasHelmet = helmet.is(ModItems.WINGED_HELMET);

                // 2. Check if the world is raining or thundering
                boolean isRaining = level.isRaining();

                // 3. Check if the player is exposed to the rain (not under a roof/in a cave)
                boolean canSeeSky = level.canSeeSky(player.blockPosition());

                // Apply the effect if all conditions are met
                if (hasHelmet && isRaining && canSeeSky) {
                    // Gives Regeneration I for 4 seconds (80 ticks) to prevent UI icon blinking
                    player.addEffect(new MobEffectInstance(
                            MobEffects.REGENERATION,
                            80,
                            0,      // 0 = Level 1, 1 = Level 2
                            false,  // ambient particles
                            true,   // show particles
                            true    // show icon in inventory
                    ));
                }
            }
        });
    }
}
