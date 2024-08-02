package org.luminacc.additions;

import dan200.computercraft.api.peripheral.PeripheralLookup;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.luminacc.additions.embedded.EmbeddedComputerPeripheral;
import org.luminacc.additions.embedded.block.EmbeddedComputerBlock;
import org.luminacc.additions.embedded.block.EmbeddedComputerBlockEntity;

public class registry {
    public static registry INSTANCE = new registry();
    public static Block EMBEDDED_COMPUTER = Registry.register(
            Registries.BLOCK,
            new Identifier("lcc-additions","embedded_computer"),
            (Block) new EmbeddedComputerBlock(AbstractBlock.Settings.create())
    );
    public static BlockEntityType<EmbeddedComputerBlockEntity> EMBEDDED_COMPUTER_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            new Identifier("lcc-additions", "embedded_computer_entity"),
            BlockEntityType.Builder.create(EmbeddedComputerBlockEntity::new, EMBEDDED_COMPUTER).build(null)
    );
    public static Item EMBEDDED_COMPUTER_ITEM = Registry.register(
            Registries.ITEM,
            new Identifier("lcc-additions", "embedded_computer"),
            new BlockItem(EMBEDDED_COMPUTER,new Item.Settings().fireproof())
    );

    public void registerPeripherals() {
        PeripheralLookup.get().registerForBlockEntities(EmbeddedComputerPeripheral::getPeripheral,EMBEDDED_COMPUTER_ENTITY);
    }
}
