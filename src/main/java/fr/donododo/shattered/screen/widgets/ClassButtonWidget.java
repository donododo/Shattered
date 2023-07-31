package fr.donododo.shattered.screen.widgets;

import com.mojang.blaze3d.systems.RenderSystem;
import fr.donododo.shattered.Shattered;
import fr.donododo.shattered.screen.utils.Colors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ClassButtonWidget extends AbstractWidget {

    private static final ResourceLocation WIDGETS_LOCATION = new ResourceLocation(Shattered.MODID, "textures/gui/race_picker.png");
    private boolean selected;
    private int original_X;
    private int screen_width;

    public ClassButtonWidget(int x, int y, int width, int height, Component component, boolean selected) {
        super(x, y, width, height, component);
        this.selected = selected;
        original_X = x;
        this.screen_width = Minecraft.getInstance().getWindow().getGuiScaledWidth();
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        if (selected) {
            pGuiGraphics.blit(WIDGETS_LOCATION, this.getX() + (int) (this.screen_width * 0.03125), this.getY(), this.getWidth(), this.getHeight(), 1, 227, 200, 20, 256, 256);
            pGuiGraphics.drawCenteredString(Minecraft.getInstance().font, this.getMessage().getString(), this.getX() + this.getWidth() / 2 + +(int) (this.screen_width * 0.03125), this.getY() + 2, Colors.WHITE);
        } else {
            pGuiGraphics.blit(WIDGETS_LOCATION, this.getX(), this.getY(), this.getWidth(), this.getHeight(), 1, 205, 200, 20, 256, 256);
            pGuiGraphics.drawCenteredString(Minecraft.getInstance().font, this.getMessage().getString(), this.getX() + this.getWidth() / 2, this.getY() + 2, Colors.WHITE);
        }
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput pNarrationElementOutput) {

    }

    @OnlyIn(Dist.CLIENT)
    public interface OnPress {
        void onPress(Button pButton);
    }

}
