package lack.lacklibrary.block;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.PushReaction;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public abstract class BlocksTemplate extends DefaultTemplate {


    private ResourceKey<@NotNull Block> blockId(final String name) {
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(getModId(), name));
    }

    public Block register(final String id, final BlockBehaviour.Properties properties) {
        return register(id, Block::new, properties);
    }

    public Block register(final String id, final Function<BlockBehaviour.Properties, Block> factory, final BlockBehaviour.Properties properties) {
        return register(blockId(id), factory, properties);
    }

    public Block register(final ResourceKey<@NotNull Block> id, final BlockBehaviour.Properties properties) {
        return register(id, Block::new, properties);
    }

    public Block register(final ResourceKey<@NotNull Block> id, final Function<BlockBehaviour.Properties, Block> factory, final BlockBehaviour.Properties properties) {
        Block block = factory.apply(properties.setId(id));
        return Registry.register(BuiltInRegistries.BLOCK, id, block);
    }


    public Block registerStairs(final String id, final Block base) {
        return registerStairs(id, base, BlockBehaviour.Properties.ofFullCopy(base));
    }

    public Block registerStairs(final String id, final Block base, final BlockBehaviour.Properties properties) {
        return register(id, (p) -> new StairBlock(base.defaultBlockState(), p), properties);
    }

    public Block registerSlab(final String id, final Block base) {
        return registerSlab(id, BlockBehaviour.Properties.ofFullCopy(base));
    }

    public Block registerSlab(final String id, final BlockBehaviour.Properties properties) {
        return register(id, SlabBlock::new, properties);
    }

    public Block registerWall(final String id, final Block base) {
        return registerWall(id, BlockBehaviour.Properties.ofFullCopy(base));
    }

    public Block registerWall(final String id, final BlockBehaviour.Properties properties) {
        return register(id, WallBlock::new, properties);
    }

    public Block registerFence(final String id, final Block base) {
        return registerFence(id, BlockBehaviour.Properties.ofFullCopy(base));
    }

    public Block registerFence(final String id, final BlockBehaviour.Properties properties) {
        return register(id, FenceBlock::new, properties);
    }

    public Block registerFenceGate(final String id, WoodType woodType, final Block base) {
        return registerFenceGate(id, woodType, BlockBehaviour.Properties.ofFullCopy(base));
    }

    public Block registerFenceGate(final String id, WoodType woodType, final BlockBehaviour.Properties properties) {
        return register(id, (p) -> new FenceGateBlock(woodType, p), properties);
    }

    public Block registerDoor(final String id, BlockSetType blockSetType, final Block base) {
        return registerDoor(id, blockSetType, BlockBehaviour.Properties.ofFullCopy(base).noOcclusion().pushReaction(PushReaction.DESTROY));
    }

    public Block registerDoor(final String id, BlockSetType blockSetType, final BlockBehaviour.Properties properties) {
        return register(id, (p) -> new DoorBlock(blockSetType, p), properties);
    }

    public Block registerTrapdoor(final String id, BlockSetType blockSetType, final Block base) {
        return registerTrapdoor(id, blockSetType, BlockBehaviour.Properties.ofFullCopy(base).noOcclusion().isValidSpawn(Blocks::never));
    }

    public Block registerTrapdoor(final String id, BlockSetType blockSetType, final BlockBehaviour.Properties properties) {
        return register(id, (p) -> new TrapDoorBlock(blockSetType, p), properties);
    }

    public Block registerButton(final String id, int ticksToStayPressed, BlockSetType blockSetType) {
        return register(id, (p) -> new ButtonBlock(blockSetType, ticksToStayPressed, p), Blocks.buttonProperties());
    }

    public Block registerPressurePlate(final String id, BlockSetType blockSetType, final Block base, float strength) {
        return registerPressurePlate(id, blockSetType, BlockBehaviour.Properties.ofFullCopy(base).forceSolidOn().strength(strength).noCollision().pushReaction(PushReaction.DESTROY));
    }

    public Block registerPressurePlate(final String id, BlockSetType blockSetType, final BlockBehaviour.Properties properties) {
        return register(id, (p) -> new PressurePlateBlock(blockSetType, p), properties);
    }


}
