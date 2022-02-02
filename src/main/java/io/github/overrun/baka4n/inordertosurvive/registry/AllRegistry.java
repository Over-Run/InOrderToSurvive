package io.github.overrun.baka4n.inordertosurvive.registry;

import io.github.overrun.baka4n.inordertosurvive.tabs.ItemTabs;
import io.github.overrun.baka4n.inordertosurvive.block.BasicBlock;
import io.github.overrun.baka4n.inordertosurvive.item.BasicItem;
import io.github.overrun.baka4n.inordertosurvive.item.TinIngot;
import io.github.overrun.baka4n.inordertosurvive.tool.Knife;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static io.github.overrun.baka4n.inordertosurvive.Modid.modid;
import static io.github.overrun.baka4n.inordertosurvive.registry.AllRegistry.ItemRegistry.KnifeRegistry;
import static io.github.overrun.baka4n.inordertosurvive.registry.MyTiers.Flint;

/**
 * @author baak4n
 * all in all: block registrym, item registy
 */
public class AllRegistry {

	public static final DeferredRegister<Block> blocks =
			DeferredRegister.create(ForgeRegistries.BLOCKS, modid);
	public static final DeferredRegister<Item> items =
			DeferredRegister.create(ForgeRegistries.ITEMS, modid);
	public static final DeferredRegister<Item> knifes =
			DeferredRegister.create(ForgeRegistries.ITEMS, modid);
	/**
	 * Forge IEvenet Bus Registry
	 * @param iEventBus
	 */
	public static void registry(IEventBus iEventBus) {
		BlockRegistry.init();
		ItemRegistry.init();
		KnifeRegistry.init();
		// first registry blocks, second registry items.
		blocks.register(iEventBus);
		items.register(iEventBus);
		knifes.register(iEventBus);

	}

	public static  class BlockRegistry {
		public static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, Item.Properties properties) {
			RegistryObject<T> RETURN = blocks.register(name, block);
			registerBlockItem(name, RETURN, properties);
			return RETURN;
		}
		public static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, Item.Properties properties) {
			return items.register(name, () -> new BlockItem(block.get(),
					properties));
		}
		public static RegistryObject<Block> test_block;

		public static void init() {
			test_block = registerBlock("test_block", () -> new BasicBlock(BasicBlock.pProperties.strength(9f)
					.requiresCorrectToolForDrops()), BasicBlock.properties.tab(ItemTabs.block_tabs));
		}
	}

	public static class ItemRegistry {
		public static RegistryObject<Item> tin_ingot;
		public static RegistryObject<Item> flint_flakes;
		public static RegistryObject<Item> fuck;
		public static void init() {
			tin_ingot = items.register("tin_ingot",
					() -> new TinIngot(TinIngot.tinIngot.tab(CreativeModeTab.TAB_MISC)));
			flint_flakes = items.register("flint_flakes",
					() -> new BasicItem(ItemTabs.basic_tabs));
			fuck = items.register("fuck",
					() -> new BasicItem(ItemTabs.basic_tabs));
		}
		public static class KnifeRegistry {

			public static Tag<Block> getTagFromVanillaTier(MyTiers tier)
			{
				return switch(tier)
						{
							case Flint -> Flints;
						};
			}

			public static final Tags.IOptionalNamedTag<Block> Flints = tag("needs_wood_tool");

			private static Tags.IOptionalNamedTag<Block> tag(String name)
			{
				return BlockTags.createOptional(new ResourceLocation("forge", name));
			}

			public static RegistryObject<Item> flint_machining_knife;
			public static void init() {
				flint_machining_knife = items.register("flint_machining_knife", () -> new Knife(Flint, 1, -2.8F, Knife.properties.tab(ItemTabs.knife)));
			}
		}
	}
}
