package org.luminacc.additions;

import dan200.computercraft.api.peripheral.PeripheralLookup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.luminacc.additions.embedded.EmbeddedComputerPeripheral;
import org.luminacc.additions.embedded.block.EmbeddedComputerBlock;
import org.luminacc.additions.embedded.block.EmbeddedComputerBlockEntity;

public class registry {
    public static Block EMBEDDED_COMPUTER = Registry.register(
            Registries.BLOCK,
            new Identifier("lcc-additions","embedded_computer"),
            (Block) new EmbeddedComputerBlock(AbstractBlock.Settings.create().pistonBehavior(PistonBehavior.BLOCK).solid())
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

    public static final ItemGroup LCC_ADDITIONS_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(EMBEDDED_COMPUTER))
            .displayName(Text.translatable("itemGroup.lcc_additions"))
            .build();
    public static RegistryKey<ItemGroup> itemGroupKey = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of("lcc-additions", "item_group"));
    public void registerPeripherals() {
        PeripheralLookup.get().registerForBlockEntities(EmbeddedComputerPeripheral::getPeripheral,EMBEDDED_COMPUTER_ENTITY);
    }
    public void registerItemGroups() {
        Registry.register(Registries.ITEM_GROUP,itemGroupKey, LCC_ADDITIONS_GROUP);
        ItemGroupEvents.modifyEntriesEvent(itemGroupKey).register(itemGroup -> {
            itemGroup.add(EMBEDDED_COMPUTER_ITEM);
        });
    }
}
