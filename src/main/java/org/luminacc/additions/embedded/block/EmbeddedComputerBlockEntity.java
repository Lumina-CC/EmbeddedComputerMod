package org.luminacc.additions.embedded.block;

import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.shared.computer.blocks.ComputerBlockEntity;
import dan200.computercraft.shared.computer.core.ComputerFamily;
import dan200.computercraft.shared.computer.core.ServerComputer;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.luminacc.additions.embedded.EmbeddedComputerPeripheral;
import org.luminacc.additions.embedded.ServerEmbeddedComputer;
import org.luminacc.additions.registry;

import javax.annotation.Nullable;

import static java.util.Objects.isNull;
import static org.luminacc.additions.main.log;

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
    protected boolean wasOn = false;
    @Override
    public void serverTick() {
        if (getWorld().isClient) {
            return; //no.
        }
        if (getComputerID() < 0) {
            return;
        }
        var comp = createServerComputer();
        var currentlyOn = comp.isOn();
        if (currentlyOn != wasOn) {
            wasOn = currentlyOn;
            markDirty();
        }
        if (!currentlyOn) {
            comp.turnOn();
        }
        comp.keepAlive();
        updateBlockState(comp.getState());
    }
}
