package io.nozistance.rome.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

import java.util.ArrayList;
import java.util.List;

@Config(name = "packs")
public class PacksData implements ConfigData {

    public final List<Entry> packs = new ArrayList<>();

}
