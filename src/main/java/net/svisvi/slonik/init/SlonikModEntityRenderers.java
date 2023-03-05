
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.svisvi.slonik.init;

import net.svisvi.slonik.client.renderer.PahomRenderer;
import net.svisvi.slonik.client.renderer.FlyRenderer;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SlonikModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(SlonikModEntities.FLY.get(), FlyRenderer::new);
		event.registerEntityRenderer(SlonikModEntities.PAHOM.get(), PahomRenderer::new);
	}
}
