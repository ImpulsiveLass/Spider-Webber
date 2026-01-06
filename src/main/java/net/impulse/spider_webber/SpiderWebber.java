package net.impulse.spider_webber;

import eu.midnightdust.lib.config.MidnightConfig;
import net.impulse.spider_webber.blocks.BlockRegistry;
import net.impulse.spider_webber.config.SpiderWebberConfig;
import net.impulse.spider_webber.creativetab.TabRegistry;
import net.impulse.spider_webber.items.ItemRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(SpiderWebber.MOD_ID)
public class SpiderWebber {
    public static final String MOD_ID = "spider_webber";

    public SpiderWebber() {
        MinecraftForge.EVENT_BUS.register(this);

        MidnightConfig.init(MOD_ID, SpiderWebberConfig.class);

        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        BlockRegistry.BLOCKS.register(modBus);
        ItemRegistry.ITEMS.register(modBus);
        TabRegistry.CREATIVE_TAB.register(modBus);
    }
}
