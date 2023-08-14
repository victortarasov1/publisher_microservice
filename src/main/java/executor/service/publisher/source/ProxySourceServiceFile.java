package executor.service.publisher.source;

import executor.service.publisher.model.ProxyConfigHolderDto;
import executor.service.publisher.model.ProxySourceDto;
import executor.service.publisher.source.reader.FileReader;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProxySourceServiceFile implements SourceService<ProxyConfigHolderDto, ProxySourceDto> {

    private final FileReader reader;

    public ProxySourceServiceFile(FileReader reader) {
        this.reader = reader;
    }

    @Override
    public List<ProxyConfigHolderDto> loadData(ProxySourceDto dto) {
        return reader.readData(dto.getProxySource(), ProxyConfigHolderDto.class);
    }

    @Override
    public String getType() {
        return "file";
    }
}
