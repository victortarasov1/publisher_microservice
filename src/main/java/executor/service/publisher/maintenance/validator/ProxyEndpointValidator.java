package executor.service.publisher.maintenance.validator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ProxyEndpointValidator {

    @Value("${api.validator.base-url}")
    private String baseUrl;

    public boolean validate(String apiEndpoint) {
        if(apiEndpoint == null || !apiEndpoint.contains(this.baseUrl)) return false;

        String endpointPattern = "(&\\w+=.+){0,10}(&code=\\d{15})";

        Pattern pattern = Pattern.compile(endpointPattern);
        Matcher matcher = pattern.matcher(apiEndpoint);

        return matcher.find();
    }

}
