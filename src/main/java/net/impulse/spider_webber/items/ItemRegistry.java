package net.impulse.spider_webber.items;

import net.impulse.spider_webber.SpiderWebber;
import net.impulse.spider_webber.blocks.BlockRegistry;
import net.impulse.spider_webber.config.SpiderWebberConfig;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = SpiderWebber.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemRegistry {
    public static final DeferredRegister ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, "spider_webber");

    public static final RegistryObject<Item> FRAGILE_WEB =
            block(BlockRegistry.FRAGILE_WEB);

    private static RegistryObject<Item> block(RegistryObject<Block> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static boolean getFragileWebInCreativeTab() {
        return SpiderWebberConfig.fragileWebInCreativeTab;
    }

    @SubscribeEvent
    public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
        if (!getFragileWebInCreativeTab()) return;
        if (tabData.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            tabData.accept(ItemRegistry.FRAGILE_WEB.get());
        }
    }
}
