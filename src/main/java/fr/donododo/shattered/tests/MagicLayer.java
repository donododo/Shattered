package fr.donododo.shattered.tests;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.donododo.shattered.Shattered;
import fr.donododo.shattered.entities.CrystalBlock;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;
import software.bernie.geckolib.util.RenderUtils;

public class MagicLayer extends GeoRenderLayer<CrystalBlock> {

    private float tick = 0;
    public static final ModelLayerLocation OUTER_RING_LAYER = new ModelLayerLocation(new ResourceLocation(Shattered.MODID, "textures/effect/magic_circle/outer_ring.png"), "outer_ring");
    public static final ModelLayerLocation INNER_RING_LAYER = new ModelLayerLocation(new ResourceLocation(Shattered.MODID, "magic_circle"), "inner_ring");
    public static final ModelLayerLocation VALID_RITUAL_INDICATOR = new ModelLayerLocation(new ResourceLocation(Shattered.MODID, "magic_circle"), "valid_ritual_indicator");

    public MagicLayer(GeoRenderer entityRendererIn) {
        super(entityRendererIn);
    }

    public static LayerDefinition createLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild(OUTER_RING_LAYER.getLayer(), CubeListBuilder.create()
                .texOffs(0, 0)
                .addBox(-64, 10, -64, 128, 128, 1), PartPose.ZERO);
        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void render(PoseStack poseStack, CrystalBlock animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderType armorRenderType = RenderType.entityCutout(OUTER_RING_LAYER.getModel());
        poseStack.scale(0.5f, 0.5f, 0.5f);
        poseStack.pushPose();
        tick = tick + 0.1f;
        Vector3f pivot = new Vector3f(0.0f, 1.0f, 0.0f);
        Vector3f axis = new Vector3f(0.0f, 0.0f, 1.0f);
        poseStack.mulPose(new Quaternionf().fromAxisAngleDeg(1.0f, 0.0f, 0.0f, 90.0f));
        poseStack.translate(pivot.x(), pivot.y(), pivot.z());
        poseStack.mulPose(new Quaternionf().fromAxisAngleDeg(axis, 0));
        poseStack.translate(-pivot.x(), -pivot.y(), -pivot.z());

        //poseStack.rotateAround(Axis.ZN.rotationDegrees(tick), 2.0f, 0f, 128f);
        if (tick >= 360) {
            tick = 0;
        }
        createLayer().bakeRoot().render(poseStack, bufferSource.getBuffer(armorRenderType), packedLight, packedOverlay, 1, 1, 1, 1);
        poseStack.popPose();
    }
}


