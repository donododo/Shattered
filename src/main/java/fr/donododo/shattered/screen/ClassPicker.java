package fr.donododo.shattered.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import fr.donododo.shattered.Shattered;
import fr.donododo.shattered.loaders.PlayerClass;
import fr.donododo.shattered.screen.utils.Colors;
import fr.donododo.shattered.screen.utils.GuiGraphicsUtils;
import fr.donododo.shattered.screen.widgets.ClassButtonWidget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;

public class ClassPicker extends Screen {

    //TODO : take care of gui scale for text rendering

    private static final ResourceLocation WIDGETS_LOCATION = new ResourceLocation(Shattered.MODID, "textures/gui/race_picker.png");
    private int i = -1;
    public List<ClassButtonWidget> buttonsClassButtonWidgets = new ArrayList<>();
    PlayerClass pl_cl;

    public ClassPicker() {
        super(Component.nullToEmpty("ClassPicker"));
    }

    @Override
    protected void init() {
        super.init();
        buttonsClassButtonWidgets = new ArrayList<>();
        i = -1;
        for (PlayerClass cl : Shattered.classList.values()) {
            i = i + 1;
            if (i == 0) {
                pl_cl = cl;
                ClassButtonWidget buttonWidget = new ClassButtonWidget((int) (this.width * 0.0084), (int) (this.height * 0.083), (int) (this.width * 0.20), (int) (this.height * 0.049), Component.nullToEmpty(cl.name), true);
                this.addRenderableWidget(buttonWidget);
                this.buttonsClassButtonWidgets.add(buttonWidget);
                continue;
            }
            ClassButtonWidget buttonWidget = new ClassButtonWidget((int) (this.width * 0.0084), (int) (this.height * 0.083) + ((int) (this.height * 0.065) * i), (int) (this.width * 0.20), (int) (this.height * 0.049), Component.nullToEmpty(cl.name), false);
            this.buttonsClassButtonWidgets.add(buttonWidget);
            this.addRenderableWidget(buttonWidget);
        }
        this.addRenderableWidget(new Button.Builder(Component.nullToEmpty("Commencer l'aventure"), (button) -> {
        }).bounds((int) (this.width * 0.68), (int) (this.height * 0.88), (int) (this.width * 0.24), (int) (this.height * 0.056)).build());
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        boolean sel = false;
        for (ClassButtonWidget but : buttonsClassButtonWidgets) {
            if (but.isMouseOver(pMouseX, pMouseY)) {
                sel = true;
            }
        }
        if (sel) {
            for (ClassButtonWidget but : buttonsClassButtonWidgets) {
                if (but.isMouseOver(pMouseX, pMouseY)) {
                    pl_cl = Shattered.classList.get(but.getMessage().getString());
                    but.setSelected(true);
                    sel = true;
                } else {
                    but.setSelected(false);
                }
            }
        }
        return super.mouseClicked(pMouseX, pMouseY, pButton);
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        // Arrow Buttons : TODO: Components
        pGuiGraphics.blit(WIDGETS_LOCATION, (int) (this.width * 0.09), (int) (this.height * 0.015), (int) (this.width * 0.040), (int) (this.height * 0.045), 150, 34, 22, 14, 256, 256); // Skill Tree Icon
        pGuiGraphics.blit(WIDGETS_LOCATION, (int) (this.width * 0.09), (int) (this.height * 0.925), (int) (this.width * 0.040), (int) (this.height * 0.045), 150, 51, 22, 14, 256, 256); // Skill Tree Icon

        // Skill Tree icon :
        pGuiGraphics.blit(WIDGETS_LOCATION, (int) (this.width * 0.957), (int) (this.height * 0.19), (int) (this.width * 0.043), (int) (this.height * 0.07), 150, 68, 32, 28, 256, 256); // Skill Tree Icon
        pGuiGraphics.pose().scale(0.8f, 0.8f, 0.8f);
        pGuiGraphics.renderFakeItem(Items.OAK_SAPLING.getDefaultInstance(), (int) ((this.width * 0.965) * 1.25), (int) ((this.height * 0.20) * 1.25));
        pGuiGraphics.pose().scale(1.25f, 1.25f, 1.25f);

        // Draw description :
        List<FormattedCharSequence> f = Minecraft.getInstance().font.split(FormattedText.of(pl_cl.description), (int) ((this.width * 0.28) * 2));
        int interline = 0;
        for (FormattedCharSequence form : f) {
            interline = interline + (int) (this.height * 0.032);
            GuiGraphicsUtils.drawCenteredScaledString(pGuiGraphics, font, form, 0.5f, (int) (this.width * 0.795), (int) (this.height * 0.23) + interline, Colors.WHITE);
        }

        // Draw stats title :
        GuiGraphicsUtils.drawScaledString(pGuiGraphics, font, "Force :", 0.5f, (int) (this.width * 0.7), (int) (this.height * 0.57), Colors.WHITE);
        GuiGraphicsUtils.drawScaledString(pGuiGraphics, font, "Consistance :", 0.5f, (int) (this.width * 0.7), (int) (this.height * 0.64), Colors.WHITE);
        GuiGraphicsUtils.drawScaledString(pGuiGraphics, font, "Défense :", 0.5f, (int) (this.width * 0.7), (int) (this.height * 0.71), Colors.WHITE);

        // Background :
        pGuiGraphics.blit(WIDGETS_LOCATION, (int) (this.width * 0.63), (int) (this.height * 0.054), (int) (this.width * 0.34), (int) (this.height * 0.83), 1, 1, 149, 168, 256, 256);
        pGuiGraphics.drawCenteredString(Minecraft.getInstance().font, Component.translatable("gui.race_picker.title"), (int) (this.width * 0.46), (int) (this.height * 0.021), Colors.WHITE);

        // Draw race title
        int name_width = Minecraft.getInstance().font.width(pl_cl.name) / 2;
        pGuiGraphics.drawCenteredString(Minecraft.getInstance().font, pl_cl.name, (int) (this.width * 0.80), (int) (this.height * 0.13), Colors.WHITE);
        pGuiGraphics.hLine((int) (this.width * 0.798) - name_width, (int) (this.width * 0.80) + name_width, (int) (this.height * 0.165), Colors.WHITE);

        // Stats Bars :
        drawBar(pGuiGraphics, (int) (this.width * 0.71), (int) (this.height * 0.595), (int) (this.width * 0.22), (int) (this.height * 0.015), ((double) pl_cl.strength / 10));
        pGuiGraphics.blit(WIDGETS_LOCATION, (int) (this.width * 0.66), (int) (this.height * 0.565), (int) (this.width * 0.033), (int) (this.height * 0.059), 150, 3, 18, 17, 256, 256); // Strengh Icon

        drawBar(pGuiGraphics, (int) (this.width * 0.71), (int) (this.height * 0.665), (int) (this.width * 0.22), (int) (this.height * 0.015), ((double) pl_cl.consistency / 10));
        pGuiGraphics.blit(WIDGETS_LOCATION, (int) (this.width * 0.662), (int) (this.height * 0.64), (int) (this.width * 0.028), (int) (this.height * 0.05), 150, 23, 7, 7, 256, 256); // Health Icon

        drawBar(pGuiGraphics, (int) (this.width * 0.71), (int) (this.height * 0.735), (int) (this.width * 0.22), (int) (this.height * 0.015), ((double) pl_cl.defense / 10));
        pGuiGraphics.blit(WIDGETS_LOCATION, (int) (this.width * 0.662), (int) (this.height * 0.70), (int) (this.width * 0.028), (int) (this.height * 0.05), 160, 22, 9, 9, 256, 256); // Armor Icon

        // Testing purpose :
        // Draw skill tree button :

        pGuiGraphics.blit(WIDGETS_LOCATION, (int) (this.width * 0.957), (int) (this.height * 0.11), (int) (this.width * 0.043), (int) (this.height * 0.07), 150, 68, 32, 28, 256, 256); // Skill Tree Icon
        pGuiGraphics.pose().scale(0.8f, 0.8f, 0.8f);
        pGuiGraphics.renderFakeItem(Items.BOOK.getDefaultInstance(), (int) ((this.width * 0.96) * 1.25), (int) ((this.height * 0.12) * 1.25));
        pGuiGraphics.pose().scale(1.25f, 1.25f, 1.25f);

        // Draw icon like advancement next to race name :
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }

