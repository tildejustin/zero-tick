package dev.tildejustin.mixin;

import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.*;

@Mixin(CropBlock.class)
public abstract class CropBlockMixin extends PlantBlock {
    protected CropBlockMixin(Settings settings) {
        super(settings);
    }

    @Shadow
    public abstract void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random);

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        this.randomTick(state, world, pos, random);
    }
}
