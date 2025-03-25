package com.senne.oneiros.storage;

import com.senne.oneiros.Oneiros;
import com.senne.oneiros.item.ItemRegister;
import com.senne.oneiros.item.Pack;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static com.senne.oneiros.tools.utils.SerializationUtils.deserialize;
import static com.senne.oneiros.tools.utils.SerializationUtils.serialize;

public class Data {

    public static void load() {


        File packsFolder = new File(Oneiros.getPlugin().getDataFolder() + "/packs");
        if (!packsFolder.exists()) {
            Oneiros.getPlugin().getLogger().info("[Oneiros] Created pack directory: " + packsFolder.mkdirs());
        }

        if (packsFolder.listFiles() == null) return;

        for (File packFile : packsFolder.listFiles()) {
            if (packFile.getName().endsWith(".pack")) {
                try {
                    Pack pack = deserialize(Files.readAllBytes(packFile.toPath()), Pack.class);
                    ItemRegister.registerPackNoSave(pack);
                } catch (IOException e) {
                    Oneiros.getPlugin().getLogger().warning("[Oneiros] Failed to load pack " + packFile.getName());
                    Oneiros.getPlugin().getLogger().throwing("Data", "load", e);
                }
            }
        }

    }


    public static void save() throws IOException {
        for (String pack : ItemRegister.getPacks()) save(pack);
    }

    public static void save(String pack) throws IOException {

        if (!new File(Oneiros.getPlugin().getDataFolder() + "/packs").exists()) {
            new File(Oneiros.getPlugin().getDataFolder() + "/packs").mkdir();
        }

        if (!ItemRegister.getPacks().contains(pack)) throw new IllegalArgumentException("Pack " + pack + " does not exsist");
        Pack itemPack = ItemRegister.getPack(pack);

        byte[] data = serialize(itemPack);

        File packFile = new File(Oneiros.getPlugin().getDataFolder() + "/packs/" + pack + ".pack");
        if (packFile.exists()) {
            packFile.renameTo(new File(Oneiros.getPlugin().getDataFolder() + "/packs/" + pack + ".pack.bak"));
        }

        Files.write(packFile.toPath(), data);
    }
}
