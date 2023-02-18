package fr.kolala.dragonadventuremod.events;

import fr.kolala.dragonadventuremod.DragonAdventureMod;
import fr.kolala.dragonadventuremod.command.ChangeDimCommand;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = DragonAdventureMod.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void onCommandRegister(RegisterCommandsEvent event) {
        new ChangeDimCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }
}
