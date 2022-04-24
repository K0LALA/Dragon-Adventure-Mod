package fr.kolala.dragonadventuremod.command.dimension;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import fr.kolala.dragonadventuremod.DragonAdventureMod;
import fr.kolala.dragonadventuremod.world.dimension.ModDimensions;
import fr.kolala.dragonadventuremod.world.dimension.ModBuildDimensionTeleporter;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;

public class BuildCommand {
    public BuildCommand(CommandDispatcher<CommandSource> dispatcher) {
        // Create a new command : /spawn
        dispatcher.register(Commands.literal("spawn").executes((command) -> {
            return buildCommand(command.getSource());
        }));
    }

    private int buildCommand (CommandSource source) throws CommandSyntaxException {
        // Get the player entity with the source
        ServerPlayerEntity player = source.asPlayer();

        //Get the server via the player
        MinecraftServer server = player.getServer();
        // If the server isn't null
        if(server != null) {
            // Check if the player is in the farm dimension
            if(player.getEntityWorld().getDimensionKey() == ModDimensions.BuildDimension) {
                // Send a message to the player saying that he's already in it
                source.sendFeedback(new TranslationTextComponent("command.build.already_in_it"), true);
                // Return -1 error code
                return -1;
            } else {
                // Get the farm dimension
                ServerWorld buildDimension = server.getWorld(ModDimensions.BuildDimension);
                // If it's not null
                if(buildDimension != null) {
                    // Change the dimension of the player
                    player.changeDimension(buildDimension, new ModBuildDimensionTeleporter());
                    // Set the position of the player to -215 68 17
                    player.setPositionAndUpdate(918, 66, -72);
                    // Return 1 to signify that everything is good
                    return 1;
                } else {
                    // Send and error message and return -1
                    source.sendFeedback(new TranslationTextComponent("command.unknown_error"), true);
                    return -1;
                }
            }
        } else {
            // Send and error message and return -1
            source.sendFeedback(new TranslationTextComponent("command.unknown_error"), true);
            return -1;
        }
    }

}
