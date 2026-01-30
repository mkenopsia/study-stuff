package inheritance;

import java.util.HashMap;

public class private_methods_in_constructor {
    public static void main(String[] args) {
        ParentClass parentClass = new ChildClass();
//        ChildClass childClass = new ParentClass();
    }
}

class ParentClass {
    public ParentClass() {
        this.privateStuff();
        this.publicStuff();
    }

    private void privateStuff() {
        System.out.println("i'm so private");
    }

    public void publicStuff() {
        System.out.println("i'm so public");
    }
}

class ChildClass extends ParentClass {
    public ChildClass() {
        super();
    }

    @Override
    public void publicStuff() {
        System.out.println("i'm so public new version lesgo");
    }
}
