/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.RenderGlobal
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderBiped
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MovingObjectPosition
 */
package com.minelittlepony.minelp.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class MineLPReflection {
    public static MineLPRData forgeAPI;

    static {
        MineLPLogger.info("Checking compatibilities...");
        forgeAPI = new MineLPRData();
        MineLPReflection.forgeAPI.compatible = MineLPReflection.reflectForgeAPI(forgeAPI);
        MineLPLogger.info("Compatibility Check Done!");
        if (MineLPReflection.forgeAPI.installed) {
            MineLPLogger.info("ForgeAPI " + (MineLPReflection.forgeAPI.compatible ? "Installed and Compatible" : "Installed but Incompatible"));
        }
    }

    public static void preCall() {
    }

    private static boolean reflectForgeAPI(MineLPRData data) {
        Class forgeAPIIItemRenderer;
        Class forgeAPIIItemRendererItemRendererHelper;
        Class forgeAPIForgeHooksClient;
        Class forgeAPIRenderBiped;
        Class forgeAPIMinecraftForgeClient;
        MineLPLogger.info("Checking ForgeAPI...");
        Class forgeAPIIItemRendererItemRenderType = MineLPReflection.getClass("net.minecraftforge.client.IItemRenderer$ItemRenderType");
        Class[] reflectedForgeAPIClasses = new Class[]{forgeAPIIItemRendererItemRenderType, forgeAPIIItemRendererItemRendererHelper = MineLPReflection.getClass("net.minecraftforge.client.IItemRenderer$ItemRendererHelper"), forgeAPIIItemRenderer = MineLPReflection.getClass("net.minecraftforge.client.IItemRenderer"), forgeAPIMinecraftForgeClient = MineLPReflection.getClass("net.minecraftforge.client.MinecraftForgeClient"), forgeAPIForgeHooksClient = MineLPReflection.getClass("net.minecraftforge.client.ForgeHooksClient"), forgeAPIRenderBiped = MineLPReflection.getClass("net.minecraft.src.RenderBiped")};
        data.installed = false;
        int n = reflectedForgeAPIClasses.length;
        int n2 = 0;
        if (n2 < n) {
            Class c = reflectedForgeAPIClasses[n2];
            if (c != null) {
                data.installed = true;
            } else {
                return false;
            }
        }
        data.putClass("ForgeHooksClient", forgeAPIForgeHooksClient);
        data.putClass("IItemRenderer", forgeAPIIItemRenderer);
        data.putClass("MinecraftForgeClient", forgeAPIMinecraftForgeClient);
        data.putClass("IItemRenderer$ItemRenderType", forgeAPIIItemRendererItemRenderType);
        data.putClass("IItemRenderer$ItemRendererHelper", forgeAPIIItemRendererItemRendererHelper);
        Method m = MineLPReflection.getMethod(forgeAPIForgeHooksClient, "getArmorModel", EntityLivingBase.class, ItemStack.class, Integer.TYPE, ModelBiped.class);
        data.putMethod("ForgeHooksClient.getArmorModel", m);
        if (m == null) {
            return false;
        }
        MineLPLogger.debug("ForgeAPI Method " + MineLPReflection.stringMethod(m));
        m = MineLPReflection.getMethod(forgeAPIForgeHooksClient, "getArmorTexture", Entity.class, ItemStack.class, String.class, Integer.TYPE, String.class);
        data.putMethod("ForgeHooksClient.getArmorTexture", m);
        if (m == null) {
            return false;
        }
        MineLPLogger.debug("ForgeAPI Method " + MineLPReflection.stringMethod(m));
        m = MineLPReflection.getMethod(forgeAPIForgeHooksClient, "orientBedCamera", Minecraft.class, EntityLivingBase.class);
        data.putMethod("ForgeHooksClient.orientBedCamera", m);
        if (m == null) {
            return false;
        }
        MineLPLogger.debug("ForgeAPI Method " + MineLPReflection.stringMethod(m));
        m = MineLPReflection.getMethod(forgeAPIForgeHooksClient, "setRenderPass", Integer.TYPE);
        data.putMethod("ForgeHooksClient.setRenderPass", m);
        if (m == null) {
            return false;
        }
        MineLPLogger.debug("ForgeAPI Method " + MineLPReflection.stringMethod(m));
        m = MineLPReflection.getMethod(forgeAPIForgeHooksClient, "onDrawBlockHighlight", RenderGlobal.class, EntityPlayer.class, MovingObjectPosition.class, Integer.TYPE, ItemStack.class, Float.TYPE);
        data.putMethod("ForgeHooksClient.onDrawBlockHighlight", m);
        if (m == null) {
            return false;
        }
        MineLPLogger.debug("ForgeAPI Method " + MineLPReflection.stringMethod(m));
        m = MineLPReflection.getMethod(forgeAPIForgeHooksClient, "dispatchRenderLast", RenderGlobal.class, Float.TYPE);
        data.putMethod("ForgeHooksClient.dispatchRenderLast", m);
        if (m == null) {
            return false;
        }
        MineLPLogger.debug("ForgeAPI Method " + MineLPReflection.stringMethod(m));
        m = MineLPReflection.getMethod(forgeAPIMinecraftForgeClient, "getItemRenderer", ItemStack.class, forgeAPIIItemRendererItemRenderType);
        data.putMethod("MinecraftForgeClient.getItemRenderer", m);
        if (m == null) {
            return false;
        }
        MineLPLogger.debug("ForgeAPI Method " + MineLPReflection.stringMethod(m));
        m = MineLPReflection.getMethod(forgeAPIIItemRenderer, "shouldUseRenderHelper", forgeAPIIItemRendererItemRenderType, ItemStack.class, forgeAPIIItemRendererItemRendererHelper);
        data.putMethod("IItemRenderer.shouldUseRenderHelper", m);
        if (m == null) {
            return false;
        }
        MineLPLogger.debug("ForgeAPI Method " + MineLPReflection.stringMethod(m));
        m = MineLPReflection.getMethod(Item.class, "getRenderPasses", Integer.TYPE);
        data.putMethod("Item.getRenderPasses", m);
        if (m == null) {
            return false;
        }
        MineLPLogger.debug("ForgeAPI Method " + MineLPReflection.stringMethod(m));
        m = MineLPReflection.getMethod(RenderBiped.class, "getArmorResource", Entity.class, ItemStack.class, Integer.TYPE, String.class);
        data.putMethod("RenderBiped.getArmorResource", m);
        if (m == null) {
            return false;
        }
        MineLPLogger.debug("ForgeAPI Method " + MineLPReflection.stringMethod(m));
        m = MineLPReflection.getMethod(Entity.class, "canRiderInteract");
        data.putMethod("Entity.canRiderInteract", m);
        if (m == null) {
            return false;
        }
        MineLPLogger.debug("ForgeAPI Method " + MineLPReflection.stringMethod(m));
        m = MineLPReflection.getMethod(RenderGlobal.class, "drawBlockDamageTexture", Tessellator.class, EntityLivingBase.class, Float.TYPE);
        data.putMethod("RenderGlobal.drawBlockDamageTexture", m);
        if (m == null) {
            return false;
        }
        MineLPLogger.debug("ForgeAPI Method " + MineLPReflection.stringMethod(m));
        Object[] enumConstants = forgeAPIIItemRendererItemRenderType.getEnumConstants();
        Object o = enumConstants[0];
        data.putObject("IItemRenderer$ItemRenderType.ENTITY", o);
        if (o == null) {
            return false;
        }
        MineLPLogger.debug("ForgeAPI Object " + o.toString());
        o = enumConstants[1];
        data.putObject("IItemRenderer$ItemRenderType.EQUIPPED", o);
        if (o == null) {
            return false;
        }
        MineLPLogger.debug("ForgeAPI Object " + o.toString());
        o = enumConstants[2];
        data.putObject("IItemRenderer$ItemRenderType.EQUIPPED_FIRST_PERSON", o);
        if (o == null) {
            return false;
        }
        MineLPLogger.debug("ForgeAPI Object " + o.toString());
        o = enumConstants[3];
        data.putObject("IItemRenderer$ItemRenderType.INVENTORY", o);
        if (o == null) {
            return false;
        }
        MineLPLogger.debug("ForgeAPI Object " + o.toString());
        o = enumConstants[4];
        data.putObject("IItemRenderer$ItemRenderType.FIRST_PERSON_MAP", o);
        if (o == null) {
            return false;
        }
        MineLPLogger.debug("ForgeAPI Object " + o.toString());
        enumConstants = forgeAPIIItemRendererItemRendererHelper.getEnumConstants();
        o = enumConstants[0];
        data.putObject("IItemRenderer$ItemRendererHelper.ENTITY_ROTATION", o);
        if (o == null) {
            return false;
        }
        MineLPLogger.debug("ForgeAPI Object " + o.toString());
        o = enumConstants[1];
        data.putObject("IItemRenderer$ItemRendererHelper.ENTITY_BOBBING", o);
        if (o == null) {
            return false;
        }
        MineLPLogger.debug("ForgeAPI Object " + o.toString());
        o = enumConstants[2];
        data.putObject("IItemRenderer$ItemRendererHelper.EQUIPPED_BLOCK", o);
        if (o == null) {
            return false;
        }
        MineLPLogger.debug("ForgeAPI Object " + o.toString());
        o = enumConstants[3];
        data.putObject("IItemRenderer$ItemRendererHelper.BLOCK_3D", o);
        if (o == null) {
            return false;
        }
        MineLPLogger.debug("ForgeAPI Object " + o.toString());
        o = enumConstants[4];
        data.putObject("IItemRenderer$ItemRendererHelper.INVENTORY_BLOCK", o);
        if (o == null) {
            return false;
        }
        MineLPLogger.debug("ForgeAPI Object " + o.toString());
        if (forgeAPI.removeNullData()) {
            MineLPLogger.warn("ForgeAPI reflection returned some nulls");
        }
        return true;
    }

    public static Class<?> getClass(String className) {
        try {
            return Class.forName(className);
        } catch (Exception e) {
            return null;
        }
    }

    public static /* varargs */ Constructor<?> getConstructor(Class<?> clazz, Class<?>... types) {
        try {
            Constructor con = null;
            try {
                con = types == null || types.length == 0 ? clazz.getConstructor() : clazz.getConstructor(types);
            } catch (Exception e) {
                // empty catch block
            }
            return con;
        } catch (Exception e) {
            MineLPLogger.error("Failed to match Constructor for class \"%s\"", clazz.getName());
            e.printStackTrace();
            return null;
        }
    }

    public static Field getField(Class<?> clazz, String fieldName) {
        Throwable ex = null;
        try {
            Field f = null;
            try {
                f = clazz.getField(fieldName);
            } catch (Exception ignored) {
                f = null;
            }
            if (f == null) {
                f = clazz.getDeclaredField(fieldName);
            }
            f.setAccessible(true);
            return f;
        } catch (Exception e) {
            MineLPLogger.error("Failed to match Field \"%s\" in %s", fieldName, clazz.getName());
            e.printStackTrace();
        }
        return null;
    }

    public static /* varargs */ Method getMethod(Class<?> clazz, String methodName, Class<?>... types) {
        try {
            Method m = null;
            try {
                m = clazz.getMethod(methodName, types);
            } catch (Exception e) {
                // empty catch block
            }
            if (m == null) {
                m = types != null && types.length != 0 ? clazz.getDeclaredMethod(methodName, types) : clazz.getDeclaredMethod(methodName);
            }
            m.setAccessible(true);
            return m;
        } catch (Exception e) {
            MineLPLogger.error("Failed to match method \"%s\" in %s", methodName, clazz.getName());
            MineLPLogger.error("Types: " + MineLPReflection.getStringFromTypes(types));
            e.printStackTrace();
            return null;
        }
    }

    public static /* varargs */ String getStringFromTypes(Class<?>... types) {
        String temp = "";
        temp = temp + "(";
        boolean first = true;
        for (Class c : types) {
            if (!first) {
                temp = temp + ",";
            } else {
                first = false;
            }
            temp = temp + c.getName();
        }
        temp = temp + ")";
        return temp;
    }

    public static /* varargs */ Class<?>[] getTypesFromObjects(Object... objects) {
        Class[] types = new Class[objects.length];
        for (int i = 0; i < objects.length; ++i) {
            types[i] = objects[i].getClass();
        }
        return types;
    }

    public static void printClass(Class<?> c) {
        MineLPReflection.printClass(c, true);
    }

    public static void printClass(Class<?> c, boolean declared) {
        MineLPReflection.printClass(c, declared, 0);
    }

    public static void printClass(Class<?> c, boolean declared, int indent) {
        int i;
        String indentation = "";
        if (indent > 0) {
            char[] indentBuffer = new char[indent];
            for (i = 0; i < indent; ++i) {
                indentBuffer[i] = 32;
            }
            indentation = String.valueOf(indentBuffer);
        }
        String lineFormat = indentation + "%03d";
        System.out.println(indentation + c.getName());
        System.out.println(indentation + "Nested Classes:");
        for (Class cc2 : c.getClasses()) {
            MineLPReflection.printClass(cc2, declared, indent + 1);
        }
        System.out.println(indentation + "Constructors:");
        i = 0;
        for (Constructor<?> cc : c.getConstructors()) {
            System.out.println(String.format(lineFormat, i) + " " + MineLPReflection.stringConstructor(cc));
            ++i;
        }
        System.out.println(indentation + "Methods:");
        i = 0;
        for (Method m2 : c.getMethods()) {
            System.out.println(String.format(lineFormat, i) + " " + MineLPReflection.stringMethod(m2));
            ++i;
        }
        System.out.println(indentation + "Fields:");
        i = 0;
        for (Field f2 : c.getFields()) {
            System.out.println(String.format(lineFormat, i) + " " + MineLPReflection.stringField(f2));
            ++i;
        }
        if (declared) {
            System.out.println(indentation + "Declared Methods:");
            i = 0;
            for (Method m : c.getDeclaredMethods()) {
                System.out.println(String.format(lineFormat, i) + " " + MineLPReflection.stringMethod(m));
                ++i;
            }
            System.out.println(indentation + "Declared Fields:");
            i = 0;
            for (Field f : c.getDeclaredFields()) {
                System.out.println(String.format(lineFormat, i) + " " + MineLPReflection.stringField(f));
                ++i;
            }
        }
    }

    public static String stringConstructor(Constructor<?> c) {
        return Modifier.toString(c.getModifiers()) + " " + c.getName() + MineLPReflection.getStringFromTypes(c.getParameterTypes()) + (c.getExceptionTypes().length > 0 ? new StringBuilder().append(" throws ").append(c.getExceptionTypes()).toString() : "");
    }

    public static String stringMethod(Method m) {
        return Modifier.toString(m.getModifiers()) + " " + (m.getReturnType() != null ? m.getReturnType().getName() : "void") + " " + m.getName() + MineLPReflection.getStringFromTypes(m.getParameterTypes()) + (m.getExceptionTypes().length > 0 ? new StringBuilder().append(" throws ").append(MineLPReflection.getStringFromTypes(m.getExceptionTypes())).toString() : "");
    }

    public static String stringField(Field f) {
        return Modifier.toString(f.getModifiers()) + " " + f.getType().getName() + " " + f.getName();
    }
}

