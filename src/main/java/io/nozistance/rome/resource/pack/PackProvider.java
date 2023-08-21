package io.nozistance.rome.resource.pack;

import io.nozistance.rome.config.ModData;
import io.nozistance.rome.config.Entry;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Environment(EnvType.CLIENT)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PackProvider {

    public static List<Path> getRomeSources() {
        return ModData.getPackEntries().stream().filter(Entry::isEnabled)
                .map(Entry::getPath).map(Path::of).map(Path::toAbsolutePath)
                .filter(Files::exists).toList();
    }

    public static boolean isFromRome(@NotNull Path path) {
        List<Path> saves = PackProvider.getRomeSources();
        return saves.contains(path.toAbsolutePath());
    }

}
