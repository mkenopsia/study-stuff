import study_test.NonInstantiableClassExample;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("hi");
        List<Integer> arr = new ArrayList<>(Arrays.asList(1,2,3));
        arr.set(0, 100);

        System.out.println(Arrays.stream(new int[]{1,2,3}).count());

        System.out.println(arr);

        List<Sample> samples = new ArrayList<>(List.of(new Sample(123), new Sample(456), new Sample(789)));
        List<Sample> newSamples = new ArrayList<>(samples);

        System.out.println(samples);
        System.out.println(newSamples);

        samples.get(1).sampleField += 10;

        System.out.println(samples);
        System.out.println(newSamples);

        var comparator = Comparator.comparingInt((Sample sample) -> sample.sampleField);
    }

    static class Sample {
        Integer sampleField;

        public Sample(Integer integer) {
            this.sampleField = integer;
        }

        @Override
        public String toString() {
            return "Sample=" + sampleField;
        }
    }
}