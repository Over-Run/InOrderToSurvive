package io.github.overrun.baka4n.inordertosurvive.tabs;

import io.github.overrun.baka4n.inordertosurvive.registry.AllRegistry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import static io.github.overrun.baka4n.inordertosurvive.Modid.modid;
import static io.github.overrun.baka4n.inordertosurvive.registry.AllRegistry.ItemRegistry.KnifeRegistry.*;

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
	public static final CreativeModeTab knife = new CreativeModeTab(modid + "." + "knife") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(flint_machining_knife.get().asItem());
		}
	};
}
