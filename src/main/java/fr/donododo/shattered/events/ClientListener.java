package fr.donododo.shattered.events;

import fr.donododo.shattered.BlockEntityRegistry;
import fr.donododo.shattered.Shattered;
import fr.donododo.shattered.renderers.CrystalRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Shattered.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientListener {

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(BlockEntityRegistry.CRYSTAL_ENTITYBLOCK.get(), context -> new CrystalRenderer());
    }


}
