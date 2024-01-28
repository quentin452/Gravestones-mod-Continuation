package gravestone.structures.catacombs.components;

import gravestone.core.GSBlock;
import gravestone.structures.BoundingBoxHelper;
import gravestone.structures.MobSpawnHelper;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Mausoleum extends CatacombsBaseComponent {

    public Mausoleum(int direction, Random random, StructureBoundingBox structureBoundingBox) {
        super(direction);
        this.boundingBox = structureBoundingBox;
        topXEnd = 5;
        topZEnd = 5;
    }

    /**
     * Build component
     */
    @Override
    public boolean addComponentParts(World world, Random random) {
        int averageGroundLevel = BoundingBoxHelper.getAverageGroundLevel(world, boundingBox);

        if (averageGroundLevel < 0) {
            return true;
        }

        this.boundingBox.offset(0, averageGroundLevel - this.boundingBox.minY - 1, 0);
        this.fillWithAir(world, boundingBox, 2, 1, 2, 11, 5, 11);
        this.fillWithAir(world, boundingBox, 1, 1, 0, 12, 5, 1);
        this.fillWithAir(world, boundingBox, 6, 0, 6, 7, 0, 8);

        // brick floor
        this.fillWithRandomizedBlocks(world, boundingBox, 3, 0, 3, 10, 0, 10, false, random, getCemeteryCatacombsStones());

        // nether floor
        this.fillWithBlocks(world, boundingBox, 5, 0, 3, 5, 0, 10, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 8, 0, 3, 8, 0, 10, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 3, 0, 5, 5, 0, 5, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 8, 0, 5, 10, 0, 5, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 3, 0, 8, 5, 0, 8, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 8, 0, 8, 10, 0, 8, Blocks.nether_brick, Blocks.nether_brick, false);

        // nether walls
        this.fillWithBlocks(world, boundingBox, 2, 0, 1, 2, 4, 2, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 1, 0, 2, 1, 4, 2, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 5, 0, 1, 5, 4, 2, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 8, 0, 1, 8, 4, 2, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 11, 0, 1, 11, 4, 2, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 12, 0, 2, 12, 4, 2, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 2, 0, 11, 2, 4, 12, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 1, 0, 11, 1, 4, 11, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 5, 0, 11, 5, 4, 12, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 8, 0, 11, 8, 4, 12, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 11, 0, 12, 11, 4, 12, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 11, 0, 11, 12, 4, 11, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 1, 0, 5, 2, 4, 5, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 11, 0, 5, 12, 4, 5, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 1, 0, 8, 2, 4, 8, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 11, 0, 8, 12, 4, 8, Blocks.nether_brick, Blocks.nether_brick, false);

        // brick walls
        this.fillWithRandomizedBlocks(world, boundingBox, 3, 0, 2, 4, 4, 2, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 6, 3, 2, 7, 4, 2, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 9, 0, 2, 10, 4, 2, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 3, 0, 11, 4, 4, 11, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 6, 0, 11, 7, 4, 11, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 9, 0, 11, 10, 4, 11, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 2, 0, 3, 2, 4, 4, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 2, 0, 6, 2, 4, 7, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 2, 0, 9, 2, 4, 10, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 11, 0, 3, 11, 4, 4, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 11, 0, 6, 11, 4, 7, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 11, 0, 9, 11, 4, 10, false, random, getCemeteryCatacombsStones());

        // trap floor
        this.fillWithBlocks(world, boundingBox, 6, 0, 2, 7, 0, 2, GSBlock.trap, GSBlock.trap, false);

        // brick columns
        this.fillWithRandomizedBlocks(world, boundingBox, 5, 1, 5, 5, 4, 5, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 8, 1, 5, 8, 4, 5, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 5, 1, 8, 5, 4, 8, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 8, 1, 8, 8, 4, 8, false, random, getCemeteryCatacombsStones());

        // roof 1
        this.fillWithMetadataBlocks(world, boundingBox, 0, 5, 0, 0, 5, 13, Blocks.stone_slab, 6, Blocks.stone_slab, 6, false);
        this.fillWithMetadataBlocks(world, boundingBox, 13, 5, 0, 13, 5, 13, Blocks.stone_slab, 6, Blocks.stone_slab, 6, false);
        this.fillWithMetadataBlocks(world, boundingBox, 2, 5, 0, 11, 5, 0, Blocks.stone_slab, 6, Blocks.stone_slab, 6, false);
        this.fillWithMetadataBlocks(world, boundingBox, 2, 5, 13, 11, 5, 13, Blocks.stone_slab, 6, Blocks.stone_slab, 6, false);
        this.fillWithBlocks(world, boundingBox, 1, 5, 1, 12, 5, 12, Blocks.nether_brick, Blocks.nether_brick, false);
        this.placeBlockAtCurrentPosition(world, Blocks.nether_brick, 0, 1, 5, 0, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.nether_brick, 0, 12, 5, 0, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.nether_brick, 0, 1, 5, 13, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.nether_brick, 0, 12, 5, 13, boundingBox);

        // roof 2
        this.fillWithMetadataBlocks(world, boundingBox, 2, 6, 0, 2, 6, 13, Blocks.stone_slab, 6, Blocks.stone_slab, 6, false);
        this.fillWithMetadataBlocks(world, boundingBox, 11, 6, 0, 11, 6, 13, Blocks.stone_slab, 6, Blocks.stone_slab, 6, false);
        this.fillWithMetadataBlocks(world, boundingBox, 3, 6, 0, 3, 6, 13, Blocks.stone_slab, 14, Blocks.stone_slab, 14, false);
        this.fillWithMetadataBlocks(world, boundingBox, 10, 6, 0, 10, 6, 13, Blocks.stone_slab, 14, Blocks.stone_slab, 14, false);
        this.fillWithBlocks(world, boundingBox, 3, 6, 1, 10, 6, 1, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 3, 6, 12, 10, 6, 12, Blocks.nether_brick, Blocks.nether_brick, false);

        // roof 3
        this.fillWithMetadataBlocks(world, boundingBox, 4, 7, 0, 4, 7, 13, Blocks.stone_slab, 6, Blocks.stone_slab, 6, false);
        this.fillWithMetadataBlocks(world, boundingBox, 9, 7, 0, 9, 7, 13, Blocks.stone_slab, 6, Blocks.stone_slab, 6, false);
        this.fillWithMetadataBlocks(world, boundingBox, 5, 7, 0, 5, 7, 13, Blocks.stone_slab, 14, Blocks.stone_slab, 14, false);
        this.fillWithMetadataBlocks(world, boundingBox, 8, 7, 0, 8, 7, 13, Blocks.stone_slab, 14, Blocks.stone_slab, 14, false);
        this.fillWithBlocks(world, boundingBox, 5, 7, 1, 8, 7, 1, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 5, 7, 12, 8, 7, 12, Blocks.nether_brick, Blocks.nether_brick, false);

        // roof 4
        this.fillWithMetadataBlocks(world, boundingBox, 6, 8, 0, 7, 8, 13, Blocks.stone_slab, 6, Blocks.stone_slab, 6, false);

        for (int x = 2; x < 12; x++) {
            for (int z = 2; z < 12; z++) {
                this.func_151554_b(world, Blocks.stonebrick, 1, x, -1, z, boundingBox);
            }
        }

        this.func_151554_b(world, Blocks.nether_brick, 0, 2, -1, 1, boundingBox);
        this.func_151554_b(world, Blocks.nether_brick, 0, 5, -1, 1, boundingBox);
        this.func_151554_b(world, Blocks.nether_brick, 0, 8, -1, 1, boundingBox);
        this.func_151554_b(world, Blocks.nether_brick, 0, 11, -1, 1, boundingBox);
        this.func_151554_b(world, Blocks.nether_brick, 0, 2, -1, 12, boundingBox);
        this.func_151554_b(world, Blocks.nether_brick, 0, 5, -1, 12, boundingBox);
        this.func_151554_b(world, Blocks.nether_brick, 0, 8, -1, 12, boundingBox);
        this.func_151554_b(world, Blocks.nether_brick, 0, 11, -1, 12, boundingBox);
        this.func_151554_b(world, Blocks.nether_brick, 0, 1, -1, 2, boundingBox);
        this.func_151554_b(world, Blocks.nether_brick, 0, 1, -1, 5, boundingBox);
        this.func_151554_b(world, Blocks.nether_brick, 0, 1, -1, 8, boundingBox);
        this.func_151554_b(world, Blocks.nether_brick, 0, 1, -1, 11, boundingBox);
        this.func_151554_b(world, Blocks.nether_brick, 0, 12, -1, 2, boundingBox);
        this.func_151554_b(world, Blocks.nether_brick, 0, 12, -1, 5, boundingBox);
        this.func_151554_b(world, Blocks.nether_brick, 0, 12, -1, 8, boundingBox);
        this.func_151554_b(world, Blocks.nether_brick, 0, 12, -1, 11, boundingBox);

        // spawn bats
        MobSpawnHelper.spawnBats(world, random, boundingBox);

        // web
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.3F, 6, 3, 3, 6, 3, 3, Blocks.web, Blocks.web, false);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.3F, 5, 4, 6, 5, 4, 6, Blocks.web, Blocks.web, false);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.3F, 7, 1, 7, 7, 1, 7, Blocks.web, Blocks.web, false);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.3F, 10, 2, 10, 10, 2, 10, Blocks.web, Blocks.web, false);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.3F, 3, 1, 9, 3, 1, 9, Blocks.web, Blocks.web, false);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.3F, 6, 4, 10, 6, 4, 10, Blocks.web, Blocks.web, false);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.3F, 10, 2, 3, 10, 2, 3, Blocks.web, Blocks.web, false);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.3F, 9, 3, 4, 9, 3, 4, Blocks.web, Blocks.web, false);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.3F, 3, 2, 4, 3, 2, 4, Blocks.web, Blocks.web, false);

        return true;
    }
}
