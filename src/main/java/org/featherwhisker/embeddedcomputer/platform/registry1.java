package org.featherwhisker.embeddedcomputer.platform;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.featherwhisker.embeddedcomputer.registry;

public class registry1 {
    public static final ItemGroup LCC_ADDITIONS_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(registry.EMBEDDED_COMPUTER))
            .displayName(Text.translatable("itemGroup.embeddedcomputer"))
            .build();
    public static RegistryKey<ItemGroup> itemGroupKey = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of("embeddedcomputer", "item_group"));
    public void registerItemGroups() {
        Registry.register(Registries.ITEM_GROUP,itemGroupKey, LCC_ADDITIONS_GROUP);
        ItemGroupEvents.modifyEntriesEvent(itemGroupKey).register(itemGroup -> {
            itemGroup.add(registry.EMBEDDED_COMPUTER_ITEM);
        });
    }
}
