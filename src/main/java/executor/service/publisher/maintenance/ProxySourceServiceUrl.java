package executor.service.publisher.maintenance;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import executor.service.publisher.exception.api.EndpointException;
import executor.service.publisher.exception.api.ProxyApiException;
import executor.service.publisher.maintenance.validator.ProxyEndpointValidator;
import executor.service.publisher.model.ProxyConfigHolderDto;
import executor.service.publisher.model.ProxyCredentialsDTO;
import executor.service.publisher.model.ProxyNetworkConfigDTO;
import executor.service.publisher.queue.QueueHandler;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ProxySourceServiceUrl {

    private final QueueHandler<ProxyConfigHolderDto> proxyQueueHandler;

    private final OkHttpClient okHttpClient;

    private final ProxyEndpointValidator endpointValidator;

    @Value("${api.endpoint}")
    private String apiEndpoint;


    @Autowired
    public ProxySourceServiceUrl(QueueHandler<ProxyConfigHolderDto> proxyQueueHandler,
                                 OkHttpClient okHttpClient,
                                 ProxyEndpointValidator endpointValidator) {
        this.proxyQueueHandler = proxyQueueHandler;
        this.okHttpClient = okHttpClient;
        this.endpointValidator = endpointValidator;
    }


    public void requestProxies() {
        try {
            if (!this.endpointValidator.validate(this.apiEndpoint))
                throw new EndpointException();

            Request request = new Request.Builder()
                    .url(this.apiEndpoint)
                    .addHeader("User-Agent", "publisher")
                    .addHeader("Accept", "application/json")
                    .build();

            this.processResponse(okHttpClient, request);

        } catch (ProxyApiException e) {
            e.printStackTrace();
        }
    }

    private void processResponse(OkHttpClient okHttpClient, Request request) {
        try (Response response = okHttpClient.newCall(request).execute();) {

            if (!response.isSuccessful())
                throw new IOException("Error with code ==> " + response.code());

            ObjectMapper mapper = new ObjectMapper();

            JsonNode tree = mapper.readTree(response.body().string());

            for (JsonNode node : tree) {
                ProxyNetworkConfigDTO networkConfig = this.mapProxyNetworkConfig(node);

                ProxyCredentialsDTO credentials = this.mapProxyCredentials(node);

                this.proxyQueueHandler.add(new ProxyConfigHolderDto(networkConfig, credentials));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ProxyNetworkConfigDTO mapProxyNetworkConfig(JsonNode node) {
        String host = node.get("host").asText();
        Integer port = Integer.parseInt(node.get("port").asText());

        return new ProxyNetworkConfigDTO(host, port);
    }

    private ProxyCredentialsDTO mapProxyCredentials(JsonNode node) {
        JsonNode usernameNode = node.get("username");
        JsonNode passwordNode = node.get("password");

        String username = "";
        String password = "";

        if (usernameNode != null && passwordNode != null) {
            username = usernameNode.asText();
            password = passwordNode.asText();
        }

        return new ProxyCredentialsDTO(username, password);
    }

}
