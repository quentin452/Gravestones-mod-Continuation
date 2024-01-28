package gravestone.structures.catacombs.components;

import gravestone.block.BlockGSGraveStone;
import gravestone.block.GraveStoneHelper;
import gravestone.structures.BoundingBoxHelper;
import gravestone.structures.GraveGenerationHelper;
import gravestone.structures.ObjectsGenerationHelper;
import net.minecraft.block.Block;
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
public class Treasury extends CatacombsBaseComponent {

    public static final int X_LENGTH = 6;
    public static final int HEIGHT = 5;
    public static final int Z_LENGTH = 7;

    public Treasury(int direction, int level, Random random, int x, int y, int z) {
        super(direction, level);
        xShift = 1;
        boundingBox = BoundingBoxHelper.getCorrectBox(direction, x, y, z, X_LENGTH, HEIGHT, Z_LENGTH, xShift);
        goTop = false;
    }

    /**
     * Build component
     */
    @Override
    public boolean addComponentParts(World world, Random random) {
        this.fillWithAir(world, boundingBox, 1, 1, 2, 5, 3, 6);

        // block floor
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 0, 1, 6, 0, 7, false, random, getCemeteryCatacombsStones());

        //block ceiling
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 4, 1, 6, 4, 7, false, random, getCemeteryCatacombsStones());

        // block walls
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 1, 2, 0, 3, 6, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 6, 1, 2, 6, 3, 6, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 1, 1, 6, 3, 1, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 0, 1, 7, 6, 3, 7, false, random, getCemeteryCatacombsStones());

        // clear entrance
        this.fillWithAir(world, boundingBox, 2, 1, 1, 4, 3, 1);

        // block entrance
        this.fillWithRandomizedBlocks(world, boundingBox, 2, 1, 0, 4, 3, 0, false, random, getCemeteryCatacombsStones());

        // nether entrance
        this.fillWithBlocks(world, boundingBox, 1, 0, 0, 5, 0, 0, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 1, 4, 0, 5, 4, 0, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 1, 1, 0, 1, 3, 0, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 5, 1, 0, 5, 3, 0, Blocks.nether_brick, Blocks.nether_brick, false);

        // web
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.4F, 2, 2, 2, 2, 2, 2, Blocks.web, Blocks.web, false);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.4F, 4, 1, 3, 4, 1, 3, Blocks.web, Blocks.web, false);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.4F, 4, 3, 5, 4, 3, 5, Blocks.web, Blocks.web, false);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.4F, 2, 1, 4, 2, 1, 4, Blocks.web, Blocks.web, false);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.4F, 1, 2, 5, 1, 2, 5, Blocks.web, Blocks.web, false);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.4F, 2, 3, 6, 2, 3, 6, Blocks.web, Blocks.web, false);

        // graves
        byte graveType = GraveStoneHelper.getGraveType(world, this.getXWithOffset(0, 0), this.getZWithOffset(0, 0), random, BlockGSGraveStone.EnumGraveType.ALL_GRAVES);
        Item sword = GraveStoneHelper.getRandomSwordForGeneration(graveType, random);
        int metaLeft = GraveStoneHelper.getMetaDirection(getLeftItemDirection(coordBaseMode));
        int metaRight = GraveStoneHelper.getMetaDirection(getRightItemDirection(coordBaseMode));
        GraveGenerationHelper.placeGrave(this, world, random, 1, 1, 2, metaLeft, graveType, sword, true);
        GraveGenerationHelper.placeGrave(this, world, random, 1, 1, 4, metaLeft, graveType, sword, true);
        GraveGenerationHelper.placeGrave(this, world, random, 1, 1, 6, metaLeft, graveType, sword, true);
        GraveGenerationHelper.placeGrave(this, world, random, 5, 1, 2, metaRight, graveType, sword, true);
        GraveGenerationHelper.placeGrave(this, world, random, 5, 1, 4, metaRight, graveType, sword, true);
        GraveGenerationHelper.placeGrave(this, world, random, 5, 1, 6, metaRight, graveType, sword, true);

        // TNT
        this.fillWithBlocks(world, boundingBox, 0, 0, 3, 1, 0, 3, Blocks.tnt, Blocks.tnt, false);
        this.fillWithBlocks(world, boundingBox, 0, 0, 5, 1, 0, 5, Blocks.tnt, Blocks.tnt, false);
        this.placeBlockAtCurrentPosition(world, Blocks.tnt, 0, 0, 0, 4, boundingBox);
        this.fillWithBlocks(world, boundingBox, 5, 0, 3, 6, 0, 3, Blocks.tnt, Blocks.tnt, false);
        this.fillWithBlocks(world, boundingBox, 5, 0, 5, 6, 0, 5, Blocks.tnt, Blocks.tnt, false);
        this.placeBlockAtCurrentPosition(world, Blocks.tnt, 0, 6, 0, 4, boundingBox);
        this.fillWithBlocks(world, boundingBox, 3, 0, 6, 3, 0, 7, Blocks.tnt, Blocks.tnt, false);

        // treasury chests
        ObjectsGenerationHelper.generateChest(this, world, random, 1, 1, 3, false, ObjectsGenerationHelper.EnumChestTypes.VALUABLE_CHESTS);
        ObjectsGenerationHelper.generateChest(this, world, random, 1, 1, 5, false, ObjectsGenerationHelper.EnumChestTypes.VALUABLE_CHESTS);
        ObjectsGenerationHelper.generateChest(this, world, random, 5, 1, 3, false, ObjectsGenerationHelper.EnumChestTypes.VALUABLE_CHESTS);
        ObjectsGenerationHelper.generateChest(this, world, random, 5, 1, 5, false, ObjectsGenerationHelper.EnumChestTypes.VALUABLE_CHESTS);
        ObjectsGenerationHelper.generateChest(this, world, random, 3, 1, 6, false, ObjectsGenerationHelper.EnumChestTypes.VALUABLE_CHESTS);

        // treasury column
        Block valuableBlock = getValuableBlock(random);
        this.fillWithBlocks(world, boundingBox, 3, 1, 4, 3, 3, 4, valuableBlock, valuableBlock, false);

        return true;
    }
}
