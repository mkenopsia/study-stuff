package password_geneartor;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public final class PasswordGenerator {

    private PasswordGenerator() {
        throw new AssertionError();
    }

    private static final SecureRandom generator;

    static {
        try {
            generator = new SecureRandom(SecureRandom.getInstanceStrong().generateSeed(20));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isSuitableCharacter(char ch) {
        return ch >= '0' && ch <= 'z' && Character.isLetterOrDigit(ch);
    }

    public static String generateRandomString(long len) {
        return generator.ints(Character.MIN_CODE_POINT, Character.MAX_CODE_POINT)
                .mapToObj(i -> (char) i)
                .filter(PasswordGenerator::isSuitableCharacter)
                .limit(len)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
    }
}

class Main {
    public static void main(String[] args) {
        System.out.println(PasswordGenerator.generateRandomString(10));
    }
}
