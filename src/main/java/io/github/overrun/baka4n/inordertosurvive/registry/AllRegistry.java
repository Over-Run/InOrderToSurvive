package io.github.overrun.baka4n.inordertosurvive.registry;
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
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static io.github.overrun.baka4n.inordertosurvive.Final.*;
import static io.github.overrun.baka4n.inordertosurvive.registry.AllRegistry.ItemRegistry.KnifeRegistry;
import static io.github.overrun.baka4n.inordertosurvive.registry.MyTiers.Flint;

/**
 * @author baak4n
 * all in all: block registrym, item registy
 */
public class AllRegistry {

	/*
		Forge IEvenet Bus Registry
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
			items.register(name, () -> new BlockItem(block.get(), properties));
			return RETURN;
		}
		public static RegistryObject<Block> test_block;

		public static void init() {
			test_block = registerBlock("test_block", () -> new BasicBlock(metalsProperties.strength(9f)
					.requiresCorrectToolForDrops()), properties.tab(block_tabs));
		}
	}

	public static class ItemRegistry {
		public static RegistryObject<Item> tin_ingot;
		public static RegistryObject<Item> flint_flakes;
		public static RegistryObject<Item> fuck;
		public static void init() {
			tin_ingot = items.register("tin_ingot",
					() -> new TinIngot(properties.tab(CreativeModeTab.TAB_MISC)));

			flint_flakes = items.register("flint_flakes",
					() -> new BasicItem(basic_tabs));
			fuck = items.register("fuck",
					() -> new BasicItem(basic_tabs));

		}
		public static class KnifeRegistry {

			public static Tag<Block> getTagFromVanillaTier(MyTiers tier) {
				if (tier == Flint) {
					return Flints;
				} else {
					return null;
				}
			}

			public static Tags.IOptionalNamedTag<Block> tag() {
				return BlockTags.createOptional(new ResourceLocation("forge", "needs_wood_tool"));
			}

			public static RegistryObject<Item> flint_machining_knife;
			public static void init() {
				flint_machining_knife = items.register("flint_machining_knife", () -> new Knife(Flint, 1, -2.8F, properties.tab(knife)));
			}
		}
	}
}
