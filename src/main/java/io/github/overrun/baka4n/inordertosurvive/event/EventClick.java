package io.github.overrun.baka4n.inordertosurvive.event;

import io.github.overrun.baka4n.inordertosurvive.registry.AllRegistry;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import javax.annotation.Nullable;

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
		if (stack.getItem().asItem().toString().equals(Items.FLINT.toString()) && world.getBlockState(blockPos).getMaterial() == Material.STONE) {
			if (value != 1) {
				ItemStack handStack = new ItemStack(Items.FLINT.asItem(), a);

				entityPlayer.setItemInHand(InteractionHand.MAIN_HAND, handStack);
				if (random <= 70) {
					ItemStack dropStack = new ItemStack(AllRegistry.ItemRegistry.flint_flakes.get().asItem());
					entityPlayer.drop(dropStack, true);
				}
			} else {
				ItemStack handStack = new ItemStack(Items.AIR.asItem());
				entityPlayer.setItemInHand(InteractionHand.MAIN_HAND, handStack);

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
		Item handItem = event.getPlayer().getItemInHand(InteractionHand.MAIN_HAND).getItem().asItem();
		ItemStack stack = new ItemStack(handItem);
		LevelAccessor world = event.getWorld();
		BlockPos blockPos = event.getPos();
		if (!world.getBlockState(blockPos).getBlock().asItem().toString().equals(Items.GRAVEL.toString())) {
			event.setCanceled(true);
		}

	}
}
