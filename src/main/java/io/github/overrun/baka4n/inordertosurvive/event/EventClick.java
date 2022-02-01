package io.github.overrun.baka4n.inordertosurvive.event;

import io.github.overrun.baka4n.inordertosurvive.registry.AllRegistry;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.material.Material;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import static net.minecraft.world.InteractionHand.MAIN_HAND;
import static net.minecraft.world.item.Items.*;


/**
 * @author baka4n
 * TODO: 2022/2/1 Happy Chinese New Year to all of you
 * When right-clicking on a stone, flint will turn into flint flakes
 * I'm sorry you grinded the flint too hard
 * you have a 70% chance of getting it
 */
@EventBusSubscriber(Dist.CLIENT)
public class EventClick {
	@SubscribeEvent
	public static void rightClient(PlayerInteractEvent.RightClickBlock event) {
		ItemStack stack = event.getItemStack();
		int value = event.getItemStack().getCount();
		int random = (int) (Math.random() * 100);
		int a = value - 1;
		Level world = event.getWorld();
		BlockPos blockPos = event.getPos();
		Player entityPlayer = event.getPlayer();
		if (stack.getItem().asItem().toString().equals(FLINT.toString()) && world.getBlockState(blockPos).getMaterial() == Material.STONE) {
			if (value != 1) {
				ItemStack handStack = new ItemStack(FLINT.asItem(), a);

				entityPlayer.setItemInHand(MAIN_HAND, handStack);
				if (random <= 70) {
					ItemStack dropStack = new ItemStack(AllRegistry.ItemRegistry.flint_flakes.get().asItem());
					entityPlayer.drop(dropStack, true);
				}
			} else {
				ItemStack handStack = new ItemStack(AIR.asItem());
				entityPlayer.setItemInHand(MAIN_HAND, handStack);

				if (random <= 70) {
					ItemStack dropStack = new ItemStack(AllRegistry.ItemRegistry.flint_flakes.get().asItem());
					entityPlayer.drop(dropStack, true);
				}
			}
		}
	}
	// Blocks cannot be broken in the early stage. You may have to make flint tools to break them.
	// 未完工
	@SubscribeEvent
	public static void Break(BlockEvent.BreakEvent event) {
		/*
		get player
		get my main hand item
		get my main hand item stack
		 */
		Player eventPlayer = event.getPlayer();
		Item handItem = eventPlayer.getItemInHand(MAIN_HAND).getItem().asItem();
		Item stack = new ItemStack(handItem).getItem().asItem();
		/*
		  get world
		  get break world block pos
		  give break world block item
		 */
		LevelAccessor world = event.getWorld();
		BlockPos blockPos = event.getPos();
		Item item = world.getBlockState(blockPos).getBlock().asItem();

		// main hand item don't have air
		boolean b = !stack.toString().equals(AIR.toString());
		//random
		event.setCanceled(!item.equals(GRAVEL)
				&& !ifBool(item, ACACIA_LEAVES, b)
				&& !ifBool(item, BIRCH_LEAVES, b)
				&& !ifBool(item, DARK_OAK_LEAVES, b)
				&& !ifBool(item, JUNGLE_LEAVES, b)
				&& !ifBool(item, SPRUCE_LEAVES, b)
				&& !ifBool(item, OAK_LEAVES, b));
	}
	public static boolean ifBool(Item item,Item item2, boolean b) {
		return item.equals(item2) && b;
	}
}
