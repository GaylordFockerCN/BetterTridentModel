package com.p1nero.btm.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.p1nero.btm.client.resource.BedrockModelLoader;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.projectile.ThrownTrident;

public final class BedrockThrownTridentRenderer extends EntityRenderer<ThrownTrident> {
    public BedrockThrownTridentRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(ThrownTrident trident, float yaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource buffers, int packedLight) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTick, trident.yRotO, trident.getYRot()) - 90.0F));
        poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTick, trident.xRotO, trident.getXRot()) + 90.0F));
        VertexConsumer buffer = ItemRenderer.getFoilBufferDirect(
                buffers, RenderType.entitySolid(BedrockModelLoader.TRIDENT_TEXTURE), false, trident.isFoil());
        BedrockModelLoader.getTridentModel().renderToBuffer(poseStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
        super.render(trident, yaw, partialTick, poseStack, buffers, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(ThrownTrident trident) {
        return BedrockModelLoader.TRIDENT_TEXTURE;
    }
}
