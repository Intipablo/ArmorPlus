/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.thedragonteam.armorplus.api.crafting.championbench.ChampionBenchCraftingManager;
import net.thedragonteam.armorplus.api.crafting.ultitechbench.SlotCrafting;

/**
 * net.thedragonteam.armorplus.container
 * ArmorPlus created by sokratis12GR on 6/19/2016 10:39 AM.
 * - TheDragonTeam
 */
public class ContainerChampionBench extends Container {

    /**
     * The crafting matrix inventory (10x10).
     */
    public InventoryCrafting craftMatrix = new InventoryCrafting(this, 10, 10);
    public IInventory craftResult = new InventoryCraftResult();

    private static final int ITEM_BOX = 18;
    private static final int RECIPE_SLOTS = 101;
    private static final int RECIPE_SIZE = 10;
    private static final int RECIPE_SIZE_TOTAL = 100;
    private static final int ROW_SLOTS = 9;
    private static final int FULL_INVENTORY_SLOTS = RECIPE_SLOTS + 36;
    private static final int MAIN_INVENTORY_SLOTS = RECIPE_SLOTS + 27;

    private final World world;

    public ContainerChampionBench(InventoryPlayer playerInventory, World worldIn) {
        this.world = worldIn;
        //1x1 Output Inventory
        this.addSlotToContainer(new SlotCrafting(playerInventory.player, this.craftMatrix, this.craftResult, 0, 230, 134));

        //10x10 Crafting Inventory
        for (int i = 0; i < RECIPE_SIZE; ++i)
            for (int j = 0; j < RECIPE_SIZE; ++j)
                this.addSlotToContainer(new Slot(this.craftMatrix, j + i * RECIPE_SIZE, 39 + j * ITEM_BOX, 17 + i * ITEM_BOX));
        //2x2 Armor Inventory
        this.addPlayerArmorInventoryTop(playerInventory, 5, 217);
        this.addPlayerArmorInventoryBot(playerInventory, 5, 235);
        //3x9 Main Inventory
        for (int k = 0; k < 3; ++k)
            for (int i1 = 0; i1 < ROW_SLOTS; ++i1)
                this.addSlotToContainer(new Slot(playerInventory, i1 + k * 9 + 9, 48 + i1 * ITEM_BOX, 199 + k * ITEM_BOX));

        //1x9 HotBar Inventory
        for (int l = 0; l < ROW_SLOTS; ++l)
            this.addSlotToContainer(new Slot(playerInventory, l, 5, 17 + l * ITEM_BOX));

        this.onCraftMatrixChanged(this.craftMatrix);
    }

    private static final EntityEquipmentSlot[] EQUIPMENT_SLOTS = new EntityEquipmentSlot[]{
            EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET};

    protected void addPlayerArmorInventoryTop(InventoryPlayer inventory, int xPos, int yPos) {
        for (int k = 0; k < 2; ++k) {
            EntityEquipmentSlot equipmentSlot = EQUIPMENT_SLOTS[k];
            addSlotToContainer(new SlotArmor(inventory, 4 * 9 + (3 - k), xPos + k * ITEM_BOX, yPos, inventory.player, equipmentSlot));
        }
    }

    protected void addPlayerArmorInventoryBot(InventoryPlayer inventory, int xPos, int yPos) {
        for (int k = 0; k < 2; ++k) {
            EntityEquipmentSlot equipmentSlot = EQUIPMENT_SLOTS[k + 2];
            addSlotToContainer(new SlotArmor(inventory, 4 * 9 + (3 - (k + 2)), xPos + k * ITEM_BOX, yPos, inventory.player, equipmentSlot));
        }
    }

    /**
     * Callback for when the crafting matrix is changed.
     */
    public void onCraftMatrixChanged(IInventory inventoryIn) {
        this.craftResult.setInventorySlotContents(0, ChampionBenchCraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.world));
    }

    /**
     * Called when the container is closed.
     */
    public void onContainerClosed(EntityPlayer playerIn) {
        super.onContainerClosed(playerIn);

        if (!this.world.isRemote) {
            for (int i = 0; i < RECIPE_SIZE_TOTAL; ++i) {
                ItemStack itemstack = this.craftMatrix.removeStackFromSlot(i);

                if (!itemstack.isEmpty()) {
                    playerIn.dropItem(itemstack, false);
                }
            }
        }
    }

    /**
     * Determines whether supplied player can use this container
     */
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    /**
     * Take a stack from the specified inventory slot.
     */
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == 0) {
                itemstack1.getItem().onCreated(itemstack1, this.world, playerIn);

                if (!this.mergeItemStack(itemstack1, RECIPE_SLOTS, FULL_INVENTORY_SLOTS, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (index >= RECIPE_SLOTS && index < MAIN_INVENTORY_SLOTS) {
                if (!this.mergeItemStack(itemstack1, MAIN_INVENTORY_SLOTS, FULL_INVENTORY_SLOTS, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= MAIN_INVENTORY_SLOTS && index < FULL_INVENTORY_SLOTS) {
                if (!this.mergeItemStack(itemstack1, RECIPE_SLOTS, MAIN_INVENTORY_SLOTS, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, RECIPE_SLOTS, FULL_INVENTORY_SLOTS, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            ItemStack itemstack2 = slot.onTake(playerIn, itemstack1);

            if (index == 0) {
                playerIn.dropItem(itemstack2, false);
            }
        }

        return itemstack;
    }

    /**
     * Called to determine if the current slot is valid for the stack merging (double-click) code. The stack passed in
     * is null for the initial slot that was double-clicked.
     */
    public boolean canMergeSlot(ItemStack stack, Slot slotIn) {
        return slotIn.inventory != this.craftResult && super.canMergeSlot(stack, slotIn);
    }
}