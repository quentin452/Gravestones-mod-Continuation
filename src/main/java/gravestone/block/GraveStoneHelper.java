package gravestone.block;

import gravestone.block.enums.EnumGraves;
import gravestone.config.GraveStoneConfig;
import gravestone.core.GSBlock;
import gravestone.core.GSMobSpawn;
import gravestone.core.compatibility.*;
import gravestone.core.logger.GSLogger;
import gravestone.inventory.GraveInventory;
import gravestone.item.corpse.CorpseHelper;
import gravestone.item.enums.EnumCorpse;
import gravestone.tileentity.DeathMessageInfo;
import gravestone.tileentity.TileEntityGSGraveStone;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import java.util.*;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GraveStoneHelper {
    public static ArrayList<Item> swordsList = new ArrayList<Item>(
            Arrays.asList(
                    Items.wooden_sword,
                    Items.stone_sword,
                    Items.iron_sword,
                    Items.golden_sword,
                    Items.diamond_sword
            )
    );

    public static final Item[] GENERATED_SWORD_GRAVES = {
            Items.wooden_sword,
            Items.stone_sword
    };

    public static final EnumGraves[] GENERATED_WOODEN_GRAVES = {
            EnumGraves.WOODEN_VERTICAL_PLATE,
            EnumGraves.WOODEN_CROSS,
            EnumGraves.WOODEN_HORISONTAL_PLATE
    };
    public static final EnumGraves[] GENERATED_SANDSTONE_GRAVES = {
            EnumGraves.SANDSTONE_VERTICAL_PLATE,
            EnumGraves.SANDSTONE_CROSS,
            EnumGraves.SANDSTONE_HORISONTAL_PLATE
    };
    public static final EnumGraves[] GENERATED_STONE_GRAVES = {
            EnumGraves.STONE_VERTICAL_PLATE,
            EnumGraves.STONE_CROSS,
            EnumGraves.STONE_HORISONTAL_PLATE
    };
    public static final EnumGraves[] GENERATED_MOSSY_GRAVES = {
            EnumGraves.MOSSY_VERTICAL_PLATE,
            EnumGraves.MOSSY_CROSS,
            EnumGraves.MOSSY_HORISONTAL_PLATE
    };
    public static final EnumGraves[] GENERATED_IRON_GRAVES = {
            EnumGraves.IRON_VERTICAL_PLATE,
            EnumGraves.IRON_CROSS,
            EnumGraves.IRON_HORISONTAL_PLATE
    };
    public static final EnumGraves[] GENERATED_GOLDEN_GRAVES = {
            EnumGraves.GOLDEN_VERTICAL_PLATE,
            EnumGraves.GOLDEN_CROSS,
            EnumGraves.GOLDEN_HORISONTAL_PLATE
    };
    public static final EnumGraves[] GENERATED_DIAMOND_GRAVES = {
            EnumGraves.DIAMOND_VERTICAL_PLATE,
            EnumGraves.DIAMOND_CROSS,
            EnumGraves.DIAMOND_HORISONTAL_PLATE
    };
    public static final EnumGraves[] GENERATED_EMERALD_GRAVES = {
            EnumGraves.EMERALD_VERTICAL_PLATE,
            EnumGraves.EMERALD_CROSS,
            EnumGraves.EMERALD_HORISONTAL_PLATE
    };
    public static final EnumGraves[] GENERATED_LAPIS_GRAVES = {
            EnumGraves.LAPIS_VERTICAL_PLATE,
            EnumGraves.LAPIS_CROSS,
            EnumGraves.LAPIS_HORISONTAL_PLATE
    };
    public static final EnumGraves[] GENERATED_REDSTONE_GRAVES = {
            EnumGraves.REDSTONE_VERTICAL_PLATE,
            EnumGraves.REDSTONE_CROSS,
            EnumGraves.REDSTONE_HORISONTAL_PLATE
    };
    public static final EnumGraves[] GENERATED_OBSIDIAN_GRAVES = {
            EnumGraves.OBSIDIAN_VERTICAL_PLATE,
            EnumGraves.OBSIDIAN_CROSS,
            EnumGraves.OBSIDIAN_HORISONTAL_PLATE
    };
    public static final EnumGraves[] GENERATED_QUARTZ_GRAVES = {
            EnumGraves.QUARTZ_VERTICAL_PLATE,
            EnumGraves.QUARTZ_CROSS,
            EnumGraves.QUARTZ_HORISONTAL_PLATE
    };
    public static final EnumGraves[] GENERATED_ICE_GRAVES = {
            EnumGraves.ICE_VERTICAL_PLATE,
            EnumGraves.ICE_CROSS,
            EnumGraves.ICE_HORISONTAL_PLATE
    };
    public static final EnumGraves[] PETS_GRAVES = {
            EnumGraves.WOODEN_DOG_STATUE,
            EnumGraves.WOODEN_CAT_STATUE,
            EnumGraves.SANDSTONE_DOG_STATUE,
            EnumGraves.SANDSTONE_CAT_STATUE,
            EnumGraves.STONE_DOG_STATUE,
            EnumGraves.STONE_CAT_STATUE,
            EnumGraves.MOSSY_DOG_STATUE,
            EnumGraves.MOSSY_CAT_STATUE,
            EnumGraves.IRON_DOG_STATUE,
            EnumGraves.IRON_CAT_STATUE,
            EnumGraves.GOLDEN_DOG_STATUE,
            EnumGraves.GOLDEN_CAT_STATUE,
            EnumGraves.DIAMOND_DOG_STATUE,
            EnumGraves.DIAMOND_CAT_STATUE,
            EnumGraves.EMERALD_DOG_STATUE,
            EnumGraves.EMERALD_CAT_STATUE,
            EnumGraves.LAPIS_DOG_STATUE,
            EnumGraves.LAPIS_CAT_STATUE,
            EnumGraves.REDSTONE_DOG_STATUE,
            EnumGraves.REDSTONE_CAT_STATUE,
            EnumGraves.OBSIDIAN_DOG_STATUE,
            EnumGraves.OBSIDIAN_CAT_STATUE,
            EnumGraves.QUARTZ_DOG_STATUE,
            EnumGraves.QUARTZ_CAT_STATUE,
            EnumGraves.ICE_DOG_STATUE,
            EnumGraves.ICE_CAT_STATUE
    };
    public static final byte[] DOGS_GRAVES = {
            (byte) EnumGraves.WOODEN_DOG_STATUE.ordinal(),
            (byte) EnumGraves.SANDSTONE_DOG_STATUE.ordinal(),
            (byte) EnumGraves.STONE_DOG_STATUE.ordinal(),
            (byte) EnumGraves.MOSSY_DOG_STATUE.ordinal(),
            (byte) EnumGraves.IRON_DOG_STATUE.ordinal(),
            (byte) EnumGraves.GOLDEN_DOG_STATUE.ordinal(),
            (byte) EnumGraves.DIAMOND_DOG_STATUE.ordinal(),
            (byte) EnumGraves.EMERALD_DOG_STATUE.ordinal(),
            (byte) EnumGraves.LAPIS_DOG_STATUE.ordinal(),
            (byte) EnumGraves.REDSTONE_DOG_STATUE.ordinal(),
            (byte) EnumGraves.OBSIDIAN_DOG_STATUE.ordinal(),
            (byte) EnumGraves.QUARTZ_DOG_STATUE.ordinal(),
            (byte) EnumGraves.ICE_DOG_STATUE.ordinal()
    };
    public static final EnumGraves[] CATS_GRAVES = {
            EnumGraves.WOODEN_CAT_STATUE,
            EnumGraves.SANDSTONE_CAT_STATUE,
            EnumGraves.STONE_CAT_STATUE,
            EnumGraves.MOSSY_CAT_STATUE,
            EnumGraves.IRON_CAT_STATUE,
            EnumGraves.GOLDEN_CAT_STATUE,
            EnumGraves.DIAMOND_CAT_STATUE,
            EnumGraves.EMERALD_CAT_STATUE,
            EnumGraves.LAPIS_CAT_STATUE,
            EnumGraves.REDSTONE_CAT_STATUE,
            EnumGraves.OBSIDIAN_CAT_STATUE,
            EnumGraves.QUARTZ_CAT_STATUE,
            EnumGraves.ICE_CAT_STATUE
    };
    public static final EnumGraves[] DOG_WOODEN_GRAVES = {EnumGraves.WOODEN_DOG_STATUE};
    public static final EnumGraves[] DOG_SANDSTONE_GRAVES = {EnumGraves.SANDSTONE_DOG_STATUE};
    public static final EnumGraves[] DOG_STONE_GRAVES = {EnumGraves.STONE_DOG_STATUE};
    public static final EnumGraves[] DOG_MOSSY_GRAVES = {EnumGraves.MOSSY_DOG_STATUE};
    public static final EnumGraves[] DOG_GOLDEN_GRAVES = {EnumGraves.GOLDEN_DOG_STATUE};
    public static final EnumGraves[] DOG_DIAMOND_GRAVES = {EnumGraves.DIAMOND_DOG_STATUE};
    public static final EnumGraves[] DOG_OBSIDIAN_GRAVES = {EnumGraves.OBSIDIAN_DOG_STATUE};
    public static final EnumGraves[] DOG_QUARTZ_GRAVES = {EnumGraves.QUARTZ_DOG_STATUE};
    public static final EnumGraves[] DOG_ICE_GRAVES = {EnumGraves.ICE_DOG_STATUE};

    public static final EnumGraves[] CAT_WOODEN_GRAVES = {EnumGraves.WOODEN_CAT_STATUE};
    public static final EnumGraves[] CAT_SANDSTONE_GRAVES = {EnumGraves.SANDSTONE_CAT_STATUE};
    public static final EnumGraves[] CAT_STONE_GRAVES = {EnumGraves.STONE_CAT_STATUE};
    public static final EnumGraves[] CAT_MOSSY_GRAVES = {EnumGraves.MOSSY_CAT_STATUE};
    public static final EnumGraves[] CAT_GOLDEN_GRAVES = {EnumGraves.GOLDEN_CAT_STATUE};
    public static final EnumGraves[] CAT_DIAMOND_GRAVES = {EnumGraves.DIAMOND_CAT_STATUE};
    public static final EnumGraves[] CAT_OBSIDIAN_GRAVES = {EnumGraves.OBSIDIAN_CAT_STATUE};
    public static final EnumGraves[] CAT_QUARTZ_GRAVES = {EnumGraves.QUARTZ_CAT_STATUE};
    public static final EnumGraves[] CAT_ICE_GRAVES = {EnumGraves.ICE_CAT_STATUE};

    public static final EnumGraves[] HORSE_WOODEN_GRAVES = {EnumGraves.WOODEN_HORSE_STATUE};
    public static final EnumGraves[] HORSE_SANDSTONE_GRAVES = {EnumGraves.SANDSTONE_HORSE_STATUE};
    public static final EnumGraves[] HORSE_STONE_GRAVES = {EnumGraves.STONE_HORSE_STATUE};
    public static final EnumGraves[] HORSE_MOSSY_GRAVES = {EnumGraves.MOSSY_HORSE_STATUE};
    public static final EnumGraves[] HORSE_OBSIDIAN_GRAVES = {EnumGraves.OBSIDIAN_HORSE_STATUE};
    public static final EnumGraves[] HORSE_QUARTZ_GRAVES = {EnumGraves.QUARTZ_HORSE_STATUE};
    public static final EnumGraves[] HORSE_ICE_GRAVES = {EnumGraves.ICE_HORSE_STATUE};


    public static final List<Block> FLOWERS_GROUND = Arrays.asList(
            Blocks.grass, Blocks.dirt);
    public static final List<BlockFlower> FLOWERS = Arrays.asList(
            Blocks.yellow_flower, Blocks.red_flower);
    public static final List<EnumGraves> FLOWER_GRAVES = Arrays.asList(
            EnumGraves.WOODEN_VERTICAL_PLATE,
            EnumGraves.WOODEN_CROSS,
            EnumGraves.SANDSTONE_VERTICAL_PLATE,
            EnumGraves.SANDSTONE_CROSS,
            EnumGraves.STONE_VERTICAL_PLATE,
            EnumGraves.STONE_CROSS,
            EnumGraves.MOSSY_VERTICAL_PLATE,
            EnumGraves.MOSSY_CROSS,
            EnumGraves.IRON_VERTICAL_PLATE,
            EnumGraves.IRON_CROSS,
            EnumGraves.GOLDEN_VERTICAL_PLATE,
            EnumGraves.GOLDEN_CROSS,
            EnumGraves.DIAMOND_VERTICAL_PLATE,
            EnumGraves.DIAMOND_CROSS,
            EnumGraves.EMERALD_VERTICAL_PLATE,
            EnumGraves.EMERALD_CROSS,
            EnumGraves.LAPIS_VERTICAL_PLATE,
            EnumGraves.LAPIS_CROSS,
            EnumGraves.REDSTONE_VERTICAL_PLATE,
            EnumGraves.REDSTONE_CROSS,
            EnumGraves.OBSIDIAN_VERTICAL_PLATE,
            EnumGraves.OBSIDIAN_CROSS,
            EnumGraves.QUARTZ_VERTICAL_PLATE,
            EnumGraves.QUARTZ_CROSS,
            EnumGraves.ICE_VERTICAL_PLATE,
            EnumGraves.ICE_CROSS
    );

    private GraveStoneHelper() {
    }

    /**
     * Check is there sword in your inventory
     */
    // TODO
    public static ItemStack oldCheckSword(List<ItemStack> items) {
        if (items != null) {
            for (ItemStack stack : items) {
                if (stack != null && swordsList.contains(stack.getItem())) {
                    ItemStack sword = stack.copy();
                    items.remove(stack);
                    return sword;
                }
            }
        }

        return null;
    }

    /**
     * Check is grave - sword grave
     *
     * @param graveType Grave type
     */
    // TODO
    public static boolean isSwordGrave(byte graveType) {
        return graveType == EnumGraves.SWORD.ordinal();
    }

    /**
     * Return random grave type
     *
     * @param random
     * @param graveType
     */
    public static byte getGraveType(World world, int x, int z, Random random, BlockGSGraveStone.EnumGraveType graveType) {
        ArrayList<EnumGraves> petsGravesList;
        switch (graveType) {
            case PLAYER_GRAVES:
                if (random.nextFloat() > 0.1) {
                    return getRandomGrave(getPlayerGraveTypes(world, x, z), random);
                } else {
                    return (byte) EnumGraves.SWORD.ordinal();
                }
            case PETS_GRAVES:
                petsGravesList = new ArrayList<EnumGraves>();
                petsGravesList.addAll(getDogGraveTypes(world, x, z));
                petsGravesList.addAll(getCatGraveTypes(world, x, z));
                return getRandomGrave(petsGravesList, random);
            case DOGS_GRAVES:
                return getRandomGrave(getDogGraveTypes(world, x, z), random);
            case CATS_GRAVES:
                return getRandomGrave(getCatGraveTypes(world, x, z), random);
            case ALL_GRAVES:
            default:
                if (random.nextFloat() > 0.2) {
                    if (random.nextFloat() > 0.1) {
                        return getRandomGrave(getPlayerGraveTypes(world, x, z), random);
                    } else {
                        return (byte) EnumGraves.SWORD.ordinal();
                    }
                } else {
                    petsGravesList = new ArrayList<EnumGraves>();
                    petsGravesList.addAll(getDogGraveTypes(world, x, z));
                    petsGravesList.addAll(getCatGraveTypes(world, x, z));
                    return getRandomGrave(petsGravesList, random);
                }
        }
    }

    /**
     * Return grave metadata by direction
     */
    public static int getMetaDirection(int direction) {
        switch (direction) {
            case 0: // S
                return 1;
            case 1: // W
                return 2;
            case 2: // N
                return 0;
            case 3: // E
                return 3;
            default:
                return 0;
        }
    }

    /**
     * Check is grave - pet grave
     *
     * @param graveType Grave type
     */
    public static boolean isPetGrave(byte graveType) {
        // TODO rework
        return Arrays.binarySearch(PETS_GRAVES, EnumGraves.getByID(graveType)) >= 0;
    }

    // TODO
    public static byte oldGraveTypeToSwordType(byte graveType) {
        return (byte) (graveType - 4);
    }

    /**
     * Check ground type and replace it on dirt if it grass or mycelium
     */
    public static void replaceGround(World world, int x, int y, int z) {
        Block botBlock = world.getBlock(x, y, z);

        if (botBlock.equals(Blocks.grass) || botBlock.equals(Blocks.mycelium)) {
            world.setBlock(x, y, z, Blocks.dirt);
        }
    }

    /**
     * Spawn mob
     */
    public static void spawnMob(World world, int x, int y, int z) {
        if (GraveStoneConfig.spawnMobAtGraveDestruction && world.rand.nextInt(10) == 0) {
            TileEntityGSGraveStone tileEntity = (TileEntityGSGraveStone) world.getTileEntity(x, y, z);

            if (tileEntity != null) {
                Entity mob = GSMobSpawn.getMobEntity(world, tileEntity.getGraveType(), x, y, z);

                if (mob != null) {
                    GSMobSpawn.spawnMob(world, mob, x, y, z, false);
                }
            }
        }
    }

    /**
     * Check can be grave placed on this type of surface
     */
    public static boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return canPlaceBlockAt(world, world.getBlock(x, y, z), x, y, z);
    }

    public static boolean canPlaceBlockAt(World world, Block block, int x, int y, int z) {
        if (GraveStoneConfig.canPlaceGravesEveryWhere) {
            return true;
        } else {
            int meta = world.getBlockMetadata(x, y, z);
            String tool = block.getHarvestTool(meta);
            if (tool != null) {
                return tool.equals("shovel");
            } else {
                return false;
            }
        }
    }

    public static int getMetadataBasedOnRotation(int rotation) {
        if (rotation >= 315 || rotation < 45) {
            return 1;
        } else if (rotation >= 45 && rotation < 135) {
            return 2;
        } else if (rotation >= 135 && rotation < 225) {
            return 0;
        } else {
            return 3;
        }
    }

    public static int[] findPlaceForGrave(World world, int x, int y, int z) {
        boolean canGenerateGrave = false;
        int[] coordinates = new int[3];

        int newY = getGround(world, x, y, z);

        if (canGenerateGraveAtCoordinates(world, x, newY, z)) {
            coordinates[0] = x;
            coordinates[1] = newY;
            coordinates[2] = z;
            return coordinates;
        } else {
            int dx = 1;
            int dz = 1;

            while (Math.abs(dx) < 9 && Math.abs(dz) < 9) {
                if (dx < 0) {
                    for (int newX = x - 1; newX >= x + dx; newX--) {
                        newY = getGround(world, newX, y, z);
                        if (canGenerateGraveAtCoordinates(world, newX, newY, z)) {
                            coordinates[0] = newX;
                            coordinates[1] = newY;
                            coordinates[2] = z;
                            return coordinates;
                        }
                    }
                } else {
                    for (int newX = x + 1; newX <= x + dx; newX++) {
                        newY = getGround(world, newX, y, z);
                        if (canGenerateGraveAtCoordinates(world, newX, newY, z)) {
                            coordinates[0] = newX;
                            coordinates[1] = newY;
                            coordinates[2] = z;
                            return coordinates;
                        }
                    }
                }
                x += dx;

                if (dz < 0) {
                    for (int newZ = z - 1; newZ >= z + dz; newZ--) {
                        newY = getGround(world, x, y, newZ);
                        if (canGenerateGraveAtCoordinates(world, x, newY, newZ)) {
                            coordinates[0] = x;
                            coordinates[1] = newY;
                            coordinates[2] = newZ;
                            return coordinates;
                        }
                    }
                } else {
                    for (int newZ = z + 1; newZ <= z + dz; newZ++) {
                        newY = getGround(world, x, y, newZ);
                        if (canGenerateGraveAtCoordinates(world, x, newY, newZ)) {
                            coordinates[0] = x;
                            coordinates[1] = newY;
                            coordinates[2] = newZ;
                            return coordinates;
                        }
                    }
                }
                z += dz;

                if (dx < 0) {
                    dx = Math.abs(dx) + 1;
                } else {
                    dx = (dx + 1) * -1;
                }

                if (dz < 0) {
                    dz = Math.abs(dz) + 1;
                } else {
                    dz = (dz + 1) * -1;
                }

            }
        }

        return null;
    }

    private static int getGround(World world, int x, int y, int z) {
        while ((world.isAirBlock(x, y - 1, z) || world.getBlock(x, y - 1, z).getMaterial().isLiquid() ||
                world.getBlock(x, y - 1, z).getMaterial().isReplaceable()) && y > 1) {
            y--;
        }
        return y;
    }

    private static boolean canGenerateGraveAtCoordinates(World world, int x, int y, int z) {
        return world.getBlock(x, y - 1, z).getMaterial().isSolid() &&
                (world.isAirBlock(x, y, z) || world.getBlock(x, y, z).getMaterial().isLiquid() || world.getBlock(x, y, z).getMaterial().isReplaceable());
    }

    public static void createPlayerGrave(EntityPlayer player, LivingDeathEvent event, long spawnTime) {
        if (player.worldObj != null && !player.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory") && GraveStoneConfig.graveItemsCount > 0 &&
                !isInRestrictedArea(player.worldObj, Vec3.createVectorHelper(player.posX, player.posY, player.posZ))) {
            List<ItemStack> items = new LinkedList<ItemStack>();

            GSCompatibilityAntiqueAtlas.placeDeathMarkerAtDeath(player);

            items.addAll(Arrays.asList(player.inventory.mainInventory));
            items.addAll(Arrays.asList(player.inventory.armorInventory));

            GSCompatibilityTwilightForest.addSlotTags(items);

            GSCompatibilityBattlegear.addItems(items, player);

            if (!GSCompatibilityTwilightForest.handleCharmsOfKeeping(items, player)) {
                player.inventory.clearInventory(null, -1);
            }

            GSCompatibilityTheCampingMod.addItems(items, player);
            GSCompatibilityBaubles.addItems(items, player);
            GSCompatibilityMariculture.addItems(items, player);
            GSCompatibilityTinkerConstruct.addItems(items, player);
            GSCompatibilityRpgInventory.addItems(items, player);
            GSCompatibilityGalacticraft.addItems(items, player);
            GSCompatibilityBackpacksMod.addItems(items, player);

            GSCompatibilityisArsMagica.getSoulboundItemsBack(items, player);
            GSCompatibilityEnderIO.getSoulboundItemsBack(items, player);

            //TODO is it really required??
            GSCompatibilityTwilightForest.removeSlotTags(items);

            createGrave(player, event, items, BlockGSGraveStone.EnumGraveType.PLAYER_GRAVES, false, spawnTime);
        } else {
            createGrave(player, event, null, BlockGSGraveStone.EnumGraveType.PLAYER_GRAVES, false, spawnTime);
        }
    }

    public static void createGrave(Entity entity, LivingDeathEvent event, List<ItemStack> items, BlockGSGraveStone.EnumGraveType entityType, boolean isVillager, long spawnTime) {
        Vec3 position = Vec3.createVectorHelper(entity.posX, entity.posY, entity.posZ);
        if (isInRestrictedArea(entity.worldObj, position)) {
            GSLogger.logInfo("Can't generate " + entity.getCommandSenderName() + "'s grave in restricted area. " + position.toString());
            if (items != null) {
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i) != null) {
                        GraveInventory.dropItem(items.get(i), entity.worldObj, (int) entity.posX, (int) entity.posY, (int) entity.posZ);
                    }
                }
            }
        } else {
            int age = (int) (entity.worldObj.getWorldTime() - spawnTime) / 24000;
            GSBlock.graveStone.createOnDeath(entity, entity.worldObj, (int) entity.posX, (int) entity.posY, (int) entity.posZ - 1,
                    getDeathMessage((EntityLivingBase) entity, event.source.damageType, isVillager),
                    MathHelper.floor_float(entity.rotationYaw), items, age, entityType, event.source);
        }
    }

    public static void createPetGrave(Entity entity, LivingDeathEvent event, long spawnTime) {
        EntityTameable pet = (EntityTameable) entity;

        if (pet.isTamed()) {
            if (pet instanceof EntityWolf) {
                createGrave(entity, event, CorpseHelper.getCorpse(entity, EnumCorpse.DOG), BlockGSGraveStone.EnumGraveType.DOGS_GRAVES, false, spawnTime);
            } else if (pet instanceof EntityOcelot) {
                createGrave(entity, event, CorpseHelper.getCorpse(entity, EnumCorpse.CAT), BlockGSGraveStone.EnumGraveType.CATS_GRAVES, false, spawnTime);
            }
        }
    }

    public static void createHorseGrave(EntityHorse horse, LivingDeathEvent event, long spawnTime) {
        if (horse.isTame()) {
            List<ItemStack> items = new ArrayList<ItemStack>();
            items.addAll(CorpseHelper.getCorpse(horse, EnumCorpse.HORSE));
            items.addAll(getHorseItems(horse));

            createGrave(horse, event, items, BlockGSGraveStone.EnumGraveType.HORSE_GRAVES, false, spawnTime);
        }
    }

    private static List<ItemStack> getHorseItems(EntityHorse horse) {
        List<ItemStack> items = new ArrayList<ItemStack>();

        NBTTagCompound nbt = new NBTTagCompound();
        horse.writeEntityToNBT(nbt);
        NBTTagList nbtItemsList = nbt.getTagList("Items", 10);

//        if (horse.isHorseSaddled()) {
//            items.add(new ItemStack(Items.saddle));
//            nbt.removeTag("SaddleItem");
//        }
//        if (nbt.hasKey("ArmorItem", 10)) {
//            items.add(ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("ArmorItem")));
//            nbt.setTag("ArmorItem", new ItemStack(Blocks.air).writeToNBT(new NBTTagCompound()));
//        }
        if (horse.isChested()) {
            for (int i = 0; i < nbtItemsList.tagCount(); i++) {
                items.add(ItemStack.loadItemStackFromNBT(nbtItemsList.getCompoundTagAt(i)));
            }

            items.add(new ItemStack(Blocks.chest));
        }

        // new chest inventory
        nbtItemsList = new NBTTagList();
        for (int slot = 2; slot < 17; slot++) {
            NBTTagCompound nbtTagCompound = new NBTTagCompound();
            nbtTagCompound.setByte("Slot", (byte) slot);
            new ItemStack(Blocks.air).writeToNBT(nbtTagCompound);
            nbtItemsList.appendTag(nbtTagCompound);
        }

        nbt.removeTag("Items");
        nbt.setTag("Items", nbtItemsList);
        horse.readEntityFromNBT(nbt);

        // must be invoked after "readEntityFromNBT" otherwise items in chest will not be cleared
        horse.setChested(false);

        return items;
    }

    private static DeathMessageInfo getDeathMessage(EntityLivingBase entity, String damageType, boolean isVillager) {
        EntityLivingBase killer = entity.func_94060_bK();
        String shortString = "death.attack." + damageType;
        String fullString = shortString + ".player";

        String entityName = entity.getCommandSenderName();
        if (entityName == null) {
            entityName = "entity." + EntityList.getEntityString(entity) + ".name";
        }

        if (killer != null) {
            String killerName;
            if (killer instanceof EntityPlayer) {
                killerName = ((EntityPlayer) killer).getDisplayName();
                if (isVillager) {
                    GSLogger.logInfoGrave("Villager was killed by " + killerName);
                }
            } else {
                killerName = EntityList.getEntityString(killer);
                if (killerName == null) {
                    killerName = "entity.generic.name";
                } else {
                    killerName = "entity." + killerName + ".name";
                }
            }
            if (StatCollector.canTranslate(fullString)) {
                return new DeathMessageInfo(entityName, fullString, killerName);
            } else {
                return new DeathMessageInfo(entityName, shortString, killerName);
            }
        } else {
            return new DeathMessageInfo(entityName, shortString, null);
        }
    }

    public static void addSwordInfo(NBTTagCompound nbt, ItemStack sword) {
        NBTTagCompound swordNBT = new NBTTagCompound();
        sword.writeToNBT(swordNBT);
        nbt.setTag("Sword", swordNBT);
    }

    public static ItemStack getSwordAsGrave(Item grave, ItemStack sword) {
        ItemStack graveStoneStack = new ItemStack(grave, 1, 0);
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setByte("GraveType", (byte) EnumGraves.SWORD.ordinal());
        GraveStoneHelper.addSwordInfo(nbt, sword);

        graveStoneStack.setTagCompound(nbt);
        return graveStoneStack;
    }

    public static Item getRandomSwordForGeneration(byte graveType, Random random) {
        if (graveType == EnumGraves.SWORD.ordinal()) {
            return GENERATED_SWORD_GRAVES[random.nextInt(GENERATED_SWORD_GRAVES.length)];
        } else {
            return null;
        }
    }

    public static void addSwordToSwordsList(Item sword) {
        swordsList.add(sword);
    }

    public static byte getRandomGrave(List<EnumGraves> graveTypes, Random rand) {
        if (graveTypes.size() > 0) {
            return (byte) graveTypes.get(rand.nextInt(graveTypes.size())).ordinal();
        } else {
            return 0;
        }
    }

    public static boolean canFlowerBePlaced(World world, int x, int y, int z, ItemStack item, TileEntityGSGraveStone te) {
        return Block.getBlockFromItem(item.getItem()).canBlockStay(world, x, y, z) && canFlowerBePlacedOnGrave(te);
    }

    public static boolean canFlowerBePlacedOnGrave(TileEntityGSGraveStone te) {
        return !te.isSwordGrave() && FLOWER_GRAVES.contains(te.getGraveType());
    }

    public static ArrayList<EnumGraves> getPlayerGraveTypes(World world, int x, int z) {
        BiomeGenBase biome = world.getBiomeGenForCoords(x, z);

        ArrayList<BiomeDictionary.Type> biomeTypesList = new ArrayList<BiomeDictionary.Type>(Arrays.asList(BiomeDictionary.getTypesForBiome(biome)));
        ArrayList<EnumGraves> graveTypes = new ArrayList<EnumGraves>();

        if (biomeTypesList.contains(BiomeDictionary.Type.SANDY) || biomeTypesList.contains(BiomeDictionary.Type.BEACH)) {
            graveTypes.addAll(Arrays.asList(GENERATED_SANDSTONE_GRAVES));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.JUNGLE) || biomeTypesList.contains(BiomeDictionary.Type.SWAMP)) {
            graveTypes.addAll(Arrays.asList(GENERATED_MOSSY_GRAVES));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.MOUNTAIN) || biomeTypesList.contains(BiomeDictionary.Type.HILLS) ||
                biomeTypesList.contains(BiomeDictionary.Type.PLAINS) || biomeTypesList.contains(BiomeDictionary.Type.MUSHROOM)) {
            graveTypes.addAll(Arrays.asList(GENERATED_STONE_GRAVES));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.FOREST)) {
            graveTypes.addAll(Arrays.asList(GENERATED_WOODEN_GRAVES));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.SNOWY)) {
            graveTypes.addAll(Arrays.asList(GENERATED_ICE_GRAVES));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.NETHER)) {
            graveTypes.addAll(Arrays.asList(GENERATED_QUARTZ_GRAVES));
        }

        // TODO
