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
    public static void initializeAbilityTick() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (var player : server.getPlayerList().getPlayers()) {
                if (player == null) continue;
                Level level = player.level();
                ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
                boolean hasHelmet = helmet.is(ModItems.WINGED_HELMET);
                boolean isRaining = level.isRaining();
                boolean canSeeSky = level.canSeeSky(player.blockPosition());
                if (hasHelmet && isRaining && canSeeSky) {
                    player.addEffect(new MobEffectInstance(
                            MobEffects.HASTE,
                            80,
                            0,
                            false,
                            true,
                            true
                    ));
                }
            }
        });
    }
}
