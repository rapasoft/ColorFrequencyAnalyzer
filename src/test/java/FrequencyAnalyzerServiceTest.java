import eu.rapasoft.service.FrequencyAnalyzerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * User: Pavol Rajzák
 * Date: 20.3.2014
 * Time: 21:04
 */
@RunWith(value = JUnit4.class)
public class FrequencyAnalyzerServiceTest {

    @Test
    public void testComputeFrequencies() throws IOException {
        URL filePath = getClass().getClassLoader().getResource("./wordle_lastfm_chart.PNG");
        Assert.assertTrue(filePath != null);
        new FrequencyAnalyzerService().computeFrequencies(new File(filePath.getPath()));
    }
}
