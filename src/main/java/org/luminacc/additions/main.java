package org.luminacc.additions;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class main implements ModInitializer {

    public static Logger log = LoggerFactory.getLogger("");
    @Override
    public void onInitialize() {
        var a = new registry();
        a.registerPeripherals();
    }
}
