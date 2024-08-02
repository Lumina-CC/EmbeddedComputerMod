package org.luminacc.additions.embedded.block;

import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.shared.computer.blocks.ComputerBlockEntity;
import dan200.computercraft.shared.computer.core.ComputerFamily;
import dan200.computercraft.shared.computer.core.ServerComputer;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.luminacc.additions.embedded.EmbeddedComputerPeripheral;
import org.luminacc.additions.embedded.ServerEmbeddedComputer;
import org.luminacc.additions.registry;

import javax.annotation.Nullable;

import static java.util.Objects.isNull;

public class EmbeddedComputerBlockEntity extends ComputerBlockEntity {
    private @Nullable IPeripheral p;
    public EmbeddedComputerBlockEntity(BlockPos pos, BlockState state) {
        super(registry.EMBEDDED_COMPUTER_ENTITY,pos,state,ComputerFamily.ADVANCED);
    }
    @Override
    protected ServerComputer createComputer(int id) {
        return new ServerEmbeddedComputer(
                (ServerWorld) getWorld(), getPos(), id, label
        );
    }

    @Override
    public IPeripheral peripheral() {
        if (!isNull(p)){
            return p;
        }
        return p = new EmbeddedComputerPeripheral(this);
    }

}
