package io.nozistance.rome.mixin.screenshot;

import io.nozistance.rome.config.ModData;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.util.ScreenshotRecorder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.io.File;

@Environment(EnvType.CLIENT)
@Mixin(ScreenshotRecorder.class)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScreenshotRecorderMixin {

    @ModifyVariable(method = "saveScreenshotInner", at = @At("STORE"), ordinal = 1)
    private static File injected(File file) {
        return ModData.getScreenshotsEntry().isEnabled() ?
                new File(ModData.getScreenshotsEntry().getPath()) :
                file;
    }

}
