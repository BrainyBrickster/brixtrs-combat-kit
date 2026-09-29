package net.brixtr.thormod.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class StormbreakerItem extends AxeItem {

    public StormbreakerItem(ToolMaterial tier, float attackDamage, float attackSpeed, Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (!level.isClientSide()) {
            AABB area = player.getBoundingBox().inflate(10.0);
            level.getEntitiesOfClass(LivingEntity.class, area, entity -> entity != player).forEach(livingEntity -> {
                livingEntity.hurt(level.damageSources().playerAttack(player), 10.0f);

                double deltaX = livingEntity.getX() - player.getX();
                double deltaZ = livingEntity.getZ() - player.getZ();
                double distance = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
                if (distance > 0){
                    deltaX /= distance;
                    deltaZ /= distance;
                }

                livingEntity.knockback(1.5D, deltaX, deltaZ);
                livingEntity.hurtMarked = true;

            });
            player.getCooldowns().addCooldown(this.getDefaultInstance(), 50);
        }

        return InteractionResult.SUCCESS;
    }
}
