package net.impulse.spider_webber;

import eu.midnightdust.lib.config.MidnightConfig;
import net.impulse.spider_webber.blocks.BlockRegistry;
import net.impulse.spider_webber.config.SpiderWebberConfig;
import net.impulse.spider_webber.items.ItemRegistry;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(SpiderWebber.MOD_ID)
public class SpiderWebber {
    public static final String MOD_ID = "spider_webber";

    public SpiderWebber(ModContainer container, IEventBus modBus) {
        MidnightConfig.init(MOD_ID, SpiderWebberConfig.class);

        BlockRegistry.BLOCKS.register(modBus);
        ItemRegistry.ITEMS.register(modBus);
    }
}
