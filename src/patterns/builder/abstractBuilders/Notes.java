package patterns.builder.abstractBuilders;

public class Notes {
    public static void main(String[] args) {
        /*
        Обратите внимание, что метод build в строителе каждого подкласса объявляется
        как возвращающий корректный подкласс: метод build класса NyPizza.Builder
        возвращает NyPizza, в то время как в Calzone.Builder возвращается Calzone.
        Эта методика, в которой метод подкласса объявляется
        как возвращающий подтип возвращаемого типа, объявленного в суперклассе,
        известна как ___ковариантное типизирование возврата___. Она позволяет клиентам
        использовать эти строители без необходимости приведения типов.
         */

        NyPizza nyPizza = NyPizza.builder(NyPizza.Size.SMALL)
                .addTopping(Pizza.Topping.MUSHROOM)
                .addTopping(Pizza.Topping.CHEESE)
                .addTopping(Pizza.Topping.HAM)
                .addTopping(Pizza.Topping.PEPPER)
                .build();
    }
}
