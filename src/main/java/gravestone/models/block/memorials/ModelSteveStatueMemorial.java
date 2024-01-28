package gravestone.models.block.memorials;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gravestone.block.enums.EnumMemorials;
import gravestone.core.Resources;
import gravestone.models.block.ModelMemorial;
import gravestone.renderer.tileentity.TileEntityGSMemorialRenderer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;


/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@SideOnly(Side.CLIENT)
public class ModelSteveStatueMemorial extends ModelMemorial {

    public ModelRenderer bipedHead;
    public ModelRenderer bipedBody;
    public ModelRenderer bipedRightArm;
    public ModelRenderer bipedLeftArm;
    public ModelRenderer bipedRightLeg;
    public ModelRenderer bipedLeftLeg;
    /**
     * Records whether the model should be rendered holding an item in the left
     * hand, and if that item is a block.
     */
    public int heldItemLeft;
    /**
     * Records whether the model should be rendered holding an item in the right
     * hand, and if that item is a block.
     */
    public int heldItemRight;
    ModelBigPedestal pedestal;

    public ModelSteveStatueMemorial() {
        float par1 = 0;
        float par2 = 0;
        textureWidth = 64;
        textureHeight = 32;
        this.heldItemLeft = 0;
        this.heldItemRight = 0;
        this.bipedHead = new ModelRenderer(this, 0, 0);
        this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, par1);
        this.bipedHead.setRotationPoint(0.0F, 0.0F + par2, 0.0F);
        this.bipedBody = new ModelRenderer(this, 16, 16);
        this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, par1);
        this.bipedBody.setRotationPoint(0.0F, 0.0F + par2, 0.0F);
        this.bipedRightArm = new ModelRenderer(this, 40, 16);
        this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, par1);
        this.bipedRightArm.setRotationPoint(-5.0F, 2.0F + par2, 0.0F);
        this.bipedLeftArm = new ModelRenderer(this, 40, 16);
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, par1);
        this.bipedLeftArm.setRotationPoint(5.0F, 2.0F + par2, 0.0F);
        this.bipedRightLeg = new ModelRenderer(this, 0, 16);
        this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, par1);
        this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F + par2, 0.0F);
        this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
        this.bipedLeftLeg.mirror = true;
        this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, par1);
        this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F + par2, 0.0F);
        pedestal = new ModelBigPedestal();
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are
     * used for animating the movement of arms and legs, where par1 represents
     * the time(so that arms and legs swing back and forth) and par2 represents
     * how "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6) {
        this.bipedHead.rotateAngleY = par4 / (180F / (float) Math.PI);
        this.bipedHead.rotateAngleX = par5 / (180F / (float) Math.PI);
        this.bipedRightArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 2.0F * par2 * 0.5F;
        this.bipedLeftArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
        this.bipedRightArm.rotateAngleZ = 0.0F;
        this.bipedLeftArm.rotateAngleZ = 0.0F;
        this.bipedRightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
        this.bipedLeftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
        this.bipedRightLeg.rotateAngleY = 0.0F;
        this.bipedLeftLeg.rotateAngleY = 0.0F;

        this.bipedRightArm.rotateAngleY = 0.0F;
        this.bipedLeftArm.rotateAngleY = 0.0F;
        this.bipedBody.rotateAngleX = 0.0F;
        this.bipedRightLeg.rotationPointZ = 0.1F;
        this.bipedLeftLeg.rotationPointZ = 0.1F;
        this.bipedRightLeg.rotationPointY = 12.0F;
        this.bipedLeftLeg.rotationPointY = 12.0F;
        this.bipedHead.rotationPointY = 0.0F;
        this.bipedRightArm.rotateAngleZ += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
        this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
        this.bipedRightArm.rotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;
        this.bipedLeftArm.rotateAngleX -= MathHelper.sin(par3 * 0.067F) * 0.05F;
    }

    @Override
    public void renderAll() {
        this.setRotationAngles(0.0625F, 0.0625F, 0.0625F, 0.0625F, 0.0625F, 0.0625F);
        pedestal.shiftModel();
        renderSteve();
        renderSteveLegs();
        pedestal.renderAll();
    }

    private void renderSteve() {
        float par7 = 0.0625F;
        this.bipedHead.render(par7);
        this.bipedBody.render(par7);
        this.bipedRightArm.render(par7);
        this.bipedLeftArm.render(par7);
    }

    private void renderSteveLegs() {
        float par7 = 0.0625F;
        this.bipedRightLeg.render(par7);
        this.bipedLeftLeg.render(par7);
    }

    @Override
    public void customRender(EnumMemorials memorialType, boolean enchanted) {
        if (enchanted) {
            renderEnchanted();
        } else {
            renderAll();
        }
        renderArmor(memorialType);
    }

    private void renderArmor(EnumMemorials memorialType) {
        pedestal.shiftModel();
        ResourceLocation texture = null;
        switch (memorialType) {
            case WOODEN_STEVE_STATUE:
                texture = Resources.WOODEN_ARMOR;
                break;
            case SANDSTONE_STEVE_STATUE:
                texture = Resources.SANDSTONE_ARMOR;
                break;
            case STONE_STEVE_STATUE:
                texture = Resources.STONE_ARMOR;
                break;
            case MOSSY_STEVE_STATUE:
                texture = Resources.MOSSY_ARMOR;
                break;
            case IRON_STEVE_STATUE:
                texture = Resources.IRON_ARMOR;
                break;
            case GOLDEN_STEVE_STATUE:
                texture = Resources.GOLDEN_ARMOR;
                break;
            case DIAMOND_STEVE_STATUE:
                texture = Resources.DIAMOND_ARMOR;
                break;
            case EMERALD_STEVE_STATUE:
                texture = Resources.EMERALD_ARMOR;
                break;
            case LAPIS_STEVE_STATUE:
                texture = Resources.LAPIS_ARMOR;
                break;
            case REDSTONE_STEVE_STATUE:
                texture = Resources.REDSTONE_ARMOR;
                break;
            case OBSIDIAN_STEVE_STATUE:
                texture = Resources.OBSIDIAN_ARMOR;
                break;
            case QUARTZ_STEVE_STATUE:
                texture = Resources.QUARTZ_ARMOR;
                break;
            case ICE_STEVE_STATUE:
                texture = Resources.ICE_ARMOR;
                break;
        }
        if (texture != null) {
            float scale = 1.1F;
            GL11.glScalef(scale, scale, scale);
            TileEntityGSMemorialRenderer.instance.bindTextureByName(texture);
            renderSteve();
        }
    }

    @Override
    public void setPedestalTexture(ResourceLocation texture) {
        pedestal.setTexture(texture);
    }
}
