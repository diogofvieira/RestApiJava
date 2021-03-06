package com.diogo.franchi.money.transfer.account;

import com.diogo.franchi.money.transfer.model.Account;
import com.diogo.franchi.money.transfer.model.MessageResponse;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import static spark.utils.Assert.notNull;

import java.util.List;
import java.util.NoSuchElementException;


public class AccountController {

    private final AccountService accountService;
    private Gson gson = new Gson();

    public AccountController(AccountService accountService) {
        notNull(accountService);
        this.accountService = accountService;
    }

    public Object newAccount(Request req, Response res) {
        AccountRequest accountRequest = gson.fromJson(req.body(), AccountRequest.class);
        try {
            Account account = accountService.create(accountRequest);
            res.status(201);
            return account;
        } catch (IllegalArgumentException ex) { 
            res.status(400);
            return new MessageResponse(400, "Bad Request");
        } catch (Exception ex) {
            res.status(500);
            return new MessageResponse(500, "Internal Server Error");
        }
    }
    
    public Object allAccounts(Request req, Response res) {
        try {
        	List<Account> accounts = accountService.listAll();
        	res.status(200);
            return accounts;
        } catch (NoSuchElementException ex) { 
        	res.status(422);
            return new MessageResponse(422, "No Elements");
        } catch (Exception ex) {
        	res.status(500);
            return new MessageResponse(500, "Internal Server Error");
        }
    }
    
    public Object deleteAll(Request req, Response res) {
    	try {
    		int count = accountService.clear();
        	res.status(200);
        	return new MessageResponse(204, "No Content - deleted "+ count + " account(s)");
    	}catch (Exception ex) {
        	res.status(500);
            return new MessageResponse(500, "Internal Server Error");
        }
    }

}
