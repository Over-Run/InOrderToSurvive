package io.github.overrun.baka4n.inordertosurvive.event;

import io.github.overrun.baka4n.inordertosurvive.registry.AllRegistry;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.Tag;

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

import java.util.Objects;

import static io.github.overrun.baka4n.inordertosurvive.Final.*;
import static io.github.overrun.baka4n.inordertosurvive.registry.AllRegistry.ItemRegistry.KnifeRegistry.*;
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
		// item count -1
		int value = event.getItemStack().getCount();
		int a = value - 1;
		//1.17.1 World ->1,18,1 Level
		Level world = event.getWorld();
		BlockPos blockPos = event.getPos();
		//entity Player
		Player entityPlayer = event.getPlayer();

		//item stack
		ItemStack stack = event.getItemStack();

		if (stack.getItem().asItem().toString().equals(FLINT.toString()) && world.getBlockState(blockPos).getMaterial() == Material.STONE) {
			if (value != 1) {
				ItemStack handStack = new ItemStack(FLINT.asItem(), a);

				entityPlayer.setItemInHand(MAIN_HAND, handStack);
				if (ran <= 70) {
					ItemStack dropStack = new ItemStack(AllRegistry.ItemRegistry.flint_flakes.get().asItem());
					entityPlayer.drop(dropStack, true);
				}
			} else {
				ItemStack handStack = new ItemStack(AIR.asItem());
				entityPlayer.setItemInHand(MAIN_HAND, handStack);

				if (ran <= 70) {
					ItemStack dropStack = new ItemStack(AllRegistry.ItemRegistry.flint_flakes.get().asItem());
					entityPlayer.drop(dropStack, true);
				}
			}
		}

		if (world.getBlockState(blockPos).getBlock().asItem().toString().equals(GRASS.toString()) && entityPlayer.getItemInHand(MAIN_HAND).getItem().asItem().toString().equals(flint_machining_knife.get().asItem().toString())) {
			final ItemStack fuck = new ItemStack(AllRegistry.ItemRegistry.fuck.get().asItem());
			//get damage
			//fix bug
			//fix damage == 6
			// AllRegistry.KnifeRegistry.flint_machining_knife.get().setDamage(stack, damage);
			final Tag tag = entityPlayer.getItemInHand(MAIN_HAND).finishUsingItem(world, entityPlayer).getTag().get("Damage");
			int damage = Integer.parseInt(String.valueOf(tag)) + 1;

			if (damage == 6) {
				entityPlayer.setItemInHand(MAIN_HAND, airs);
			} else {
				flint_machining_knife.get().setDamage(stack, damage);
			}
			if (ran <=30) {
				entityPlayer.drop(fuck, true);
			}
		}
	}
	// Blocks cannot be broken in the early stage. You may have to make flint tools to break them.
	// 未完0工
	@SubscribeEvent
	public static void Break(BlockEvent.BreakEvent event) {
		final ItemStack fuck = new ItemStack(AllRegistry.ItemRegistry.fuck.get().asItem());
		/*
		get player
		get my main hand item
		get my main hand item stack
		 */
		final Player eventPlayer = event.getPlayer();
		final Item handItem = eventPlayer.getItemInHand(MAIN_HAND).getItem().asItem();
		final Item stack = new ItemStack(handItem).getItem().asItem();
		/*
		  get world
		  get break world block pos
		  give break world block item
		 */
		final LevelAccessor world = event.getWorld();
		final BlockPos blockPos = event.getPos();
		final Item item = world.getBlockState(blockPos).getBlock().asItem();

		// main hand item don't have air
		final boolean b = !stack.toString().equals(AIR.toString());
		final boolean c = stack.toString().equals(flint_machining_knife.get().asItem().toString());

		event.setCanceled(!item.equals(GRAVEL)
				&& !ifBool(item, ACACIA_LEAVES, b)
				&& !ifBool(item, BIRCH_LEAVES, b)
				&& !ifBool(item, DARK_OAK_LEAVES, b)
				&& !ifBool(item, JUNGLE_LEAVES, b)
				&& !ifBool(item, SPRUCE_LEAVES, b)
				&& !ifBool(item, OAK_LEAVES, b)
				&& !ifBool(item, GRASS, c));

		if (ifBool(item, GRASS, c)) {
			Tag tag = eventPlayer.getItemInHand(MAIN_HAND).finishUsingItem((Level) world, eventPlayer).getTag().get("Damage");
			int damage = Integer.parseInt(String.valueOf(tag)) + 1;
			switch (ran) {
				// There is a certain probability that there is no loss
				case 1, 3, 5, 7, 11, 13, 17, 19, 23 ->{}
				default -> {
					if (damage == 6) {
						eventPlayer.setItemInHand(MAIN_HAND, airs);
					} else {
						flint_machining_knife.get().setDamage(eventPlayer.getItemInHand(MAIN_HAND), damage);
					}
				}
			}

			if (ran <=31) {
				eventPlayer.drop(fuck, true);
			}
		}

	}
	public static boolean ifBool(Item item,Item item2, boolean b) {
		return item.equals(item2) && b;
	}
}
