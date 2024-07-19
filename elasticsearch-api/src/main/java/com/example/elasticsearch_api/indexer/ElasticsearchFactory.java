package com.example.elasticsearch_api.indexer;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.TransportUtils;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import javax.net.ssl.SSLContext;
import lombok.RequiredArgsConstructor;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ElasticsearchFactory {

    private final String serverUrl = "https://localhost:9200";
    private final String apiKey = "dDNRd3hKQUJ2OWg1X25EUmhfQ2I6OVFDYnB4VkNTenltR19BNGM5Um9ndw==";
    private final String username = "elastic";
    private final String userpassword = "4gnTLDUru0OW4EqGm_*A";
    private final String fingerprint = "30a62371887892550c18a75f0c9477dd8d8224f52b0355ea1e65c5fbc9e87f7c";

    ElasticsearchClient getElasticsearchClient() {
        RestClient restClient = getRestClient();
        return new ElasticsearchClient(new RestClientTransport(restClient, new JacksonJsonpMapper()));
    }

    public RestClient getRestClient() {
        SSLContext sslContext = TransportUtils.sslContextFromCaFingerprint(fingerprint);
        BasicCredentialsProvider credsProv = new BasicCredentialsProvider();
        credsProv.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, userpassword));
        return RestClient
                .builder(HttpHost.create(serverUrl))
                .setDefaultHeaders(new Header[]{new BasicHeader("Authorization", "ApiKey" + apiKey)})
                .setHttpClientConfigCallback(httpAsyncClientBuilder -> httpAsyncClientBuilder
                        .setSSLContext(sslContext)
                        .setDefaultCredentialsProvider(credsProv))
                .build();
    }


}
