package com.quebic.productsearch.dataloader;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovi.searchbox.annotation.DataLoader;
import com.lovi.searchbox.annotation.DataLoaderFunction;
import com.quebic.productsearch.model.Product;

@DataLoader
public class ProductsLoader {
	
	private Logger logger = LoggerFactory.getLogger(ProductsLoader.class);

	@DataLoaderFunction
    public Set<Product> loadData() throws Exception{
		
		logger.info("loading data...");
		
		Set<Product> products = new HashSet<>();
		
		Resource resource = new ClassPathResource("products-data.json");
		InputStream resourceInputStream = resource.getInputStream();
		
		ObjectMapper objectMapper = new ObjectMapper();
		products = objectMapper.readValue(resourceInputStream, new TypeReference<Set<Product>>() {});
		
		return products;
		
	}
	
}
