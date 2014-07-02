package eu.rapasoft.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ApplicationPropertiesTest {

    private ApplicationProperties applicationProperties;

    @Before
    public void init() {
        applicationProperties = new ApplicationProperties();
        applicationProperties.init();

        Assert.assertNotNull(applicationProperties);
    }

    @Test
    public void testGetApplicationVersion() throws Exception {
        Assert.assertEquals("VERSION", applicationProperties.getApplicationVersion());
    }

    @Test
    public void testGetAuthor() throws Exception {
        Assert.assertEquals("AUTHOR", applicationProperties.getAuthor());
    }

    @Test
    public void testGetApplicationName() throws Exception {
        Assert.assertEquals("APP_NAME", applicationProperties.getApplicationName());
    }
}