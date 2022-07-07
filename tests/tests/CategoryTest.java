package tests;

import main.model.Leaf;
import main.model.Node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    Node n = new Node("Nodo", "categoria nodo");
    Leaf f = new Leaf("Foglia", "categoria foglia");
    Leaf f1 = new Leaf("Foglia", "categoria foglia");
    Leaf f2 = new Leaf("Foglia", "categoria foglia");


    @Test
    void isNameTaken() {
        n.addChild(f);
        assertTrue(n.isNameTaken("Foglia"));
    }

    @Test
    void nodeStructureIsInvalidIfHasNoChildren() {
        assertFalse(n.isStructureValid());
    }

    @Test
    void nodeStructureIsInvalidIfHasOneChild(){
        n.addChild(f);
        assertFalse(n.isStructureValid());
    }

    @Test
    void nodeStructureIsValidIfHasTwoChildren(){
        n.addChild(f);
        n.addChild(f1);
        assertTrue(n.isStructureValid());
    }

    @Test
    void nodeStructureIsValidIfHasMoreThanTwoChildren(){
        n.addChild(f);
        n.addChild(f1);
        n.addChild(f2);
        assertTrue(n.isStructureValid());
    }

    @Test
    void leafStructureIsValid(){
        assertTrue(f.isStructureValid());
    }

}