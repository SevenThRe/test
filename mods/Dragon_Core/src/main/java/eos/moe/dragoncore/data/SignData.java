/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package eos.moe.dragoncore.data;

import java.util.List;
import net.minecraft.item.ItemStack;

public class SignData {
    private List<String> info;
    private String model;
    private String check;
    private int colorR;
    private int colorG;
    private int colorB;
    private int colorA;
    private double offsetY;
    private String item;
    private ItemStack itemStack;

    public SignData() {
        SignData a2;
    }

    public List<String> getInfo() {
        SignData a2;
        return a2.info;
    }

    public String getModel() {
        SignData a2;
        return a2.model;
    }

    public int getColorR() {
        SignData a2;
        return a2.colorR;
    }

    public int getColorG() {
        SignData a2;
        return a2.colorG;
    }

    public int getColorB() {
        SignData a2;
        return a2.colorB;
    }

    public int getColorA() {
        SignData a2;
        return a2.colorA;
    }

    public double getOffsetY() {
        SignData a2;
        return a2.offsetY;
    }

    public String getItem() {
        SignData a2;
        return a2.item;
    }

    public void setInfo(List<String> a2) {
        a.info = a2;
    }

    public void setModel(String a2) {
        a.model = a2;
    }

    public void setColorR(int a2) {
        a.colorR = a2;
    }

    public void setColorG(int a2) {
        a.colorG = a2;
    }

    public void setColorB(int a2) {
        a.colorB = a2;
    }

    public void setColorA(int a2) {
        a.colorA = a2;
    }

    public void setOffsetY(double a2) {
        a.offsetY = a2;
    }

    public void setItem(String a2) {
        a.item = a2;
    }

    public String getCheck() {
        SignData a2;
        return a2.check;
    }

    public void setCheck(String a2) {
        a.check = a2;
    }

    public ItemStack getItemStack() {
        SignData a2;
        return a2.itemStack;
    }

    public void setItemStack(ItemStack a2) {
        a.itemStack = a2;
    }
}

