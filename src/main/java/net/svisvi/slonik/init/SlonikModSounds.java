
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.svisvi.slonik.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;
import java.util.HashMap;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SlonikModSounds {
	public static Map<ResourceLocation, SoundEvent> REGISTRY = new HashMap<>();
	static {
		REGISTRY.put(new ResourceLocation("slonik", "entity.pahom.death"), new SoundEvent(new ResourceLocation("slonik", "entity.pahom.death")));
		REGISTRY.put(new ResourceLocation("slonik", "entity.pahom.hurt"), new SoundEvent(new ResourceLocation("slonik", "entity.pahom.hurt")));
		REGISTRY.put(new ResourceLocation("slonik", "entity.pahom.kurlyk"), new SoundEvent(new ResourceLocation("slonik", "entity.pahom.kurlyk")));
		REGISTRY.put(new ResourceLocation("slonik", "entity.pahom.flies"), new SoundEvent(new ResourceLocation("slonik", "entity.pahom.flies")));
		REGISTRY.put(new ResourceLocation("slonik", "entity.pahom.spawned"), new SoundEvent(new ResourceLocation("slonik", "entity.pahom.spawned")));
	}

	@SubscribeEvent
	public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
		for (Map.Entry<ResourceLocation, SoundEvent> sound : REGISTRY.entrySet())
			event.getRegistry().register(sound.getValue().setRegistryName(sound.getKey()));
	}
}
