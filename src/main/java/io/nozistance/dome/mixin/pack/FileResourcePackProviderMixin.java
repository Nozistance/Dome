package io.nozistance.dome.mixin.pack;

import io.nozistance.dome.config.ModData;
import io.nozistance.dome.data.ModDataProvider;
import io.nozistance.dome.data.PacksProvider;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resource.FileResourcePackProvider;
import net.minecraft.resource.ResourcePackProfile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

@Environment(EnvType.CLIENT)
@Mixin(FileResourcePackProvider.class)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class FileResourcePackProviderMixin {

    @Unique
    private final ModDataProvider provider = new PacksProvider(ModData.getPackEntries());

    @Redirect(method = "register", at = @At(value = "INVOKE", target = "Lnet/minecraft/resource/FileResourcePackProvider;forEachProfile(Ljava/nio/file/Path;ZLjava/util/function/BiConsumer;)V"))
    private void register(Path packsDir, boolean alwaysStable, BiConsumer<Path, ResourcePackProfile.PackFactory> consumer) throws IOException {
        try (Stream<Path> directoryStream = Files.walk(packsDir)) {
            Stream.concat(directoryStream, provider.get().stream()).forEach(path -> {
                ResourcePackProfile.PackFactory packFactory = FileResourcePackProvider.getFactory(path, alwaysStable);
                String name = "%s (%s)".formatted(path.getFileName(), provider.isFromMod(path) ? "Dome" : "Local");
                if (packFactory != null) consumer.accept(Path.of(name), packFactory);
            });
        }
    }
}
