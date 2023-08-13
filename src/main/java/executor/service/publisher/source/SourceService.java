package executor.service.publisher.source;

import executor.service.publisher.model.ProxySourceDto;

import java.util.List;

public interface SourceService <T>{
    List<T> loadData(ProxySourceDto dto);

    String getType();
}
