package gravestone.models.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gravestone.entity.monster.EntityUndeadDog;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

/**
 * GraveStone mod
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
@SideOnly(Side.CLIENT)
public class ModelUndeadDog extends ModelBase {

    /**
     * main box for the wolf head
     */
    public ModelRenderer wolfHeadMain;
    /**
     * The wolf's body
     */
    public ModelRenderer wolfBody;
    /**
     * Wolf'se first leg
     */
    public ModelRenderer wolfLeg1;
    /**
     * Wolf's second leg
     */
    public ModelRenderer wolfLeg2;
    /**
     * Wolf's third leg
     */
    public ModelRenderer wolfLeg3;
    /**
     * Wolf's fourth leg
     */
    public ModelRenderer wolfLeg4;
    /**
     * The wolf's tail
     */
    ModelRenderer wolfTail;
    /**
     * The wolf's mane
     */
    ModelRenderer wolfMane;

    public ModelUndeadDog() {
        float f = 0.0F;
        float f1 = 13.5F;
        this.wolfHeadMain = new ModelRenderer(this, 0, 0);
        this.wolfHeadMain.addBox(-3.0F, -3.0F, -2.0F, 6, 6, 4, f);
        this.wolfHeadMain.setRotationPoint(-1.0F, f1, -7.0F);
        this.wolfBody = new ModelRenderer(this, 18, 14);
        this.wolfBody.addBox(-4.0F, -2.0F, -3.0F, 6, 9, 6, f);
        this.wolfBody.setRotationPoint(0.0F, 14.0F, 2.0F);
        this.wolfMane = new ModelRenderer(this, 21, 0);
        this.wolfMane.addBox(-4.0F, -3.0F, -3.0F, 8, 6, 7, f);
        this.wolfMane.setRotationPoint(-1.0F, 14.0F, 2.0F);
        this.wolfLeg1 = new ModelRenderer(this, 0, 18);
        this.wolfLeg1.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, f);
        this.wolfLeg1.setRotationPoint(-2.5F, 16.0F, 7.0F);
        this.wolfLeg2 = new ModelRenderer(this, 0, 18);
        this.wolfLeg2.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, f);
        this.wolfLeg2.setRotationPoint(0.5F, 16.0F, 7.0F);
        this.wolfLeg3 = new ModelRenderer(this, 0, 18);
        this.wolfLeg3.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, f);
        this.wolfLeg3.setRotationPoint(-2.5F, 16.0F, -4.0F);
        this.wolfLeg4 = new ModelRenderer(this, 0, 18);
        this.wolfLeg4.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, f);
        this.wolfLeg4.setRotationPoint(0.5F, 16.0F, -4.0F);
        this.wolfTail = new ModelRenderer(this, 9, 18);
        this.wolfTail.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, f);
        this.wolfTail.setRotationPoint(-1.0F, 12.0F, 8.0F);
        this.wolfHeadMain.setTextureOffset(16, 14).addBox(-3.0F, -5.0F, 0.0F, 2, 2, 1, f);
        this.wolfHeadMain.setTextureOffset(16, 14).addBox(1.0F, -5.0F, 0.0F, 2, 2, 1, f);
        this.wolfHeadMain.setTextureOffset(0, 10).addBox(-1.5F, 0.0F, -5.0F, 3, 3, 4, f);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    @Override
    public void render(Entity entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        super.render(entity, par2, par3, par4, par5, par6, par7);
        this.setRotationAngles(par2, par3, par4, par5, par6, par7, entity);
        this.wolfHeadMain.renderWithRotation(par7);
        this.wolfBody.render(par7);
        this.wolfLeg1.render(par7);
        this.wolfLeg2.render(par7);
        this.wolfLeg3.render(par7);
        this.wolfLeg4.render(par7);
        this.wolfTail.renderWithRotation(par7);
        this.wolfMane.render(par7);
    }

    /**
     * Used for easily adding entity-dependent animations. The second and third
     * float params here are the same second and third as in the
     * setRotationAngles method.
     */
    @Override
    public void setLivingAnimations(EntityLivingBase entityLiving, float par2, float par3, float par4) {
        EntityUndeadDog undeadDog = (EntityUndeadDog) entityLiving;
        this.wolfTail.rotateAngleY = MathHelper.cos(par2 * 0.6662F) * 1.4F * par3;
        this.wolfBody.setRotationPoint(0.0F, 14.0F, 2.0F);
        this.wolfBody.rotateAngleX = ((float) Math.PI / 2F);
        this.wolfMane.setRotationPoint(-1.0F, 14.0F, -3.0F);
        this.wolfMane.rotateAngleX = this.wolfBody.rotateAngleX;
        this.wolfTail.setRotationPoint(-1.0F, 12.0F, 8.0F);
        this.wolfLeg1.setRotationPoint(-2.5F, 16.0F, 7.0F);
        this.wolfLeg2.setRotationPoint(0.5F, 16.0F, 7.0F);
        this.wolfLeg3.setRotationPoint(-2.5F, 16.0F, -4.0F);
        this.wolfLeg4.setRotationPoint(0.5F, 16.0F, -4.0F);
        this.wolfLeg1.rotateAngleX = MathHelper.cos(par2 * 0.6662F) * 1.4F * par3;
        this.wolfLeg2.rotateAngleX = MathHelper.cos(par2 * 0.6662F + (float) Math.PI) * 1.4F * par3;
        this.wolfLeg3.rotateAngleX = MathHelper.cos(par2 * 0.6662F + (float) Math.PI) * 1.4F * par3;
        this.wolfLeg4.rotateAngleX = MathHelper.cos(par2 * 0.6662F) * 1.4F * par3;
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are
     * used for animating the movement of arms and legs, where par1 represents
     * the time(so that arms and legs swing back and forth) and par2 represents
     * how "far" arms and legs can swing at most.
     */
    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
        super.setRotationAngles(par1, par2, par3, par4, par5, par6, entity);
        this.wolfHeadMain.rotateAngleX = par5 / (180F / (float) Math.PI);
        this.wolfHeadMain.rotateAngleY = par4 / (180F / (float) Math.PI);
        this.wolfTail.rotateAngleX = par3;
    }
}
