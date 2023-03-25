package fr.kolala.dragonadventuremod.world.dimension;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.ITeleporter;

import java.util.function.Function;

public class ModNewDimensionTeleporter implements ITeleporter {

    private BlockPos pos;

    public ModNewDimensionTeleporter(BlockPos pos)
    {
        this.pos = pos;
    }

    @Override
    public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
        entity = repositionEntity.apply(false);

        entity.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());

        return entity;
    }
}
