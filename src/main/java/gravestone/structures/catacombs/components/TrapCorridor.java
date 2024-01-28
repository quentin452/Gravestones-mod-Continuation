package gravestone.structures.catacombs.components;

import gravestone.core.GSBlock;
import gravestone.structures.BoundingBoxHelper;
import gravestone.structures.ObjectsGenerationHelper;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class TrapCorridor extends CatacombsBaseComponent {

    public static final int X_LENGTH = 6;
    public static final int HEIGHT = 5;
    public static final int Z_LENGTH = 5;

    public TrapCorridor(int direction, int level, Random random, int x, int y, int z) {
        super(direction, level);
        xShift = 1;
        boundingBox = BoundingBoxHelper.getCorrectBox(direction, x, y, z, X_LENGTH, HEIGHT, Z_LENGTH, xShift);
        topZEnd = Z_LENGTH - 1;
        topXEnd = 1;
    }

    /**
     * Build component
     */
    @Override
    public boolean addComponentParts(World world, Random random) {
        this.fillWithAir(world, boundingBox, 2, 1, 0, 4, 3, 3);

        // block floor
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 0, 1, 5, 0, 3, false, random, getCemeteryCatacombsStones());

        // trap floor
        this.fillWithBlocks(world, boundingBox, 1, 0, 0, 5, 0, 0, GSBlock.trap, GSBlock.trap, false);

        // neter ceiling
        this.fillWithBlocks(world, boundingBox, 1, 4, 0, 5, 4, 3, Blocks.nether_brick, Blocks.nether_brick, false);

        // block walls
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 1, 1, 1, 3, 3, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 5, 1, 1, 5, 3, 3, false, random, getCemeteryCatacombsStones());
        this.fillWithRandomizedBlocks(world, boundingBox, 1, 0, 4, 5, 4, 4, false, random, getCemeteryCatacombsStones());

        // nether walls
        this.fillWithBlocks(world, boundingBox, 1, 1, 0, 1, 3, 0, Blocks.nether_brick, Blocks.nether_brick, false);
        this.fillWithBlocks(world, boundingBox, 5, 1, 0, 5, 3, 0, Blocks.nether_brick, Blocks.nether_brick, false);

        // blocks
        this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 0, 1, 2, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.stonebrick, 0, 6, 1, 2, boundingBox);

        // tripWire hook
        this.placeBlockAtCurrentPosition(world, Blocks.tripwire_hook, getRightItemDirection(coordBaseMode), 1, 1, 2, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.tripwire_hook, getLeftItemDirection(coordBaseMode), 5, 1, 2, boundingBox);

        // tripWire
        this.fillWithBlocks(world, boundingBox, 2, 1, 2, 4, 1, 2, Blocks.tripwire, Blocks.tripwire, false);

        // dispencer
        ObjectsGenerationHelper.generateDispenser(world, this, random, 0, 2, 2, getLeftItemDirection(coordBaseMode));
        ObjectsGenerationHelper.generateDispenser(world, this, random, 6, 2, 2, getRightItemDirection(coordBaseMode));
        this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 1, 2, 2, boundingBox);
        this.placeBlockAtCurrentPosition(world, Blocks.air, 0, 5, 2, 2, boundingBox);

        // web
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 2, 1, 1, 2, 1, 1, Blocks.web, Blocks.web, false);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 4, 2, 2, 4, 2, 2, Blocks.web, Blocks.web, false);
        this.randomlyFillWithBlocks(world, boundingBox, random, 0.2F, 3, 3, 3, 3, 3, 3, Blocks.web, Blocks.web, false);

        return true;
    }
}
