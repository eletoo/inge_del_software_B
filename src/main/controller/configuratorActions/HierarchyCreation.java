package main.controller.configuratorActions;

import main.controller.*;
import main.model.*;
import main.model.stores.HierarchiesStore;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * crea una gerarchia
 *
 * @author Elena Tonini, Claudia Manfredi, Mattia Pavlovic
 */
public class HierarchyCreation implements UserSelectable {

    /**
     * Genera i campi nativi (chiedendone nome e obbligatorieta') da aggiungere alla categoria e aggiunge quelli che
     * essa eredita dalla categoria parent
     *
     * @param parent categoria parent da cui ereditare i campi
     * @return campi nativi
     */
    public @NotNull Map<String, NativeField> generateNativeFields(Category parent, Controller controller) {
        Map<String, NativeField> campi = new HashMap<>();

        if (parent == null) {
            campi.putAll(Category.generateRootNativeFields());
        } else {
            campi.putAll(parent.getNativeFields());
        }

        boolean ans;
        do {
            ans = controller.askBooleanFromView(YesOrNoMessage.ADD_NATIVE_FIELD);

            if (ans) {
                String name = controller.askLineFromView(GenericMessage.FIELD_NAME);
                NativeField nuovo = new NativeField(
                        controller.askBooleanFromView(YesOrNoMessage.COMPULSORY_FIELD),
                        NativeField.Tipo.STRING
                );
                campi.put(name, nuovo);
            }
        } while (ans);

        return campi;
    }

    /**
     * aggiunge una categoria figlia a una categoria padre
     *
     * @param controller controller
     * @param padre      categoria padre
     * @param root       categoria radice della gerarchia
     * @return categoria radice
     */
    public Category addChildToCategory(Controller controller, @NotNull CategoryEntry padre, Category root) {
        if (padre.getCat() == root)
            root = padre.asNode();
        else {
            padre.asNode();
        }

        String name = controller.askLineFromView(GenericMessage.CATEGORY_NAME);
        if (!root.isNameTaken(name)) {
            String desc = controller.askPotentiallyEmptyStringFromView(GenericMessage.CATEGORY_DESCRIPTION);
            var cat = new Leaf(name, desc);
            cat.setNativeFields(this.generateNativeFields(padre.getCat(), controller));
            ((Node) padre.getCat()).addChild(cat);
        } else
            controller.signalToView(ErrorMessage.E_EXISTING_NAME_IN_HIERARCHY);

        return root;
    }

    /**
     * crea una categoria
     *
     * @param controller controller
     * @param rootname   nome della radice della gerarchia
     * @return categoria radice
     */
    public Category makeCategory(@NotNull Controller controller, String rootname) {
        Category root = new Leaf(rootname, controller.askPotentiallyEmptyStringFromView(GenericMessage.CATEGORY_DESCRIPTION));
        root.setNativeFields(this.generateNativeFields(null, controller));

        while (!root.isStructureValid() || controller.askBooleanFromView(YesOrNoMessage.ADD_CATEGORY)) {
            CategoryEntry padre = controller.getView().choose(
                    GenericMessage.AT_LEAST_TWO_CHILDREN,
                    getCategoriesAsList(root),
                    CategoryEntry::getDisplayName
            );

            root = this.addChildToCategory(controller, padre, root);
        }
        return root;
    }

    /**
     * crea la gerarchia
     *
     * @param controller controller
     * @param user       utente
     * @throws IOException eccezione I/O
     */
    @Override
    public void runAction(@NotNull Controller controller, User user) throws IOException {
        Application app = controller.getApp();
        String rootname = controller.askLineFromView(GenericMessage.CATEGORY_NAME);

        if (this.getHierarchies(app).isHierarchyNameTaken(rootname)) {
            controller.signalToView(ErrorMessage.E_EXISTING_ROOT_CATEGORY);
            return;
        }

        Category root = makeCategory(controller, rootname);

        Hierarchy h = new Hierarchy(root);
        this.getHierarchies(app).addHierarchy(h);

        if (controller.askBooleanFromView(YesOrNoMessage.SAVE_HIERARCHY)) {
            this.getHierarchies(app).save();
            controller.signalToView(GenericMessage.SAVED_CORRECTLY);
            return;
        }

        this.getHierarchies(app).removeHierarchy(h);
    }

    /**
     * @return descrizione dell'azione
     */
    @Override
    public String getActionName() {
        return "Crea una nuova gerarchia";
    }

    /**
     * ottiene le categorie di una gerarchia in forma di lista
     *
     * @param root radice della gerarchia
     * @return lista di categorie
     */
    @Contract("_ -> new")
    private @NotNull List<CategoryEntry> getCategoriesAsList(Category root) {
        return getCategoriesAsList(root, null, new ArrayList<>(), "");
    }

    /**
     * Ottiene una lista di {@link CategoryEntry} a partire da una categoria radice.
     *
     * @param root    categoria radice
     * @param padre   categoria padre
     * @param choices lista di categorie
     * @param prefix  prefisso
     * @return lista di categorie
     */
    private @NotNull List<CategoryEntry> getCategoriesAsList(Category root, Node padre, @NotNull List<CategoryEntry> choices, String prefix) {
        choices.add(new CategoryEntry(root, padre, prefix + root.getNome()));
        if (root instanceof Node)
            for (Category child : ((Node) root).getCategorieFiglie())
                getCategoriesAsList(child, (Node) root, choices, prefix + root.getNome() + "->");
        return choices;
    }

    /**
     * @param app applicazione
     * @return insieme delle gerarchie
     */
    private HierarchiesStore getHierarchies(@NotNull Application app) {
        return app.getHierarchiesStore();
    }
}
