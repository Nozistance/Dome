package io.nozistance.dome.config;

import lombok.Getter;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Getter
@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
public class Entry {

    private String path;
    private boolean enabled;

}
