package main.controller.configuratorActions;

import main.Application;
import main.controller.*;
import main.exceptions.InvalidMethodException;
import main.model.*;
import main.model.stores.HierarchiesStore;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HierarchyCreation implements Selectable, ListSelect {
    @Override
    public void runAction(@NotNull Application app) throws IOException {
        createNewHierarchy(app);
    }

    @Override
    public void runAction(Application app, User user) {
        throw new InvalidMethodException();
    }

    @Override
    public String getActionName() {
        return "Crea una nuova gerarchia";
    }

    private void createNewHierarchy(Application app) throws IOException {
        String rootname = Controller.askStringFromView(GenericMessage.CATEGORY_NAME);
        if (this.getHierarchies(app).isHierarchyNameTaken(rootname)) {
            Controller.signalToView(ErrorMessage.E_EXISTING_ROOT_CATEGORY.getMessage());
            return;
        }

        Category root = new Leaf(rootname, Controller.askPotentiallyEmptyStringFromView(GenericMessage.CATEGORY_DESCRIPTION));
        root.setNativeFields(root.generateNativeFields(null));

        //Se la struttura non è valida l'utente dovrà proseguire nell'aggiunta al fine di renderla tale (oppure se ha sbagliato ricomincia)
        //altrimenti chiediamo se vuole aggiungere una categoria
        while (!root.isStructureValid() || Controller.askBooleanFromView(YesOrNoMessage.ADD_CATEGORY)) {
            Controller.signalToView(GenericMessage.AT_LEAST_TWO_CHILDREN.getMessage());

            CategoryEntry padre = choose(getCategoriesAsList(root), CategoryEntry::getDisplayName);

            if (padre.getCat() == root)
                root = padre.asNode();
            else {
                padre.asNode();
            }

            String name = Controller.askStringFromView(GenericMessage.CATEGORY_NAME);
            if (!root.isNameTaken(name)) {
                String desc = Controller.askStringFromView(GenericMessage.CATEGORY_DESCRIPTION);
                var cat = new Leaf(name, desc);
                cat.setNativeFields(cat.generateNativeFields(padre.getCat()));
                ((Node) padre.getCat()).addChild(cat);
            } else
                Controller.signalToView(ErrorMessage.E_EXISTING_NAME_IN_HIERARCHY.getMessage());

        }

        Hierarchy h = new Hierarchy(root);
        this.getHierarchies(app).addHierarchy(h);

        if (Controller.askBooleanFromView(YesOrNoMessage.SAVE_HIERARCHY)) {
            this.getHierarchies(app).save();
            Controller.signalToView(GenericMessage.SAVED_CORRECTLY.getMessage());
            return;
        }

        this.getHierarchies(app).removeHierarchy(h);
    }

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

    private HierarchiesStore getHierarchies(@NotNull Application app) {
        return app.getHierarchiesStore();
    }
}
