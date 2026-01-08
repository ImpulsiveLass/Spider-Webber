package net.impulse.spider_webber.entity;

import net.impulse.spider_webber.SpiderWebber;
import net.impulse.spider_webber.blocks.BlockRegistry;
import net.impulse.spider_webber.config.SpiderWebberConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.spider.Spider;
import net.minecraft.world.level.Level;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingChangeTargetEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.bus.api.SubscribeEvent;

@EventBusSubscriber(modid = SpiderWebber.MOD_ID)
public class PlaceWebs {

    private static final String WEB_COOLDOWN = "WebCooldownTicks";

    private static int getInitialCooldownTicks() {
        return (int)(SpiderWebberConfig.webCooldownInSeconds * 20);
    }

    @SubscribeEvent
    public static void onSpiderTargets(LivingChangeTargetEvent event) {
        Entity entity = event.getEntity();
        if (!(entity instanceof Spider spider)) return;

        Level level = spider.level();
        if (level.isClientSide()) return;

        BlockPos pos = spider.blockPosition();

        int cooldown = spider.getPersistentData().getInt(WEB_COOLDOWN).orElse(0);
        if (cooldown > 0) return;

        if (!level.getBlockState(pos).isAir()) return;

        level.setBlock(pos, BlockRegistry.FRAGILE_WEB.get().defaultBlockState(), 3);
        spider.getPersistentData().putInt(WEB_COOLDOWN, getInitialCooldownTicks());
    }

    @SubscribeEvent
    public static void onSpiderTick(EntityTickEvent.Post event) {
        if (!(event.getEntity() instanceof Spider spider)) return;
        if (spider.level().isClientSide()) return;

        int cooldown = spider.getPersistentData().getInt(WEB_COOLDOWN).orElse(0);
        if (cooldown > 0) {
            spider.getPersistentData().putInt(WEB_COOLDOWN, cooldown - 1);
        }
    }
}
