
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.svisvi.slonik.init;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;

public class SlonikModTabs {
	public static CreativeModeTab TAB_GREENELEPHANT;

	public static void load() {
		TAB_GREENELEPHANT = new CreativeModeTab("tabgreenelephant") {
			@Override
			public ItemStack makeIcon() {
				return new ItemStack(Items.TRIDENT);
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
}
