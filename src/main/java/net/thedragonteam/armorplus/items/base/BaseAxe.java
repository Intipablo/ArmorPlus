/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.base;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.util.Utils;

public class BaseAxe extends ItemAxe implements IItemHelper {

    public String itemName;

    public BaseAxe(ToolMaterial material, String name) {
        super(material);
        this.itemName = name;
        setUnlocalizedName(Utils.setName(name));
        setRegistryName(name);
        GameRegistry.register(this);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void initModel() {
        if (getRegistryName() != null)
            ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Override
    public ItemStack getItemStack(ItemStack stack) {
        return stack;
    }

    @Override
    public Item getItem(Item item) {
        return item;
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(this);
    }

    @Override
    public Item getItem() {
        return this;
    }

    @Override
    public String getName(String name) {
        return name;
    }

    @Override
    public String getName() {
        return this.itemName;
    }
}
