package fr.donododo.shattered.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import fr.donododo.shattered.Shattered;
import fr.donododo.shattered.screen.widgets.ClassButtonWidget;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class ClassPicker extends Screen {

    private static final ResourceLocation WIDGETS_LOCATION = new ResourceLocation(Shattered.MODID, "textures/gui/race_picker.png");

    public ClassPicker() {
        super(Component.nullToEmpty("ClassPicker"));
    }

    @Override
    protected void init() {
        super.init();
        this.addRenderableWidget(new ClassButtonWidget((int) (this.width * 0.0084), (int) (this.height * 0.033), (int) (this.width * 0.20), (int) (this.height * 0.0498), Component.nullToEmpty("Technomancien"), true));
        this.addRenderableWidget(new ClassButtonWidget((int) (this.width * 0.0084), (int) (this.height * 0.098), (int) (this.width * 0.20), (int) (this.height * 0.0498), Component.nullToEmpty("ShadowRunner"), false));
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        RenderSystem.enableBlend();
        pGuiGraphics.pose().pushPose();
        pGuiGraphics.blit(WIDGETS_LOCATION, (int) (this.width * 0.63), (int) (this.height * 0.054), (int) (this.width * 0.34), (int) (this.height * 0.83), 662, 899, 10, 10, 1275, 899);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        pGuiGraphics.pose().popPose();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
