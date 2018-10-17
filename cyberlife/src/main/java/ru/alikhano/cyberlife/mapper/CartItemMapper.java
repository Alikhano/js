package ru.alikhano.cyberlife.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import ru.alikhano.cyberlife.DTO.CartDTO;
import ru.alikhano.cyberlife.DTO.CartItemDTO;
import ru.alikhano.cyberlife.DTO.CategoryDTO;
import ru.alikhano.cyberlife.DTO.ConsDTO;
import ru.alikhano.cyberlife.DTO.ProductDTO;
import ru.alikhano.cyberlife.model.Cart;
import ru.alikhano.cyberlife.model.CartItem;
import ru.alikhano.cyberlife.model.Category;
import ru.alikhano.cyberlife.model.Consciousness;
import ru.alikhano.cyberlife.model.Product;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, uses = { CartMapper.class })
public interface CartItemMapper {

	@Mappings({
		@Mapping(target = "cart", qualifiedByName = "cartDTOtoCart") })
	CartItem cartDTOtoCartItem(CartItemDTO cartItemDTO);

	@Mappings({
			@Mapping(target = "cart", qualifiedByName = "cartToCartDTO") })
	CartItemDTO cartItemToCartItemDTO(CartItem CartItem);

	@Named("cartToCartDTO")
	@Mappings({ @Mapping(target = "items", expression = "java(null)") })
	CartDTO cartToCartDTO(Cart cart);
	
	@Named("cartDTOtoCart")
	@Mappings({ @Mapping(target = "items", expression = "java(null)") })
	Cart cartDTOtoCart(CartDTO cartDTO);

	ProductDTO productToProductDTO(Product product);

	Product productDTOtOProduct(ProductDTO productDTO);

	CategoryDTO categoryToCategoryDTO(Category category);

	Category categoryDTOtoCategory(CategoryDTO categoryDTO);

	ConsDTO consToConsDTO(Consciousness cons);

	Consciousness consDTOtoCons(ConsDTO consDTO);

}