//        if (biomeTypesList.contains(BiomeDictionary.Type.END)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.MAGICAL)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.WATER)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.WASTELAND)) {
//        }

        if (graveTypes.isEmpty()) {
            graveTypes.addAll(Arrays.asList(GENERATED_STONE_GRAVES));
        }

        return graveTypes;
    }

    public static ArrayList<EnumGraves> getDogGraveTypes(World world, int x, int z) {
        BiomeGenBase biome = world.getBiomeGenForCoords(x, z);

        ArrayList<BiomeDictionary.Type> biomeTypesList = new ArrayList<BiomeDictionary.Type>(Arrays.asList(BiomeDictionary.getTypesForBiome(biome)));
        ArrayList<EnumGraves> graveTypes = new ArrayList<EnumGraves>();

        if (biomeTypesList.contains(BiomeDictionary.Type.SANDY) || biomeTypesList.contains(BiomeDictionary.Type.BEACH)) {
            graveTypes.addAll(Arrays.asList(DOG_SANDSTONE_GRAVES));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.JUNGLE) || biomeTypesList.contains(BiomeDictionary.Type.SWAMP)) {
            graveTypes.addAll(Arrays.asList(DOG_MOSSY_GRAVES));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.MOUNTAIN) || biomeTypesList.contains(BiomeDictionary.Type.HILLS) ||
                biomeTypesList.contains(BiomeDictionary.Type.PLAINS) || biomeTypesList.contains(BiomeDictionary.Type.MUSHROOM)) {
            graveTypes.addAll(Arrays.asList(DOG_STONE_GRAVES));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.FOREST)) {
            graveTypes.addAll(Arrays.asList(DOG_WOODEN_GRAVES));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.SNOWY)) {
            graveTypes.addAll(Arrays.asList(DOG_ICE_GRAVES));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.NETHER)) {
            graveTypes.addAll(Arrays.asList(DOG_QUARTZ_GRAVES));
        }

        // TODO
