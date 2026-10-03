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

    // Track total allowed jumps (including the initial ground jump)
    private static final int MAX_JUMPS = 3;
    private static int jumpsRemaining = MAX_JUMPS;
    private static boolean wasJumpPressed = false;

    public BreechItem(Properties properties) {
        super(properties);
    }

    /**
     * Call this single method from your ThorModClient.java's onInitializeClient() method.
     * Keeps all logic bundled within BreechItem while remaining multiplayer-safe.
     */
    @Environment(EnvType.CLIENT)
    public static void initializeAbilityTick() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;

            var player = client.player;
            boolean isJumpPressed = client.options.keyJump.isDown();
            boolean hasBreeches = player.getItemBySlot(EquipmentSlot.LEGS).is(ModItems.BREECHES);

            // 1. Reset the jump capacity when touching ground, climbing, or swimming
            if (player.onGround() || player.onClimbable() || player.isInWater()) {
                jumpsRemaining = MAX_JUMPS;
            }
            // 2. Consume a jump charge on the rising edge of the initial ground jump
            else if (isJumpPressed && !wasJumpPressed && jumpsRemaining == MAX_JUMPS) {
                jumpsRemaining--; // Subtract the first jump off the ground
            }
            // 3. Trigger mid-air jumps if they have breeches and remaining charges left (max 2 mid-air jumps)
            else if (isJumpPressed && !wasJumpPressed && jumpsRemaining > 0) {

                if (hasBreeches) {
                    // Apply upward vertical physics impulse
                    Vec3 motion = player.getDeltaMovement();
                    player.setDeltaMovement(motion.x, 2.0D, motion.z);

                    // Display visual cloud particle feedback at player's location
                    player.level().addParticle(
                            ParticleTypes.CLOUD,
                            player.getX(), player.getY(), player.getZ(),
                            0, 0.1, 0
                    );

                    // Deduct a mid-air jump charge
                    jumpsRemaining--;
                }
            }

            // Record the keyboard state for evaluation on the subsequent game tick
            wasJumpPressed = isJumpPressed;
        });
    }
}
