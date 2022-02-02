package io.github.overrun.baka4n.inordertosurvive.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import static io.github.overrun.baka4n.inordertosurvive.Final.properties;

//basic item registry
public class BasicItem extends Item {
	//basic for item
	//	public BasicItem() {
	//		super(pProperties);
	//	}
	//basic for food item
//	public BasicItem(FoodProperties foodProperties, CreativeModeTab tab) {
//		super(Final.properties.tab(tab).food(foodProperties));
//	}
	//flint flakes
	public BasicItem(CreativeModeTab tab) {
		super(properties.tab(tab));
	}
}
