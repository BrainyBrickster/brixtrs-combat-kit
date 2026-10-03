package net.brixtr.thormod;

import net.brixtr.thormod.armor.BreechItem;
import net.fabricmc.api.ClientModInitializer;

public class ThorModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Hooks up the ability inside BreechItem directly to the game's engine loop
        BreechItem.initializeAbilityTick();
    }
}
