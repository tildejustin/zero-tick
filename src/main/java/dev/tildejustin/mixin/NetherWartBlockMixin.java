package dev.tildejustin.mixin;

import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.*;

@Mixin(NetherWartBlock.class)
public abstract class NetherWartBlockMixin extends PlantBlock {
    protected NetherWartBlockMixin(Settings settings) {
        super(settings);
    }

    @Shadow
    protected abstract void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random);

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        this.randomTick(state, world, pos, random);
    }
}
