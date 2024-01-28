package gravestone.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gravestone.block.enums.EnumSpawner;
import gravestone.config.GraveStoneConfig;
import gravestone.core.GSBlock;
import gravestone.core.GSTabs;
import gravestone.core.Resources;
import gravestone.particle.EntityGreenFlameFX;
import gravestone.tileentity.TileEntityGSSpawner;
import net.minecraft.block.Block;
import net.minecraft.block.BlockMobSpawner;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BlockGSSpawner extends BlockMobSpawner {

    public static final List<Byte> MOB_SPAWNERS = new ArrayList<Byte>(Arrays.asList(
            (byte) EnumSpawner.SKELETON_SPAWNER.ordinal(),
            (byte) EnumSpawner.ZOMBIE_SPAWNER.ordinal()));
    public static final List<Byte> BOSS_SPAWNERS = new ArrayList<Byte>(Arrays.asList(
            (byte) EnumSpawner.WITHER_SPAWNER.ordinal()));

    public BlockGSSpawner() {
        super();
        this.setBlockName("Spawner");
        this.setHardness(5.0F);
        this.setLightLevel(0.45F);
        this.setStepSound(Block.soundTypeMetal);
        this.disableStats();
        this.setCreativeTab(GSTabs.otherItemsTab);
        this.setBlockTextureName(Resources.PENTAGRAM_ICO);
        this.setBlockBounds(-0.5F, 0, -0.5F, 1.5F, 0.05F, 1.5F);
        this.setHarvestLevel("pickaxe", 1);
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing
     * the block.
     */
    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
        return new TileEntityGSSpawner();
    }

    /**
     * The type of render function that is called for this block
     */
    @Override
    public int getRenderType() {
        return GraveStoneConfig.spawnerRenderID;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube? This determines whether
     * or not to render the shared face of two adjacent blocks and also whether
     * the player can attach torches, redstone wire, etc to this block.
     */
    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return null;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    /**
     * A randomly called display update to be able to add particles or other
     * items for display
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        double xPos = x + 0.5F;
        double yPos = y + 0.85;
        double zPos = z + 0.5F;
        double dRotation = Math.toRadians(72);
        double rotation = Math.toRadians(-36);
        double d = 1.07;
        double dx;
        double dz;

        for (int i = 0; i < 5; i++) {
            dx = -Math.sin(rotation) * d;
            dz = Math.cos(rotation) * d;
            world.spawnParticle("smoke", xPos + dx, yPos, zPos + dz, 0, 0, 0);
            EntityFX entityfx = new EntityGreenFlameFX(world, xPos + dx, yPos, zPos + dz, 0, 0, 0);
            Minecraft.getMinecraft().effectRenderer.addEffect(entityfx);
            rotation += dRotation;
        }
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(getItemDropped(metadata, world.rand, fortune), quantityDropped(world.rand), getItemMeta(metadata)));

        for (int i = 0; i < 5; i++) {
            if ((fortune > 0 && world.rand.nextInt(100) < 5 * fortune) ||
                    world.rand.nextInt(100) < 5 * fortune) {
                ret.add(getCustomItemsDropped(metadata));
            }
        }
        return ret;
    }

    public ItemStack getCustomItemsDropped(int meta) {
        switch (meta) {
            case 1:
                return new ItemStack(GSBlock.skullCandle, 1, 0);
            case 2:
                return new ItemStack(GSBlock.skullCandle, 1, 2);
            case 0:
            default:
                return new ItemStack(GSBlock.skullCandle, 1, 1);
        }
    }


    public int getItemMeta(int metadata) {
        return 15;
    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        return Items.dye;
    }

    @Override
    public int quantityDropped(Random random) {
        return 3;
    }

    /**
     * Returns a list of blocks with the same ID, but different meta (eg: wood
     * returns 4 blocks)
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (byte meta : MOB_SPAWNERS) {
            list.add(new ItemStack(item, 1, meta));
        }
        for (byte meta : BOSS_SPAWNERS) {
            list.add(new ItemStack(item, 1, meta));
        }
    }
}
