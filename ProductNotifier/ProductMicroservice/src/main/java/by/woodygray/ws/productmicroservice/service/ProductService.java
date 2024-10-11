package by.woodygray.ws.productmicroservice.service;

import by.woodygray.ws.productmicroservice.service.dto.CreateProductDto;

import java.util.concurrent.ExecutionException;

public interface ProductService {
    String createProduct(CreateProductDto createProductDto) throws ExecutionException, InterruptedException;
}
