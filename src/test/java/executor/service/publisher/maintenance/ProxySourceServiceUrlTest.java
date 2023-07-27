package executor.service.publisher.maintenance;

import executor.service.publisher.exception.api.EndpointException;
import executor.service.publisher.maintenance.validator.ProxyEndpointValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@SpringBootTest
class ProxySourceServiceUrlTest {

    @MockBean
    private ProxySourceServiceUrl proxySourceServiceUrl;

    @MockBean
    private ProxyEndpointValidator endpointValidator;


    @Autowired
    private void injectBeans(ProxySourceServiceUrl proxySourceServiceUrl) {
        this.proxySourceServiceUrl = proxySourceServiceUrl;
    }


    @Test
    void testValidEndpointWithSuccessfulExecution() {

        Mockito.when(this.endpointValidator.validate(Mockito.anyString())).thenReturn(true);

        this.proxySourceServiceUrl.requestProxies();

        Mockito.verify(this.proxySourceServiceUrl, Mockito.times(2)).requestProxies();
    }

    @Test
    void testInvalidEndpoint() {
        Mockito.when(this.endpointValidator.validate(Mockito.anyString())).thenReturn(false);

        Mockito.doThrow(EndpointException.class).when(this.proxySourceServiceUrl).requestProxies();

        Assertions.assertThrows(EndpointException.class, () -> this.proxySourceServiceUrl.requestProxies());
    }
}
