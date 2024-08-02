package org.luminacc.additions.embedded;

import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.shared.computer.blocks.AbstractComputerBlockEntity;
import dan200.computercraft.shared.computer.core.ServerComputer;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;
import org.luminacc.additions.embedded.block.EmbeddedComputerBlockEntity;
import static java.util.Objects.isNull;
import static org.luminacc.additions.main.log;

public class EmbeddedComputerPeripheral implements IPeripheral {
    public final EmbeddedComputerBlockEntity comp;
    public EmbeddedComputerPeripheral(AbstractComputerBlockEntity owner) {
        super();
        this.comp = (EmbeddedComputerBlockEntity) owner;
    }


    @Override
    public String getType() {
        return "embeddedcomputer";
    }

    @Override
    public boolean equals(@Nullable IPeripheral other) {
        return false;
    }

    @Nullable
    public ServerComputer getServerComp() {
        var comp1 = comp.getServerComputer();
        if (!isNull(comp1) && !comp1.getLevel().isClient()) {
            comp1 = comp.createServerComputer();
        }
        return comp1;
    }

    @LuaFunction
    public final boolean isOn() {
        var comp1= getServerComp();
        return !isNull(comp1) && comp1.isOn();
    }
    @LuaFunction
    public final void reboot() {
        var comp1= getServerComp();
        if (!isNull(comp1)) comp1.reboot();
    }
    @LuaFunction
    public final void format() {
        var comp1 = getServerComp();
        if (!isNull(comp1)) {
            try {
                for (String i : comp1.getAPIEnvironment().getFileSystem().list("/")) {
                    try {
                        comp1.getAPIEnvironment().getFileSystem().delete("/"+i);
                    } catch (Exception e) {
                        log.info(e.getMessage());
                    }
                }
            } catch (Exception e) {
                log.info(e.getMessage());
            }
        }
    }

    // Generic functions
    @Override
    public void attach(IComputerAccess computer) {}
    @Override
    public void detach(IComputerAccess computer) {}

    @Override
    public Object getTarget() {
        return comp;
    }

    public static IPeripheral getPeripheral(BlockEntity blockEntity, Direction direction) {
        return ((EmbeddedComputerBlockEntity)blockEntity).peripheral();
    }
}
