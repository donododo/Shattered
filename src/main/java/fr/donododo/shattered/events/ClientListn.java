package fr.donododo.shattered.events;

import com.mojang.blaze3d.platform.InputConstants;
import fr.donododo.shattered.Shattered;
import fr.donododo.shattered.screen.ClassPicker;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Shattered.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientListn {

    public final static KeyMapping fight_screen_key = new KeyMapping("fight_screen", KeyConflictContext.UNIVERSAL, InputConstants.Type.KEYSYM, InputConstants.KEY_O, KeyMapping.CATEGORY_INVENTORY);

    @SubscribeEvent
    public static void clientTick(TickEvent.ClientTickEvent event) {
        if (fight_screen_key.isDown()) {
            Minecraft.getInstance().setScreen(new ClassPicker());
        }
    }

}
