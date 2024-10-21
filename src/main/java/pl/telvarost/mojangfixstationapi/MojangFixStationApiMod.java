/*
 * Copyright (C) 2022-2024 js6pak
 *
 * This file is part of MojangFixStationAPI.
 *
 * MojangFixStationAPI is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation, version 3.
 *
 * MojangFixStationAPI is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with MojangFixStationAPI. If not, see <https://www.gnu.org/licenses/>.
 */

package pl.telvarost.mojangfixstationapi;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MojangFixStationApiMod implements ModInitializer {
    private static Logger LOGGER;
    private static ModMetadata METADATA;
    public static boolean isRetroCommandsLoaded;

    @Override
    public void onInitialize() {
        ModContainer mod = FabricLoader.getInstance()
                .getModContainer("mojangfixstationapi")
                .orElseThrow(NullPointerException::new);

        METADATA = mod.getMetadata();
        LOGGER = LoggerFactory.getLogger(METADATA.getName());

        isRetroCommandsLoaded = (  FabricLoader.getInstance().isModLoaded("spc")
                                || FabricLoader.getInstance().isModLoaded("retrocommands")
                                );
    }

    public static Logger getLogger() {
        if (LOGGER == null) {
            throw new IllegalStateException("Logger not yet available");
        }

        return LOGGER;
    }

    public static ModMetadata getMetadata() {
        if (METADATA == null) {
            throw new NullPointerException("Metadata hasn't been populated yet");
        }

        return METADATA;
    }

    public static String getVersion() {
        return getMetadata().getVersion().getFriendlyString();
    }
}
