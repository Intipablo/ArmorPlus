/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.armors.base;

import net.darkhax.tesla.api.ITeslaConsumer;
import net.darkhax.tesla.api.ITeslaHolder;
import net.darkhax.tesla.api.ITeslaProducer;
import net.darkhax.tesla.api.implementation.BaseTeslaContainer;
import net.darkhax.tesla.lib.TeslaUtils;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.base.BaseARPTeslaContainerProvider;
import net.thedragonteam.armorplus.util.ARPTeslaUtils;

import java.util.List;

import static net.thedragonteam.armorplus.ARPConfig.costSteelArmor;

public class BaseTeslaArmor extends ItemArmor implements ITeslaConsumer, ITeslaProducer, ITeslaHolder {

    @Override
    public long givePower(long power, boolean simulated) {
        return output;
    }

    @Override
    public long takePower(long power, boolean simulated) {
        return output;
    }

    @Override
    public long getStoredPower() {
        return power;
    }

    @Override
    public long getCapacity() {
        return maxCapacity;
    }

    public int getPower() {
        return power;
    }

    private int power;
    private int maxCapacity;
    private int output;
    private int input;

    public BaseTeslaArmor(ArmorMaterial armorMaterial, int armorPreffix, EntityEquipmentSlot slot, String name, int power, int maxCapacity, int input, int output) {
        super(armorMaterial, armorPreffix, slot);
        setMaxStackSize(1);
        setRegistryName(name);
        setUnlocalizedName(ArmorPlus.MODID + "." + name);
        GameRegistry.register(this);
        setCreativeTab(ArmorPlus.tabArmorplus);
        setMaxStackSize(1);
        this.power = power;
        this.maxCapacity = maxCapacity;
        this.output = output;
        this.input = input;
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        if (player.getLastAttacker() != null && player.hurtTime > 0)
            ARPTeslaUtils.usePower(itemStack, costSteelArmor);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        ItemStack powered = ARPTeslaUtils.createChargedStack(new ItemStack(itemIn));
        ItemStack unpowered = new ItemStack(itemIn);
        subItems.add(powered);
        subItems.add(unpowered);
    }

    @Override
    public boolean isRepairable() {
        return false;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return (1 - (double) ARPTeslaUtils.getStoredPower(stack) / (double) ARPTeslaUtils.getMaxCapacity(stack));
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return true;
    }


    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        TeslaUtils.createTooltip(stack, tooltip);
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
        return new BaseARPTeslaContainerProvider(new BaseTeslaContainer(), power, maxCapacity, output, input);
    }
}
