package lack.lacklibrary.block;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;
import java.util.function.Function;

public abstract class ItemsTemplate extends DefaultTemplate {


    private ResourceKey<@NotNull Item> itemId(final String name) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(getModId(), name));
    }

    private ResourceKey<@NotNull Item> blockIdToItemId(final ResourceKey<@NotNull Block> blockName) {
        return ResourceKey.create(Registries.ITEM, blockName.identifier());
    }


    public Item registerBlockItem(final Block block) {
        return registerBlockItem(block, BlockItem::new);
    }

    public Item registerBlockItem(final Block block, final BiFunction<Block, Item.Properties, Item> itemFactory) {
        return registerBlockItem(block, itemFactory, new Item.Properties());
    }

    public Item registerBlockItem(final Block block, final BiFunction<Block, Item.Properties, Item> itemFactory, final Item.Properties properties) {
        return registerItem(blockIdToItemId(block.builtInRegistryHolder().key()), (p) -> itemFactory.apply(block, p), properties.useBlockDescriptionPrefix().requiredFeatures(block.requiredFeatures()));
    }


    public Item registerItem(final String name, final Item.Properties properties) {
        return registerItem(itemId(name), Item::new, properties);
    }

    public Item registerItem(final String name) {
        return registerItem(itemId(name), Item::new, new Item.Properties());
    }


    public Item registerItem(final ResourceKey<@NotNull Item> key, final Function<Item.Properties, Item> itemFactory, final Item.Properties properties) {
        Item item = itemFactory.apply(properties.setId(key));
        if (item instanceof BlockItem blockItem) {
            blockItem.registerBlocks(Item.BY_BLOCK, item);
        }

        return Registry.register(BuiltInRegistries.ITEM, key, item);
    }


}
