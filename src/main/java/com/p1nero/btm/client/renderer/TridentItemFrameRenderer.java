package com.p1nero.btm.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.p1nero.btm.BetterTridentModelMod;
import com.p1nero.btm.client.resource.BedrockModelLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.RenderItemInFrameEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BetterTridentModelMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class TridentItemFrameRenderer {
    private TridentItemFrameRenderer() {
    }

    @SubscribeEvent
    public static void renderItemFrame(RenderItemInFrameEvent event) {
        if (!event.getItemStack().is(Items.TRIDENT)) {
            return;
        }

        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        BakedModel model = Minecraft.getInstance().getModelManager().getModel(BedrockModelLoader.TRIDENT_INVENTORY_MODEL);
        PoseStack poseStack = event.getPoseStack();
        poseStack.pushPose();
        poseStack.scale(0.5F, 0.5F, 0.5F);
        model = ForgeHooksClient.handleCameraTransforms(poseStack, model, ItemDisplayContext.FIXED, false);
        poseStack.translate(-0.5F, -0.5F, -0.5F);

        int packedLight = event.getItemFrameEntity().getType() == EntityType.GLOW_ITEM_FRAME
                ? LightTexture.FULL_BRIGHT
                : event.getPackedLight();
        for (BakedModel pass : model.getRenderPasses(event.getItemStack(), true)) {
            for (var renderType : pass.getRenderTypes(event.getItemStack(), true)) {
                VertexConsumer buffer = ItemRenderer.getFoilBufferDirect(
                        event.getMultiBufferSource(), renderType, true, event.getItemStack().hasFoil());
                itemRenderer.renderModelLists(
                        pass, event.getItemStack(), packedLight, OverlayTexture.NO_OVERLAY, poseStack, buffer);
            }
        }

        poseStack.popPose();
        event.setCanceled(true);
    }
}
