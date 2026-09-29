package net.brixtr.thormod.weapons;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MaceItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class MjolnirItem extends MaceItem {
    public MjolnirItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        Vec3 lookDirection = player.getLookAngle();
        double dashStrength = 1.5;

        player.setDeltaMovement(
                lookDirection.x * dashStrength,
                player.getDeltaMovement().y,
                lookDirection.z * dashStrength
        );
        player.getCooldowns().addCooldown(this.getDefaultInstance(), 50);
        if(!level.isClientSide()){
            player.hurtMarked = true;
        }
        return InteractionResult.SUCCESS;

    }
}

/*@Override
public void hurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {


    target.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 0), attacker);
    target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 0), attacker);
    super.hurtEnemy(itemStack, target, attacker);
}*/