package net.impulse.spider_webber.items;

import net.impulse.spider_webber.SpiderWebber;
import net.impulse.spider_webber.blocks.BlockRegistry;
import net.impulse.spider_webber.config.SpiderWebberConfig;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@EventBusSubscriber(modid=SpiderWebber.MOD_ID)
public class ItemRegistry {

    public static final ResourceKey<?> FRAGILE_WEB_ITEM_ID = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(SpiderWebber.MOD_ID, "fragile_web"));

    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(SpiderWebber.MOD_ID);

    public static final DeferredItem<BlockItem> FRAGILE_WEB =
            ITEMS.register("fragile_web",
                    () -> new BlockItem(BlockRegistry.FRAGILE_WEB.get(),
                            new Item.Properties().
                                    setId(ResourceKey.create(Registries.ITEM, FRAGILE_WEB_ITEM_ID.identifier())))
            );

    private static boolean getFragileWebInCreativeTab() {
        return SpiderWebberConfig.fragileWebInCreativeTab;
    }

    @SubscribeEvent
    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (!getFragileWebInCreativeTab()) return;
        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.accept(ItemRegistry.FRAGILE_WEB.get());
        }
    }
}
