package gravestone.core.compatibility;

import gravestone.config.GraveStoneConfig;
import gravestone.core.logger.GSLogger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import tconstruct.api.IPlayerExtendedInventoryWrapper;
import tconstruct.api.TConstructAPI;

import java.util.List;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSCompatibilityTinkerConstruct {

    protected static boolean isInstalled = false;

    private static final int ACCESSORIES_SLOTS_COUNT = 4;

    private GSCompatibilityTinkerConstruct() {
    }

    public static void addItems(List<ItemStack> items, EntityPlayer player) {
        if (isLoaded() && GraveStoneConfig.storeTinkerConstructItems) {
            IPlayerExtendedInventoryWrapper inventoryWrapper = TConstructAPI.getInventoryWrapper(player);
            if (inventoryWrapper != null) {
                IInventory knapsackInventory = inventoryWrapper.getKnapsackInventory(player);
                if (knapsackInventory != null) {
                    for (int slot = 0; slot < knapsackInventory.getSizeInventory(); slot++) {
                        ItemStack stack = knapsackInventory.getStackInSlot(slot);
                        if (stack != null) {
                            items.add(stack.copy());
                            knapsackInventory.setInventorySlotContents(slot, null);
                        }

                    }
                } else {
                    GSLogger.logError("Can't get Tinkers Construct knapsack items!!!");
                }

                IInventory accessoryInventory = inventoryWrapper.getAccessoryInventory(player);
                if (accessoryInventory != null) {
                    //Heart Canisters should not go in the grave as they are not supposed to be dropped on death, so only first 4 slots required
                    for (int slot = 0; slot < ACCESSORIES_SLOTS_COUNT; slot++) {
                        ItemStack stack = accessoryInventory.getStackInSlot(slot);
                        if (stack != null) {
                            items.add(stack.copy());
                            accessoryInventory.setInventorySlotContents(slot, null);
                        }

                    }
                } else {
                    GSLogger.logError("Can't get Tinkers Construct accessory items!!!");
                }
            } else {
                GSLogger.logError("Can't get access to Tinkers Construct items!!!");
            }
        }
    }

    public static boolean isLoaded() {
        return isInstalled;
    }
}
