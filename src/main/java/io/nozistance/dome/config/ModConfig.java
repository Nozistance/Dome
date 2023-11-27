package io.nozistance.dome.config;

import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;

@Config(name = "dome")
public class ModConfig extends PartitioningSerializer.GlobalData {

    @ConfigEntry.Category("packs")
    @ConfigEntry.Gui.TransitiveObject
    PacksData packs = new PacksData();

    @ConfigEntry.Category("saves")
    @ConfigEntry.Gui.TransitiveObject
    SavesData saves = new SavesData();

    @ConfigEntry.Category("screenshots")
    @ConfigEntry.Gui.TransitiveObject
    ScreenshotsData screenshots = new ScreenshotsData();
}
