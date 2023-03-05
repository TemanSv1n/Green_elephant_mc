
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.svisvi.slonik.init;

import net.svisvi.slonik.SlonikMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleType;

public class SlonikModParticleTypes {
	public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, SlonikMod.MODID);
	public static final RegistryObject<ParticleType<?>> SHIT_DRIP_PARTICLE = REGISTRY.register("shit_drip_particle", () -> new SimpleParticleType(false));
}
