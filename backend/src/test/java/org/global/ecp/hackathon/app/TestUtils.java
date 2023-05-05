package org.global.ecp.hackathon.app;

import static org.global.ecp.hackathon.app.TestConstants.EMAIL;
import static org.global.ecp.hackathon.app.TestConstants.PASSWORD;
import static org.global.ecp.hackathon.app.TestConstants.USERNAME;

import java.util.ArrayList;
import java.util.List;

import org.global.ecp.hackathon.app.basket.Basket;
import org.global.ecp.hackathon.app.basket.BasketProduct;
import org.global.ecp.hackathon.app.product.Product;
import org.global.ecp.hackathon.app.product.ProductDto;
import org.global.ecp.hackathon.app.user.UserDto;

public class TestUtils {

    public static final float DEFAULT_PRICE = 1.5f;
    public static final String DEFAULT_DESCRIPTION = "description";

    public static UserDto getUserDto() {

        return UserDto.builder()
                      .username(USERNAME)
                      .password(PASSWORD)
                      .email(EMAIL).build();
    }

    public static Basket getBasket(final BasketProduct... basketProducts) {

        final var basketProductList = new ArrayList<>(List.of(basketProducts));
        final var totalProducts = basketProductList.stream().mapToInt(BasketProduct::getCount).sum();

        return Basket.builder()
                     .username(USERNAME)
                     .basketProducts(basketProductList)
                     .totalProducts(totalProducts)
                     .build();
    }

    public static Product getProduct(final Long id, final String name) {

        return Product.builder()
                      .id(id)
                      .name(name)
                      .description(DEFAULT_DESCRIPTION)
                      .price(DEFAULT_PRICE).build();
    }

    public static BasketProduct getBasketProduct(final Product product, final int count) {

        return BasketProduct.builder().product(product).count(count)
                            .build();
    }

    public static ProductDto getProductDto(final String name) {

        return ProductDto.builder()
                         .name(name)
                         .description(DEFAULT_DESCRIPTION)
                         .price(DEFAULT_PRICE)
                         .build();
    }

}
