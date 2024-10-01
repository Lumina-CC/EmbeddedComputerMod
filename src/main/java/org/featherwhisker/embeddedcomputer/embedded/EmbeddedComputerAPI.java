package org.featherwhisker.embeddedcomputer.embedded;

import dan200.computercraft.api.ComputerCraftAPI;
import dan200.computercraft.api.filesystem.WritableMount;
import dan200.computercraft.api.lua.ILuaAPI;
import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.shared.computer.core.ServerComputer;

import static java.util.Objects.isNull;
import static org.featherwhisker.embeddedcomputer.main.log;

public class EmbeddedComputerAPI implements ILuaAPI {
    private final IEmbeddedComputer brain;
    public EmbeddedComputerAPI(IEmbeddedComputer brain) {
        this.brain = brain;
    }
    @Override
    public String[] getNames() {
        return new String[]{"embedded"};
    }
    @LuaFunction
    public final void format() { //
        ServerComputer comp = brain.getOwner().getServerComputer();
        if (!isNull(comp)) {
            try {
                WritableMount fs = ComputerCraftAPI.createSaveDirMount(comp.getLevel().getServer(), "computer/" + comp.getID(), 100);
                try {
                    fs.delete("/startup.lua");
                } catch(Exception ignored){}
                try {
                    fs.delete("/startup");
                } catch(Exception ignored){}
                try {
                    fs.delete("/.settings");
                } catch(Exception ignored){}
                comp.reboot();
            }catch(Exception ignored){
                log.info(ignored.getMessage());
            }
        }
    }
}