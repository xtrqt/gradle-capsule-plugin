import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.zeroturnaround.exec.ProcessExecutor;
import org.zeroturnaround.exec.stream.NullOutputStream;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Tests {

  @Test
  public void testCompositeBuild()
      throws IOException, InterruptedException, TimeoutException {
    final String output = StringUtils.trimToNull(new ProcessExecutor().command("java", "-jar",
            "../composite-build/subproject3/my-app/build/libs/my-app-capsule.jar")
        .exitValue(0)
        .redirectError(System.err)
        .readOutput(true).execute()
        .outputUTF8());
    Assert.assertEquals("Hello, Bob!", output);
  }

  @Test
  public void testGroovyDsl()
      throws IOException, InterruptedException, TimeoutException {
    final String output = StringUtils.trimToNull(new ProcessExecutor().command("java", "-jar",
            "../groovy-dsl/build/libs/groovy-dsl-capsule.jar")
        .exitValue(0)
        .redirectError(System.err)
        .readOutput(true).execute()
        .outputUTF8());
    Assert.assertEquals("hELLO, bOB!", output);
  }

  @Test
  public void testKotlinDsl()
      throws IOException, InterruptedException, TimeoutException {
    final String output = StringUtils.trimToNull(new ProcessExecutor().command("java", "-jar",
            "../kotlin-dsl/build/libs/kotlin-dsl-capsule.jar")
        .exitValue(0)
        .redirectError(System.err)
        .readOutput(true).execute()
        .outputUTF8());
    Assert.assertEquals("hELLO, bOB!", output);
  }

  @Test
  public void testKitchenSink1()
      throws IOException, InterruptedException, TimeoutException {
    final String output = StringUtils.trimToNull(new ProcessExecutor().command("java", "-jar",
            "../kitchen-sink/module1/build/libs/module1-capsule.jar")
        .exitValue(0)
        .redirectError(System.err)
        .readOutput(true).execute()
        .outputUTF8());
    Assert.assertEquals("hELLO, bOB!", output);
  }

  @Test
  public void testKitchenSink2_1()
      throws IOException, InterruptedException, TimeoutException {
    final String output = StringUtils.trimToNull(new ProcessExecutor().command("java", "-jar",
            "../kitchen-sink/module2/build/libs/module2-all.jar")
        .exitValue(0)
        .redirectError(System.err)
        .readOutput(true).execute()
        .outputUTF8());
    Assert.assertEquals("hELLO, bOB!", output);
  }

  @Test
  public void testKitchenSink2_2()
      throws IOException, InterruptedException, TimeoutException {
    final String output = StringUtils.trimToNull(new ProcessExecutor().command("java", "-jar",
            "../kitchen-sink/module2/build/libs/module2-capsule.jar")
        .exitValue(1)
        .redirectError(System.err)
        .readOutput(true).execute()
        .outputUTF8());
    Assert.assertEquals(null, output);
  }
}
