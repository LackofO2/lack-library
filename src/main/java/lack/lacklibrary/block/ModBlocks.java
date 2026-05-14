package lack.lacklibrary.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ModBlocks {
    public static BlocksTemplate template = new BlocksTemplate() {
        @Override
        public String getModId() {
            return "";
        }
    };

    public static Block TEST = template.register("test", BlockBehaviour.Properties.of());
    public static Block TEST_STAIRS = template.register("test_stairs", BlockBehaviour.Properties.of());
}
