package dev.tildejustin.mixin;

import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import org.spongepowered.asm.mixin.*;

@Mixin(AbstractPlantStemBlock.class)
public abstract class AbstractPlantStemBlockMixin extends AbstractPlantPartBlock {
    protected AbstractPlantStemBlockMixin(Settings settings, Direction growthDirection, VoxelShape outlineShape, boolean tickWater) {
        super(settings, growthDirection, outlineShape, tickWater);
    }

    @Shadow
    public abstract void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random);

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!state.canPlaceAt(world, pos)) {
            world.breakBlock(pos, true);
        } else {
            this.randomTick(state, world, pos, random);
        }
    }
}
