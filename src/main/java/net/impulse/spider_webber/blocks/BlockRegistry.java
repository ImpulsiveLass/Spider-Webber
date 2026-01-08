package net.impulse.spider_webber.blocks;

import net.impulse.spider_webber.SpiderWebber;
import net.impulse.spider_webber.blocks.custom.FragileWeb;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlockRegistry {

    public static final ResourceKey<?> FRAGILE_WEB_BLOCK_ID = ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(SpiderWebber.MOD_ID, "fragile_web"));

    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(SpiderWebber.MOD_ID);

    public static final DeferredBlock<FragileWeb> FRAGILE_WEB =
            BLOCKS.registerBlock("fragile_web",
                    FragileWeb::new,
                    () -> BlockBehaviour
                            .Properties.of()
                            .mapColor(MapColor.WOOL)
                            .sound(SoundType.COBWEB)
                            .forceSolidOn()
                            .noCollision()
                            .requiresCorrectToolForDrops()
                            .strength(4.0F)
                            .pushReaction(PushReaction.DESTROY)
                            .setId(ResourceKey.create(Registries.BLOCK, FRAGILE_WEB_BLOCK_ID.identifier()))
            );
}
