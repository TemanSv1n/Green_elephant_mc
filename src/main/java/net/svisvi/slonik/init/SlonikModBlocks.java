
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.svisvi.slonik.init;

import net.svisvi.slonik.block.PskBlock;
import net.svisvi.slonik.block.EmptyplateBlock;
import net.svisvi.slonik.block.BreadplateBlock;
import net.svisvi.slonik.SlonikMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.block.Block;

public class SlonikModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, SlonikMod.MODID);
	public static final RegistryObject<Block> BREADPLATE = REGISTRY.register("breadplate", () -> new BreadplateBlock());
	public static final RegistryObject<Block> EMPTY_PLATE = REGISTRY.register("empty_plate", () -> new EmptyplateBlock());
	public static final RegistryObject<Block> PSK = REGISTRY.register("psk", () -> new PskBlock());

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientSideHandler {
		@SubscribeEvent
		public static void clientSetup(FMLClientSetupEvent event) {
			BreadplateBlock.registerRenderLayer();
			EmptyplateBlock.registerRenderLayer();
		}
	}
}
