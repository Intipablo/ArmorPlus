package sokratis12GR.ArmorPlus.armors;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import sokratis12GR.ArmorPlus.ArmorPlus;
import sokratis12GR.ArmorPlus.ConfigHandler;

import java.util.Random;

public class EmeraldArmor
{

	public EmeraldArmor()
	{
	}

	public static Item helmet;
	public static Item chestplate;
	public static Item legs;
	public static Item boots;
	public Object instance;

	public void load(FMLInitializationEvent event)
	{
		if (event.getSide() == Side.CLIENT)
		{

			ModelLoader.setCustomModelResourceLocation(helmet, 0,
					new ModelResourceLocation("armorplus:EmeraldHelmet", "inventory"));
			ModelLoader.setCustomModelResourceLocation(chestplate, 0,
					new ModelResourceLocation("armorplus:EmeraldChestplate", "inventory"));
			ModelLoader.setCustomModelResourceLocation(legs, 0,
					new ModelResourceLocation("armorplus:EmeraldLeggings", "inventory"));
			ModelLoader.setCustomModelResourceLocation(boots, 0,
					new ModelResourceLocation("armorplus:EmeraldBoots", "inventory"));
		}
		if (ConfigHandler.enableEmeraldArmorRecipes)
		{
			GameRegistry.addRecipe(new ItemStack(helmet, 1), new Object[]
			{ "XXX", "345", "6X8", Character.valueOf('3'), new ItemStack(Items.emerald, 1), Character.valueOf('4'),
					new ItemStack(Items.emerald, 1), Character.valueOf('5'), new ItemStack(Items.emerald, 1),
					Character.valueOf('6'), new ItemStack(Items.emerald, 1), Character.valueOf('8'),
					new ItemStack(Items.emerald, 1), });
			GameRegistry.addRecipe(new ItemStack(helmet, 1), new Object[]
			{ "012", "3X5", "XXX", Character.valueOf('0'), new ItemStack(Items.emerald, 1), Character.valueOf('1'),
					new ItemStack(Items.emerald, 1), Character.valueOf('2'), new ItemStack(Items.emerald, 1),
					Character.valueOf('3'), new ItemStack(Items.emerald, 1), Character.valueOf('5'),
					new ItemStack(Items.emerald, 1), });
			GameRegistry.addRecipe(new ItemStack(chestplate, 1), new Object[]
			{ "0X2", "345", "678", Character.valueOf('0'), new ItemStack(Items.emerald, 1), Character.valueOf('2'),
					new ItemStack(Items.emerald, 1), Character.valueOf('3'), new ItemStack(Items.emerald, 1),
					Character.valueOf('4'), new ItemStack(Items.emerald, 1), Character.valueOf('5'),
					new ItemStack(Items.emerald, 1), Character.valueOf('6'), new ItemStack(Items.emerald, 1),
					Character.valueOf('7'), new ItemStack(Items.emerald, 1), Character.valueOf('8'),
					new ItemStack(Items.emerald, 1), });
			GameRegistry.addRecipe(new ItemStack(legs, 1), new Object[]
			{ "012", "3X5", "6X8", Character.valueOf('0'), new ItemStack(Items.emerald, 1), Character.valueOf('1'),
					new ItemStack(Items.emerald, 1), Character.valueOf('2'), new ItemStack(Items.emerald, 1),
					Character.valueOf('3'), new ItemStack(Items.emerald, 1), Character.valueOf('5'),
					new ItemStack(Items.emerald, 1), Character.valueOf('6'), new ItemStack(Items.emerald, 1),
					Character.valueOf('8'), new ItemStack(Items.emerald, 1), });
			GameRegistry.addRecipe(new ItemStack(boots, 1), new Object[]
			{ "XXX", "3X5", "6X8", Character.valueOf('3'), new ItemStack(Items.emerald, 1), Character.valueOf('5'),
					new ItemStack(Items.emerald, 1), Character.valueOf('6'), new ItemStack(Items.emerald, 1),
					Character.valueOf('8'), new ItemStack(Items.emerald, 1), });
			GameRegistry.addRecipe(new ItemStack(boots, 1), new Object[]
			{ "0X2", "3X5", "XXX", Character.valueOf('0'), new ItemStack(Items.emerald, 1), Character.valueOf('2'),
					new ItemStack(Items.emerald, 1), Character.valueOf('3'), new ItemStack(Items.emerald, 1),
					Character.valueOf('5'), new ItemStack(Items.emerald, 1), });
			helmet.setCreativeTab(ArmorPlus.tabArmorPlus);
			chestplate.setCreativeTab(ArmorPlus.tabArmorPlus);
			legs.setCreativeTab(ArmorPlus.tabArmorPlus);
			boots.setCreativeTab(ArmorPlus.tabArmorPlus);
		}
	}

