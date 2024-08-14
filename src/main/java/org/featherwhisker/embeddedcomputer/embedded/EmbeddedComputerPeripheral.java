package org.featherwhisker.embeddedcomputer.embedded;

import dan200.computercraft.api.ComputerCraftAPI;
import dan200.computercraft.api.filesystem.WritableMount;
import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.shared.computer.blocks.AbstractComputerBlockEntity;
import dan200.computercraft.core.computer.Computer;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;
import org.featherwhisker.embeddedcomputer.embedded.block.EmbeddedComputerBlockEntity;
import java.lang.reflect.Method;
import dan200.computercraft.core.filesystem.FileSystem;
import static java.util.Objects.isNull;
import static org.featherwhisker.embeddedcomputer.main.log;

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

    public ServerEmbeddedComputer getServerComp() {
        var comp1 = comp.getServerComputer();
        return (ServerEmbeddedComputer) comp1;
    }

    @LuaFunction
    public final boolean isOn() {
        var comp1= getServerComp();
        return !isNull(comp1) && comp1.isOn();
    }
    @LuaFunction
    public final void reboot() {
        var comp1 = getServerComp();
        if (!isNull(comp1)) comp1.reboot();
    }
    @LuaFunction
    public final void format() {
        var comp1 = getServerComp();
        if (!isNull(comp1)) {
            try {
                WritableMount fs = ComputerCraftAPI.createSaveDirMount(comp1.getLevel().getServer(), "computer/" + comp1.getID(), 100);
                try {
                    fs.delete("/startup.lua");
                } catch(Exception ignored){}
                try {
                    fs.delete("/startup");
                } catch(Exception ignored){}
                try {
                    fs.delete("/.settings");
                } catch(Exception ignored){}
                comp1.reboot();
            }catch(Exception ignored){
                log.info(ignored.getMessage());
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
