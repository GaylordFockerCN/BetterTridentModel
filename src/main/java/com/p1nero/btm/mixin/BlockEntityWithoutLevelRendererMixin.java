package com.p1nero.btm.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.p1nero.btm.client.resource.BedrockModelLoader;
import net.minecraft.client.model.TridentModel;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockEntityWithoutLevelRenderer.class)
public class BlockEntityWithoutLevelRendererMixin {

    @Shadow
    private TridentModel tridentModel;

    @Inject(method = "renderByItem", at = @At(value = "HEAD"), cancellable = true)
    private void better_trident_model$renderByItem(ItemStack p_108830_, ItemDisplayContext p_270899_, PoseStack poseStack, MultiBufferSource p_108833_, int p_108834_, int p_108835_, CallbackInfo ci) {
        if (p_108830_.is(Items.TRIDENT)) {
            poseStack.pushPose();
            poseStack.scale(1.0F, -1.0F, -1.0F);
            poseStack.translate(0, -0.5F, 0);
            VertexConsumer vertexconsumer1 = ItemRenderer.getFoilBufferDirect(p_108833_, this.tridentModel.renderType(BedrockModelLoader.TRIDENT_TEXTURE), false, p_108830_.hasFoil());
            BedrockModelLoader.getTridentModel().renderToBuffer(poseStack, vertexconsumer1, p_108834_, p_108835_, 1.0F, 1.0F, 1.0F, 1.0F);
            poseStack.popPose();
            ci.cancel();
        }
    }

}
