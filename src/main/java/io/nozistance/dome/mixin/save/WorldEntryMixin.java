package io.nozistance.dome.mixin.save;

import io.nozistance.dome.config.ModData;
import io.nozistance.dome.data.ModDataProvider;
import io.nozistance.dome.data.SavesProvider;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.world.WorldListWidget;
import net.minecraft.world.level.storage.LevelSummary;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.nio.file.Path;

@Environment(EnvType.CLIENT)
@Mixin(WorldListWidget.WorldEntry.class)
public abstract class WorldEntryMixin {

    @Unique
    private final ModDataProvider provider = new SavesProvider(ModData.getSaveEntries());
    @Shadow
    private @Final LevelSummary level;

    @ModifyVariable(method = "render", at = @At("STORE"), ordinal = 0)
    public String getDisplayName(String name) {
        return "%s (%s)".formatted(level.getDisplayName(),
                provider.isFromMod(Path.of(level.getName()))
                        ? "Dome"
                        : "Local");
    }

    @ModifyVariable(method = "render", at = @At("STORE"), ordinal = 1)
    public String getName(@NotNull String sign) {
        String name = sign.replaceAll(" \\(.*\\)", "");
        String time = StringUtils.difference(name, sign);
        return Path.of(name).getFileName() + time;
    }
}
