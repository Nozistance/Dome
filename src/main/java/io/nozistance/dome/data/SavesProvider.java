package io.nozistance.dome.data;

import io.nozistance.dome.config.Entry;
import lombok.extern.slf4j.Slf4j;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.level.storage.LevelStorage.LevelSave;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.Files.isRegularFile;

@Slf4j
@Environment(EnvType.CLIENT)
public class SavesProvider extends ModDataProvider {

    public SavesProvider(List<Entry> entries) {
        super(entries);
    }

    @Override
    protected boolean validate(Path entry) {
        LevelSave save = new LevelSave(entry);
        return Files.exists(entry)
                && (isRegularFile(save.getLevelDatPath())
                || isRegularFile(save.getLevelDatOldPath()));
    }
}
