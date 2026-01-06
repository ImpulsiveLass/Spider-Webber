package net.impulse.spider_webber.config;

import eu.midnightdust.lib.config.MidnightConfig;

public class SpiderWebberConfig extends MidnightConfig {
    @Entry(min=0.1f, max=100.0f, isSlider=true)
    public static float webCooldownInSeconds = 1.75f;
    @Entry(min=0.1f, max=100.0f, isSlider=true)
    public static float webDecayInSeconds = 5.25f;
}
