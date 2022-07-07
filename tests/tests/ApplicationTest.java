package tests;


import main.model.Application;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ApplicationTest {
    Application app = new Application();

    @Test
    @DisplayName("Root name should be recognised")
    void isHierarchyNameTaken() {
        //app.addHierarchy("radice", new Gerarchia(new Foglia("radice", "categoria radice")));
        //assertTrue(app.isHierarchyNameTaken("radice"));
    }

    @Test
    void canAddHierarchy(){
        //app.addGerarchia("radice", new Gerarchia(new Foglia("radice", "categoria radice")));
       // assert app.getHierarchies().keySet().size() == 1;
    }

    @Test
    void canRetrieveHierarchy(){
        //app.addGerarchia("radice", new Gerarchia(new Foglia("radice", "categoria radice")));
        //assertNotNull(app.getHierarchies().get("radice"));
    }

}