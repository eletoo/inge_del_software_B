package tests;

import main.model.Application;
import main.model.Hierarchy;
import main.model.Leaf;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ApplicationTest {
    Application app = new Application();

    @Test
    @DisplayName("Root name should be recognised")
    void isHierarchyNameTaken() {
        app.getHierarchiesStore().addHierarchy(new Hierarchy(new Leaf("radice", "categoria radice")));
        assertTrue(app.getHierarchiesStore().isHierarchyNameTaken("radice"));
    }

    @Test
    void canAddHierarchy(){
        app.getHierarchiesStore().addHierarchy(new Hierarchy(new Leaf("radice", "categoria radice")));
        assert app.getHierarchiesStore().getHierarchies().keySet().size() == 1;
    }

    @Test
    void canRetrieveHierarchy(){
        app.getHierarchiesStore().addHierarchy(new Hierarchy(new Leaf("radice", "categoria radice")));
        assertNotNull(app.getHierarchiesStore().getHierarchies().get("radice"));
    }

}