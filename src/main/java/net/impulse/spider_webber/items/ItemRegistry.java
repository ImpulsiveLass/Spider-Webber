package net.impulse.spider_webber.items;

import net.impulse.spider_webber.blocks.BlockRegistry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {
    public static final DeferredRegister ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, "spider_webber");

    public static final RegistryObject<Item> FRAGILE_WEB =
            block(BlockRegistry.FRAGILE_WEB);

    private static RegistryObject<Item> block(RegistryObject<Block> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
