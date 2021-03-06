package com.diogo.franchi.money.transfer.transfer;

import com.diogo.franchi.money.transfer.model.Account;
import com.diogo.franchi.money.transfer.model.MessageResponse;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import static spark.utils.Assert.notNull;

public class TransferController {
		
		private final TransferService transferService;
		
		public TransferController(TransferService transferService) {
	        notNull(transferService);
	        this.transferService = transferService;
	    }

	    public Object doTransfer(Request req, Response res) {
	        TransferRequest transferRequest = new Gson().fromJson(req.body(), TransferRequest.class);
	        try {
	        	Account account = transferService.update(transferRequest);
	        	res.status(200);
	        	return account;
	        } catch (IllegalArgumentException ex) { 
	            res.status(400);
	            return new MessageResponse(400, "Bad Request");
	        } catch (Exception ex) {
	            res.status(500);
	            return new MessageResponse(500, "Internal Server Error");
	        }
	    }
}
