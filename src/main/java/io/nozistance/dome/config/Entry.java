package io.nozistance.dome.config;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Getter
@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Entry {

    private String path;
    private boolean enabled;
}
