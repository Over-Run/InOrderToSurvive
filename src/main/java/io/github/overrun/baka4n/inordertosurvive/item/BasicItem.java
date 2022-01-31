package io.github.overrun.baka4n.inordertosurvive.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

//basic item reigstry
public class BasicItem extends Item {
	public static final Properties pProperties = new Item.Properties().tab(CreativeModeTab.TAB_MISC);

	//basic for item
	public BasicItem() {
		super(pProperties);
	}
	//basic for food item
	public BasicItem(FoodProperties foodProperties) {
		super(pProperties.food(foodProperties));
	}
	//flint flakes
	public BasicItem(CreativeModeTab tab) {
		super(pProperties.tab(tab));
	}
}
