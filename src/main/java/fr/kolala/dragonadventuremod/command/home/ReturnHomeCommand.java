package fr.kolala.dragonadventuremod.command.home;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import fr.kolala.dragonadventuremod.DragonAdventureMod;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;

public class ReturnHomeCommand {
    // Create a new command : /home
    public ReturnHomeCommand(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("home").executes((command) -> {
            return returnHome(command.getSource());
        }));
    }

    private int returnHome(CommandSource source) throws CommandSyntaxException {
        // Get the player with source
        ServerPlayerEntity player = source.asPlayer();
        // Check if the player has already set a home point
        boolean hasHomePos = player.getPersistentData().getIntArray(DragonAdventureMod.MOD_ID + "homepos").length != 0;

        // If yes
        if(hasHomePos) {
            // Get the home position via persistentData
            int[] playerPos = player.getPersistentData().getIntArray(DragonAdventureMod.MOD_ID + "homepos");
            // Update the player position
            player.setPositionAndUpdate(playerPos[0], playerPos[1], playerPos[2]);

            source.sendFeedback(new TranslationTextComponent("command.home.return"), true);
            return 1;
        } else {
            // Error message saying that the player hasn't any home yet
            source.sendFeedback(new TranslationTextComponent("command.home.return_fail"), true);
            return -1;
        }
    }
}
