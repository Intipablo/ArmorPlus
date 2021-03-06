/*
 * Copyright (c) TheDragonTeam 2016-2017.
 */

package net.thedragonteam.armorplus.items.weapons;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.thedragonteam.armorplus.api.properties.iface.IRemovable;
import net.thedragonteam.armorplus.api.properties.iface.IRepairable;

import static net.minecraft.init.Blocks.*;
import static net.minecraft.item.ItemStack.EMPTY;
import static net.minecraft.util.text.TextFormatting.getValueByName;
import static net.thedragonteam.armorplus.ModConfig.RegistryConfig.*;
import static net.thedragonteam.armorplus.registry.APItems.*;
import static net.thedragonteam.armorplus.registry.ModBlocks.compressedObsidian;
import static net.thedragonteam.armorplus.registry.ModItems.lavaCrystal;
import static net.thedragonteam.armorplus.registry.ModItems.materials;
import static net.thedragonteam.thedragonlib.util.ItemStackUtils.getItemStack;

/**
 * @author Sokratis Fotkatzikis - TheDragonTeam
 **/
public enum Bows implements IRepairable, IRemovable {
    COAL("coal", getItemStack(COAL_BLOCK), coal, coalBow, global_registry.enableCoalWeapons),
    LAPIS("lapis", getItemStack(LAPIS_BLOCK), lapis, lapisBow, global_registry.enableEmeraldWeapons),
    REDSTONE("redstone", getItemStack(REDSTONE_BLOCK), redstone, redstoneBow, global_registry.enableRedstoneWeapons),
    EMERALD("emerald", getItemStack(EMERALD_BLOCK), emerald, emeraldBow, global_registry.enableEmeraldWeapons),
    OBSIDIAN("obsidian", getItemStack(compressedObsidian), obsidian, obsidianBow, global_registry.enableObsidianWeapons),
    LAVA("infused_lava", getItemStack(lavaCrystal, 1), lava, lavaBow, global_registry.enableLavaWeapons),
    GUARDIAN("guardian", getItemStack(materials, 1), guardian, guardianBow, global_registry.enableGuardianWeapons),
    SUPER_STAR("super_star", getItemStack(materials, 2), super_star, superStarBow, global_registry.enableSuperStarWeapons),
    ENDER_DRAGON("ender_dragon", getItemStack(materials, 3), ender_dragon, enderDragonBow, global_registry.enableEnderDragonWeapons);

    private final String name;
    private final BowStats bowStats;
    private final boolean isEnabled;
    private final ItemStack repairStack;
    private final TextFormatting textFormatting;
    private final Item bowItem;

    Bows(String nameIn, ItemStack repairStackIn, OriginMaterial material, Item bowItemIn, boolean[] isEnabled) {
        this.name = nameIn;
        this.bowStats = new BowStats(material);
        this.isEnabled = isEnabled[2];
        if (repairStackIn == null) repairStackIn = EMPTY;
        this.repairStack = repairStackIn;
        this.textFormatting = getValueByName(material.weapons.itemNameColor);
        this.bowItem = bowItemIn;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    public String toString() {
        return this.name;
    }

    public String getName() {
        return this.name;
    }

    public double getDamage() {
        return this.bowStats.getBonusDamage();
    }

    public int getDurability() {
        return this.bowStats.getDurability();
    }

    public Item getBowItem() {
        return this.bowItem;
    }

    public ItemStack getRepairStack() {
        return this.repairStack;
    }

    public TextFormatting getTextFormatting() {
        return this.textFormatting;
    }

    private static class BowStats {
        private final int durabilityIn;
        private final double bonusDamage;

        BowStats(OriginMaterial material) {
            this(material.weapons.bow.durability, material.weapons.bow.arrowBonusDamage);
        }

        private BowStats(int durability, double bonusDamage) {
            this.durabilityIn = durability;
            this.bonusDamage = bonusDamage;
        }

        double getBonusDamage() {
            return bonusDamage;
        }

        int getDurability() {
            return durabilityIn;
        }
    }
}
