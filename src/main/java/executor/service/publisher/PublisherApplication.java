package executor.service.publisher;


import executor.service.publisher.maintenance.ProxySourceServiceUrl;
import executor.service.publisher.maintenance.ProxySourceServiceFile;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PublisherApplication implements CommandLineRunner {

	private final ProxySourceServiceFile proxySourceServiceFile;
	private final ProxySourceServiceUrl proxySourceServiceUrl;

	public PublisherApplication(ProxySourceServiceFile proxySourceServiceFile,
								ProxySourceServiceUrl proxySourceServiceUrl) {
		this.proxySourceServiceFile = proxySourceServiceFile;
		this.proxySourceServiceUrl = proxySourceServiceUrl;
	}

	public static void main(String[] args) {
		SpringApplication.run(PublisherApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		proxySourceServiceFile.getProxy();
		proxySourceServiceUrl.requestProxies();
	}
}
