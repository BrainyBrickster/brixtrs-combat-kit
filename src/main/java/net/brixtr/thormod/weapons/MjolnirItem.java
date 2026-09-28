package net.brixtr.thormod.weapons;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MaceItem;

public class MjolnirItem extends MaceItem {
    public MjolnirItem(Properties properties) {
        super(properties);
    }

    @Override
    public void hurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
        target.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 0), attacker);
        target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 0), attacker);
        super.hurtEnemy(itemStack, target, attacker);
    }
}
    /*public boolean postHurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
        // Only apply status effects on the logical server side
        if (!target.level().isClientSide()) {
            target.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 0), attacker);
            target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 0), attacker);
        }

        // Call the parent implementation and return true to indicate the hit registered
        super.postHurtEnemy(itemStack, target, attacker);
    }

}*/
