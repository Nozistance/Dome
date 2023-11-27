package io.nozistance.dome.data;

import io.nozistance.dome.config.Entry;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
public abstract class ModDataProvider {

    private final List<Entry> entries;

    public boolean isFromMod(@NotNull Path entry) {
        Path entryPath = entry.toAbsolutePath();
        return this.get().contains(entryPath);
    }

    public List<Path> get() {
        return entries.stream()
                .filter(Entry::isEnabled).map(Entry::getPath)
                .map(Path::of).map(Path::toAbsolutePath)
                .filter(this::validate).toList();
    }

    protected abstract boolean validate(Path entry);
}
