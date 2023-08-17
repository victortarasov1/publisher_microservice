package executor.service.publisher.processing.proxy;

import executor.service.publisher.model.ProxyConfigHolderDto;

import java.util.List;

public interface ProxyProcessingService {
    void add(ProxyConfigHolderDto dto);
    void addAll(List<ProxyConfigHolderDto> dtoList);
}
