package org.featherwhisker.embeddedcomputer;

import dan200.computercraft.api.ComputerCraftAPI;
import net.fabricmc.api.ModInitializer;
import org.featherwhisker.embeddedcomputer.embedded.EmbeddedComputerAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class main implements ModInitializer {

    public static Logger log = LoggerFactory.getLogger("LccAdditions");
    @Override
    public void onInitialize() {
        var a = new registry();
        a.registerPeripherals();
        a.registerItemGroups();
        ComputerCraftAPI.registerAPIFactory(computer -> {
            var embedded = computer.getComponent(ComputerComponents.EMBEDDED);
            return embedded == null ? null : new EmbeddedComputerAPI(embedded);
        });
    }
}
