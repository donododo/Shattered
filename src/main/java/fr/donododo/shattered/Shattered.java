package fr.donododo.shattered;

import com.mojang.logging.LogUtils;
import fr.donododo.shattered.events.ClientListn;
import fr.donododo.shattered.loaders.RaceLoader;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib.GeckoLib;

@Mod(Shattered.MODID)
public class Shattered {

    public static final String MODID = "shattered";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Shattered() {
        GeckoLib.initialize();
        MinecraftForge.EVENT_BUS.register(this);
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        EntityRegistry.ENTITIES.register(modEventBus);
        BlockEntityRegistry.TILES.register(modEventBus);
        BlockRegistry.BLOCKS.register(modEventBus);
    }

    @SubscribeEvent
    public void registerLoader(AddReloadListenerEvent e) {
        e.addListener(new RaceLoader());
    }

    @SubscribeEvent
    public static void keyMapRegistry(RegisterKeyMappingsEvent event) {
        event.register(ClientListn.fight_screen_key);
    }

}
