package executor.service.publisher.source.okhttp;

import okhttp3.Request;

import java.util.List;

public interface OkhttpClient {
    <T> List<T> loadData(Request request, Class<T> clazz);
}
