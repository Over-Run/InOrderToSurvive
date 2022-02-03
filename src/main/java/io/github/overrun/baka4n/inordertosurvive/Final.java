package io.github.overrun.baka4n.inordertosurvive;

import io.github.overrun.baka4n.inordertosurvive.registry.AllRegistry;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

import static io.github.overrun.baka4n.inordertosurvive.registry.AllRegistry.ItemRegistry.KnifeRegistry.flint_machining_knife;
import static java.util.Set.of;
import static net.minecraft.world.item.Items.AIR;

// save final
public class Final {

	public static final Item.Properties properties = new Item.Properties();
	public static final BlockBehaviour.Properties metalsProperties = BlockBehaviour.Properties.of(Material.METAL);
	public static final String inOrderToSurvive = "inordertosurvive";

	public static final ToolAction toolActionKnife = ToolAction.get("pickaxe_dig");
	public static final Set<ToolAction> default_knife = of(toolActionKnife);

	//random
	public static int ran = (int) (Math.random() * 100);

	//log4j
	public static final Logger msg = LogManager.getLogger();

	//Item Stacks
	public static final ItemStack airs = new ItemStack(AIR);


	//registry object
	public static final DeferredRegister<Block> blocks =
			DeferredRegister.create(ForgeRegistries.BLOCKS, inOrderToSurvive);
	public static final DeferredRegister<Item> items =
			DeferredRegister.create(ForgeRegistries.ITEMS, inOrderToSurvive);
	public static final DeferredRegister<Item> knifes =
			DeferredRegister.create(ForgeRegistries.ITEMS, inOrderToSurvive);

	// tag
	public static final Tags.IOptionalNamedTag<Block> Flints = AllRegistry.ItemRegistry.KnifeRegistry.tag();

	//ItemGroup
	public static final CreativeModeTab basic_tabs = new CreativeModeTab(inOrderToSurvive + ".basic") {
		@Override
		public @NotNull ItemStack makeIcon() {
			return new ItemStack(AllRegistry.ItemRegistry.flint_flakes.get().asItem());
		}
	};
	public static final CreativeModeTab block_tabs = new CreativeModeTab(inOrderToSurvive + ".block") {
		@Override
		public @NotNull ItemStack makeIcon() {
			return new ItemStack(AllRegistry.BlockRegistry.test_block.get().asItem());
		}
	};
	public static final CreativeModeTab knife = new CreativeModeTab(inOrderToSurvive + ".knife") {
		@Override
		public @NotNull ItemStack makeIcon() {
			return new ItemStack(flint_machining_knife.get().asItem());
		}
	};
}
