package ru.alikhano.cyberlife.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.alikhano.cyberlife.DTO.CustomLogicException;
import ru.alikhano.cyberlife.DTO.OrderDTO;
import ru.alikhano.cyberlife.DTO.OrderItemDTO;
import ru.alikhano.cyberlife.DTO.ProductDTO;
import ru.alikhano.cyberlife.DTO.ProductInfo;
import ru.alikhano.cyberlife.dao.ProductDao;
import ru.alikhano.cyberlife.mapper.ProductInfoMapper;
import ru.alikhano.cyberlife.mapper.ProductMapper;
import ru.alikhano.cyberlife.model.Product;
import ru.alikhano.cyberlife.service.OrderService;
import ru.alikhano.cyberlife.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;

	@Autowired
	ProductMapper productMapper;

	@Autowired
	ProductInfoMapper productInfoMapper;
	
	@Autowired
	OrderService orderService;

	@Autowired
	MessagingService messaginService;

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
	public ProductDTO getById(int id) throws CustomLogicException {
		ProductDTO productDTO = productMapper.productToProductDTO((Product) (productDao.getById(id)));
		if (productDTO == null) {
			throw new CustomLogicException("No product with such id");
		}
		return productDTO;
	}

	@Override
	@Transactional
	public void create(ProductDTO productDTO) throws CustomLogicException {
		ProductDTO product = getByModel(productDTO.getModel());
		if (product != null) {
			throw new CustomLogicException("The model you have specified already exists in the catalogue");
		}
		productDao.create(productMapper.productDTOtOProduct(productDTO));

	}

	@Override
	@Transactional
	public String update(ProductDTO productDTO) throws IOException, TimeoutException, CustomLogicException {
		
		if (productDTO == null) {
			throw new CustomLogicException("No such product, cannot edit");
		}
		if (productDTO.getUnitsInStock() < 0) {
			return "negative units";
		}
		else if (productDTO.getPrice() < 0) {
			return "negative price";
		}
		productDao.update(productMapper.productDTOtOProduct(productDTO));
		if (isInTop(productDTO)) {
			messaginService.sendUpdateMessage("table should be updated!");
		}
		
		return "sucess";

	}

	@Override
	@Transactional
	public String delete(ProductDTO productDTO) throws CustomLogicException, IOException, TimeoutException {
		if (productDTO == null) {
			throw new CustomLogicException("No such product, cannot delete");
		}
		
		if (canBeDeleted(productDTO)) {
			productDao.delete(productMapper.productDTOtOProduct(productDTO));
			if (isInTop(productDTO)) {
				messaginService.sendUpdateMessage("table should be updated!");
			}
			
			return "success";
		}
		
		return "failed";
		
		

	}

	@Override
	@Transactional
	public ProductDTO getByModel(String model) throws CustomLogicException {
		return productMapper.productToProductDTO(productDao.getByModel(model));

	}

	@Override
	@Transactional
	public List<ProductInfo> searchParam(String model, int category, int consLevel, double fromPrice, double toPrice) {
		List<Product> list = productDao.searchParam(model, category, consLevel, fromPrice, toPrice);
		
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
		List<Product> prodList = productDao.getTopProducts();
		List<ProductDTO> dtoList = new ArrayList<>();

		for (Product prod : prodList) {
			ProductDTO prodDTO = productMapper.productToProductDTO(prod);
			dtoList.add(prodDTO);
		}

		return dtoList;
	}

	@Override
	@Transactional
	public ProductDTO selectForUpdate(int id) {
		return productMapper.productToProductDTO(productDao.selectForUpdate(id));
	}

	@Override
	@Transactional
	public Product getProductById(int id) {
		return productDao.getById(id);
	}

	@Override
	@Transactional
	public boolean isInTop(ProductDTO productDTO) {
		List<ProductDTO> top = getTopProducts();
		for (ProductDTO product : top) {
			if (product.getProductId() == productDTO.getProductId()) {
				return true;
			}
		}
		return false;
	}

	@Override
	@Transactional
	public void merge(ProductDTO productDTO) throws IOException, TimeoutException {
		productDao.merge(productMapper.productDTOtOProduct(productDTO));
		if (isInTop(productDTO)) {
			messaginService.sendUpdateMessage("table should be updated!");
		}
	}

	@Override
	@Transactional
	public boolean canBeDeleted(ProductDTO productDTO) {
	    List<OrderDTO> orders = orderService.getAll();
	    for (OrderDTO order : orders) {
	    	Set<OrderItemDTO> orderItems = order.getOrderedItems();
	    	for (OrderItemDTO orderItem : orderItems) {
	    		if (orderItem.getProduct().getProductId() == productDTO.getProductId() && order.getPaymentStatus() != "paid" && order.getOrderStatus() != "delivered and recieved") {
	    			return false;
	    		}
	    	}
	    }
		return true;
	}

}
