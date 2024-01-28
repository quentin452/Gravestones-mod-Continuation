
package gravestone.entity.monster;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class EntityWitherSkullCrawler extends EntitySkullCrawler {

    public EntityWitherSkullCrawler(World world) {
        super(world);
        this.isImmuneToFire = true;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2);
    }

    @Override
    protected void dropRareDrop(int par1) {
        this.entityDropItem(new ItemStack(Items.skull, 1, 1), 0);
    }
    
    @Override
    protected PotionEffect getPotionEffect() {
        return new PotionEffect(Potion.wither.id, 100);
    }
    
    @Override
    protected void silverfishLikeBehaviour() {
    }
}
