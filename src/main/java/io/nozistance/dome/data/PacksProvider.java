package io.nozistance.dome.data;

import io.nozistance.dome.config.Entry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Environment(EnvType.CLIENT)
public class PacksProvider extends ModDataProvider {

    public PacksProvider(List<Entry> entries) {
        super(entries);
    }

    @Override
    protected boolean validate(Path entry) {
        return Files.exists(entry);
    }
}
