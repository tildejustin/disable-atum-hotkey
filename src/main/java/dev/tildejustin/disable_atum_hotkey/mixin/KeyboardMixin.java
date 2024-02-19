package dev.tildejustin.disable_atum_hotkey.mixin;

import com.bawnorton.mixinsquared.TargetHandler;
import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public abstract class KeyboardMixin {
    @SuppressWarnings({"FieldCanBeLocal", "FieldMayBeFinal"})
    @Unique
    private boolean shouldCancel = true;

    @SuppressWarnings({"MixinAnnotationTarget", "InvalidMemberReference"})
    @TargetHandler(mixin = "me.voidxwalker.autoreset.mixin.hotkey.KeyboardMixin", name = "atum_onKey")
    @Inject(method = "@MixinSquared:Handler", at = @At("HEAD"), cancellable = true)
    private void disableHotkey(CallbackInfo ci) {
        if (shouldCancel) {
            ci.cancel();
        }
    }
}
