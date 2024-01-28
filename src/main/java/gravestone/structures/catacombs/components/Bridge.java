package gravestone.structures.catacombs.components;

import gravestone.block.BlockGSGraveStone.EnumGraveType;
import gravestone.block.GraveStoneHelper;
import gravestone.structures.BoundingBoxHelper;
import gravestone.structures.GraveGenerationHelper;
import gravestone.structures.MobSpawnHelper;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class Bridge extends CatacombsBaseComponent {

    public static final int X_LENGTH = 13;
    public static final int HEIGHT = 14;
    public static final int Z_LENGTH = 7;

    public Bridge(int direction, int level, Random random, int x, int y, int z) {
        super(direction, level);
        xShift = 4;
        y = y - HEIGHT + 6;
        boundingBox = BoundingBoxHelper.getCorrectBox(direction, x, y, z, X_LENGTH, HEIGHT, Z_LENGTH, xShift);
        yEnd = 8;
        topXEnd = 4;
        topZEnd = Z_LENGTH;
        goTop = true;
    }

    /**
     * Build component
     */
    @Override
    public boolean addComponentParts(World world, Random random) {
        this.fillWithAir(world, boundingBox, 3, 3, 1, 9, 12, 6);
        this.fillWithAir(world, boundingBox, 1, 9, 1, 2, 10, 6);
        this.fillWithAir(world, boundingBox, 10, 9, 1, 11, 10, 6);
        // neter floor and ceiling
        this.fillWithBlocks(world, boundingBox, 2, 0, 0, 10, 0, 7, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 2, 13, 0, 10, 13, 7, Blocks.nether_brick, Blocks.nether_brick, false);
        // nether walls
        this.fillWithBlocks(world, boundingBox, 3, 1, 0, 9, 12, 0, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 3, 1, 7, 9, 12, 7, Blocks.nether_brick, Blocks.nether_brick, false);
        // block walls
        this.fillWithRandomizedBlocks(world, boundingBox, 2, 1, 0, 2, 8, 7, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 2, 11, 0, 2, 12, 7, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 10, 1, 0, 10, 8, 7, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 10, 11, 0, 10, 12, 7, false, random, getCemeteryCatacombsStones());
        // graves floor & ceilng
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 8, 0, 1, 8, 7, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 11, 0, 1, 11, 7, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 11, 8, 0, 11, 8, 7, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 11, 11, 0, 11, 11, 7, false, random, getCemeteryCatacombsStones());
        // graves walls
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 9, 0, 0, 10, 7, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 12, 9, 0, 12, 10, 7, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 9, 0, 2, 10, 0, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 9, 0, 2, 10, 0, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 9, 7, 2, 10, 7, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 9, 7, 2, 10, 7, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 10, 9, 0, 11, 10, 0, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 10, 9, 0, 11, 10, 0, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 10, 9, 7, 11, 10, 7, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 10, 9, 7, 11, 10, 7, false, random, getCemeteryCatacombsStones());
        // graves
        byte graveType = GraveStoneHelper.getGraveType(world, this.getXWithOffset(0, 0), this.getZWithOffset(0, 0), random, EnumGraveType.PLAYER_GRAVES);
        Item sword = GraveStoneHelper.getRandomSwordForGeneration(graveType, random);
        int metaLeft = GraveStoneHelper.getMetaDirection(getLeftItemDirection(coordBaseMode));
        int metaRight = GraveStoneHelper.getMetaDirection(getRightItemDirection(coordBaseMode));
        GraveGenerationHelper.fillGraves(this, world, random, 1, 9, 1, 1, 9, 6, metaLeft, graveType, sword, true);
        GraveGenerationHelper.fillGraves(this, world, random, 11, 9, 1, 11, 9, 6, metaRight, graveType, sword, true);
        // lava
        this.fillWithBlocks(world, boundingBox, 3, 1, 1, 9, 2, 6, Blocks.lava, Blocks.lava, false);
        // bridge
        this.fillWithMetadataBlocks(world, boundingBox, 6, 8, 1, 6, 8, 6, Blocks.stone_slab, 14, Blocks.stone_slab, 14, false);

        if (random.nextInt(10) < 4) {
            this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 6, 8, 5, boundingBox);
        }

        // block exit wall
        this.fillWithRandomizedBlocks(world, boundingBox, 5, 9, 7, 7, 11, 7, false, random, getCemeteryCatacombsStones());
        this.fillWithAir(world, boundingBox, 5, 9, 0, 7, 11, 0);
        // spawn bats
        MobSpawnHelper.spawnBats(world, random, boundingBox);
        return true;
    }
}
