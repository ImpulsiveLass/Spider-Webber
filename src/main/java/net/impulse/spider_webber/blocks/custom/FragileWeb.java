package net.impulse.spider_webber.blocks.custom;

import net.impulse.spider_webber.config.SpiderWebberConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WebBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.Vec3;

public class FragileWeb extends WebBlock {
    public static final IntegerProperty DECAY = IntegerProperty.create("decay", 0, 2000);

    private static int getInitialDecayTicks() {
        return Math.min(
                (int)(SpiderWebberConfig.webDecayInSeconds * 20),
                2000
        );
    }

    public FragileWeb(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(DECAY, getInitialDecayTicks()));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(DECAY);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(DECAY, getInitialDecayTicks());
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!level.isClientSide) {
            level.scheduleTick(pos, this, 1);
        }
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        level.scheduleTick(pos, this, 1);
        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int decay = state.getValue(DECAY);

        if (decay <= 0) {
            level.destroyBlock(pos, false);
            return;
        }

        level.setBlock(pos, state.setValue(DECAY, decay - 1), 3);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof Spider) return;
        entity.makeStuckInBlock(state, new Vec3(0.25F, 0.05F, 0.25F));
    }
}