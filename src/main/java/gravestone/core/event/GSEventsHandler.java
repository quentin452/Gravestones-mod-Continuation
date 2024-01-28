package gravestone.core.event;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import gravestone.block.BlockGSGraveStone.EnumGraveType;
import gravestone.block.GraveStoneHelper;
import gravestone.config.GraveStoneConfig;
import gravestone.core.GSBlock;
import gravestone.core.GSMobSpawn;
import gravestone.core.MobHandler;
import gravestone.core.compatibility.GSCompatibilityWitchery;
import gravestone.core.logger.GravesLogger;
import gravestone.entity.monster.EntitySkullCrawler;
import gravestone.entity.monster.EntityWitherSkullCrawler;
import gravestone.entity.monster.EntityZombieSkullCrawler;
import gravestone.item.corpse.CorpseHelper;
import gravestone.item.enums.EnumCorpse;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.world.WorldEvent;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSEventsHandler {

    // Hopefully ensure we capture items before other things do (set to high so other mods can run before if they have more specialness
    @SubscribeEvent(priority= EventPriority.HIGH)
    public void onEntityLivingDeath(LivingDeathEvent event) {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            if (!GraveStoneConfig.generateGravesInLava && event.source.damageType.equals("lava")) {
                return;
            }

            if (GraveStoneConfig.generatePlayerGraves && event.entityLiving instanceof EntityPlayer &&
                    !GSCompatibilityWitchery.isVampire((EntityPlayer) event.entity, event.source)) {
                GraveStoneHelper.createPlayerGrave((EntityPlayer) event.entity, event, MobHandler.getAndRemoveSpawnTime(event.entity));
            } else {
                if (GraveStoneConfig.generateVillagerGraves && event.entity instanceof EntityVillager) {
                    GraveStoneHelper.createGrave(event.entity, event, CorpseHelper.getCorpse(event.entity, EnumCorpse.VILLAGER),
                            EnumGraveType.PLAYER_GRAVES, true, MobHandler.getAndRemoveSpawnTime(event.entity));
                    return;
                }

                if (GraveStoneConfig.generatePetGraves && event.entity instanceof EntityTameable) {
                    GraveStoneHelper.createPetGrave(event.entity, event, MobHandler.getAndRemoveSpawnTime(event.entity));
                    return;
                }

                if (GraveStoneConfig.generatePetGraves && event.entity instanceof EntityHorse) {
                    GraveStoneHelper.createHorseGrave((EntityHorse) event.entity, event, MobHandler.getAndRemoveSpawnTime(event.entity));
                    return;
                }
            }

            if (GraveStoneConfig.spawnSkullCrawlersAtMobsDeath) {
                if (event.entity instanceof EntitySkeleton) {
                    EntitySkullCrawler crawler;
                    if (GSMobSpawn.isWitherSkeleton((EntitySkeleton) event.entity)) {
                        crawler = new EntityWitherSkullCrawler(event.entity.worldObj);
                    } else {
                        crawler = new EntitySkullCrawler(event.entity.worldObj);
                    }
                    GSMobSpawn.spawnCrawler(event.entity, crawler);
                } else if (event.entity instanceof EntityZombie) {
                    GSMobSpawn.spawnCrawler(event.entity, new EntityZombieSkullCrawler(event.entity.worldObj));
                }
            }
            if (event.entity instanceof EntityCreeper && ((EntityCreeper) event.entity).getPowered()) {
                // drop creeper statue if entity is a charged creeper
                GSBlock.memorial.dropCreeperMemorial(event.entity.worldObj, (int) event.entity.posX,
                        (int) event.entity.posY, (int) event.entity.posZ);
            }
        }
    }

    @SubscribeEvent
    public void entityJoinWorldEvent(EntityJoinWorldEvent event) {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            Entity entity = event.entity;
            if (entity instanceof EntityVillager ||
                    entity instanceof EntityWolf ||
                    entity instanceof EntityOcelot ||
                    entity instanceof EntityHorse) {
                MobHandler.setMobSpawnTime(event.entity);
            }
        }
    }

    // TODO remove mobs info at despawn

    @SubscribeEvent
    public void worldLoading(WorldEvent.Load event) {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer()) {
            MobHandler.loadMobsSpawnTime(event.world);
            GravesLogger.setWorldDirectory(event.world.getSaveHandler().getWorldDirectory());
        }
    }
}
