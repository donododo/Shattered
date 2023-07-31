package fr.donododo.shattered.screen.utils;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.FormattedCharSequence;
import org.joml.Vector3f;

public class GuiGraphicsUtils {

    // TODO : Scale from the gui scale option

    // Draw String with scaled font size
    public static void drawScaledString(GuiGraphics pGuiGraphics, Font font, String text, float scale, int x, int y, int color) {
        float originalScaleX = pGuiGraphics.pose().last().pose().getScale(new Vector3f(1.0f, 1.0f, 1.0f)).x;
        float originalScaleY = pGuiGraphics.pose().last().pose().getScale(new Vector3f(1.0f, 1.0f, 1.0f)).y;
        float originalScaleZ = pGuiGraphics.pose().last().pose().getScale(new Vector3f(1.0f, 1.0f, 1.0f)).z;
        pGuiGraphics.pose().scale(scale, scale, scale);
        pGuiGraphics.drawString(font, text, (int) (x / scale), (int) (y / scale), color);
        pGuiGraphics.pose().scale(originalScaleX / scale, originalScaleY / scale, originalScaleZ / scale);
    }

    // Draw Center String with scaled font size
    public static void drawCenteredScaledString(GuiGraphics pGuiGraphics, Font font, FormattedCharSequence text, float scale, int x, int y, int color) {
        float originalScaleX = pGuiGraphics.pose().last().pose().getScale(new Vector3f(1.0f, 1.0f, 1.0f)).x;
        float originalScaleY = pGuiGraphics.pose().last().pose().getScale(new Vector3f(1.0f, 1.0f, 1.0f)).y;
        float originalScaleZ = pGuiGraphics.pose().last().pose().getScale(new Vector3f(1.0f, 1.0f, 1.0f)).z;
        pGuiGraphics.pose().scale(scale, scale, scale);
        pGuiGraphics.drawCenteredString(font, text, (int) (x / scale), (int) (y / scale), color);
        pGuiGraphics.pose().scale(originalScaleX / scale, originalScaleY / scale, originalScaleZ / scale);
    }
}
