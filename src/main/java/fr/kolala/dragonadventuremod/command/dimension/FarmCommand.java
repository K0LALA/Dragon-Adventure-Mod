package fr.kolala.dragonadventuremod.command.dimension;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import fr.kolala.dragonadventuremod.world.dimension.ModDimensions;
import fr.kolala.dragonadventuremod.world.dimension.ModBuildDimensionTeleporter;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class FarmCommand {
    // Create a new command : /farm
    public FarmCommand(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("farm").executes((command) -> {
            return farmCommand(command.getSource());
        }));
    }

    private int farmCommand (CommandSource source) throws CommandSyntaxException {
        // Get the player entity with the source
        ServerPlayerEntity player = source.asPlayer();

        // Get the server via the player
        MinecraftServer server = player.getServer();
        // Check if the server isn't null
        if(server != null) {
            // Check if the player is in the farm dimension
            if(player.getEntityWorld().getDimensionKey() == ModDimensions.BuildDimension) {
                // Get the overworld dimension
                ServerWorld overWorld = server.getWorld(World.OVERWORLD);
                // If it's not null
                if(overWorld != null) {
                    // Change the dimension of the player to the overworld
                    player.changeDimension(overWorld, new ModBuildDimensionTeleporter());
                    // Change the position of the player to
                    player.setPositionAndUpdate(-215, 68, 17);
                    return 1;
                } else {
                    // Send and error message and return -1
                    source.sendFeedback(new TranslationTextComponent("command.unknown_error"), true);
                return -1;
                }
            } else {
                // Send and error message saying that he's already in it
                source.sendFeedback(new TranslationTextComponent("command.farm.already_in_it"), true);
                return -1;
            }
        } else {
            // Send and error message and return -1
            source.sendFeedback(new TranslationTextComponent("command.unknown_error"), true);
            return -1;
        }
    }
}
