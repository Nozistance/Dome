package io.nozistance.dome.mixin.pack;

import io.nozistance.dome.data.pack.PackProvider;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resource.FileResourcePackProvider;
import net.minecraft.resource.ResourcePackProfile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

@Environment(EnvType.CLIENT)
@Mixin(FileResourcePackProvider.class)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileResourcePackProviderMixin {

    @Redirect(method = "register", at = @At(value = "INVOKE", target = "Lnet/minecraft/resource/FileResourcePackProvider;forEachProfile(Ljava/nio/file/Path;ZLjava/util/function/BiConsumer;)V"))
    private void register(Path packsDir, boolean alwaysStable, BiConsumer<Path, ResourcePackProfile.PackFactory> consumer) throws IOException {
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(packsDir)) {
            List<Path> paths = new ArrayList<>(PackProvider.getModEntries());
            for (Path path : directoryStream) paths.add(path);
            for (Path path : paths) {
                ResourcePackProfile.PackFactory packFactory = FileResourcePackProvider.getFactory(path, alwaysStable);
                path = Path.of(path.getFileName().toString() + (PackProvider.isFromMod(path) ? " (Rome)" : " (Local)"));
                if (packFactory != null) consumer.accept(path, packFactory);
            }
        }
    }

}
