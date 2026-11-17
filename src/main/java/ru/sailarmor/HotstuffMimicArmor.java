package ru.sailarmor;

import com.hotelbows.hotstuff.copycatsail.CopycatSailBlockEntity;
import com.hotelbows.hotstuff.linkcasing.CopycatLinkCasingBlockEntity;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import rbasamoyai.createbigcannons.block_armor_properties.mimicking_blocks.AbstractMimickingBlockArmorProperties;
import rbasamoyai.createbigcannons.block_armor_properties.mimicking_blocks.MimickingBlockArmorUnit;

/** Берёт материал, вставленный в паруса / корпус связи Hot's Aerostuff, как "скопированный" блок для брони CBC. */
public class HotstuffMimicArmor extends AbstractMimickingBlockArmorProperties {

    public HotstuffMimicArmor(MimickingBlockArmorUnit defaultUnit,
                              Map<BlockState, MimickingBlockArmorUnit> unitsByState) {
        super(defaultUnit, unitsByState);
    }

    @Override
    protected BlockState getCopiedState(Level level, BlockState state, BlockPos pos) {
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof CopycatSailBlockEntity sail) return sail.getMaterial();
        if (be instanceof CopycatLinkCasingBlockEntity casing) return casing.getMaterial();
        return Blocks.AIR.defaultBlockState();
    }
}
