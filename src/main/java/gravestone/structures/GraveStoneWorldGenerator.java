package gravestone.structures;

import cpw.mods.fml.common.IWorldGenerator;
import gravestone.config.GraveStoneConfig;
import gravestone.structures.catacombs.CatacombsGenerator;
import gravestone.structures.graves.SingleGraveGenerator;
import gravestone.structures.memorials.MemorialGenerator;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GraveStoneWorldGenerator implements IWorldGenerator {

    public static final int DEFAULT_DIMENSION_ID = 0;

    public GraveStoneWorldGenerator() {
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.dimensionId == GraveStoneConfig.structuresDimensionId) {
            generateSurface(world, random, chunkX * 16, chunkZ * 16);
        }
    }

    public void generateSurface(World world, Random rand, int x, int z) {
        double chance = rand.nextDouble();

        if (!CatacombsGenerator.getInstance().generate(world, rand, x, z, chance, false)) {
            if (!MemorialGenerator.getInstance().generate(world, rand, x, z, chance, false)) {
                SingleGraveGenerator.getInstance().generate(world, rand, x, z, chance, false);
            }
        }
    }
}
