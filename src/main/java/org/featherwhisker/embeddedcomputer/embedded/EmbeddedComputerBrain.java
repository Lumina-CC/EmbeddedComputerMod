package org.featherwhisker.embeddedcomputer.embedded;

import org.featherwhisker.embeddedcomputer.embedded.block.EmbeddedComputerBlockEntity;

public class EmbeddedComputerBrain implements IEmbeddedComputer{
    private final EmbeddedComputerBlockEntity owner;
    public EmbeddedComputerBrain(EmbeddedComputerBlockEntity owner){
        this.owner = owner;
    }

    @Override
    public EmbeddedComputerBlockEntity getOwner() {
        return this.owner;
    }
}
