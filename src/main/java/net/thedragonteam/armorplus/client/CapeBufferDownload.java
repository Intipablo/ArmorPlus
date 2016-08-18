/*******************************************************************************
 * Copyright (c) TheDragonTeam 2016.
 ******************************************************************************/

package net.thedragonteam.armorplus.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;

@SideOnly(Side.CLIENT)
public class CapeBufferDownload extends Thread {
    public String username;

    public String staticCapeUrl;

    public ResourceLocation resourceLocation;

    public ThreadDownloadImageData capeImage;

    boolean downloaded = false;

    public CapeBufferDownload(String name, String url) {
        username = name;
        staticCapeUrl = url;

        setDaemon(true);
        setName("Cape Download Thread");
    }

    @Override
    public void run() {
        try {
            download();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void download() {
        try {
            resourceLocation = new ResourceLocation("armorplus/" + StringUtils.stripControlCodes(username));

            capeImage = downloadCape();
        } catch (Exception e) {
            e.printStackTrace();
        }

        downloaded = true;
    }

    public ThreadDownloadImageData getImage() {
        return capeImage;
    }

    public ResourceLocation getResourceLocation() {
        return resourceLocation;
    }

    public ThreadDownloadImageData downloadCape() {
        try {
            File capeFile = new File(resourceLocation.getResourcePath() + ".png");

            TextureManager manager = Minecraft.getMinecraft().getTextureManager();
            ThreadDownloadImageData data = new ThreadDownloadImageData(capeFile, staticCapeUrl, null, null);

            manager.loadTexture(resourceLocation, data);

            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}