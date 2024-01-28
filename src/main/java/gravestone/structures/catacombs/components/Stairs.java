package gravestone.structures.catacombs.components;

import gravestone.structures.BoundingBoxHelper;
import gravestone.structures.catacombs.CatacombsLevel;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Stairs extends CatacombsBaseComponent {

    public static final int X_LENGTH = 5;
    public static final int HEIGHT = 16;
    public static final int Z_LENGTH = 13;

    public Stairs(int direction, int level, Random random, int x, int y, int z) {
        super(direction, level);
        y = y - HEIGHT + 4;
        boundingBox = BoundingBoxHelper.getCorrectBox(direction, x, y, z, X_LENGTH, HEIGHT, Z_LENGTH, xShift);
        goTop = true;
        topXEnd = 0;
        topZEnd = Z_LENGTH;
    }

    /**
     * Build component
     */
    @Override
    public boolean addComponentParts(World world, Random random) {
        int metaBot = this.getMetadataWithOffset(Blocks.nether_brick_stairs, 2);
        int metaTop = this.getMetadataWithOffset(Blocks.stone_brick_stairs, 3) + 4;
        int top = boundingBox.maxY - boundingBox.minY - 1;
        int shiftY;
        int shiftZ;
        this.fillWithBlocks(world, boundingBox, 0, top + 1, 0, 4, top + 1, 0, Blocks.nether_brick, Blocks.nether_brick, false);

        for (int i = 0; i < 3; i++) {
            shiftZ = i * 4;
            shiftY = top - i * 4 + 1;
            this.fillWithAir(world, boundingBox, 1, shiftY - 4, shiftZ, 3, shiftY - 1, shiftZ + 1);
            this.fillWithAir(world, boundingBox, 1, shiftY - 5, shiftZ + 1, 3, shiftY - 2, shiftZ + 2);
            this.fillWithAir(world, boundingBox, 1, shiftY - 6, shiftZ + 2, 3, shiftY - 3, shiftZ + 3);
            this.fillWithAir(world, boundingBox, 1, shiftY - 7, shiftZ + 3, 3, shiftY - 4, shiftZ + 4);
            
            // nether walls
            this.fillWithBlocks(world, boundingBox, 0, shiftY - 4, shiftZ, 0, shiftY, shiftZ, Blocks.nether_brick, Blocks.nether_brick, false);
            this.fillWithBlocks(world, boundingBox, 4, shiftY - 4, shiftZ, 4, shiftY, shiftZ, Blocks.nether_brick, Blocks.nether_brick, false);
            
            // block walls
            this.fillWithRandomizedBlocks(world, boundingBox, 0, shiftY - 5, shiftZ + 1, 0, shiftY - 1, shiftZ + 1, false, random, getCemeteryCatacombsStones());
            this.fillWithRandomizedBlocks(world, boundingBox, 4, shiftY - 5, shiftZ + 1, 4, shiftY - 1, shiftZ + 1, false, random, getCemeteryCatacombsStones());
            this.fillWithRandomizedBlocks(world, boundingBox, 0, shiftY - 6, shiftZ + 2, 0, shiftY - 2, shiftZ + 2, false, random, getCemeteryCatacombsStones());
            this.fillWithRandomizedBlocks(world, boundingBox, 4, shiftY - 6, shiftZ + 2, 4, shiftY - 2, shiftZ + 2, false, random, getCemeteryCatacombsStones());
            this.fillWithRandomizedBlocks(world, boundingBox, 0, shiftY - 7, shiftZ + 3, 0, shiftY - 3, shiftZ + 3, false, random, getCemeteryCatacombsStones());
            this.fillWithRandomizedBlocks(world, boundingBox, 4, shiftY - 7, shiftZ + 3, 4, shiftY - 3, shiftZ + 3, false, random, getCemeteryCatacombsStones());
            
            // nether stairs
            this.fillWithMetadataBlocks(world, boundingBox, 1, shiftY - 4, shiftZ, 3, shiftY - 4, shiftZ, Blocks.nether_brick_stairs, metaBot, Blocks.nether_brick_stairs, metaBot, false);
            this.fillWithMetadataBlocks(world, boundingBox, 1, shiftY - 5, shiftZ + 1, 3, shiftY - 5, shiftZ + 1, Blocks.nether_brick_stairs, metaBot, Blocks.nether_brick_stairs, metaBot, false);
            this.fillWithMetadataBlocks(world, boundingBox, 1, shiftY - 6, shiftZ + 2, 3, shiftY - 6, shiftZ + 2, Blocks.nether_brick_stairs, metaBot, Blocks.nether_brick_stairs, metaBot, false);
            this.fillWithMetadataBlocks(world, boundingBox, 1, shiftY - 7, shiftZ + 3, 3, shiftY - 7, shiftZ + 3, Blocks.nether_brick_stairs, metaBot, Blocks.nether_brick_stairs, metaBot, false);
            
            Block stairsBlock = CatacombsLevel.getCatacombsStairsId(this.level);
            
            // block stairs
            this.fillWithMetadataBlocks(world, boundingBox, 1, shiftY - 1, shiftZ + 1, 3, shiftY - 1, shiftZ + 1, stairsBlock, metaTop, stairsBlock, metaTop, false);
            this.fillWithMetadataBlocks(world, boundingBox, 1, shiftY - 2, shiftZ + 2, 3, shiftY - 2, shiftZ + 2, stairsBlock, metaTop, stairsBlock, metaTop, false);
            this.fillWithMetadataBlocks(world, boundingBox, 1, shiftY - 3, shiftZ + 3, 3, shiftY - 3, shiftZ + 3, stairsBlock, metaTop, stairsBlock, metaTop, false);
            this.fillWithMetadataBlocks(world, boundingBox, 1, shiftY - 4, shiftZ + 4, 3, shiftY - 4, shiftZ + 4, stairsBlock, metaTop, stairsBlock, metaTop, false);
            
            // web
            this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 1, shiftY - 3, shiftZ, 1, shiftY - 3, shiftZ, Blocks.web, Blocks.web, false);
            this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 3, shiftY - 3, shiftZ + 1, 3, shiftY - 3, shiftZ + 1, Blocks.web, Blocks.web, false);
            this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 2, shiftY - 5, shiftZ + 2, 2, shiftY - 5, shiftZ + 2, Blocks.web, Blocks.web, false);
            this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 3, shiftY - 5, shiftZ + 3, 3, shiftY - 5, shiftZ + 3, Blocks.web, Blocks.web, false);
        }

        this.fillWithBlocks(world, boundingBox, 0, 0, Z_LENGTH - 1, 4, 0, Z_LENGTH - 1, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 0, Z_LENGTH - 1, 0, 4, Z_LENGTH - 1, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 4, 0, Z_LENGTH - 1, 4, 4, Z_LENGTH - 1, false, random, getCemeteryCatacombsStones());
        return true;
    }
}
