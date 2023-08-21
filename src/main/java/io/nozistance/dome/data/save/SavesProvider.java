package io.nozistance.dome.data.save;

import io.nozistance.dome.config.ModData;
import io.nozistance.dome.config.Entry;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.world.level.storage.LevelStorage.LevelSave;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Slf4j
@Environment(EnvType.CLIENT)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SavesProvider {

    private static final Path SAVES_DIRECTORY = MinecraftClient
            .getInstance().getLevelStorage().getSavesDirectory();

    private static final Predicate<LevelSave> isValidLevelSave =
            l -> Files.isRegularFile(l.getLevelDatPath()) ||
                    Files.isRegularFile(l.getLevelDatOldPath());

    public static List<Path> getLocalEntries() {
        try (Stream<Path> stream = Files.list(SAVES_DIRECTORY)) {
            return stream.toList();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new UncheckedIOException(e);
        }
    }

    public static List<Path> getModEntries() {
        return ModData.getSaveEntries().stream().filter(Entry::isEnabled)
                .map(Entry::getPath).map(Path::of).map(Path::toAbsolutePath)
                .filter(Files::exists).toList();
    }

    public static List<LevelSave> getLevelSaves() {
        List<Path> saves = new ArrayList<>();
        saves.addAll(SavesProvider.getLocalEntries());
        saves.addAll(SavesProvider.getModEntries());
        return saves.stream().map(LevelSave::new)
                .filter(SavesProvider.isValidLevelSave)
                .toList();
    }

    public static boolean isFromMod(String name) {
        List<Path> saves = SavesProvider.getModEntries();
        return saves.contains(Path.of(name).toAbsolutePath());
    }

}
