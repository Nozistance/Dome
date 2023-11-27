package io.nozistance.dome.mixin.save;

import io.nozistance.dome.config.ModData;
import io.nozistance.dome.data.ModDataProvider;
import io.nozistance.dome.data.SavesProvider;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.level.storage.LevelStorage;
import net.minecraft.world.level.storage.LevelStorage.LevelList;
import net.minecraft.world.level.storage.LevelStorage.LevelSave;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.stream.Stream;

@Environment(EnvType.CLIENT)
@Mixin(LevelStorage.class)
public abstract class LevelStorageMixin {

    @Unique
    private final ModDataProvider provider = new SavesProvider(ModData.getSaveEntries());

    @Inject(method = "getLevelList", at = @At("RETURN"), cancellable = true)
    private void getLevelList(@NotNull CallbackInfoReturnable<LevelList> cir) {
        List<LevelSave> modLevelSaves = provider.get().stream().map(LevelSave::new).toList();
        List<LevelSave> localLevelSaves = cir.getReturnValue().levels();
        List<LevelSave> all = Stream.of(modLevelSaves, localLevelSaves)
                .flatMap(List::stream).toList();
        cir.setReturnValue(new LevelList(all));
    }
}
