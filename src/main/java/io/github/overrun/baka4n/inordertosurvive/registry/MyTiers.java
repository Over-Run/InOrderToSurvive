package io.github.overrun.baka4n.inordertosurvive.registry;

import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static io.github.overrun.baka4n.inordertosurvive.registry.AllRegistry.ItemRegistry.KnifeRegistry.*;
import static net.minecraft.world.item.crafting.Ingredient.fromValues;

public enum MyTiers implements Tier {
	Flint(0, 5, 2.0F, 0.0F, 15, () -> {
		return of(ItemTags.PLANKS);
	});

	public static Ingredient of(Tag<Item> pTag) {
		return fromValues(Stream.of(new Ingredient.TagValue(pTag)));
	}

	private final int level;
	private final int uses;
	private final float speed;
	private final float damage;
	private final int enchantmentValue;
	private final LazyLoadedValue<Ingredient> repairIngredient;

	MyTiers(int level, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> ingredientSupplier) {
		this.level = level;
		this.uses = uses;
		this.speed = speed;
		this.damage = damage;
		this.enchantmentValue = enchantmentValue;
		this.repairIngredient = new LazyLoadedValue<>(ingredientSupplier);
	}

	public int getUses() {
		return this.uses;
	}

	public float getSpeed() {
		return this.speed;
	}

	public float getAttackDamageBonus() {
		return this.damage;
	}

	public int getLevel() {
		return this.level;
	}

	public int getEnchantmentValue() {
		return this.enchantmentValue;
	}

	public Ingredient getRepairIngredient() {
		return this.repairIngredient.get();
	}

	@javax.annotation.Nullable public net.minecraft.tags.Tag<net.minecraft.world.level.block.Block> getTag() { return getTagFromVanillaTier(this);}
}
