package executor.service.publisher.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProxySourceTest {

    private static final String TEST_SOURCE = "testSource";
    private static final String TEST_STORAGE = "testStorage";
    private static final String TEST_TYPE = "testType";
    private static final String DIFFERENT_SOURCE = "differentSource";
    private static final String DIFFERENT_STORAGE = "differentStorage";
    private static final String DIFFERENT_TYPE = "differentType";

    @Test
    public void testDefaultConstructor() {
        ProxySource proxySource = new ProxySource();
        assertThat(proxySource.getSource()).isNull();
        assertThat(proxySource.getStorage()).isNull();
        assertThat(proxySource.getType()).isNull();
    }

    @Test
    public void testParameterizedConstructor() {
        ProxySource proxySource = new ProxySource(TEST_SOURCE, TEST_STORAGE, TEST_TYPE);

        assertThat(proxySource.getSource()).isEqualTo(TEST_SOURCE);
        assertThat(proxySource.getStorage()).isEqualTo(TEST_STORAGE);
        assertThat(proxySource.getType()).isEqualTo(TEST_TYPE);
    }

    @Test
    public void testSourceGetterAndSetter() {
        ProxySource proxySource = new ProxySource();
        proxySource.setSource(TEST_SOURCE);
        assertThat(proxySource.getSource()).isEqualTo(TEST_SOURCE);
    }

    @Test
    public void testStorageGetterAndSetter() {
        ProxySource proxySource = new ProxySource();
        proxySource.setStorage(TEST_STORAGE);
        assertThat(proxySource.getStorage()).isEqualTo(TEST_STORAGE);
    }

    @Test
    public void testTypeGetterAndSetter() {
        ProxySource proxySource = new ProxySource();
        proxySource.setType(TEST_TYPE);
        assertThat(proxySource.getType()).isEqualTo(TEST_TYPE);
    }

    @Test
    public void testEqualsWithNull() {
        ProxySource proxySource1 = new ProxySource(TEST_SOURCE, TEST_STORAGE, TEST_TYPE);
        assertThat(proxySource1).isNotEqualTo(null);
    }

    @Test
    public void testEqualsWithDifferentType() {
        ProxySource proxySource1 = new ProxySource(TEST_SOURCE, TEST_STORAGE, TEST_TYPE);
        String differentType = "string";
        assertThat(proxySource1).isNotEqualTo(differentType);
    }

    @Test
    public void testEqualsWithItself() {
        ProxySource proxySource1 = new ProxySource(TEST_SOURCE, TEST_STORAGE, TEST_TYPE);
        assertThat(proxySource1).isEqualTo(proxySource1);
    }

    @Test
    public void testEqualsAndHashCodeForEqualSources() {
        ProxySource proxySource1 = new ProxySource(TEST_SOURCE, TEST_STORAGE, TEST_TYPE);
        ProxySource proxySource2 = new ProxySource(TEST_SOURCE, TEST_STORAGE, TEST_TYPE);

        assertThat(proxySource1).isEqualTo(proxySource2);
        assertThat(proxySource1.hashCode()).isEqualTo(proxySource2.hashCode());
    }

    @Test
    public void testEqualsAndHashCodeForDifferentSources() {
        ProxySource proxySource1 = new ProxySource(TEST_SOURCE, TEST_STORAGE, TEST_TYPE);
        ProxySource proxySource3 = new ProxySource(DIFFERENT_SOURCE, DIFFERENT_STORAGE, DIFFERENT_TYPE);

        assertThat(proxySource1).isNotEqualTo(proxySource3);
        assertThat(proxySource1.hashCode()).isNotEqualTo(proxySource3.hashCode());
    }

    @Test
    public void testToString() {
        ProxySource proxySource = new ProxySource(TEST_SOURCE, TEST_STORAGE, TEST_TYPE);

        String expectedToString = "ProxySourceDto{proxySource='" + TEST_SOURCE + "', proxySourceType='" + TEST_STORAGE +
                "', proxyType='" + TEST_TYPE + "'}";
        assertThat(proxySource.toString()).isEqualTo(expectedToString);
    }
}