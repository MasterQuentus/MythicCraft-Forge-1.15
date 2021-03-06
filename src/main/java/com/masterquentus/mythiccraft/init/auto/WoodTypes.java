package com.masterquentus.mythiccraft.init.auto;

import com.masterquentus.mythiccraft.objects.blocks.*;
import com.masterquentus.mythiccraft.world.feature.ModTree;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

public enum WoodTypes {
	BLOODOAK, WHITEOAK, SILVERWOOD, WITCHWOOD, ALDER, HAWTHORN, ROWAN, WILLOW, BEECH, ASH, BLACKTHORN, CEDAR, ELDER,
	JUNIPER, WITCHHAZEL, YEW, INFESTED, CHARRED, ICY, TWISTED, DISTORTED;

	public Supplier<Block> log;
	public Supplier<Block> wood;
	public Supplier<Block> strippedLog;
	public Supplier<Block> strippedWood;
	public Supplier<Block> plank;
	public Supplier<Block> leaves;
	public Supplier<Block> door;
	public Supplier<Block> crate;
	public Supplier<Block> trapdoor;
	public Supplier<Block> stairs;
	public Supplier<Block> slab;
	public Supplier<Block> button;
	public Supplier<Block> pressureplate;
	public Supplier<Block> fence;
	public Supplier<Block> gate;
	public Supplier<Block> sapling;
	public ModTree tree;

	public void register(DeferredRegister<Block> blockRegistry) {
		String name = this.name().toLowerCase();
		this.tree = new ModTree(this);
		this.log = blockRegistry.register(name + "_log", () -> new ModLogBlock(MaterialColor.COLOR_RED, false));
		this.strippedLog = blockRegistry.register(name + "_stripped_log",
				() -> new ModLogBlock(MaterialColor.COLOR_RED, true));
		this.wood = blockRegistry.register(name + "_wood", () -> new ModLogBlock(MaterialColor.COLOR_RED, false));
		this.strippedWood = blockRegistry.register(name + "_stripped_wood",
				() -> new ModLogBlock(MaterialColor.COLOR_RED, true));
		this.plank = blockRegistry.register(name + "_planks",
				() -> new Block(Block.Properties.copy(Blocks.OAK_PLANKS)));
		this.leaves = blockRegistry.register(name + "_leaves",
				() -> new LeavesBlock(Block.Properties.copy(Blocks.OAK_LEAVES)));
		this.door = blockRegistry.register(name + "_door", () -> new ModDoor(Block.Properties.copy(Blocks.OAK_DOOR)));
		this.crate = blockRegistry.register(name + "_crate",
				() -> new CrateBlock(Block.Properties.copy(Blocks.BARREL)));
		this.trapdoor = blockRegistry.register(name + "_trapdoor",
				() -> new ModTrapDoor(Block.Properties.copy(Blocks.OAK_TRAPDOOR)));
		this.stairs = blockRegistry.register(name + "_stairs",
				() -> new StairsBlock(() -> this.plank.get().defaultBlockState(),
						Block.Properties.of(Material.WOOD, MaterialColor.COLOR_RED)));
		this.slab = blockRegistry.register(name + "_slab",
				() -> new SlabBlock(Block.Properties.copy(this.plank.get())));
		this.button = blockRegistry.register(name + "_button",
				() -> new ModWoodButtonBlock(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_RED)));
		this.pressureplate = blockRegistry.register(name + "_pressureplate",
				() -> new ModWoodPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
						Block.Properties.of(Material.WOOD, MaterialColor.COLOR_RED)));
		this.fence = blockRegistry.register(name + "_fence",
				() -> new FenceBlock(Block.Properties.of(Material.WOOD, MaterialColor.COLOR_RED)));
		this.gate = blockRegistry.register(name + "_fence_gate",
				() -> new FenceGateBlock(Block.Properties.copy(Blocks.OAK_FENCE_GATE)));
		this.sapling = blockRegistry.register(name + "_sapling",
				() -> new SaplingBlock(this.tree, Block.Properties.copy(Blocks.OAK_SAPLING)));
	}
}
