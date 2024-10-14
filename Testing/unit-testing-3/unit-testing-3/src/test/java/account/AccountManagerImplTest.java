package account;

import org.example.account.AccountManagerImpl;
import org.example.account.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class AccountManagerImplTest {

    private AccountManagerImpl accountManager;
    private Customer customer;

    @BeforeEach
    public void setUp() {
        accountManager = new AccountManagerImpl();
        customer = new Customer();
        customer.setBalance(500); // Initial balance
        customer.setCreditAllowed(true);
        customer.setVip(false);
    }

    @Test
    public void givenPositiveAmount_whenDeposit_thenBalanceIncreases() {
        // Act
        accountManager.deposit(customer, 200);

        // Assert
        Assertions.assertEquals(700, customer.getBalance());
    }

    @Test
    public void givenSufficientBalance_whenWithdraw_thenSuccessAndBalanceDecreases() {
        // Act
        String status = accountManager.withdraw(customer, 300);

        // Assert
        Assertions.assertEquals("success", status);
        Assertions.assertEquals(200, customer.getBalance());
    }

    @Test
    public void givenInsufficientBalanceAndNoCreditAllowed_whenWithdraw_thenFailure() {
        // Arrange
        customer.setCreditAllowed(false);

        // Act
        String status = accountManager.withdraw(customer, 600);

        // Assert
        Assertions.assertEquals("insufficient account balance", status);
        Assertions.assertEquals(500, customer.getBalance());
    }

    @Test
    public void givenInsufficientBalanceAndCreditAllowedAndNotExceedMaxLimit_whenWithdraw_thenSuccessWithinCreditLimit() {
        // Act
        String status = accountManager.withdraw(customer, 600); // Balance would become -100, not within credit limit

        // Assert
        Assertions.assertEquals("success", status);
        Assertions.assertEquals(-100, customer.getBalance());
    }

    @Test
    public void givenInsufficientBalanceAndCreditAllowedAndExceedMaxLimitAndNotVip_whenWithdraw_thenFailure() {
        // Act
        String status = accountManager.withdraw(customer, 1600); // Balance would become -1100, not within credit limit

        // Assert
        Assertions.assertEquals("maximum credit exceeded", status);
        Assertions.assertEquals(500, customer.getBalance());
    }

    @Test
    public void givenInsufficientBalanceAndCreditAllowedAndExceedMaxLimitAndVip_whenWithdraw_thenSuccess() {
        customer.setVip(true);
        // Act
        String status = accountManager.withdraw(customer, 1600); // Balance would become -1100, not within credit limit

        // Assert
        Assertions.assertEquals("success", status);
        Assertions.assertEquals(-1100, customer.getBalance());
    }

}
