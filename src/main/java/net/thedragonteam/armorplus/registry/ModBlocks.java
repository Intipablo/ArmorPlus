/*
 * Copyright (c) TheDragonTeam 2016.
 */

package net.thedragonteam.armorplus.registry;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.thedragonteam.armorplus.blocks.base.BlockBench;
import net.thedragonteam.armorplus.blocks.benches.Benches;
import net.thedragonteam.armorplus.blocks.castle.StoneBricks;
import net.thedragonteam.armorplus.blocks.castle.base.BlockStoneBrick;
import net.thedragonteam.armorplus.blocks.castle.base.BlockStoneBrickCorner;
import net.thedragonteam.armorplus.blocks.castle.base.BlockStoneBrickTower;
import net.thedragonteam.armorplus.blocks.castle.base.BlockStonebrickWall;
import net.thedragonteam.armorplus.blocks.normal.BlockLavaCrystal;
import net.thedragonteam.armorplus.blocks.normal.CompressedObsidian;
import net.thedragonteam.armorplus.blocks.normal.LavaCactus;
import net.thedragonteam.armorplus.blocks.normal.LavaNetherBrick;
import net.thedragonteam.armorplus.blocks.v2.BaseMetalBlock;

import static net.thedragonteam.armorplus.blocks.benches.Benches.*;
import static net.thedragonteam.armorplus.blocks.castle.StoneBricks.*;
import static net.thedragonteam.armorplus.blocks.v2.Metals.ELECTRICAL;
import static net.thedragonteam.armorplus.blocks.v2.Metals.STEEL;

/**
 * net.thedragonteam.armorplus.registry
 * ArmorPlus created by sokratis12GR on 6/13/2016 9:39 PM.
 * - TheDragonTeam
 */
public class ModBlocks {

    public static BlockLavaCrystal blockLavaCrystal;
    public static CompressedObsidian compressedObsidian;
    public static BaseMetalBlock steelBlock;
    public static BaseMetalBlock electricalBlock;
    public static LavaNetherBrick lavaNetherBrick;
    public static BlockStoneBrick whiteStoneBrick, redStoneBrick, blackStoneBrick, blueStoneBrick, yellowStoneBrick, greenStoneBrick, purpleStoneBrick;
    public static BlockStoneBrick[] stoneBricks = new BlockStoneBrick[]{
            whiteStoneBrick, redStoneBrick, blackStoneBrick, blueStoneBrick, yellowStoneBrick, greenStoneBrick, purpleStoneBrick
    };
    public static BlockStoneBrickTower whiteStoneBrickTower, redStoneBrickTower, blackStoneBrickTower, blueStoneBrickTower, yellowStoneBrickTower, greenStoneBrickTower, purpleStoneBrickTower;
    public static BlockStoneBrickTower[] stoneBrickTowers = new BlockStoneBrickTower[]{
            whiteStoneBrickTower, redStoneBrickTower, blackStoneBrickTower, blueStoneBrickTower, yellowStoneBrickTower, greenStoneBrickTower, purpleStoneBrickTower
    };
    public static BlockStoneBrickCorner whiteStoneBrickCorner, redStoneBrickCorner, blackStoneBrickCorner, blueStoneBrickCorner, yellowStoneBrickCorner, greenStoneBrickCorner, purpleStoneBrickCorner;
    public static BlockStoneBrickCorner[] stoneBrickCorners = new BlockStoneBrickCorner[]{
            whiteStoneBrickCorner, redStoneBrickCorner, blackStoneBrickCorner, blueStoneBrickCorner, yellowStoneBrickCorner, greenStoneBrickCorner, purpleStoneBrickCorner
    };
    public static BlockStonebrickWall whiteStoneBrickWall, redStoneBrickWall, blackStoneBrickWall, blueStoneBrickWall, yellowStoneBrickWall, greenStoneBrickWall, purpleStoneBrickWall;
    public static BlockStonebrickWall[] stonebrickWalls = new BlockStonebrickWall[]{
            whiteStoneBrickWall, redStoneBrickWall, blackStoneBrickWall, blueStoneBrickWall, yellowStoneBrickWall, greenStoneBrickWall, purpleStoneBrickWall
    };
    public static LavaCactus lavaCactus;
    public static BlockBench arpWorkbench, arpHighTechBench, arpUltiTechBench, arpChampionBench;
    public static BlockBench[] benches = new BlockBench[]{arpWorkbench, arpHighTechBench, arpUltiTechBench, arpChampionBench};
    public static Benches[] benchTypes = new Benches[]{WORKBENCH, HIGH_TECH, ULTI_TECH, CHAMPION};
    public static StoneBricks[] stoneBrickTypes = new StoneBricks[]{WHITE, RED, BLACK, BLUE, GREEN, YELLOW, PURPLE};

    public static void init() {
        blockLavaCrystal = new BlockLavaCrystal();
        compressedObsidian = new CompressedObsidian();
        steelBlock = new BaseMetalBlock(STEEL);
        electricalBlock = new BaseMetalBlock(ELECTRICAL);
        lavaCactus = new LavaCactus();
        lavaNetherBrick = new LavaNetherBrick();
        for (int s = 0; s <= 6; s++) {
            stoneBricks[s] = new BlockStoneBrick(stoneBrickTypes[s]);
            stoneBrickTowers[s] = new BlockStoneBrickTower(stoneBrickTypes[s]);
            stoneBrickCorners[s] = new BlockStoneBrickCorner(stoneBrickTypes[s], stoneBricks[s].getDefaultState());
            stonebrickWalls[s] = new BlockStonebrickWall(stoneBricks[s]);
        }
        for (int b = 0; b <= 3; b++) {
            benches[b] = new BlockBench(benchTypes[b]);
        }
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        blockLavaCrystal.initModel();
        compressedObsidian.initModel();
        steelBlock.initModel();
        electricalBlock.initModel();
        lavaCactus.initModel();
        lavaNetherBrick.initModel();
        for (int i = 0; i <= 6; i++) {
            stoneBricks[i].initModel();
            stoneBrickTowers[i].initModel();
            stoneBrickCorners[i].initModel();
            stonebrickWalls[i].initModel();
        }
        for (int b = 0; b <= 3; b++) {
            benches[b].initModel();
        }
    }
}