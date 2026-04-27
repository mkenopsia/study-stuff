package processBuilding;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class ProcessRunner {

    public static void main(String[] args) throws InterruptedException, IOException {
        var command = Arrays.asList(
                "soundstretch",
                "/home/kaio/Documents/test.wav",
                "/home/kaio/Documents/out.wav",
                "-tempo=-10",
                "-pitch=-3"
        );

        var processBuilder = new ProcessBuilder(command);
        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();
        process.waitFor(10, TimeUnit.SECONDS);
    }

}
