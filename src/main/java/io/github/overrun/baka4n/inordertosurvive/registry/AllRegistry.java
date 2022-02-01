package io.github.overrun.baka4n.inordertosurvive.registry;

import io.github.overrun.baka4n.inordertosurvive.Tabs.ItemTabs;
import io.github.overrun.baka4n.inordertosurvive.block.BasicBlock;
import io.github.overrun.baka4n.inordertosurvive.item.BasicItem;
import io.github.overrun.baka4n.inordertosurvive.item.TinIngot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static io.github.overrun.baka4n.inordertosurvive.Modid.modid;

/**
 * @author baak4n
 * all in all: block registrym, item registy
 */
public class AllRegistry {

	public static final DeferredRegister<Item> items =
			DeferredRegister.create(ForgeRegistries.ITEMS, modid);
	public static final DeferredRegister<Block> blocks =
			DeferredRegister.create(ForgeRegistries.BLOCKS, modid);

	/**
	 * Forge IEvenet Bus Registry
	 * @param iEventBus
	 */
	public static void registry(IEventBus iEventBus) {
		new BlockRegistry();
		new ItemRegistry();
		// first registry blocks, second registry items.
		blocks.register(iEventBus);
		items.register(iEventBus);

	}

	public static  class BlockRegistry {
		public static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, Item.Properties properties) {
			RegistryObject<T> RETURN = blocks.register(name, block);
			registerBlockItem(name, RETURN, properties);
			return RETURN;
		}
		public static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
																		   Item.Properties properties) {
			return items.register(name, () -> new BlockItem(block.get(),
					properties));
		}
		public static final RegistryObject<Block> test_block;
		static {
			test_block = registerBlock("test_block", () -> new BasicBlock(BasicBlock.pProperties.strength(9f)
					.requiresCorrectToolForDrops()), BasicBlock.properties.tab(ItemTabs.block_tabs));
		}
	}

	public static class ItemRegistry {
		public static final RegistryObject<Item> tin_ingot;
		public static final RegistryObject<Item> flint_flakes;

		static {
			tin_ingot = items.register("tin_ingot",
					() -> new TinIngot(TinIngot.tinIngot.tab(CreativeModeTab.TAB_MISC)));
			flint_flakes = items.register("flint_flakes",
					() -> new BasicItem(ItemTabs.basic_tabs));
		}
	}

}
