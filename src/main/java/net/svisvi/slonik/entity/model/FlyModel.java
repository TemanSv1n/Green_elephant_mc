package net.svisvi.slonik.entity.model;

import software.bernie.geckolib3.model.AnimatedGeoModel;

import net.svisvi.slonik.entity.FlyEntity;

import net.minecraft.resources.ResourceLocation;

public class FlyModel extends AnimatedGeoModel<FlyEntity> {
	@Override
	public ResourceLocation getAnimationFileLocation(FlyEntity entity) {
		return new ResourceLocation("slonik", "animations/j_fly.animation.json");
	}

	@Override
	public ResourceLocation getModelLocation(FlyEntity entity) {
		return new ResourceLocation("slonik", "geo/j_fly.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(FlyEntity entity) {
		return new ResourceLocation("slonik", "textures/entities/" + entity.getTexture() + ".png");
	}

}
