package com.p1nero.btm.client.resource;

import com.github.mcmodderanchor.simplebedrockmodel.v1.common.model.BedrockModel;
import com.p1nero.btm.BetterTridentModelMod;
import com.p1nero.btm.client.manager.BedrockModelRegister;
import com.p1nero.btm.client.manager.BedrockModelRegisterEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BedrockModelLoader {

    private static BedrockModel trident;
    public static final ResourceLocation TRIDENT = ResourceLocation.fromNamespaceAndPath(BetterTridentModelMod.MOD_ID, "bedrock/entity/trident");
    public static final ResourceLocation TRIDENT_TEXTURE = ResourceLocation.fromNamespaceAndPath(BetterTridentModelMod.MOD_ID, "textures/entity/trident.png");

    @SubscribeEvent
    public static void onRegisterBedrockModelRenderers(BedrockModelRegisterEvent event) {
        event.register(TRIDENT, BedrockModel::new);
    }

    public static BedrockModel getTridentModel() {
        if(trident == null) {
            trident = BedrockModelRegister.INSTANCE.getModel(TRIDENT);
        }
        return trident;
    }
}