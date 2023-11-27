package io.nozistance.dome.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "screenshots")
public class ScreenshotsData implements ConfigData {

    @ConfigEntry.Gui.TransitiveObject
    public final Entry entry = new Entry();
}
