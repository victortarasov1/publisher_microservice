package executor.service.publisher.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProxyCredentialsTest {
    private static final String TEST_USERNAME = "testUsername";
    private static final String TEST_PASSWORD = "testPassword";
    private static final String DIFFERENT_USERNAME = "differentUsername";
    private static final String DIFFERENT_PASSWORD = "differentPassword";

    @Test
    public void testDefaultConstructor() {
        ProxyCredentials credentials = new ProxyCredentials();
        assertThat(credentials.getUsername()).isNull();
        assertThat(credentials.getPassword()).isNull();
    }

    @Test
    public void testParameterizedConstructor() {
        ProxyCredentials credentials = new ProxyCredentials(TEST_USERNAME, TEST_PASSWORD);

        assertThat(credentials.getUsername()).isEqualTo(TEST_USERNAME);
        assertThat(credentials.getPassword()).isEqualTo(TEST_PASSWORD);
    }

    @Test
    public void testUsernameGetterAndSetter() {
        ProxyCredentials credentials = new ProxyCredentials();
        credentials.setUsername(TEST_USERNAME);
        assertThat(credentials.getUsername()).isEqualTo(TEST_USERNAME);
    }

    @Test
    public void testPasswordGetterAndSetter() {
        ProxyCredentials credentials = new ProxyCredentials();
        credentials.setPassword(TEST_PASSWORD);
        assertThat(credentials.getPassword()).isEqualTo(TEST_PASSWORD);
    }

    @Test
    public void testEqualsWithNull() {
        ProxyCredentials credentials1 = new ProxyCredentials(TEST_USERNAME, TEST_PASSWORD);
        assertThat(credentials1).isNotEqualTo(null);
    }

    @Test
    public void testEqualsWithDifferentType() {
        ProxyCredentials credentials1 = new ProxyCredentials(TEST_USERNAME, TEST_PASSWORD);
        String differentType = "string";
        assertThat(credentials1).isNotEqualTo(differentType);
    }

    @Test
    public void testEqualsWithItself() {
        ProxyCredentials credentials1 = new ProxyCredentials(TEST_USERNAME, TEST_PASSWORD);
        assertThat(credentials1).isEqualTo(credentials1);
    }

    @Test
    public void testEqualsAndHashCodeForEqualCredentials() {
        ProxyCredentials credentials1 = new ProxyCredentials(TEST_USERNAME, TEST_PASSWORD);
        ProxyCredentials credentials2 = new ProxyCredentials(TEST_USERNAME, TEST_PASSWORD);

        assertThat(credentials1).isEqualTo(credentials2);
        assertThat(credentials1.hashCode()).isEqualTo(credentials2.hashCode());
    }

    @Test
    public void testEqualsAndHashCodeForDifferentCredentials() {
        ProxyCredentials credentials1 = new ProxyCredentials(TEST_USERNAME, TEST_PASSWORD);
        ProxyCredentials credentials3 = new ProxyCredentials(DIFFERENT_USERNAME, DIFFERENT_PASSWORD);

        assertThat(credentials1).isNotEqualTo(credentials3);
        assertThat(credentials1.hashCode()).isNotEqualTo(credentials3.hashCode());
    }

    @Test
    public void testToString() {
        ProxyCredentials credentials = new ProxyCredentials(TEST_USERNAME, TEST_PASSWORD);

        String expectedToString = "ProxyCredentialsDTO{username='" + TEST_USERNAME + "', password='" + TEST_PASSWORD + "'}";
        assertThat(credentials.toString()).isEqualTo(expectedToString);
    }
}