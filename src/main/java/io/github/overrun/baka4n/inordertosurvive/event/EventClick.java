package io.github.overrun.baka4n.inordertosurvive.event;

import io.github.overrun.baka4n.inordertosurvive.InOrderToSurvive;
import io.github.overrun.baka4n.inordertosurvive.registry.AllRegistry;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author baka4n
 * TODO: 2022/2/1 Happy Chinese New Year to all of you
 * When right-clicking on a stone, flint will turn into flint flakes
 * I'm sorry you grinded the flint too hard
 * you have a 70% chance of getting it
 */
@Mod.EventBusSubscriber(Dist.CLIENT)
public class EventClick {
	@SubscribeEvent
	public static void LookingBlocks() {

	}

	@SubscribeEvent
	public static void rightClient(PlayerInteractEvent.RightClickBlock event) {

		ItemStack stack = event.getItemStack();
		int value = event.getItemStack().getCount();
		int random = (int) (Math.random() * 100);
		int a = value - 1;
		Player entityPlayer = event.getPlayer();

		if (stack.getItem().asItem().toString().equals(Items.FLINT.toString())) {
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
}
