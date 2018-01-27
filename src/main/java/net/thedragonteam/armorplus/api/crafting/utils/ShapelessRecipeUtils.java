package net.thedragonteam.armorplus.api.crafting.utils;

import com.google.common.collect.Lists;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.thedragonteam.armorplus.container.base.InventoryCraftingImproved;

import java.util.List;

public class ShapelessRecipeUtils {

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    public static boolean matches(List<ItemStack> input, InventoryCraftingImproved inv) {
        List<ItemStack> list = Lists.newArrayList(input);

        for (int i = 0; i < inv.getHeight(); ++i) {
            for (int j = 0; j < inv.getWidth(); ++j) {
                ItemStack itemstack = inv.getStackInRowAndColumn(j, i);

                if (!itemstack.isEmpty()) {
                    boolean flag = false;

                    for (ItemStack itemstack1 : list) {
                        if (itemstack.getItem() == itemstack1.getItem() && (itemstack1.getMetadata() == OreDictionary.WILDCARD_VALUE || itemstack.getMetadata() == itemstack1.getMetadata())) {
                            flag = true;
                            list.remove(itemstack1);
                            break;
                        }
                    }

                    if (!flag) return false;
                }
            }
        }

        return list.isEmpty();
    }
}
