package net.impulse.spider_webber.entity;

import net.impulse.spider_webber.SpiderWebber;
import net.impulse.spider_webber.blocks.BlockRegistry;
import net.impulse.spider_webber.config.SpiderWebberConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SpiderWebber.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlaceWebs {

    private static final String WEB_COOLDOWN = "WebCooldownTicks";
    private static final int COOLDOWN_TICKS = (int)(SpiderWebberConfig.webCooldownInSeconds * 20);

    @SubscribeEvent
    public static void onSpiderTargets(LivingChangeTargetEvent event) {
        Entity entity = event.getEntity();
        if (!(entity instanceof Spider spider)) return;

        Level level = spider.level();
        if (level.isClientSide) return;

        BlockPos pos = spider.blockPosition();

        int cooldown = spider.getPersistentData().getInt(WEB_COOLDOWN);
        if (cooldown > 0) return;

        if (!level.getBlockState(pos).isAir()) return;

        level.setBlock(pos, BlockRegistry.FRAGILE_WEB.get().defaultBlockState(), 3);
        spider.getPersistentData().putInt(WEB_COOLDOWN, COOLDOWN_TICKS);
    }

    @SubscribeEvent
    public static void onSpiderTick(LivingTickEvent event) {
        if (!(event.getEntity() instanceof Spider spider)) return;
        if (spider.level().isClientSide) return;

        int cooldown = spider.getPersistentData().getInt(WEB_COOLDOWN);
        if (cooldown > 0) {
            spider.getPersistentData().putInt(WEB_COOLDOWN, cooldown - 1);
        }
    }
}
