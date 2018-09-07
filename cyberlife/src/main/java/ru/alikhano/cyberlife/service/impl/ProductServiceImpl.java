package ru.alikhano.cyberlife.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.alikhano.cyberlife.DTO.ProductDTO;
import ru.alikhano.cyberlife.dao.ProductDao;
import ru.alikhano.cyberlife.mapper.ProductMapper;
import ru.alikhano.cyberlife.model.Product;
import ru.alikhano.cyberlife.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	ProductMapper productMapper;
	
	@Override
	public List<ProductDTO> getProductDTOList() {
		List<Product> products = productDao.getProductList();
		List<ProductDTO> productsDTO = new ArrayList<>();
		products.stream().forEach(product -> {
			ProductDTO productDTO = productMapper.productToProductDTO(product);
			productsDTO.add(productDTO);
		});
		
		return productsDTO;
	}

	@Override
	public ProductDTO getProductDTOById(int id) {
		return productMapper.productToProductDTO((productDao.getProductById(id)));
	}

	@Override
	public void addProduct(ProductDTO productDTO) {
		productDao.addProduct(productMapper.productDTOtOProduct(productDTO));
		
	}

	@Override
	public void editProduct(ProductDTO productDTO) {
		productDao.editProduct(productMapper.productDTOtOProduct(productDTO));
		
	}

	@Override
	public void deleteProduct(ProductDTO productDTO) {
		productDao.deleteProduct(productMapper.productDTOtOProduct(productDTO));
		
	}

}
