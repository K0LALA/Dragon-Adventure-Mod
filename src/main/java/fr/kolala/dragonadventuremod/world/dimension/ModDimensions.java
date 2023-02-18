package fr.kolala.dragonadventuremod.world.dimension;

import fr.kolala.dragonadventuremod.DragonAdventureMod;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class ModDimensions {
    public static RegistryKey<World> NewDimension = RegistryKey.getOrCreateKey(Registry.WORLD_KEY,
            new ResourceLocation(DragonAdventureMod.MOD_ID, "new_dimension"));
}
