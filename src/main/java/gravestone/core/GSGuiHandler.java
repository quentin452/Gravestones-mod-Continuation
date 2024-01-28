package gravestone.core;

import cpw.mods.fml.common.network.IGuiHandler;
import gravestone.gui.GSAltarGui;
import gravestone.gui.GSGraveInventoryGui;
import gravestone.gui.container.AltarContainer;
import gravestone.gui.container.GraveContainer;
import gravestone.tileentity.TileEntityGSAltar;
import gravestone.tileentity.TileEntityGSGraveStone;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class GSGuiHandler implements IGuiHandler {
    public static final int GRAVE_INVENTORY_GUI_ID = 0;
    public static final int ALTAR_GUI_ID = 2;

    //returns an instance of the Container you made earlier
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity;
        switch (id) {
            case GRAVE_INVENTORY_GUI_ID:
                tileEntity = world.getTileEntity(x, y, z);
                if (tileEntity instanceof TileEntityGSGraveStone) {
                    return new GraveContainer(player.inventory, (TileEntityGSGraveStone) tileEntity);
                }
                break;
            case ALTAR_GUI_ID:
                tileEntity = world.getTileEntity(x, y, z);
                if (tileEntity instanceof TileEntityGSAltar) {
                    return new AltarContainer(player.inventory, (TileEntityGSAltar) tileEntity);
                }
                break;
        }
        return null;
    }

    //returns an instance of the Gui you made earlier
    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity;
        switch (id) {
            case GRAVE_INVENTORY_GUI_ID:
                tileEntity = world.getTileEntity(x, y, z);
                if (tileEntity instanceof TileEntityGSGraveStone) {
                    return new GSGraveInventoryGui(player.inventory, (TileEntityGSGraveStone) tileEntity);
                }
                break;
            case ALTAR_GUI_ID:
                tileEntity = world.getTileEntity(x, y, z);
                if (tileEntity instanceof TileEntityGSAltar) {
                    return new GSAltarGui(player.inventory, (TileEntityGSAltar) tileEntity);
                }
                break;
        }
        return null;
    }
}
