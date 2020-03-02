package awd.mis.servers.model.elasticsearch;

import java.io.Serializable;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "ryxxIndex", type = "ryxx")
public class Ryxx implements Serializable {

}
