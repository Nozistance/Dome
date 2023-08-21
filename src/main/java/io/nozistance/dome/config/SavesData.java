package io.nozistance.dome.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

import java.util.ArrayList;
import java.util.List;

@Config(name = "saves")
public class SavesData implements ConfigData {

    public final List<Entry> saves = new ArrayList<>();

}
