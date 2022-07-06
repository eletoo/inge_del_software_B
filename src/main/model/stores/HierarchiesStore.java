package main.model.stores;

import com.google.gson.*;
import main.controller.Controller;
import main.controller.ErrorMessage;
import main.controller.GenericMessage;
import main.model.*;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HierarchiesStore implements Loadable, Saveable, Serializable {
    private static final String DB_JSON_FILES = System.getProperty("user.dir") + "/db/jsonFiles/";

    private Map<String, Hierarchy> hierarchies;

    public HierarchiesStore() {
        this.hierarchies = new HashMap<>();
    }

    public Map<String, Hierarchy> getHierarchies() {
        return this.hierarchies;
    }

    public void setHierarchies(Map<String, Hierarchy> hierarchies) {
        this.hierarchies = hierarchies;
    }

    public boolean isHierarchyNameTaken(String name) {
        return hierarchies.containsKey(name);
    }

    public void addHierarchy(Hierarchy h){
        this.hierarchies.put(h.getRoot().getNome(), h);
    }

    public void removeHierarchy(@NotNull Hierarchy h){
        this.hierarchies.remove(h.getRoot().getNome());
    }

    @Override
    public void load() throws IOException {
        var db = new File(System.getProperty("user.dir") + "/db");
        assert db.exists() || db.mkdir();

        var gf = new File(System.getProperty("user.dir") + "/db/gerarchie.dat");
        if (gf.exists()) {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(gf));
            try {
                this.setHierarchies((Map<String, Hierarchy>) ois.readObject());
            } catch (ClassNotFoundException | IOException e) {
                this.setHierarchies(new HashMap<>());

            }
        } else {
            this.setHierarchies(new HashMap<>());
        }
    }

    @Override
    public void loadFromFile() throws IOException {
        Hierarchy h;
        try {
            List<Path> paths = LocalPath.generatePathListForImportFromFile(DB_JSON_FILES);
            Reader reader;

            if (paths.isEmpty()) {
                Controller.signalToView(ErrorMessage.E_INVALID_FILE_CONTENT.getMessage());
                return;
            }

            for (Path p : paths) {
                reader = Files.newBufferedReader(p);
                GsonBuilder builder = new GsonBuilder();
                JsonDeserializer<Category> categoria_deserializer = (json, typeOfT, context) -> {
                    JsonObject jsonObject = json.getAsJsonObject();
                    if (jsonObject.get("figlie") != null && jsonObject.get("figlie").isJsonArray() && jsonObject.get("figlie").getAsJsonArray().size() > 1)
                        return context.deserialize(jsonObject, Node.class);
                    return context.deserialize(jsonObject, Leaf.class);
                };

                JsonDeserializer<Node> nodo_deserializer = (json, typeOfT, context) -> {
                    JsonObject jsonObject = json.getAsJsonObject();
                    var nodo = new Node(jsonObject.get("nome").getAsString(), jsonObject.get("descrizione").getAsString());
                    var cn = new HashMap<String, NativeField>();

                    var o = jsonObject.get("campiNativi").getAsJsonObject();
                    o.keySet().forEach(e -> cn.put(e, context.deserialize(o.get(e), NativeField.class)));
                    nodo.setNativeFields(cn);
                    jsonObject.get("figlie").getAsJsonArray().forEach(e -> nodo.addChild(context.deserialize(e, Category.class)));
                    return nodo;
                };

                JsonDeserializer<Leaf> foglia_deserializer = (json, typeOfT, context) -> {
                    JsonObject jsonObject = json.getAsJsonObject();
                    var f = new Leaf(jsonObject.get("nome").getAsString(), jsonObject.get("descrizione").getAsString());
                    var cn = new HashMap<String, NativeField>();

                    var o = jsonObject.get("campiNativi").getAsJsonObject();
                    o.keySet().forEach(e -> cn.put(e, context.deserialize(o.get(e), NativeField.class)));
                    f.setNativeFields(cn);
                    return f;
                };

                Gson gson = builder
                        .registerTypeAdapter(Category.class, categoria_deserializer)
                        .registerTypeAdapter(Node.class, nodo_deserializer)
                        .registerTypeAdapter(Leaf.class, foglia_deserializer)
                        .create();

                h = gson.fromJson(reader, Hierarchy.class);

                boolean valid = true;

                if (h.getRoot() instanceof Node)
                    for (Category c : ((Node) h.getRoot()).getCategorieFiglie())
                        if (!c.isNameTakenOnlyOnce(h.getRoot())) {
                            valid = false;
                            break;
                        }

                if (!this.isHierarchyNameTaken(h.getRoot().getNome()) && valid) {//non sovrascrive nell'applicazione gerarchie omonime già esistenti
                    this.hierarchies.put(h.getRoot().getNome(), h);
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Controller.signalToView(GenericMessage.SUCCESSFUL_HIERARCHY_IMPORT.getMessage());
        this.save();
        return;
    }

    @Override
    public void save() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(System.getProperty("user.dir") + "/db/gerarchie.dat");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(this.getHierarchies());
        objectOutputStream.close();

        for (Hierarchy h : this.getHierarchies().values()) {
            this.saveOnFile(h);
        }
    }

    @Override
    public void saveOnFile(Serializable hierarchy) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.registerTypeAdapter(Category.class, (JsonSerializer<Category>) (categoria, type, jsonSerializationContext) -> {
            JsonObject ret = new JsonObject();
            ret.addProperty("nome", categoria.getNome());
            ret.addProperty("descrizione", categoria.getDescrizione());
            ret.add("campiNativi", jsonSerializationContext.serialize(categoria.getNativeFields()));
            if (categoria instanceof Node)
                ret.add("figlie", jsonSerializationContext.serialize(((Node) categoria).getCategorieFiglie()));
            return ret;
        }).create();

        String file = DB_JSON_FILES + ((Hierarchy) hierarchy).getRoot().getNome() + ".json";
        try (FileWriter wr = new FileWriter(file)) {
            wr.write(gson.toJson(hierarchy));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
