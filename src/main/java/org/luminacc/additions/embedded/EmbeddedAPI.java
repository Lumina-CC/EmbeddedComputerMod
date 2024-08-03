package org.luminacc.additions.embedded;

import dan200.computercraft.api.lua.ILuaAPI;
import dan200.computercraft.api.lua.LuaFunction;

import static java.util.Objects.isNull;

public class EmbeddedAPI implements ILuaAPI {
    private final ServerEmbeddedComputer comp;
    public EmbeddedAPI(ServerEmbeddedComputer comp1) {
        comp = comp1;
    }
    @Override
    public String[] getNames() {
        return new String[]{"embedded"};
    }

    @LuaFunction
    public final void format() { //
        if (!isNull(comp)) {
            try {
                for (String i : comp.getAPIEnvironment().getFileSystem().list("/")) {
                    try {
                        comp.getAPIEnvironment().getFileSystem().delete("/"+i);
                    }catch(Exception ignored){}
                }
                comp.reboot();
            }catch(Exception ignored){}
        }
    }
}
