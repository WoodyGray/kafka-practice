package by.woodygray.ws.productmicroservice.service;

import by.woodygray.core.ProductCreatedEvent;
import by.woodygray.ws.productmicroservice.service.dto.CreateProductDto;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class ProductServiceImpl implements ProductService{

    private KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public ProductServiceImpl(KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public String createProduct(CreateProductDto createProductDto) throws ExecutionException, InterruptedException {
        //TODO save db
        String productId = UUID.randomUUID().toString();

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(
                productId,
                createProductDto.title(),
                createProductDto.price(),
                createProductDto.quantity()
        );

//        CompletableFuture<SendResult<String, ProductCreatedEvent>> future =kafkaTemplate
//                .send("product-sold-events-topic", productId, productCreatedEvent);
//
//        future.whenComplete((result, exception) -> {
//           if (exception != null) {
//                LOGGER.error("Failed to send message: {}", exception.getMessage());
//           } else {
//                LOGGER.info("Successfully sent message: {}", result.getRecordMetadata());
//           }
//        });
//
//
//
//        LOGGER.info("Return: {}", productId);


        ProducerRecord<String, ProductCreatedEvent> record = new ProducerRecord<>(
                "product-sold-events-topic",
                productId,
                productCreatedEvent
        );

        record.headers().add("messageId", "UUID.randomUUID().toString()".getBytes());



        SendResult<String, ProductCreatedEvent> result =kafkaTemplate
                         .send(record).get();


        LOGGER.info("Topic: {}", result.getRecordMetadata().topic());
        LOGGER.info("Partition: {}", result.getRecordMetadata().partition());
        LOGGER.info("Offset: {}", result.getRecordMetadata().offset());
        LOGGER.info("Return: {}", productId);

        return productId;
    }
}
