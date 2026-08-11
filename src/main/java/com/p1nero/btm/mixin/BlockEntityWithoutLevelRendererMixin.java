package com.p1nero.btm.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.p1nero.btm.client.resource.BedrockModelLoader;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockEntityWithoutLevelRenderer.class)
public class BlockEntityWithoutLevelRendererMixin {

    @Inject(method = "renderByItem", at = @At(value = "HEAD"), cancellable = true)
    private void better_trident_model$renderByItem(ItemStack stack, ItemDisplayContext displayContext, PoseStack poseStack,
                                                   MultiBufferSource buffers, int packedLight, int packedOverlay,
                                                   CallbackInfo callback) {
        if (!stack.is(Items.TRIDENT)) {
            return;
        }

        poseStack.pushPose();
        poseStack.scale(1.0F, -1.0F, -1.0F);
        poseStack.translate(0.0F, -0.5F, 0.0F);
        VertexConsumer buffer = ItemRenderer.getFoilBufferDirect(
                buffers, RenderType.entitySolid(BedrockModelLoader.TRIDENT_TEXTURE), false, stack.hasFoil());
        BedrockModelLoader.getTridentModel().renderToBuffer(poseStack, buffer, packedLight, packedOverlay);
        poseStack.popPose();
        callback.cancel();
    }

}