    public void drawBar(GuiGraphics guiGraphics, int x, int y, int width, int height, double stats_percent) {
        RenderSystem.enableBlend();
        if (stats_percent >= 0.65) {
            guiGraphics.blit(WIDGETS_LOCATION, x, y, width, height, 1, 179, 182, 5, 256, 256); // Barshadow Green
            guiGraphics.blit(WIDGETS_LOCATION, x, y, (int) (width * stats_percent), height, 1, 184, (int) (182 * stats_percent), 5, 256, 256); // BarColored Green
        }
        if (stats_percent >= 0.3 && stats_percent < 0.65) {
            guiGraphics.blit(WIDGETS_LOCATION, x, y, width, height, 1, 189, 182, 5, 256, 256); // Barshadow Yellow
            guiGraphics.blit(WIDGETS_LOCATION, x, y, (int) (width * stats_percent), height, 1, 194, (int) (182 * stats_percent), 5, 256, 256); // BarColored Yellow
        }
        if (stats_percent < 0.3) {
            guiGraphics.blit(WIDGETS_LOCATION, x, y, width, height, 1, 169, 182, 5, 256, 256); // Barshadow Yellow
            guiGraphics.blit(WIDGETS_LOCATION, x, y, (int) (width * stats_percent), height, 1, 174, (int) (182 * stats_percent), 5, 256, 256); // BarColored Yellow
        }
        guiGraphics.blit(WIDGETS_LOCATION, x, y, width, height, 1, 199, 182, 5, 256, 256); // BarSegment
        RenderSystem.disableBlend();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