//        if (biomeTypesList.contains(BiomeDictionary.Type.END)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.MAGICAL)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.WATER)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.WASTELAND)) {
//        }

        if (graveTypes.isEmpty()) {
            graveTypes.addAll(Arrays.asList(DOG_STONE_GRAVES));
        }

        return graveTypes;
    }

    public static ArrayList<EnumGraves> getCatGraveTypes(World world, int x, int z) {
        BiomeGenBase biome = world.getBiomeGenForCoords(x, z);

        ArrayList<BiomeDictionary.Type> biomeTypesList = new ArrayList<BiomeDictionary.Type>(Arrays.asList(BiomeDictionary.getTypesForBiome(biome)));
        ArrayList<EnumGraves> graveTypes = new ArrayList<EnumGraves>();

        if (biomeTypesList.contains(BiomeDictionary.Type.SANDY) || biomeTypesList.contains(BiomeDictionary.Type.BEACH)) {
            graveTypes.addAll(Arrays.asList(CAT_SANDSTONE_GRAVES));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.JUNGLE) || biomeTypesList.contains(BiomeDictionary.Type.SWAMP)) {
            graveTypes.addAll(Arrays.asList(CAT_MOSSY_GRAVES));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.MOUNTAIN) || biomeTypesList.contains(BiomeDictionary.Type.HILLS) ||
                biomeTypesList.contains(BiomeDictionary.Type.PLAINS) || biomeTypesList.contains(BiomeDictionary.Type.MUSHROOM)) {
            graveTypes.addAll(Arrays.asList(CAT_STONE_GRAVES));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.FOREST)) {
            graveTypes.addAll(Arrays.asList(CAT_WOODEN_GRAVES));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.SNOWY)) {
            graveTypes.addAll(Arrays.asList(CAT_ICE_GRAVES));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.NETHER)) {
            graveTypes.addAll(Arrays.asList(CAT_QUARTZ_GRAVES));
        }

        // TODO
