package be.florens.expandability.mixin.swimming.client;

import be.florens.expandability.EventDispatcher;
import be.florens.expandability.Util;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerRenderer.class)
public abstract class PlayerRendererMixin {

	@SuppressWarnings("AmbiguousMixinReference") // It's really not?
	@Redirect(method = "setupRotations", require = 0, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/AbstractClientPlayer;isInWater()Z"))
	private boolean setInWater(AbstractClientPlayer player) {
		return Util.processEventResult(EventDispatcher.onPlayerSwim(player), player::isInWater);
	}
}
