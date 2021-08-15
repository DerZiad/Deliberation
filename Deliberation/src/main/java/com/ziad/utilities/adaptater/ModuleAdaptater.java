package com.ziad.utilities.adaptater;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.ziad.models.Modulee;
import java.lang.reflect.Type;

public class ModuleAdaptater implements JsonSerializer<Modulee>{
    @Override
    public JsonElement serialize(Modulee module, Type type, JsonSerializationContext jsc) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id_filiere", module.getSemestre().getEtape().getFiliere().getId_filiere());
        jsonObject.addProperty("id_module", module.getId_module());
        jsonObject.addProperty("id_semestre", module.getSemestre().getId_semestre());
        jsonObject.addProperty("libelle_module", module.getLibelle_module());
        jsonObject.addProperty("validation", module.getValidation());
        jsonObject.addProperty("coefficient", module.getCoeficient());
        jsonObject.addProperty("eliminatoire", module.getEliminatoire());
        return jsonObject;      
    }
}