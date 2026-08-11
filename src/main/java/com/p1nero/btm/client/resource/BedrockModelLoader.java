package com.p1nero.btm.client.resource;

import com.github.mcmodderanchor.simplebedrockmodel.v1.common.model.BedrockModel;
import com.github.mcmodderanchor.simplebedrockmodel.v1.event.RegisterBedrockModelEvent;
import com.github.mcmodderanchor.simplebedrockmodel.v1.event.RegisterBedrockModelReloadListenerEvent;
import com.github.mcmodderanchor.simplebedrockmodel.v1.resource.RawResourceLoaders;
import com.p1nero.btm.BetterTridentModelMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = BetterTridentModelMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class BedrockModelLoader {
    public static final ResourceLocation TRIDENT = id("entity/trident");
    public static final ResourceLocation TRIDENT_TEXTURE = id("textures/entity/trident.png");

    private static BedrockModel trident;

    private BedrockModelLoader() {
    }

    @SubscribeEvent
    public static void registerModel(RegisterBedrockModelEvent event) {
        event.register(TRIDENT, RawResourceLoaders.CLIENT_ONLY_LOADER);
    }

    @SubscribeEvent
    public static void captureModel(RegisterBedrockModelReloadListenerEvent event) {
        event.register(models -> trident = models.get(TRIDENT));
    }

    public static BedrockModel getTridentModel() {
        return Objects.requireNonNull(trident, "Trident model has not been loaded");
    }

    private static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(BetterTridentModelMod.MOD_ID, path);
    }
}
