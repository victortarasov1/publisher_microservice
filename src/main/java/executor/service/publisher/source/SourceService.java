package executor.service.publisher.source;

import java.util.List;

public interface SourceService <T, D>{
    List<T> loadData(D dto);

    String getType();
}