//        if (biomeTypesList.contains(BiomeDictionary.Type.END)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.MAGICAL)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.WATER)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.WASTELAND)) {
//        }

        if (graveTypes.isEmpty()) {
            graveTypes.addAll(Arrays.asList(CAT_STONE_GRAVES));
        }
        return graveTypes;
    }

    public static ArrayList<EnumGraves> getHorseGraveTypes(World world, int x, int z) {
        BiomeGenBase biome = world.getBiomeGenForCoords(x, z);

        ArrayList<BiomeDictionary.Type> biomeTypesList = new ArrayList<BiomeDictionary.Type>(Arrays.asList(BiomeDictionary.getTypesForBiome(biome)));
        ArrayList<EnumGraves> graveTypes = new ArrayList<EnumGraves>();

        if (biomeTypesList.contains(BiomeDictionary.Type.SANDY) || biomeTypesList.contains(BiomeDictionary.Type.BEACH)) {
            graveTypes.addAll(Arrays.asList(HORSE_SANDSTONE_GRAVES));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.JUNGLE) || biomeTypesList.contains(BiomeDictionary.Type.SWAMP)) {
            graveTypes.addAll(Arrays.asList(HORSE_MOSSY_GRAVES));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.MOUNTAIN) || biomeTypesList.contains(BiomeDictionary.Type.HILLS) ||
                biomeTypesList.contains(BiomeDictionary.Type.PLAINS) || biomeTypesList.contains(BiomeDictionary.Type.MUSHROOM)) {
            graveTypes.addAll(Arrays.asList(HORSE_STONE_GRAVES));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.FOREST)) {
            graveTypes.addAll(Arrays.asList(HORSE_WOODEN_GRAVES));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.SNOWY)) {
            graveTypes.addAll(Arrays.asList(HORSE_ICE_GRAVES));
        }
        if (biomeTypesList.contains(BiomeDictionary.Type.NETHER)) {
            graveTypes.addAll(Arrays.asList(HORSE_QUARTZ_GRAVES));
        }

        // TODO
