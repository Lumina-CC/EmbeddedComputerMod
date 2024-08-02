package org.luminacc.additions.embedded;

import dan200.computercraft.shared.computer.core.ComputerFamily;
import dan200.computercraft.shared.computer.core.ServerComputer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import static org.luminacc.additions.main.log;

public class ServerEmbeddedComputer extends ServerComputer {
    public ServerEmbeddedComputer(ServerWorld level, BlockPos position, int computerID, @Nullable String label) {
        super(level, position, computerID, label, ComputerFamily.ADVANCED, 10, 4);
    }
}
