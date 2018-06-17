package com.cloud99.rental.controller;

import com.cloud99.rental.dataCreation.DataCreator;
import com.cloud99.rental.domain.document.account.Account;
import com.cloud99.rental.domain.document.security.User;
import com.cloud99.rental.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(path = "/v1")
public class SecurityController {

	@Autowired
	private DataCreator dataCreator;

	@Autowired
	private UserService userService;

	@Autowired
	private TokenStore tokenStore;

	@RequestMapping(method = RequestMethod.POST, value = "/tokens/revokeRefreshToken/{tokenId:.*}")
	@ResponseBody
	public String revokeRefreshToken(@PathVariable String tokenId) {
		// TODO - replace this with AuthorizationTokenStore
		if (tokenStore instanceof JdbcTokenStore) {
			((JdbcTokenStore) tokenStore).removeRefreshToken(tokenId);
		}
		return tokenId;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/tokens")
	@ResponseBody
	public List<String> getTokens() {
		List<String> tokenValues = new ArrayList<String>();
		Collection<OAuth2AccessToken> tokens = tokenStore.findTokensByClientId("sampleClientId");
		if (tokens != null) {
			for (OAuth2AccessToken token : tokens) {
				tokenValues.add(token.getValue());
			}
		}
		return tokenValues;
	}

	@GetMapping("/loadTestData")
	@ResponseBody
	public Account test(@RequestParam String name) throws Exception {
		Account acct = dataCreator.execute();
		return acct;
	}
	
	@PostMapping("/user/registration")
	public User registerUserAccount(@Valid User user, BindingResult result,
			HttpServletRequest request,
			Errors errors) {
		
		if (!result.hasErrors()) {
			return userService.create(user, request);
		} else {
			// TODO - parse validation error and return to the client
			return null;
		}
	
	}
}
