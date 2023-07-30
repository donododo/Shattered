package fr.donododo.shattered.renderers;

import fr.donododo.shattered.entities.CrystalBlock;
import fr.donododo.shattered.tests.MagicLayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class CrystalRenderer extends GeoBlockRenderer<CrystalBlock> {

    public CrystalRenderer() {
        super(new CrystalModel());
        this.addRenderLayer(new MagicLayer(this));
    }

    @Override
    public RenderType getRenderType(CrystalBlock animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }

}
