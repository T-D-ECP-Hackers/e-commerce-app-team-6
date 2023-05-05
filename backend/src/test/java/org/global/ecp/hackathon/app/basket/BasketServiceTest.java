package org.global.ecp.hackathon.app.basket;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.global.ecp.hackathon.app.TestConstants.INCORRECT_USERNAME;
import static org.global.ecp.hackathon.app.TestConstants.USER;
import static org.global.ecp.hackathon.app.TestConstants.USERNAME;
import static org.global.ecp.hackathon.app.TestUtils.getBasket;
import static org.global.ecp.hackathon.app.TestUtils.getBasketProduct;
import static org.global.ecp.hackathon.app.TestUtils.getProduct;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.global.ecp.hackathon.app.authentication.AuthService;
import org.global.ecp.hackathon.app.exception.ProductNotFoundException;
import org.global.ecp.hackathon.app.exception.UnauthenticatedUserException;
import org.global.ecp.hackathon.app.product.Product;
import org.global.ecp.hackathon.app.product.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BasketServiceTest {

    private static final long PRODUCT_ID_1 = 1;
    private static final long PRODUCT_ID_2 = 2;
    private static final Product PRODUCT = getProduct(PRODUCT_ID_1, "name");
    private static final Product NEW_PRODUCT = getProduct(PRODUCT_ID_2, "new_name");

    @Mock private BasketRepository basketRepository;
    @Mock private ProductService productService;
    @Mock private AuthService authService;

    private BasketService underTest;

    @BeforeEach
    void setUp() {

        underTest = new BasketService(basketRepository, productService, authService);
    }

    @Test
    void getBasketByUsernameWillReturnBasketIfPresentForUser() {

        final var emptyBasket = getBasket();
        mockBasketFromRepository(emptyBasket);
        final var actualBasket = underTest.getBasketByUsername(USERNAME);
        assertIsExpectedBasketEqualToActualBasket(actualBasket,0);
    }

    @Test
    void getBasketByUsernameWillThrowAnUnauthenticatedUserExceptionIfUsernameDoesNotExist() {

        doThrow(new UnauthenticatedUserException("boom")).when(authService).getUser(INCORRECT_USERNAME);
        assertThatThrownBy(() -> underTest.getBasketByUsername(INCORRECT_USERNAME)).isInstanceOf(
                UnauthenticatedUserException.class).hasMessage("boom");
    }

    @Test
    void getBasketByUsernameWillCreateAndReturnANewBasketIfOneDoesNotAlreadyExistForAUser() {

        final var newBasketFromRepo = getBasket();
        mockBasketFromRepository(null);
        when(basketRepository.save(newBasketFromRepo)).thenReturn(newBasketFromRepo);
        final var actualBasket = underTest.getBasketByUsername(USERNAME);
        assertIsExpectedBasketEqualToActualBasket(actualBasket, 0);
    }

    @Test
    void addToBasketWillIncreaseTheCountOfAProductIfItIsAlreadyInTheBasket() {

        final var basketProduct = getBasketProduct(PRODUCT, 1);
        final var basketFromRepo = getBasket(basketProduct);
        mockBasketFromRepository(basketFromRepo);
        when(productService.getById(PRODUCT_ID_1)).thenReturn(PRODUCT);
        when(basketRepository.save(basketFromRepo)).thenReturn(basketFromRepo);
        final var basket = underTest.addToBasket(USERNAME, PRODUCT_ID_1);
        assertIsExpectedBasketEqualToActualBasket(basket, 2, basketProduct.toBuilder().count(2).build());
    }

    @Test
    void addToBasketWillAddANewBasketProductIfItIsNotAlreadyInTheBasket() {

        final var basketProduct = getBasketProduct(PRODUCT, 1);
        final var newBasketProduct = getBasketProduct(NEW_PRODUCT, 1);
        final var basketFromRepo = getBasket(basketProduct);
        final var basketWithNewProducts = getBasket(basketProduct, newBasketProduct);
        mockBasketFromRepository(basketFromRepo);
        when(productService.getById(PRODUCT_ID_2)).thenReturn(NEW_PRODUCT);
        when(basketRepository.save(basketFromRepo)).thenReturn(basketWithNewProducts);
        final var basket = underTest.addToBasket(USERNAME, PRODUCT_ID_2);
        assertIsExpectedBasketEqualToActualBasket(basket, 2, basketProduct, newBasketProduct);
    }

    @Test
    void addToBasketWillThrowAProductNotFoundExceptionIfProductIdDoesNotExistInTheDB() {

        final var basketProduct = getBasketProduct(PRODUCT, 1);
        final var basketFromRepo = getBasket(basketProduct);
        mockBasketFromRepository(basketFromRepo);
        doThrow(new ProductNotFoundException("boom!")).when(productService).getById(PRODUCT_ID_2);
        assertThatThrownBy(() -> underTest.addToBasket(USERNAME, PRODUCT_ID_2)).isInstanceOf(
                ProductNotFoundException.class).hasMessage("boom!");

    }

    @Test
    void removeFromBasketWillRemoveAProductIfItIsAlreadyInTheBasketAndHasACountEqualTo1() {

        final var basketProduct = getBasketProduct(PRODUCT, 1);
        final var basketFromRepo = getBasket(basketProduct);
        mockBasketFromRepository(basketFromRepo);
        when(productService.getById(PRODUCT_ID_1)).thenReturn(PRODUCT);
        when(basketRepository.save(basketFromRepo)).thenReturn(basketFromRepo);
        final var actualBasket = underTest.removeFromBasket(USERNAME, PRODUCT_ID_1);
        assertIsExpectedBasketEqualToActualBasket(actualBasket, 0);
    }

    @Test
    void removeFromBasketWillDecreaseTheCountOfAProductIfItIsAlreadyInTheBasketAndHasACountHigherThan1() {

        final var basketProductWithCountOf3 = getBasketProduct(PRODUCT, 3);
        final var basketFromRepo = getBasket(basketProductWithCountOf3);
        mockBasketFromRepository(basketFromRepo);
        when(productService.getById(PRODUCT_ID_1)).thenReturn(PRODUCT);
        when(basketRepository.save(basketFromRepo)).thenReturn(basketFromRepo);
        final var actualBasket = underTest.removeFromBasket(USERNAME, PRODUCT_ID_1);
        assertIsExpectedBasketEqualToActualBasket(actualBasket, 2, basketProductWithCountOf3.toBuilder()
                                                                                            .count(2)
                                                                                            .build());
    }

    @Test
    void checkoutWillClearAllBasketProductsAndSaveEmptyBasket() {

        final var basketProduct = getBasketProduct(PRODUCT, 1);
        final var basketFromRepo = getBasket(basketProduct);
        mockBasketFromRepository(basketFromRepo);
        when(basketRepository.save(basketFromRepo)).thenReturn(basketFromRepo);
        final var actualBasket = underTest.checkout(USERNAME);
        assertIsExpectedBasketEqualToActualBasket(actualBasket, 0);
    }

    private void mockBasketFromRepository(final Basket basket) {

        final Optional<Basket> optionalBasket = basket == null ? Optional.empty() : Optional.of(basket);
        when(authService.getUser(USERNAME)).thenReturn(USER);
        when(basketRepository.getBasketByUsername(USERNAME)).thenReturn(optionalBasket);
    }

    private void assertIsExpectedBasketEqualToActualBasket(final Basket basket,
                                                           final int totalProducts,
                                                           final BasketProduct... basketProducts) {

        assertThat(basket.getUsername()).isEqualTo(USERNAME);
        assertThat(basket.getTotalProducts()).isEqualTo(totalProducts);
        assertThat(basket.getBasketProducts()).containsExactly(basketProducts);
    }
}