//        if (biomeTypesList.contains(BiomeDictionary.Type.END)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.MAGICAL)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.WATER)) {
//        }
//        if (biomeTypesList.contains(BiomeDictionary.Type.WASTELAND)) {
//        }

        if (graveTypes.isEmpty()) {
            graveTypes.addAll(Arrays.asList(HORSE_STONE_GRAVES));
        }

        return graveTypes;
    }


    public static ArrayList<EnumGraves> getPlayerGraveForDeath(DamageSource damageSource, String damageType) {
        ArrayList<EnumGraves> graveTypes = new ArrayList<EnumGraves>();

        if (isFireDamage(damageSource, damageType) || isLavaDamage(damageSource, damageType) || isBlastDamage(damageType) || isFireballDamage(damageType)) {
            graveTypes.addAll(Arrays.asList(GENERATED_OBSIDIAN_GRAVES));
        }
        return graveTypes;
    }

    public static ArrayList<EnumGraves> getDogGraveForDeath(DamageSource damageSource, String damageType) {
        ArrayList<EnumGraves> graveTypes = new ArrayList<EnumGraves>();

        if (isFireDamage(damageSource, damageType) || isLavaDamage(damageSource, damageType) || isBlastDamage(damageType) || isFireballDamage(damageType)) {
            graveTypes.addAll(Arrays.asList(DOG_OBSIDIAN_GRAVES));
        }
        return graveTypes;
    }

    public static ArrayList<EnumGraves> getCatGraveForDeath(DamageSource damageSource, String damageType) {
        ArrayList<EnumGraves> graveTypes = new ArrayList<EnumGraves>();

        if (isFireDamage(damageSource, damageType) || isLavaDamage(damageSource, damageType) || isBlastDamage(damageType) || isFireballDamage(damageType)) {
            graveTypes.addAll(Arrays.asList(CAT_OBSIDIAN_GRAVES));
        }
        return graveTypes;
    }

    public static ArrayList<EnumGraves> getHorseGraveForDeath(DamageSource damageSource, String damageType) {
        ArrayList<EnumGraves> graveTypes = new ArrayList<EnumGraves>();

        if (isFireDamage(damageSource, damageType) || isLavaDamage(damageSource, damageType) || isBlastDamage(damageType) || isFireballDamage(damageType)) {
            graveTypes.addAll(Arrays.asList(HORSE_OBSIDIAN_GRAVES));
        }
        return graveTypes;
    }

    public static ArrayList<EnumGraves> getPlayerGraveForLevel(Entity entity) {
        ArrayList<EnumGraves> graveTypes = new ArrayList<EnumGraves>();

        if (entity instanceof EntityPlayer) {
            int level = ((EntityPlayer) entity).experienceLevel;
            if (level >= 65) {
                graveTypes.addAll(Arrays.asList(GENERATED_EMERALD_GRAVES));
            } else if (level >= 55) {
                graveTypes.addAll(Arrays.asList(GENERATED_DIAMOND_GRAVES));
            } else if (level >= 45) {
                graveTypes.addAll(Arrays.asList(GENERATED_REDSTONE_GRAVES));
            } else if (level >= 35) {
                graveTypes.addAll(Arrays.asList(GENERATED_GOLDEN_GRAVES));
            } else if (level >= 25) {
                graveTypes.addAll(Arrays.asList(GENERATED_LAPIS_GRAVES));
            } else if (level >= 15) {
                graveTypes.addAll(Arrays.asList(GENERATED_IRON_GRAVES));
            }
        }

        return graveTypes;
    }

    public static boolean isFireDamage(DamageSource damageSource, String damageType) {
        return DamageSource.inFire.equals(damageSource) || DamageSource.onFire.equals(damageSource) ||
                damageType.toLowerCase().contains("nFire");
    }

    public static boolean isLavaDamage(DamageSource damageSource, String damageType) {
        return DamageSource.lava.equals(damageSource) || damageType.toLowerCase().contains("lava");
    }

    public static boolean isBlastDamage(String damageType) {
        return damageType.toLowerCase().contains("explosion");
    }

    public static boolean isFireballDamage(String damageType) {
        return damageType.toLowerCase().contains("fireball");
    }

    public static boolean isMagicDamage(DamageSource damageSource, String damageType) {
        return DamageSource.magic.equals(damageSource) || damageType.toLowerCase().contains("magic");
    }

    private static boolean isInRestrictedArea(World world, Vec3 position) {
        for (RestrictedArea area : GraveStoneConfig.restrictGraveGenerationInArea) {
            if (area.isInArea(world, position)) {
                return true;
            }
        }
        return false;
    }

    public static class RestrictedArea {
        private final int dimensionId;
        private final Vec3 firstPoint;
        private final Vec3 lastPoint;

        public RestrictedArea(int startX, int startY, int startZ, int endX, int endY, int endZ) {
            this(0, startX, startY, startZ, endX, endY, endZ);
        }

        public RestrictedArea(int dimensionId, int startX, int startY, int startZ, int endX, int endY, int endZ) {
            this.dimensionId = dimensionId;
            this.firstPoint = Vec3.createVectorHelper(startX, startY, startZ);
            this.lastPoint = Vec3.createVectorHelper(endX, endY, endZ);
        }

        public boolean isInArea(World world, Vec3 position) {
            return world.provider.dimensionId == dimensionId &&
                    position.xCoord >= firstPoint.xCoord && position.xCoord <= lastPoint.xCoord &&
                    position.yCoord >= firstPoint.yCoord && position.yCoord <= lastPoint.yCoord &&
                    position.zCoord >= firstPoint.zCoord && position.zCoord <= lastPoint.zCoord;
        }

        public static RestrictedArea getFromString(String area) {
            String[] coordinates = area.split(",");
            if (coordinates.length == 7) {
                try {
                    return new GraveStoneHelper.RestrictedArea(
                            Integer.parseInt(coordinates[0]),
                            Integer.parseInt(coordinates[1]), Integer.parseInt(coordinates[2]), Integer.parseInt(coordinates[3]),
                            Integer.parseInt(coordinates[4]), Integer.parseInt(coordinates[5]), Integer.parseInt(coordinates[6]));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            } else if (coordinates.length == 6) {
                try {
                    return new GraveStoneHelper.RestrictedArea(
                            Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]), Integer.parseInt(coordinates[2]),
                            Integer.parseInt(coordinates[3]), Integer.parseInt(coordinates[4]), Integer.parseInt(coordinates[5]));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}
