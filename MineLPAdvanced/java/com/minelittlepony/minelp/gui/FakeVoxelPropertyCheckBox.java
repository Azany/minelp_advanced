/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  com.thevoxelbox.common.LiteModVoxelCommon
 *  com.thevoxelbox.common.interfaces.IVoxelPropertyProvider
 *  com.thevoxelbox.common.interfaces.IVoxelPropertyProviderInteger
 *  com.thevoxelbox.common.util.gui.IAdvancedDrawGui
 *  com.thevoxelbox.common.util.properties.VoxelProperty
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.util.ResourceLocation
 */
package com.minelittlepony.minelp.gui;

import com.thevoxelbox.common.LiteModVoxelCommon;
import com.thevoxelbox.common.interfaces.IVoxelPropertyProvider;
import com.thevoxelbox.common.interfaces.IVoxelPropertyProviderInteger;
import com.thevoxelbox.common.util.gui.IAdvancedDrawGui;
import com.thevoxelbox.common.util.properties.VoxelProperty;

public class FakeVoxelPropertyCheckBox
        extends VoxelProperty<IVoxelPropertyProviderInteger> {
    private int width = 11;

    public FakeVoxelPropertyCheckBox(IVoxelPropertyProvider propertyProvider, String binding, String text, int xPos, int yPos) {
        super(propertyProvider, binding, text, xPos, yPos);
        this.width = this.fontRenderer.getStringWidth(this.displayText) + 20;
    }

    @Override
    public void draw(IAdvancedDrawGui host, int mouseX, int mouseY) {
        this.drawString(this.fontRenderer, this.displayText, this.xPosition + 20, this.yPosition + 2, 16777215);
        boolean overButton = this.mouseOver(mouseX, mouseY);
        boolean checked = true;
        try {
            int readInt = (this.propertyProvider).getIntProperty(this.propertyBinding);
            if (readInt < 2 && readInt > -1) {
                checked = readInt != 0;
            }
        } catch (Exception e) {
            // empty catch block
        }
        host.drawTessellatedModalBorderRect(LiteModVoxelCommon.GUIPARTS, 256, this.xPosition, this.yPosition, this.xPosition + 11, this.yPosition + 11, 0, overButton ? 16 : 0, 16, overButton ? 32 : 16, 4);
        host.drawTexturedModalRect(LiteModVoxelCommon.GUIPARTS, this.xPosition, this.yPosition, this.xPosition + 10, this.yPosition + 10, checked ? 12 : 0, 52, checked ? 23 : 11, 63);
    }

    public void mouseClicked(int mouseX, int mouseY) {
        if (this.mouseOver(mouseX, mouseY)) {
            boolean checked = true;
            try {
                int readInt = this.propertyProvider.getIntProperty(this.propertyBinding);
                if (readInt < 2 && readInt > -1) {
                    checked = readInt != 0;
                }
            } catch (Exception e) {
                // empty catch block
            }
            if (checked) {
                this.propertyProvider.setProperty(this.propertyBinding, 0);
            } else {
                this.propertyProvider.setProperty(this.propertyBinding, 1);
            }
        }
    }

    public boolean mouseOver(int mouseX, int mouseY) {
        return mouseX > this.xPosition && mouseX < this.xPosition + this.width && mouseY > this.yPosition && mouseY < this.yPosition + 11;
    }

    public void keyTyped(char keyChar, int keyCode) {
    }
}