	public void generateNether(World world, Random random, int chunkX, int chunkZ)
	{
	}

	public void generateSurface(World world, Random random, int chunkX, int chunkZ)
	{
	}

	public int addFuel(ItemStack fuel)
	{
		return 0;
	}

	public void serverLoad(FMLServerStartingEvent event)
	{
	}

	public void preInit(FMLPreInitializationEvent event)
	{
		if (event.getSide() == Side.CLIENT)
		{

			ModelLoader.setCustomModelResourceLocation(helmet, 0,
					new ModelResourceLocation("armorplus:EmeraldHelmet", "inventory"));
			ModelLoader.setCustomModelResourceLocation(chestplate, 0,
					new ModelResourceLocation("armorplus:EmeraldChestplate", "inventory"));
			ModelLoader.setCustomModelResourceLocation(legs, 0,
					new ModelResourceLocation("armorplus:EmeraldLeggings", "inventory"));
			ModelLoader.setCustomModelResourceLocation(boots, 0,
					new ModelResourceLocation("armorplus:EmeraldBoots", "inventory"));
		}
	}

	public void registerRenderers()
	{
	}

	static
	{

		ItemArmor.ArmorMaterial enuma = EnumHelper.addArmorMaterial("EMERALDARMOR", "EmeraldArmor", 35, new int[]
		{ 3, 6, 9, 4 }, 20, SoundEvents.item_armor_equip_diamond);

		int armorPreffix = 0;
		helmet = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.HEAD)
		{
			public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack)
			{
				if (ConfigHandler.enableEmeraldHHaste)
				{
					if (true)
					{
						if (entity instanceof EntityLivingBase)
							((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.digSpeed, 120, 1));
					}
				}
			}
		}).setUnlocalizedName("EmeraldHelmet");
		helmet.setMaxStackSize(1);

		chestplate = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.CHEST)
		{
			public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack)
			{
				if (ConfigHandler.enableEmeraldCHaste)
				{
					if (true)
					{
						if (entity instanceof EntityLivingBase)
							((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.digSpeed, 120, 1));
					}
				}
			}
		}).setUnlocalizedName("EmeraldChestplate");
		chestplate.setMaxStackSize(1);

		legs = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.LEGS)
		{
			public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack)
			{
				if (ConfigHandler.enableEmeraldLHaste)
				{
					if (true)
					{
						if (entity instanceof EntityLivingBase)
							((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.digSpeed, 120, 1));
					}
				}
			}
		}).setUnlocalizedName("EmeraldLeggings");
		legs.setMaxStackSize(1);

		boots = (new ItemArmor(enuma, armorPreffix, EntityEquipmentSlot.FEET)
		{
			public void onArmorTick(World world, EntityPlayer entity, ItemStack itemStack)
			{
				if (ConfigHandler.enableEmeraldBHaste)
				{
					if (true)
					{
						if (entity instanceof EntityLivingBase)
							((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.digSpeed, 120, 1));
					}
				}
			}
		}).setUnlocalizedName("EmeraldBoots");
		boots.setMaxStackSize(1);

		GameRegistry.registerItem(helmet, "EmeraldHelmet");
		GameRegistry.registerItem(chestplate, "EmeraldChestplate");
		GameRegistry.registerItem(legs, "EmeraldLeggings");
		GameRegistry.registerItem(boots, "EmeraldBoots");

	}
}
