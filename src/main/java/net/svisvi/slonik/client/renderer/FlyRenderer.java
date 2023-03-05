
package net.svisvi.slonik.client.renderer;

import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import net.svisvi.slonik.entity.model.FlyModel;
import net.svisvi.slonik.entity.FlyEntity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class FlyRenderer extends GeoEntityRenderer<FlyEntity> {
	public FlyRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new FlyModel());
		this.shadowRadius = 0.30000000000000004f;
	}

	@Override
	public RenderType getRenderType(FlyEntity entity, float partialTicks, PoseStack stack, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
		stack.scale(1f, 1f, 1f);
		return RenderType.entityTranslucent(getTextureLocation(entity));
	}
}
