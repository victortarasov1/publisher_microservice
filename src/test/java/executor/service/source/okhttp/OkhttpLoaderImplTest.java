package executor.service.source.okhttp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import executor.service.exception.source.DataParsingException;
import executor.service.exception.source.okhttp.CallException;
import executor.service.exception.source.okhttp.EmptyResponseBodyException;
import executor.service.exception.source.okhttp.UnsuccessfulResponseException;
import okhttp3.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class OkhttpLoaderImplTest {

    private OkHttpClient okHttpClient;
    private ObjectMapper objectMapper;
    private Request request;
    private Call call;
    private Response response;
    private ResponseBody responseBody;
    private OkhttpLoader okhttpLoader;

    @BeforeEach
    void setUp() {
        okHttpClient = mock(OkHttpClient.class);
        objectMapper = mock(ObjectMapper.class);
        request = mock(Request.class);
        call = mock(Call.class);
        responseBody = mock(ResponseBody.class);
        response = mock(Response.class);
        okhttpLoader = new OkhttpLoaderImpl(okHttpClient, objectMapper);
    }

    @Test
    void testLoadData_SuccessfulResponse() throws IOException {
        List<Dto> expected = List.of(new Dto(), new Dto(), new Dto());
        CollectionType collectionType = mock(CollectionType.class);
        TypeFactory typeFactory = mock(TypeFactory.class);
        when(okHttpClient.newCall(request)).thenReturn(call);
        when(call.execute()).thenReturn(response);
        when(response.isSuccessful()).thenReturn(true);
        when(response.body()).thenReturn(responseBody);
        when(objectMapper.getTypeFactory()).thenReturn(typeFactory);
        when(typeFactory.constructCollectionType(List.class, Dto.class))
                .thenReturn(collectionType);
        when(objectMapper.readValue(responseBody.string(), collectionType)).thenReturn(expected);
        List<Dto> result = okhttpLoader.loadData(request, Dto.class);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testLoadData_shouldThrowEmptyResponseBodyException() throws IOException {
        when(okHttpClient.newCall(request)).thenReturn(call);
        when(call.execute()).thenReturn(response);
        when(response.isSuccessful()).thenReturn(true);
        when(response.body()).thenReturn(null);
        assertThatThrownBy(() -> okhttpLoader.loadData(request, Dto.class))
                .isInstanceOf(EmptyResponseBodyException.class);
    }

    @Test
    void testLoadData_shouldThrowUnsuccessfulResponseException() throws IOException {
        when(okHttpClient.newCall(request)).thenReturn(call);
        when(call.execute()).thenReturn(response);
        when(response.isSuccessful()).thenReturn(false);
        assertThatThrownBy(() -> okhttpLoader.loadData(request, Dto.class))
                .isInstanceOf(UnsuccessfulResponseException.class);
    }

    @Test
    void testLoadData_shouldThrowCallException() throws IOException {
        when(okHttpClient.newCall(request)).thenReturn(call);
        when(call.execute()).thenThrow(IOException.class);
        assertThatThrownBy(() -> okhttpLoader.loadData(request, Dto.class))
                .isInstanceOf(CallException.class);
    }

    @Test
    void testLoadData_shouldThrowDataParsingException() throws IOException {
        CollectionType collectionType = mock(CollectionType.class);
        TypeFactory typeFactory = mock(TypeFactory.class);
        when(okHttpClient.newCall(request)).thenReturn(call);
        when(call.execute()).thenReturn(response);
        when(response.isSuccessful()).thenReturn(true);
        when(response.body()).thenReturn(responseBody);
        when(objectMapper.getTypeFactory()).thenReturn(typeFactory);
        when(typeFactory.constructCollectionType(List.class, Dto.class))
                .thenReturn(collectionType);
        when(responseBody.string()).thenThrow(IOException.class);
        assertThatThrownBy(() -> okhttpLoader.loadData(request, Dto.class))
                .isInstanceOf(DataParsingException.class);
    }

    static class Dto {

    }
}