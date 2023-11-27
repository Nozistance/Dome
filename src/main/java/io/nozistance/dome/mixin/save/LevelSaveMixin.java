package io.nozistance.dome.mixin.save;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.level.storage.LevelStorage;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.nio.file.Path;

@Environment(EnvType.CLIENT)
@Mixin(LevelStorage.LevelSave.class)
public abstract class LevelSaveMixin {

    @Shadow
    private @Final Path path;

    @Inject(method = "getRootPath", at = @At("TAIL"), cancellable = true)
    public void getRootPath(@NotNull CallbackInfoReturnable<String> cir) {
        cir.setReturnValue(path.toAbsolutePath().toString());
    }
}
