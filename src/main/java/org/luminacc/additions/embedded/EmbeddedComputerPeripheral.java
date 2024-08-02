package org.luminacc.additions.embedded;

import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.shared.computer.blocks.AbstractComputerBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;
import org.luminacc.additions.embedded.block.EmbeddedComputerBlockEntity;
import static java.util.Objects.isNull;

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

    @LuaFunction
    public final boolean isOn() {
        var comp1= comp.getServerComputer();
        return !isNull(comp1) && comp1.isOn();
    }
    @LuaFunction
    public final void reboot() {
        var comp1= comp.getServerComputer();
        if (!isNull(comp1)) comp1.reboot();
    }
    @LuaFunction
    public final void format() {
        var comp1= comp.getServerComputer();
        if (!isNull(comp1)) {
            try {
                for (String i : comp1.getAPIEnvironment().getFileSystem().list("/")) {
                    try {
                        comp1.getAPIEnvironment().getFileSystem().delete("/");
                    } catch (Exception e) {
                        comp1.getAPIEnvironment().getFileSystem().delete("/");
                    }
                }
            } catch (Exception e) {}
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
