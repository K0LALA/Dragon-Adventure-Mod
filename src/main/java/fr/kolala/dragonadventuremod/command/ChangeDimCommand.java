package fr.kolala.dragonadventuremod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import fr.kolala.dragonadventuremod.world.dimension.ModDimensions;
import fr.kolala.dragonadventuremod.world.dimension.ModNewDimensionTeleporter;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Dimension;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class ChangeDimCommand {
    public ChangeDimCommand(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("dim").executes((command) -> {
            return changeDimCommand(command.getSource());
        }));
    }

    private int changeDimCommand(CommandSource source) throws CommandSyntaxException {
        // Get the player with source
        ServerPlayerEntity player = source.asPlayer();
        MinecraftServer server = player.getServer();

        if(server != null)
        {
            if(player.getEntityWorld().getDimensionKey() == ModDimensions.NewDimension)
            {
                source.sendFeedback(new TranslationTextComponent("command.dim.already_in"), true);
                return -1;
            } else {
                ServerWorld dim = server.getWorld(ModDimensions.NewDimension);
                if(dim != null)
                {
                    player.changeDimension(dim, new ModNewDimensionTeleporter());
                    source.sendFeedback(new TranslationTextComponent("command.dim.success"), true);
                    return 1;
                } else {
                    source.sendFeedback(new TranslationTextComponent("command.dim.no_dim"), true);
                    return -1;
                }
            }
        } else {
            source.sendFeedback(new TranslationTextComponent("command.dim.no_server"), true);
            return -1;
        }
    }
}
