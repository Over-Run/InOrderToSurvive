package io.github.overrun.baka4n.inordertosurvive.block;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class BasicBlock extends Block {
	public static final BlockBehaviour.Properties pProperties = BlockBehaviour.Properties.of(Material.METAL);
	public static final Item.Properties properties = new Item.Properties();

	public BasicBlock(Properties pProperties) {
		super(pProperties);
	}


}
