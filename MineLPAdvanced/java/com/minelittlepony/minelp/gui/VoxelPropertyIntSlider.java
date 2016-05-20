
package com.minelittlepony.minelp.gui;

import com.thevoxelbox.common.LiteModVoxelCommon;
import com.thevoxelbox.common.interfaces.IVoxelPropertyProviderInteger;
import com.thevoxelbox.common.util.gui.IAdvancedDrawGui;
import com.thevoxelbox.common.util.properties.VoxelProperty;
import net.minecraft.util.MathHelper;
import org.lwjgl.input.Mouse;

public class VoxelPropertyIntSlider
        extends VoxelProperty<IVoxelPropertyProviderInteger> {
    int minValue = 0;
    int maxValue = 2;
    float value = 2.0f;
    boolean overReset = false;
    boolean overSlide = false;
    boolean overBar = false;
    boolean setBar = false;
    boolean dragging = false;
    int offset = 0;
    String minText = "0";
    String midText = "1";
    String maxText = "2";
    String labelText;

    public VoxelPropertyIntSlider(IVoxelPropertyProviderInteger parent, String binding, String text, String minText, String maxText, int xPos, int yPos, int min, int max) {
        this(parent, binding, text, minText, maxText, xPos, yPos);
        this.minValue = min;
        this.maxValue = max;
    }

    public VoxelPropertyIntSlider(IVoxelPropertyProviderInteger parent, String binding, String text, int xPos, int yPos, float min, float max) {
        this(parent, binding, text, xPos, yPos);
    }

    public VoxelPropertyIntSlider(IVoxelPropertyProviderInteger parent, String binding, String text, String minText, String maxText, int xPos, int yPos) {
        this(parent, binding, text, xPos, yPos);
        this.minText = minText;
        this.maxText = maxText;
    }

    public VoxelPropertyIntSlider(IVoxelPropertyProviderInteger parent, String binding, String text, int xPos, int yPos) {
        super(parent, binding, text, xPos, yPos);
    }

    public void draw(IAdvancedDrawGui gui, int mouseX, int mouseY) {
        this.overReset = this.mouseOverReset(mouseX, mouseY);
        int outset = this.overReset ? 1 : 0;
        int v = this.overReset ? 16 : 0;
        VoxelPropertyIntSlider.drawRect(this.xPosition + 160 - outset, this.yPosition + 11 - outset, this.xPosition + 212 + outset, this.yPosition + 26 + outset, -16777216);
        gui.drawTessellatedModalBorderRect(LiteModVoxelCommon.GUIPARTS, 256, this.xPosition + 159 - outset, this.yPosition + 10 - outset, this.xPosition + 213 + outset, this.yPosition + 27 + outset, 0, v, 16, 16 + v, 4);

        this.drawString(this.mc.fontRendererObj, "Default", this.xPosition + 169, this.yPosition + 15, this.overReset ? 16777215 : 10066329);
        int sliderLeft = this.xPosition + 48;
        int sliderRight = this.xPosition + 124;
        int sliderXPos = sliderLeft + 32;
        int sliderXPos2 = sliderLeft + 45;
        int sliderYPos = this.yPosition + 12;
        int sliderYPos2 = this.yPosition + 25;
        int sliderMinX = sliderLeft - (sliderXPos + sliderXPos2) / 2 + 5;
        int sliderMaxX = sliderRight - (sliderXPos + sliderXPos2) / 2 - 5;
        this.drawHorizontalLine(sliderLeft, sliderRight, this.yPosition + 18, -6710887);
        this.drawVerticalLine(this.xPosition + 86, this.yPosition + 14, this.yPosition + 22, -6710887);
        this.drawVerticalLine(sliderLeft, this.yPosition + 14, this.yPosition + 22, -6710887);
        this.drawVerticalLine(sliderRight, this.yPosition + 14, this.yPosition + 22, -6710887);
        if (this.displayText != null) {
            this.drawString(this.mc.fontRendererObj, this.displayText, this.xPosition + 15, this.yPosition - 14, 10079487);
        }
        this.drawString(this.mc.fontRendererObj, this.minText, this.xPosition + 50, this.yPosition, 16777215);
        if (this.midText != null) {
            this.drawString(this.mc.fontRendererObj, this.midText, this.xPosition + 84, this.yPosition, 16777215);
        }
        if (this.maxText != null) {
            this.drawString(this.mc.fontRendererObj, this.maxText, this.xPosition + 118, this.yPosition, 16777215);
        } else {
            float scale = (this.value + 1.0f) / 2.0f;
            int displayValue = MathHelper.ceiling_float_int(((float) this.minValue + (float) (this.maxValue - this.minValue) * scale) * 100.0f);
            this.drawString(this.mc.fontRendererObj, "" + displayValue + "%", this.xPosition + 130, this.yPosition + 15, 16777215);
        }
        this.overSlide = this.mouseIn(mouseX, mouseY, sliderXPos, sliderYPos, sliderXPos2, sliderYPos2);
        boolean bl = this.overBar = this.mouseIn(mouseX, mouseY, sliderLeft, sliderYPos, sliderRight, sliderYPos2) && !this.overSlide;
        if (this.dragging) {
            if (Mouse.isButtonDown(0)) {
                this.offset = Math.min(Math.max(mouseX - (sliderXPos + sliderXPos2) / 2, sliderMinX), sliderMaxX);
                this.value = (float) this.offset / (float) sliderMaxX;
            } else {
                this.value = (float) this.offset / (float) sliderMaxX;
                this.propertyProvider.setProperty(this.propertyBinding, Math.round(this.value) + 1);
                this.dragging = false;
            }
        } else {
            this.offset = (this.propertyProvider.getIntProperty(this.propertyBinding) - 1) * sliderMaxX;
            this.value = (float) this.offset / (float) sliderMaxX;
        }
        if (this.setBar) {
            this.offset = mouseX - (sliderXPos + sliderXPos2) / 2;
            this.value = (float) this.offset / (float) sliderMaxX;
            this.propertyProvider.setProperty(this.propertyBinding, Math.round(this.value) + 1);
            this.setBar = false;
            this.dragging = true;
        }
        if (this.offset > sliderMaxX) {
            this.offset = sliderMaxX;
        }
        if (this.offset < sliderMinX) {
            this.offset = sliderMinX;
        }
        VoxelPropertyIntSlider.drawRect(sliderXPos2 - 1 + this.offset, sliderYPos2 - 1, sliderXPos + 1 + this.offset, sliderYPos + 1, -16777216);
        gui.drawTessellatedModalBorderRect(LiteModVoxelCommon.GUIPARTS, 256, sliderXPos + this.offset, sliderYPos, sliderXPos2 + this.offset, sliderYPos2, 0, !this.overSlide && !this.dragging ? 0 : 16, 16, !this.overSlide && !this.dragging ? 16 : 32, 4);
    }

    protected boolean mouseIn(int mouseX, int mouseY, int x1, int y1, int x2, int y2) {
        return mouseX > x1 + this.offset && mouseX < x2 + this.offset && mouseY > y1 && mouseY < y2;
    }

    protected boolean mouseOverReset(int mouseX, int mouseY) {
        return mouseX > this.xPosition + 159 && mouseX < this.xPosition + 213 && mouseY > this.yPosition + 10 && mouseY < this.yPosition + 27;
    }

    public void mouseClicked(int mouseX, int mouseY) {
        if (this.overSlide) {
            this.dragging = true;
            this.playClickSound(this.mc.getSoundHandler());
        } else if (this.overBar) {
            this.setBar = true;
            this.playClickSound(this.mc.getSoundHandler());
        } else if (this.overReset) {
            this.propertyProvider.setProperty(this.propertyBinding, 2);
            this.playClickSound(this.mc.getSoundHandler());
        }
    }

    public void keyTyped(char keyChar, int keyCode) {
    }
}

