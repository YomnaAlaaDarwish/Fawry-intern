package org.example.store;

import org.example.account.AccountManager;
import org.example.account.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class StoreImplTest {

    @Test
    public void givenProductWithPositiveQuantityAndCustomerWithSufficientBalance_whenBuy_thenSuccess() {

        // Arrange
        Product product = new Product();
        product.setPrice(100);
        product.setQuantity(4);

        Customer customer = new Customer();

        AccountManager accountManager = mock(AccountManager.class);
        when(accountManager.withdraw(customer, 100))
                .thenReturn("success");

        Store store = new StoreImpl(accountManager);

        // Act
        store.buy(product, customer);

        // Assert
        Assertions.assertEquals(3, product.getQuantity());
        verify(accountManager).withdraw(customer, 100);
    }

    @Test
    public void givenProductWithNoQuantity_whenBuy_thenThrowsException() {
        // Arrange
        Product product = new Product();
        product.setPrice(100);
        product.setQuantity(0); // Out of stock

        Customer customer = new Customer();

        AccountManager accountManager = mock(AccountManager.class);

        Store store = new StoreImpl(accountManager);

        // Act & Assert
        Assertions.assertThrows(RuntimeException.class, () -> {
            store.buy(product, customer);
        });
    }

    @Test
    public void givenProductWithPositiveQuantityAndInsufficientBalance_whenBuy_thenThrowsException() {
        // Arrange
        Product product = new Product();
        product.setPrice(100);
        product.setQuantity(3);

        Customer customer = new Customer();

        AccountManager accountManager = mock(AccountManager.class);
        when(accountManager.withdraw(customer, 100))
                .thenReturn("insufficient account balance");

        Store store = new StoreImpl(accountManager);

        // Act & Assert
        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            store.buy(product, customer);
        });
        Assertions.assertEquals("Payment failure: insufficient account balance", thrown.getMessage());
    }

    @Test
    public void givenProductWithPositiveQuantityAndExceedsMaxCredit_whenBuy_thenThrowsException() {
        // Arrange
        Product product = new Product();
        product.setPrice(100);
        product.setQuantity(3);

        Customer customer = new Customer();

        AccountManager accountManager = mock(AccountManager.class);
        when(accountManager.withdraw(customer, 100))
                .thenReturn("maximum credit exceeded");

        Store store = new StoreImpl(accountManager);

        // Act & Assert
        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            store.buy(product, customer);
        });
        Assertions.assertEquals("Payment failure: maximum credit exceeded", thrown.getMessage());
    }
}
