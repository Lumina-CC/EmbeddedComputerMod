package org.featherwhisker.embeddedcomputer.embedded;

import dan200.computercraft.core.computer.Computer;
import dan200.computercraft.shared.computer.core.ComputerFamily;
import dan200.computercraft.shared.computer.core.ServerComputer;
import dan200.computercraft.shared.util.ComponentMap;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;

import static java.lang.Thread.sleep;
import static java.util.Objects.isNull;
import static org.featherwhisker.embeddedcomputer.main.log;

public class ServerEmbeddedComputer extends ServerComputer {
    public ServerEmbeddedComputer(ServerWorld level, BlockPos position, int computerID, @Nullable String label) {
        super(level, position, computerID, label, ComputerFamily.ADVANCED, 10, 10, ComponentMap.builder().build());
    }
}
