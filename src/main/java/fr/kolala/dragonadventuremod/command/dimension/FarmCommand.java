package fr.kolala.dragonadventuremod.command.dimension;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import fr.kolala.dragonadventuremod.world.dimension.ModDimensions;
import fr.kolala.dragonadventuremod.world.dimension.ModFarmDimensionTeleporter;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;

public class FarmCommand {
    public FarmCommand(CommandDispatcher<CommandSource> dispatcher) {
        // Create a new command : /farm
        dispatcher.register(Commands.literal("farm").executes((command) -> {
            return farmCommand(command.getSource());
        }));
    }

    private int farmCommand (CommandSource source) throws CommandSyntaxException {
        // Get the player entity with the source
        ServerPlayerEntity player = source.asPlayer();

        //Get the server via the player
        MinecraftServer server = player.getServer();
        // If the server isn't null
        if(server != null) {
            // Check if the player is in the farm dimension
            if(player.getEntityWorld().getDimensionKey() == ModDimensions.FarmDimension) {
                // Send a message to the player saying that he's already in it
                source.sendFeedback(new TranslationTextComponent("command.farm.already_in_it"), true);
                // Return -1 error code
                return -1;
            } else {
                // Get the farm dimension
                ServerWorld FarmDimension = server.getWorld(ModDimensions.FarmDimension);
                // If it's not null
                if(FarmDimension != null) {
                    // Change the dimension of the player
                    player.changeDimension(FarmDimension, new ModFarmDimensionTeleporter());
                    // Set the position of the player to -215 68 17
                    player.setPositionAndUpdate(-215, 68, 17);
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
