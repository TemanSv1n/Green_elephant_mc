
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.svisvi.slonik.init;

import net.svisvi.slonik.SlonikMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.BlockItem;

public class SlonikModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, SlonikMod.MODID);
	public static final RegistryObject<Item> BREADPLATE = block(SlonikModBlocks.BREADPLATE, SlonikModTabs.TAB_GREENELEPHANT);
	public static final RegistryObject<Item> EMPTY_PLATE = block(SlonikModBlocks.EMPTY_PLATE, SlonikModTabs.TAB_GREENELEPHANT);
	public static final RegistryObject<Item> PSK = block(SlonikModBlocks.PSK, SlonikModTabs.TAB_GREENELEPHANT);

	private static RegistryObject<Item> block(RegistryObject<Block> block, CreativeModeTab tab) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
	}
}
