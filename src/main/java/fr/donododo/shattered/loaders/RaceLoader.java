package fr.donododo.shattered.loaders;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import fr.donododo.shattered.Shattered;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.level.storage.loot.Deserializers;

import java.util.Map;

public class RaceLoader extends SimpleJsonResourceReloadListener {

    private static final Gson GSON_INSTANCE = Deserializers.createFunctionSerializer().create();
    private static final String FOLDER = "races";

    public RaceLoader() {
        super(GSON_INSTANCE, FOLDER);
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> pObject, ResourceManager pResourceManager, ProfilerFiller pProfiler) {
        Shattered.classList.clear();

        pObject.forEach((location, json) -> {
            try {
                String name = json.getAsJsonObject().get("name").getAsString();
                String desc = json.getAsJsonObject().get("description").getAsString();
                PlayerClass cl = new PlayerClass(name, desc);
                cl.strength = Integer.parseInt(parseOrEmpty(json.getAsJsonObject().get("stats").getAsJsonObject(), "strength"));
                cl.consistency = Integer.parseInt(parseOrEmpty(json.getAsJsonObject().get("stats").getAsJsonObject(), "constitution"));
                cl.defense = Integer.parseInt(parseOrEmpty(json.getAsJsonObject().get("stats").getAsJsonObject(), "defense"));

                Shattered.classList.put(cl.name, cl);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public String parseOrEmpty(JsonElement el, String parameter) {
        if (el.getAsJsonObject().get(parameter) == null) {
            return "";
        } else {
            return el.getAsJsonObject().get(parameter).getAsString();
        }
    }
}
