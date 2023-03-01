package net.svisvi.slonik.entity.spawneggs;

import net.svisvi.slonik.init.SlonikModTabs;
import net.svisvi.slonik.init.SlonikModEntities;
import net.svisvi.slonik.SlonikMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.ForgeSpawnEggItem;

import net.minecraft.world.item.Item;

public class GeckoLibSpawnEggs {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, SlonikMod.MODID);

	public static final RegistryObject<Item> PAHOM = REGISTRY.register("pahom_spawn_egg", () -> new ForgeSpawnEggItem(SlonikModEntities.PAHOM, -13408768, -205, new Item.Properties().tab(SlonikModTabs.TAB_GREENELEPHANT)));
}
