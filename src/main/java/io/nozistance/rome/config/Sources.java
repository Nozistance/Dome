package io.nozistance.rome.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

import java.util.ArrayList;
import java.util.List;

@Config(name = "rome")
@Config.Gui.Background("minecraft:textures/block/sandstone_bottom.png")
public class Sources implements ConfigData {

    @ConfigEntry.Gui.Excluded
    public static final Sources CONFIG = AutoConfig
            .register(Sources.class, GsonConfigSerializer::new)
            .getConfig();

    public final List<Source> packs = new ArrayList<>();
    public final List<Source> worlds = new ArrayList<>();

    public static List<Source> getPacks() {
        return CONFIG.packs;
    }

    public static List<Source> getWorldSaves() {
        return CONFIG.worlds;
    }

}
