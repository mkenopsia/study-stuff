package study_test;

/**
 * Пример переопределения конструктора по умолчанию в качестве приватного для обеспечения
 * предотвращения инстанцирования
 * Обычно используется для классов-утилит (Arrays, Math, ...)
 * <p>
 * В качестве побочного эффекта эта идиома предотвращает также наследова
 * ние такого класса. Все конструкторы подкласса должны прямо или косвенно
 * вызывать конструктор суперкласса, но подкласс не будет иметь возможности
 * вызова конструктора суперкласса
 */

public class NonInstantiableClassExample {
    public static void main(String[] args) {
//        NonInstantiableClass nonInstantiableClass = new NonInstantiableClass();
    }
}

final class NonInstantiableClass {

    // Ensuring non-instantiability.
    private NonInstantiableClass() {
        throw new AssertionError();
    }

    static void Method1() {}

    static void Method2() {}

    static void Method3() {}
}
