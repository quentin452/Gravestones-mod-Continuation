package gravestone.entity.monster;

import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class EntityUndeadCat extends EntityUndeadPet {

    public EntityUndeadCat(World world) {
        super(world);
        this.setSize(0.6F, 0.8F);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(18, Byte.valueOf((byte) 0));
    }

    /**
     * main AI tick function, replaces updateEntityActionState
     */
    @Override
    public void updateAITick() {
        if (this.getMoveHelper().isUpdating()) {
            double f = this.getMoveHelper().getSpeed();

            if (f == 0.18F) {
                this.setSneaking(true);
                this.setSprinting(false);
            } else if (f == 0.4F) {
                this.setSneaking(false);
                this.setSprinting(true);
            } else {
                this.setSneaking(false);
                this.setSprinting(false);
            }
        } else {
            this.setSneaking(false);
            this.setSprinting(false);
        }
    }

    /**
     * Called when the mob is falling. Calculates and applies fall damage.
     */
    @Override
    protected void fall(float par1) {
    }
}
