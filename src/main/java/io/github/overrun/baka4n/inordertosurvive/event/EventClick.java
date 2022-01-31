package io.github.overrun.baka4n.inordertosurvive.event;

import io.github.overrun.baka4n.inordertosurvive.registry.AllRegistry;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * @author baka4n
 * TODO: 2022/2/1 Happy Chinese New Year to all of you
 * When right-clicking on a stone, flint will turn into flint flakes
 */
@Mod.EventBusSubscriber(Dist.CLIENT)
public class EventClick {

	@SubscribeEvent
	public static void rightClient(PlayerInteractEvent.RightClickBlock event) {
		ItemStack stack = event.getItemStack();
		
		if (stack.getItem().asItem().toString().equals(Items.FLINT.toString())) {

			ItemStack stack1 = new ItemStack(AllRegistry.ItemRegistry.flint_flakes.get().asItem());
			Player entityPlayer = event.getPlayer();
			entityPlayer.setItemInHand(InteractionHand.MAIN_HAND, stack1);

		}

	}
}
