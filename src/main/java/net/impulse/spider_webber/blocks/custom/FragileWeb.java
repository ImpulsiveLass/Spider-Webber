package net.impulse.spider_webber.blocks.custom;

import net.impulse.spider_webber.config.SpiderWebberConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.spider.Spider;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WebBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.Vec3;

public class FragileWeb extends WebBlock {

    public static final IntegerProperty DECAY =
            IntegerProperty.create("decay", 0, 2000);

    public FragileWeb(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(
                this.defaultBlockState().setValue(DECAY, getInitialDecayTicks())
        );
    }

    private static int getInitialDecayTicks() {
        return Math.min(
                (int)(SpiderWebberConfig.webDecayInSeconds * 20),
                2000
        );
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
        if (!level.isClientSide()) {
            level.scheduleTick(pos, this, 1);
        }
    }

    @Override
    public BlockState updateShape(
            BlockState state,
            LevelReader world,
            ScheduledTickAccess scheduledTicks,
            BlockPos pos,
            Direction direction,
            BlockPos neighborPos,
            BlockState neighborState,
            RandomSource random
    ) {
        if (world instanceof Level level && !level.isClientSide()) {
            level.scheduleTick(pos, this, 1);
        }
        return super.updateShape(state, world, scheduledTicks, pos, direction, neighborPos, neighborState, random);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int decay = state.getValue(DECAY);

        if (decay <= 0) {
            level.destroyBlock(pos, false);
        } else {
            level.setBlock(pos, state.setValue(DECAY, decay - 1), Block.UPDATE_CLIENTS);
        }
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier insideBlockEffectApplier, boolean bool) {
        if (!(entity instanceof Spider)) {
            Vec3 vec3 = new Vec3(0.25, 0.05F, 0.25);
            if (entity instanceof LivingEntity livingentity && livingentity.hasEffect(MobEffects.WEAVING)) {
                vec3 = new Vec3(0.5, 0.25, 0.5);
            }

            entity.makeStuckInBlock(state, vec3);
        }
    }
}
