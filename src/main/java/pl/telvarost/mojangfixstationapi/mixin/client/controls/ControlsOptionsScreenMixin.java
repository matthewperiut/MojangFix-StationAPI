/*
 * Copyright (C) 2022-2024 js6pak
 *
 * This file is part of MojangFixStationAPI.
 *
 * MojangFixStationAPI is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation, version 3.
 *
 * MojangFixStationAPI is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with MojangFixStationAPI. If not, see <https://www.gnu.org/licenses/>.
 */

package pl.telvarost.mojangfixstationapi.mixin.client.controls;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.KeybindsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.OptionButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pl.telvarost.mojangfixstationapi.client.gui.CallbackButtonWidget;
import pl.telvarost.mojangfixstationapi.client.gui.ControlsListWidget;
import pl.telvarost.mojangfixstationapi.mixinterface.KeyBindingAccessor;

@Mixin(KeybindsScreen.class)
public class ControlsOptionsScreenMixin extends Screen {
    @Shadow
    private GameOptions gameOptions;

    @Unique
    private ControlsListWidget controlsList;

    @Inject(method = "init", at = @At("HEAD"))
    private void onInit(CallbackInfo ci) {
        this.controlsList = new ControlsListWidget((KeybindsScreen) (Object) this, minecraft, gameOptions);
    }

    @Redirect(method = "init", at = @At(value = "NEW", target = "(IIIIILjava/lang/String;)Lnet/minecraft/client/gui/widget/OptionButtonWidget;"))
    private OptionButtonWidget redirectOptionButtonWidget(int id, int x, int y, int width, int height, String text) {
        OptionButtonWidget editButton = new OptionButtonWidget(id, -1, -1, width, height, text);
        CallbackButtonWidget resetButton = new CallbackButtonWidget(-1, -1, 50, 20, "Reset", (button) -> {
            KeyBinding keyBinding = this.gameOptions.allKeys[id];
            keyBinding.code = ((KeyBindingAccessor) keyBinding).getDefaultKeyCode();
            ((ButtonWidget) this.buttons.get(id)).text = this.gameOptions.getKeybindKey(id);
        });
        controlsList.getButtons().put(this.gameOptions.allKeys[id], new ControlsListWidget.KeyBindingEntry(editButton, resetButton));
        return editButton;
    }

    @Inject(method = "init", at = @At("RETURN"))
    private void afterInit(CallbackInfo ci) {
        for (ControlsListWidget.KeyBindingEntry entry : controlsList.getButtons().values()) {
            this.buttons.add(entry.getResetButton());
        }
    }

    @Unique
    private ButtonWidget doneButton;

    @Redirect(method = "init", at = @At(value = "NEW", target = "(IIILjava/lang/String;)Lnet/minecraft/client/gui/widget/ButtonWidget;"))
    private ButtonWidget redirectDoneButton(int id, int x, int y, String text) {
        return doneButton = new ButtonWidget(id, x, this.height - 30, text);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/option/KeybindsScreen;renderBackground()V", shift = At.Shift.AFTER))
    private void onRender(int mouseX, int mouseY, float delta, CallbackInfo ci) {
        this.controlsList.render(mouseX, mouseY, delta);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/option/KeybindsScreen;getControlsListX()I"), cancellable = true)
    private void onDrawButtons(int mouseX, int mouseY, float delta, CallbackInfo ci) {
        doneButton.render(this.minecraft, mouseX, mouseY);
        ci.cancel();
    }
}
