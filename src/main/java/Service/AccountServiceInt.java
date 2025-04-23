package Service;

import Model.Account;

/*
 * Interface for AccountService
 */
public interface AccountServiceInt {
    public Account createAccount(Account account);
    public Account loginAccount(Account account);
}
