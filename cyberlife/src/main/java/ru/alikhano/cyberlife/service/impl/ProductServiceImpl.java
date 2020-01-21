package ru.alikhano.cyberlife.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.alikhano.cyberlife.dto.CustomLogicException;
import ru.alikhano.cyberlife.dto.OrderDTO;
import ru.alikhano.cyberlife.dto.OrderItemDTO;
import ru.alikhano.cyberlife.dto.ProductDTO;
import ru.alikhano.cyberlife.dto.ProductInfo;
import ru.alikhano.cyberlife.dto.SearchRequest;
import ru.alikhano.cyberlife.dao.ProductDao;
import ru.alikhano.cyberlife.mapper.ProductInfoMapper;
import ru.alikhano.cyberlife.mapper.ProductMapper;
import ru.alikhano.cyberlife.model.Product;
import ru.alikhano.cyberlife.service.CategoryService;
import ru.alikhano.cyberlife.service.ConsciousnessService;
import ru.alikhano.cyberlife.service.OrderService;
import ru.alikhano.cyberlife.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private ProductInfoMapper productInfoMapper;
	
	@Autowired
	private OrderService orderService;

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ConsciousnessService consService;
	
	@Autowired
	private MessagingService messagingService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public List<ProductDTO> getAll() {
		List<Product> products = productDao.getAll();
		List<ProductDTO> productsDTO = new ArrayList<>();
		products.forEach(product -> {
			ProductDTO productDTO = productMapper.productToProductDTO(product);
			productsDTO.add(productDTO);
		});

		return productsDTO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public ProductDTO getById(int id) throws CustomLogicException {
		ProductDTO productDTO = productMapper.productToProductDTO((productDao.getById(id)));
		if (productDTO == null) {
			throw new CustomLogicException("No product with such id");
		}
		return productDTO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void create(ProductDTO productDTO) throws CustomLogicException {
		ProductDTO product = getByModel(productDTO.getModel());
		if (product != null) {
			throw new CustomLogicException("The model you have specified already exists in the catalogue");
		}
		productDao.create(productMapper.productDTOtOProduct(productDTO));
	}

	/**
	 * {@inheritDoc}
	 */
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
			messagingService.sendUpdateMessage("table should be updated!");
		}
		
		return "sucess";

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public String delete(ProductDTO productDTO) throws CustomLogicException, IOException, TimeoutException {
		if (productDTO == null) {
			throw new CustomLogicException("No such product, cannot delete");
		}
		
		if (isAvailableForDeletion(productDTO)) {
			productDao.delete(productMapper.productDTOtOProduct(productDTO));
			if (isInTop(productDTO)) {
				messagingService.sendUpdateMessage("table should be updated!");
			}
			
			return "success";
		}
		
		return "failed";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public ProductDTO getByModel(String model) {
		return productMapper.productToProductDTO(productDao.getByModel(model));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public List<ProductInfo> searchParam(SearchRequest request) {
		int catId;
		int consId;
		String category = request.getCategory();
		String model = request.getModel();
		
		 if(category.equals("any")) {
			 catId = 0;
		 }
		 else {
			 catId = categoryService.getByType(category).getCategoryId();
		 }

		 String consLevel = request.getConsLevel();
		 if(consLevel.equals("any")) {
			 consId = 0;
		 }
		 else {
	        consId = consService.getByLevel(consLevel).getConsId();
		 }
		
		 double fromPrice = request.getFromPrice();
		 double toPrice = request.getToPrice();
		 
		List<Product> list = productDao.searchParam(model, catId, consId, fromPrice, toPrice);
		
		List<ProductInfo> infoList = new ArrayList<>();
		for (Product product : list) {
			ProductInfo productInfo = productInfoMapper.productToProductInfo(product);
			infoList.add(productInfo);
		}

		return infoList;
	}

	/**
	 * {@inheritDoc}
	 */
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public ProductDTO selectForUpdate(int id) {
		return productMapper.productToProductDTO(productDao.selectForUpdate(id));
	}

	/**
	 * {@inheritDoc}
	 */
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void merge(ProductDTO productDTO) throws IOException, TimeoutException {
		productDao.merge(productMapper.productDTOtOProduct(productDTO));
		if (isInTop(productDTO)) {
			messagingService.sendUpdateMessage("table should be updated!");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public boolean isAvailableForDeletion(ProductDTO productDTO) {
	    List<OrderDTO> orders = orderService.getAll();
	    for (OrderDTO order : orders) {
	    	Set<OrderItemDTO> orderItems = order.getOrderedItems();
	    	for (OrderItemDTO orderItem : orderItems) {
	    		if (orderItem.getProduct().getProductId() == productDTO.getProductId()
						&& "paid".equals(order.getPaymentStatus())
						&& "delivered and recieved".equals(order.getOrderStatus())) {
	    			return false;
	    		}
	    	}
	    }
		return true;
	}

	@Override
	@Transactional
	public boolean isProductExistingById(Integer productId) {
		try {
		 getById(productId);
		 return true;
		}
		catch (CustomLogicException e) {
			return false;
		}

	}

	@Override
	@Transactional
	public boolean isProductExistingByModel(String productModel) {
		return getByModel(productModel) != null;
	}

}
