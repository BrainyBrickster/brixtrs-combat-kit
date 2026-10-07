package net.brixtr.thormod.armor;

import net.brixtr.thormod.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.Vec3;

// Fabric API imports
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class BreechItem extends Item {

    private static final int MAX_JUMPS = 3;
    private static int jumpsRemaining = MAX_JUMPS;
    private static boolean wasJumpPressed = false;

    public BreechItem(Properties properties) {
        super(properties);
    }

    @Environment(EnvType.CLIENT)
    public static void initializeAbilityTick() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;

            var player = client.player;
            boolean isJumpPressed = client.options.keyJump.isDown();
            boolean hasBreeches = player.getItemBySlot(EquipmentSlot.LEGS).is(ModItems.BREECHES);
            if (player.onGround() || player.onClimbable() || player.isInWater()) {
                jumpsRemaining = MAX_JUMPS;
            }
            else if (isJumpPressed && !wasJumpPressed && jumpsRemaining == MAX_JUMPS) {
                jumpsRemaining--;
            }
            else if (isJumpPressed && !wasJumpPressed && jumpsRemaining > 0) {
                if (hasBreeches) {
                    Vec3 motion = player.getDeltaMovement();
                    player.setDeltaMovement(motion.x, 2.0D, motion.z);

                    player.level().addParticle(
                            ParticleTypes.CLOUD,
                            player.getX(), player.getY(), player.getZ(),
                            0, 0.1, 0
                    );
                    jumpsRemaining--;
                }
            }
            wasJumpPressed = isJumpPressed;
        });
    }
}
