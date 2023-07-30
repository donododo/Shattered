package fr.donododo.shattered.renderers;

import fr.donododo.shattered.Shattered;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;

public class CrystalModel extends GeoModel {

    @Override
    public ResourceLocation getModelResource(GeoAnimatable animatable) {
        return new ResourceLocation(Shattered.MODID, "geo/crystal.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GeoAnimatable animatable) {
        return new ResourceLocation(Shattered.MODID, "textures/block/crystal.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GeoAnimatable animatable) {
        return new ResourceLocation(Shattered.MODID, "animations/block/crystal.animation.json");
    }


}
