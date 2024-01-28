package gravestone.renderer.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@SideOnly(Side.CLIENT)
public class TileEntityGSRenderer extends TileEntitySpecialRenderer {

    /**
     * Binds a texture to the renderEngine given a filename from the JAR.
     */
    public void bindTextureByName(ResourceLocation texture) {
        bindTexture(texture);
    }

    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float f) {
    }
}
