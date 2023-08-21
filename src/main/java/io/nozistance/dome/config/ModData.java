package io.nozistance.dome.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ModData {

    private static final ModConfig DATA = AutoConfig
            .register(ModConfig.class, PartitioningSerializer.wrap(GsonConfigSerializer::new))
            .getConfig();

    public static List<Entry> getPackEntries() {
        return DATA.packs.packs;
    }

    public static List<Entry> getSaveEntries() {
        return DATA.saves.saves;
    }

    public static Entry getScreenshotsEntry() {
        return DATA.screenshots.screenshots;
    }

}
