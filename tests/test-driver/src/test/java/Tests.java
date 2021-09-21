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
        .redirectError(new NullOutputStream())
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
        .redirectError(new NullOutputStream())
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
        .redirectError(new NullOutputStream())
        .readOutput(true).execute()
        .outputUTF8());
    Assert.assertEquals("hELLO, bOB!", output);
  }
}
