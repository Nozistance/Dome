package io.nozistance.rome.config;

import lombok.Getter;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
public class Source {

    @Getter private String path;
    @Getter private boolean enabled;

}
