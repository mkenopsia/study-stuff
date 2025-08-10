package strings_links_interns;

public class StringsLinksTest {
    public static void main(String[] args) {
        /// Создаются именно объекты, но через intern() мы записываем ссылку не на объект
        /// а на строку из пула строк.
        String test1 = new String(new byte[]{40,82}).intern();
        String test2 = new String(new byte[]{40,82}).intern();

        /// При загрузке класса компилятор Java помещает все строковые
        /// литералы в константную таблицу (constant pool)  этого класса.
        ///
        ///      0: ldc           #2                  // String 123
        ///      2: astore_1
        ///      3: ldc           #2                  // String 123
        ///      5: astore_2
        /// Далее эти строки загрузятся из constant pool, и таким образом
        /// JVM гарантирует, что все одинаковые строковые литералы  будут совпадать по ссылке (==).
        String test3 = "123";
        String test4 = "123";

        ///  value.of вызывает toString() у объектов, передаваемых в параметры
        /// или new String(), если передаётся массив
        String test5 = String.valueOf(10).intern();
        String test6 = String.valueOf(10).intern();

        /// также безопасно работает с null
        Object obj = null;
        String nullString = String.valueOf(obj).intern();

        System.out.println(test1 == test2);
        System.out.println(test3 == test4);
        System.out.println(test5 == test6);
        System.out.println(nullString);
    }
}
