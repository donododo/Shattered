package fr.donododo.shattered.screen.widgets;

import com.mojang.blaze3d.systems.RenderSystem;
import fr.donododo.shattered.Shattered;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.object.Color;

public class ClassButtonWidget extends AbstractWidget {

    private static final ResourceLocation WIDGETS_LOCATION = new ResourceLocation(Shattered.MODID, "textures/gui/race_picker.png");
    private boolean selected;
    private int screen_width;

    public ClassButtonWidget(int x, int y, int width, int height, Component component, boolean selected) {
        super(x, y, width, height, component);
        this.selected = selected;
        this.screen_width = Minecraft.getInstance().getWindow().getGuiScaledWidth();
    }

    @Override
    public void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        if (selected) {
            pGuiGraphics.blit(WIDGETS_LOCATION, this.getX() + (int) (this.screen_width * 0.03125), this.getY(), this.getWidth(), this.getHeight(), 662, 66, 388, 53, 1275, 899);
            pGuiGraphics.drawCenteredString(Minecraft.getInstance().font, this.getMessage().getString(), this.getX() + this.getWidth() / 2 + +(int) (this.screen_width * 0.03125), this.getY() + (int) (3), Color.WHITE.getColor());
        } else {
            pGuiGraphics.blit(WIDGETS_LOCATION, this.getX(), this.getY(), this.getWidth(), this.getHeight(), 662, 6, 388, 53, 1275, 899);
            pGuiGraphics.drawCenteredString(Minecraft.getInstance().font, this.getMessage().getString(), this.getX() + this.getWidth() / 2, this.getY() + (int) (3), Color.WHITE.getColor());
        }
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput pNarrationElementOutput) {

    }

}
