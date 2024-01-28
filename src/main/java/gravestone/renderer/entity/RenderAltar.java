package gravestone.renderer.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gravestone.tileentity.TileEntityGSAltar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@SideOnly(Side.CLIENT)
public class RenderAltar extends TileEntitySpecialRenderer {

    public void renderTileEntityAt(TileEntityGSAltar te, double x, double y, double z, float f) {
        ItemStack corpse = te.getCorpse();
        if (corpse != null) {
            GL11.glPushMatrix();

            float time = Minecraft.getMinecraft().theWorld.getTotalWorldTime() + f;
            GL11.glTranslatef((float) x + 0.5F, (float) y + 1.2F, (float) z + 0.5F);
            GL11.glRotatef(time % 360, 0, 1, 0);

            EntityItem entityItem = new EntityItem(te.getWorldObj(), 0, 0, 0, corpse);
            if (corpse.hasTagCompound()) {
                entityItem.getEntityItem().setTagCompound((NBTTagCompound) corpse.getTagCompound().copy());
            }
            entityItem.hoverStart = 0;

            RenderManager.instance.renderEntityWithPosYaw(entityItem, 0, 0, 0, 0, 0);
            GL11.glPopMatrix();
        }
    }

    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float xz) {
        this.renderTileEntityAt((TileEntityGSAltar) te, x, y, z, xz);
    }
}
