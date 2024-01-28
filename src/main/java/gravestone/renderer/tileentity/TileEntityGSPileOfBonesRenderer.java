package gravestone.renderer.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gravestone.core.Resources;
import gravestone.models.block.ModelPileOfBones;
import gravestone.tileentity.TileEntityGSPileOfBones;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@SideOnly(Side.CLIENT)
public class TileEntityGSPileOfBonesRenderer extends TileEntitySpecialRenderer {

    private ModelPileOfBones pileOfBonesModel = new ModelPileOfBones();

    public void renderTileEntityCandleAt(TileEntityGSPileOfBones tileEntity, float x, float y, float z, float par8) {
        this.bindTexture(Resources.PILE_OF_BONES);

        int meta;

        GL11.glPushMatrix();
        if (tileEntity.getWorldObj() == null ) {
            GL11.glTranslatef(x + 0.5F, y + 2.7F, z + 0.5F);
            GL11.glScalef(1.8F, -1.8F, -1.8F);
            GL11.glRotatef(-90, 0, 1, 0);
            meta = tileEntity.blockMetadata;
        } else {
            GL11.glTranslatef(x + 0.5F, y + 1.5F, z + 0.5F);
            GL11.glScalef(1, -1, -1);
            meta = tileEntity.getBlockMetadata();
            int direction = tileEntity.getDirection();
            switch (direction) {
                case 1:
                    direction = -90;
                    break;
                case 0:
                    direction = 180;
                    break;
                case 2:
                    direction = 0;
                    break;
                case 3:
                default:
                    direction = 90;
                    break;
            }
            GL11.glRotatef(direction, 0.0F, 1.0F, 0.0F);
        }

        pileOfBonesModel.renderAll(meta != 0);
        GL11.glPopMatrix();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float par8) {
        this.renderTileEntityCandleAt((TileEntityGSPileOfBones) tileEntity, (float) x, (float) y, (float) z, par8);
    }
}
