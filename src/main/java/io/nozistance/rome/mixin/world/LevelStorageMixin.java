package io.nozistance.rome.mixin.world;

import io.nozistance.rome.resource.world.WorldSavesProvider;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.level.storage.LevelStorage;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Environment(EnvType.CLIENT)
@Mixin(LevelStorage.class)
public class LevelStorageMixin {

    @Inject(method = "getLevelList", at = @At("RETURN"), cancellable = true)
    private void getLevelList(@NotNull CallbackInfoReturnable<LevelStorage.LevelList> cir) {
        List<LevelStorage.LevelSave> saves = WorldSavesProvider.getLevelSaves();
        cir.setReturnValue(new LevelStorage.LevelList(saves));
    }

}
