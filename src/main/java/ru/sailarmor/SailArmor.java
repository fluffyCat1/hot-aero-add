package ru.sailarmor;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import rbasamoyai.createbigcannons.block_armor_properties.BlockArmorPropertiesHandler;
import rbasamoyai.createbigcannons.block_armor_properties.mimicking_blocks.AbstractMimickingBlockArmorProperties;

@Mod(SailArmor.MODID)
public class SailArmor {
    public static final String MODID = "hotstuff_cbc_armor";

    private static final String[] BLOCKS = {
        "copycat_sail",
        "copycat_symmetric_sail",
        "copycat_link_casing"
    };

    public SailArmor(IEventBus modBus) {
        modBus.addListener(this::onCommonSetup);
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            for (String name : BLOCKS) {
                Block block = BuiltInRegistries.BLOCK.get(ResourceLocation.fromNamespaceAndPath("hotstuff", name));
                if (block == Blocks.AIR) continue;
                BlockArmorPropertiesHandler.registerCustomSerializer(
                    block,
                    AbstractMimickingBlockArmorProperties.createMimicrySerializer(HotstuffMimicArmor::new)
                );
            }
        });
    }
}
