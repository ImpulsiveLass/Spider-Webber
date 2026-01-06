package net.impulse.spider_webber.blocks;

import net.impulse.spider_webber.blocks.custom.FragileWeb;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockRegistry {
    public static final DeferredRegister BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, "spider_webber");

    public static final RegistryObject<Block> FRAGILE_WEB =
            BLOCKS.register("fragile_web",
                    () -> new FragileWeb(BlockBehaviour
                            .Properties.of()
                            .mapColor(MapColor.WOOL)
                            .forceSolidOn()
                            .noCollission()
                            .requiresCorrectToolForDrops()
                            .strength(4.0F)
                            .pushReaction(PushReaction.DESTROY)));
}
