package io.github.overrun.baka4n.inordertosurvive.tool;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraftforge.common.ToolAction;

import java.util.Set;

import static java.util.Set.of;

public class Knife extends DiggerItem {
	public static final Item.Properties properties = new Properties();
	public static final ToolAction knife = ToolAction.get("pickaxe_dig");
	public static final Set<ToolAction> default_knife = of(knife);
	public Knife(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Item.Properties pProperties) {
		super((float)pAttackDamageModifier, pAttackSpeedModifier, pTier, BlockTags.MINEABLE_WITH_PICKAXE, pProperties);
	}

	@Override
	public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
		return default_knife.contains(toolAction);
	}
}
