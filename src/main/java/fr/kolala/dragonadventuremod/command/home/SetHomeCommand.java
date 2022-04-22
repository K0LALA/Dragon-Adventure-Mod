package fr.kolala.dragonadventuremod.command.home;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import fr.kolala.dragonadventuremod.DragonAdventureMod;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class SetHomeCommand {
    // Create a new command : /set home
    public SetHomeCommand(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("set").then(Commands.literal("home").executes((command) -> {
            return setHome(command.getSource());
        })));
    }

    private int setHome(CommandSource source) throws CommandSyntaxException {
        // Get the player with source
        ServerPlayerEntity player = source.asPlayer();
        // Get the player position
        BlockPos playerPos = player.getPosition();
        // Create a new String with the player position
        String pos = "(" + playerPos.getX() + ", " + playerPos.getY() + ", " + playerPos.getZ() + ")";

        // Put a new int array in persistentData
        player.getPersistentData().putIntArray(DragonAdventureMod.MOD_ID + "homepos",
                new int[]{ playerPos.getX(), playerPos.getY(), playerPos.getZ() });

        source.sendFeedback(ITextComponent.getTextComponentOrEmpty(new TranslationTextComponent("command.home.set") + pos), true);
        return 1;
    }

}
