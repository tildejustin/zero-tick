package dev.tildejustin.mixin;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;

@Mixin(FarmlandBlock.class)
public abstract class FarmlandBlockMixin {
    @Shadow
    public static void setToDirt(@Nullable Entity entity, BlockState state, World world, BlockPos pos) {
    }

    @Shadow
    public abstract void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random);

    @Overwrite
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!state.canPlaceAt(world, pos)) {
            setToDirt(null, state, world, pos);
        } else {
            this.randomTick(state, world, pos, random);
        }
    }
}
