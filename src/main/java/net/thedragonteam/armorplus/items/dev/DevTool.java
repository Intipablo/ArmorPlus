/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.dev;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.items.base.BaseItem;
import net.thedragonteam.armorplus.util.EnumHelperUtil;

import java.util.List;

import static net.thedragonteam.armorplus.util.ToolTipUtils.showInfo;

public class DevTool extends BaseItem {

    private EnumRarity dev;

    public DevTool() {
        super("dev_tool");
        dev = EnumHelperUtil.addRarity("DEV", TextFormatting.BOLD, "Dev");
        this.setCreativeTab(ArmorPlus.tabArmorplusItems);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return dev;
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
        if (!playerIn.world.isRemote) {
            if (target != null) {
                playerIn.sendStatusMessage(new TextComponentString(TextFormatting.RED +
                        "[" + target.getName() + "]"
                        + " - " + "Health: " + target.getHealth()
                        + " - " + "Max Health: " + target.getMaxHealth()
                        + " - " + "Class: " + target.getClass()
                        + " - " + "Held Item Off Hand: " + target.getHeldItemOffhand()
                        + " - " + "Held Item Main Hand: " + target.getHeldItemMainhand()
                        + " - " + "Position: " + target.getPosition()), false);
                return true;
            }
            return true;
        }
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced) {
        final KeyBinding keyBindSneak = Minecraft.getMinecraft().gameSettings.keyBindSneak;
        if (GameSettings.isKeyDown(keyBindSneak)) {
            tooltip.add("\2479Ability: " + "\247rGives Information about the Target");
            tooltip.add("\2473Use: " + "\247rRight Click a Target");
        } else {
            showInfo(tooltip, keyBindSneak, TextFormatting.BOLD);
        }
    }
}
