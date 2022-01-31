package io.github.overrun.baka4n.inordertosurvive.Tabs;

import io.github.overrun.baka4n.inordertosurvive.Modid;
import io.github.overrun.baka4n.inordertosurvive.registry.AllRegistry;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

import static io.github.overrun.baka4n.inordertosurvive.Modid.modid;

public class ItemTabs {
	public static final CreativeModeTab basic_tabs = new CreativeModeTab(modid +"." + "basic") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(AllRegistry.ItemRegistry.flint_flakes.get().asItem());
		}
	};
	public static final CreativeModeTab block_tabs = new CreativeModeTab(modid +"." + "block") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(AllRegistry.BlockRegistry.test_block.get().asItem());
		}
	};
}
