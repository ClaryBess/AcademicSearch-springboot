package com.example.demo.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.util.ArrayList;
import java.util.List;

public class ElasticSearchClient {
    private String[] hostsAndPorts;

    public ElasticSearchClient(String[] hostsAndPorts) {
        this.hostsAndPorts = hostsAndPorts;
    }
    public RestHighLevelClient getClient() {
        RestHighLevelClient client = null;
        List<HttpHost> httpHosts = new ArrayList<HttpHost>();
        if (hostsAndPorts.length > 0) {
            for (String hostsAndPort : hostsAndPorts) {
                String[] hp = hostsAndPort.split(":");
                httpHosts.add(new HttpHost(hp[0], Integer.valueOf(hp[1]), "http"));
            }
            client = new RestHighLevelClient(
                    RestClient.builder(httpHosts.toArray(new HttpHost[0])));
        } else {
            client = new RestHighLevelClient(
                    RestClient.builder(new HttpHost("127.0.0.1", 9200, "http")));
        }
        return client;
    }
}
