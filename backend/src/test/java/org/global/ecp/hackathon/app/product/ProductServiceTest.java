package org.global.ecp.hackathon.app.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.global.ecp.hackathon.app.TestUtils.getProduct;
import static org.global.ecp.hackathon.app.TestUtils.getProductDto;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.global.ecp.hackathon.app.exception.ProductAlreadyExistsException;
import org.global.ecp.hackathon.app.exception.ProductNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    public static final ProductDto PRODUCT_DTO = getProductDto("name");
    public static final Product PRODUCT_WITH_NULL_ID = getProduct(null, "name");
    public static final Product PRODUCT = getProduct(1L, "name");

    @Mock private ProductRepository productRepository;

    private ProductService underTest;

    @BeforeEach
    void setUp() {

        underTest = new ProductService(productRepository);
    }

    @Test
    void createWillCreateAndReturnAProductFromProductDto() {

        when(productRepository.existsByName(PRODUCT_DTO.getName())).thenReturn(false);
        when(productRepository.save(PRODUCT_WITH_NULL_ID)).thenReturn(PRODUCT);
        assertThat(underTest.create(PRODUCT_DTO)).isEqualTo(PRODUCT);
    }

    @Test
    void createWillThrowAProductAlreadyExistsExceptionIfProductAlreadyExistsInRepository() {

        when(productRepository.existsByName(PRODUCT_DTO.getName())).thenReturn(true);
        assertThatThrownBy(() -> underTest.create(PRODUCT_DTO))
                .isInstanceOf(ProductAlreadyExistsException.class)
                .hasMessage("Product already exists with the same name: '" + PRODUCT_DTO.getName() + "'");
    }

    @Test
    void getByIdWillReturnAProductIfItExistsInTheRepository() {

        when(productRepository.getProductById(PRODUCT.getId())).thenReturn(Optional.of(PRODUCT));
        assertThat(underTest.getById(PRODUCT.getId())).isEqualTo(PRODUCT);
    }

    @Test
    void getByIdWillThrowAProductNotFoundExceptionIfItDoesNotExistInTheRepository() {

        when(productRepository.getProductById(PRODUCT.getId())).thenReturn(Optional.empty());
        assertThatThrownBy(() -> underTest.getById(PRODUCT.getId()))
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessage("Product not found with id: '" + PRODUCT.getId() + "'");
    }

}