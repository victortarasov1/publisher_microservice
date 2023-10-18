package executor.service.source.okhttp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import executor.service.exception.source.DataParsingException;
import executor.service.exception.source.okhttp.CallException;
import executor.service.exception.source.okhttp.EmptyResponseBodyException;
import executor.service.exception.source.okhttp.UnsuccessfulResponseException;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OkhttpLoaderImpl implements OkhttpLoader {
    private final OkHttpClient okHttpClient;
    private final ObjectMapper objectMapper;

    @Override
    public <T> List<T> loadData(Request request, Class<T> clazz) {
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (response.isSuccessful()) return handleSuccessfulResponse(clazz, response);
            throw new UnsuccessfulResponseException(response.code());
        } catch (IOException ex) {
            throw new CallException(ex);
        }
    }

    private <T> List<T> handleSuccessfulResponse(Class<T> clazz, Response response) {
        try(ResponseBody body = response.body()){
            if(body == null) throw new EmptyResponseBodyException();
            CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);
            return objectMapper.readValue(body.string(), collectionType);
        } catch (IOException ex) {
            throw new DataParsingException(ex);
        }
    }

}