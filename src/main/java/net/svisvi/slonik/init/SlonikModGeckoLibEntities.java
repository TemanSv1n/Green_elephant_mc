package net.svisvi.slonik.init;

import net.svisvi.slonik.entity.spawneggs.GeckoLibSpawnEggs;
import net.svisvi.slonik.entity.PahomEntity;
import net.svisvi.slonik.entity.FlyEntity;
import net.svisvi.slonik.SlonikMod;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

@Mod.EventBusSubscriber(modid = SlonikMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SlonikModGeckoLibEntities {
	@SubscribeEvent
	public static void register(FMLConstructModEvent event) {
		event.enqueueWork(() -> {
			GeckoLibSpawnEggs.REGISTRY.register(FMLJavaModLoadingContext.get().getModEventBus());
		});
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			FlyEntity.init();
			PahomEntity.init();
		});
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(SlonikModEntities.FLY.get(), FlyEntity.createAttributes().build());
		event.put(SlonikModEntities.PAHOM.get(), PahomEntity.createAttributes().build());
	}
}
