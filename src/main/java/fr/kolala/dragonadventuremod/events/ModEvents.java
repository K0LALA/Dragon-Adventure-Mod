package fr.kolala.dragonadventuremod.events;

import fr.kolala.dragonadventuremod.DragonAdventureMod;
import fr.kolala.dragonadventuremod.command.dimension.BuildCommand;
import fr.kolala.dragonadventuremod.command.dimension.FarmCommand;
import fr.kolala.dragonadventuremod.command.home.ReturnHomeCommand;
import fr.kolala.dragonadventuremod.command.home.SetHomeCommand;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = DragonAdventureMod.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void onCommandRegister(RegisterCommandsEvent event) {
        new SetHomeCommand(event.getDispatcher());
        new ReturnHomeCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onPlayerCloneEvent(PlayerEvent.Clone event) {
        if(!event.getOriginal().getEntityWorld().isRemote()) {
            event.getPlayer().getPersistentData().putIntArray(DragonAdventureMod.MOD_ID + "homepos",
                    event.getOriginal().getPersistentData().getIntArray(DragonAdventureMod.MOD_ID + "homepos"));
        }
    }
}
