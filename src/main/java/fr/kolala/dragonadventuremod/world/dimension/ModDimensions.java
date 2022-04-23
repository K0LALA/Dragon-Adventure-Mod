package fr.kolala.dragonadventuremod.world.dimension;

import fr.kolala.dragonadventuremod.DragonAdventureMod;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class ModDimensions {
    // Create the new build dimension
    public static RegistryKey<World> BuildDimension = RegistryKey.getOrCreateKey(Registry.WORLD_KEY,
            new ResourceLocation(DragonAdventureMod.MOD_ID, "build_dimension"));
}
