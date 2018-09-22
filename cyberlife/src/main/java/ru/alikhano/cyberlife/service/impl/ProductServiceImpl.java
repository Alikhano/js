package ru.alikhano.cyberlife.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.alikhano.cyberlife.DTO.ProductDTO;
import ru.alikhano.cyberlife.DTO.ProductInfo;
import ru.alikhano.cyberlife.dao.ProductDao;
import ru.alikhano.cyberlife.mapper.ProductInfoMapper;
import ru.alikhano.cyberlife.mapper.ProductMapper;
import ru.alikhano.cyberlife.model.Product;
import ru.alikhano.cyberlife.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	ProductMapper productMapper;
	
	@Autowired
	ProductInfoMapper productInfoMapper;
	
	@Override
	@Transactional
	public List<ProductDTO> getAll() {
		List<Product> products = productDao.getAll();
		List<ProductDTO> productsDTO = new ArrayList<>();
		products.stream().forEach(product -> {
			ProductDTO productDTO = productMapper.productToProductDTO(product);
			productsDTO.add(productDTO);
		});
		
		return productsDTO;
	}

	@Override
	@Transactional
	public ProductDTO getById(int id) {
		return productMapper.productToProductDTO((Product)(productDao.getById(id)));
	}

	@Override
	@Transactional
	public void create(ProductDTO productDTO) {
		productDao.create(productMapper.productDTOtOProduct(productDTO));
		
	}

	@Override
	@Transactional
	public void update(ProductDTO productDTO) {
		productDao.update(productMapper.productDTOtOProduct(productDTO));
		
	}

	@Override
	@Transactional
	public void delete(ProductDTO productDTO) {
		productDao.delete(productMapper.productDTOtOProduct(productDTO));
		
	}

	@Override
	@Transactional
	public ProductDTO getByModel(String model) {
		return productMapper.productToProductDTO(productDao.getByModel(model));
	}

	@Override
	@Transactional
	public List<ProductInfo> searchParam(int category, int consLevel, double fromPrice, double toPrice) {
		List<Product> list = productDao.searchParam(category, consLevel, fromPrice, toPrice);
		List<ProductInfo> infoList = new ArrayList<>();
		for (Product product : list) {
			ProductInfo productInfo = productInfoMapper.productToProductInfo(product);
			infoList.add(productInfo);
		}
		
		return infoList;
	}

	@Override
	@Transactional
	public List<ProductDTO> getTopProducts() {
		List<Product> prodList =  productDao.getTopProducts();
		List<ProductDTO> dtoList = new ArrayList<>();
		
		for (Product prod : prodList) {
			ProductDTO prodDTO = productMapper.productToProductDTO(prod);
			dtoList.add(prodDTO);
		}
		
		return dtoList;
	}

}
