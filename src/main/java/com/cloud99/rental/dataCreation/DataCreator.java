package com.cloud99.rental.dataCreation;

import com.cloud99.rental.domain.Name;
import com.cloud99.rental.domain.Person;
import com.cloud99.rental.domain.account.Account;
import com.cloud99.rental.domain.account.Feature;
import com.cloud99.rental.domain.account.Subscription;
import com.cloud99.rental.domain.security.User;
import com.cloud99.rental.service.AccountService;
import com.cloud99.rental.service.FeatureService;
import com.cloud99.rental.service.UserService;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataCreator {

	private User user;
	private List<Feature> features;
	private Account account;

	@Autowired
	private AccountService acctService;

	@Autowired
	private UserService userService;

	@Autowired
	private FeatureService featureService;

	public Account execute() {
		account = createAccount("Nicks 12345 Account");
		features = createFeatures();
		user = createUser();

		user = userService.create(user);
		account.addUserFeatureAccess(user.getId(), features);
		acctService.update(account);

		return account;
	}

	private User createUser() {
		User user = new User();

		user.setEmail("nickgilas@gmail.com");
		user.setPerson(createNewPerson("Nick", "nickgilas@gmail.com"));
		user.setPassword("password");
		user.setEnabled(true);
		return userService.create(user);
	}

	private Person createNewPerson(String name, String email) {
		Person person = new Person();
		person.setAge(40);
		person.setEmail(email);
		person.setName(createName(name));
		return person;
	}

	private Name createName(String first) {
		Name name = new Name();
		name.setFirstName(first);
		return name;
	}

	public List<Feature> createFeatures() {
		return Arrays.asList(

				createFeature("Property Manager", "property managment "),
				createFeature("Tenant", "Tenant portal access"), 
				createFeature("Maintance", "Maintance for property"), 
				createFeature("Accounting", "Accounting an report"),
				createFeature("Storage", "Document storage"));
	}

	private Feature createFeature(String name, String desc) {
		Feature feature = new Feature();
		feature.setEnabledDate(LocalDateTime.now());
		feature.setName(name);
		feature.setDescription(desc);
		return featureService.create(feature);
	}


	public void createSecurity() {

	}

	private Account createAccount(String name) {
		Account acct = new Account();
		acct.setName(name);
		acct.setSubscriptions(buildSubscriptions());
		return acctService.create(acct);
	}

	private List<Subscription> buildSubscriptions() {
		return Arrays.asList(buildSubscription("Pro Subscription"));
	}

	private Subscription buildSubscription(String string) {
		Subscription sub = new Subscription();
		sub.setCreateDate(DateTime.now());
		sub.setName(string);
		sub.setPrice(100);
		return sub;
	}
}
