package ru.chuikov.MuseServer.config;



import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import javax.annotation.Resource;

@Configuration
@PropertySource("classpath:/properties/mongo.properties")
public class MongoDbConfig extends AbstractMongoConfiguration {

    @Resource
    private Environment environment;

    @Bean
    public GridFsTemplate gridFsTemplate() throws Exception {
        return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
    }

    @Override
    protected String getDatabaseName() {
        return environment.getRequiredProperty("mongo.database");
    }

    @Override
    public MongoClient mongoClient() {
        return new MongoClient(environment.getRequiredProperty("mongo.address"));
    }

    @Bean
    public GridFSBucket getGridFSBuckets() {
        MongoDatabase db = mongoDbFactory().getDb();
        return GridFSBuckets.create(db);
    }
}
