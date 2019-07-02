package com.rkktt.taskmaster;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TaskmasterApplication.class)
@WebAppConfiguration
@ActiveProfiles("local")
public class TaskInfoRepositoryIntegrationTest {

    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    TaskInfoRepository repository;

    private static final String EXPECTED_TITLE = "Dishes";
    private static final String EXPECTED_DESC = "Suds n Bubbles";
    private static final String EXPECTED_STATUS = "Available";

    @Before
    public void setup() throws Exception {
        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

        CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(TaskInfo.class);

        tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));

        dynamoDBMapper.batchDelete((List<TaskInfo>)repository.findAll());
    }

    @Test
    public void readWriteTestCase() {
        TaskInfo brotato = new TaskInfo(EXPECTED_TITLE, EXPECTED_DESC, EXPECTED_STATUS);
        repository.save(brotato);

        List<TaskInfo> result = (List<TaskInfo>) repository.findAll();

        assertTrue("Not empty", result.size() > 0);
        assertTrue("Contains item with expected cost", result.get(0).getTitle().equals(EXPECTED_TITLE));
    }
}