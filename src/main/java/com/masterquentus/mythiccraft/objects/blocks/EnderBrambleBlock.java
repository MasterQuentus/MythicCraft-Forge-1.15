package com.masterquentus.mythiccraft.objects.blocks;


import java.util.Random;

import com.masterquentus.mythiccraft.init.BlockInit;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SugarCaneBlock;
import net.minecraft.entity.Entity;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
public class EnderBrambleBlock extends SugarCaneBlock implements net.minecraftforge.common.IPlantable{
	public static final IntegerProperty AGE = BlockStateProperties.AGE_15;
	   public static final VoxelShape COLLISION_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D);
	   public static final VoxelShape OUTLINE_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

	public EnderBrambleBlock(Properties p_i48440_1_) {
		super(p_i48440_1_);
	}
	

	   public void entityInside(BlockState p_196262_1_, World p_196262_2_, BlockPos p_196262_3_, Entity p_196262_4_) {
	      p_196262_4_.teleportTo(jumpFactor, friction, explosionResistance);
	   }
	   
	   @SuppressWarnings("deprecation")
	@Override
	   public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
	       BlockState above = world.getBlockState(pos.above());
	       if (above.isAir()){
	    	   world.setBlockAndUpdate(pos.above(), BlockInit.ENDER_BRAMBLE.get().defaultBlockState());
	       }
	   }
}