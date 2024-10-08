package org.featherwhisker.embeddedcomputer;

import dan200.computercraft.api.peripheral.PeripheralLookup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.featherwhisker.embeddedcomputer.embedded.EmbeddedComputerPeripheral;
import org.featherwhisker.embeddedcomputer.embedded.block.EmbeddedComputerBlock;
import org.featherwhisker.embeddedcomputer.embedded.block.EmbeddedComputerBlockEntity;
import org.featherwhisker.embeddedcomputer.embedded.item.ComputerBlockItem;
import org.featherwhisker.embeddedcomputer.platform.registry1;

public class registry {
    public static Block EMBEDDED_COMPUTER = Registry.register(
            Registries.BLOCK,
            new Identifier("embeddedcomputer","embedded_computer"),
            (Block) new EmbeddedComputerBlock(AbstractBlock.Settings.create().pistonBehavior(PistonBehavior.BLOCK).solid())
    );
    public static BlockEntityType<EmbeddedComputerBlockEntity> EMBEDDED_COMPUTER_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            new Identifier("embeddedcomputer", "embedded_computer_entity"),
            BlockEntityType.Builder.create(EmbeddedComputerBlockEntity::new, EMBEDDED_COMPUTER).build(null)
    );
    public static Item EMBEDDED_COMPUTER_ITEM = Registry.register(
            Registries.ITEM,
            new Identifier("embeddedcomputer", "embedded_computer"),
            new ComputerBlockItem(EMBEDDED_COMPUTER)
    );

    public void registerPeripherals() {
        PeripheralLookup.get().registerForBlockEntities(EmbeddedComputerPeripheral::getPeripheral,EMBEDDED_COMPUTER_ENTITY);
    }
    public void registerItemGroups() {
        var a = new registry1();
        a.registerItemGroups();
    }
}
