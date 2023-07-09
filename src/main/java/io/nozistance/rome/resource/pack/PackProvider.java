package io.nozistance.rome.resource.pack;

import io.nozistance.rome.config.Source;
import io.nozistance.rome.config.Sources;
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
        return Sources.getPacks().stream().filter(Source::isEnabled)
                .map(Source::getPath).map(Path::of).map(Path::toAbsolutePath)
                .filter(Files::exists).toList();
    }

    public static boolean isFromRome(@NotNull Path path) {
        List<Path> saves = PackProvider.getRomeSources();
        return saves.contains(path.toAbsolutePath());
    }

}
