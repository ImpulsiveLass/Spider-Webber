package net.impulse.spider_webber.creativetab;

import net.impulse.spider_webber.SpiderWebber;
import net.impulse.spider_webber.items.ItemRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;

@Mod.EventBusSubscriber(modid = SpiderWebber.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TabRegistry {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TAB;

    @SubscribeEvent
    public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
        if (tabData.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            tabData.accept(ItemRegistry.FRAGILE_WEB.get());
        }
    }

    static {
        CREATIVE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB,
                SpiderWebber.MOD_ID);
    }
}
