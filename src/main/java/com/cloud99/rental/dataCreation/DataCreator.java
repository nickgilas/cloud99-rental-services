package com.cloud99.rental.dataCreation;

import com.cloud99.rental.config.security.SecurityRole;
import com.cloud99.rental.domain.document.Name;
import com.cloud99.rental.domain.document.Person;
import com.cloud99.rental.domain.document.account.Account;
import com.cloud99.rental.domain.document.account.Feature;
import com.cloud99.rental.domain.document.account.Subscription;
import com.cloud99.rental.domain.document.security.SecurityAccess.Access;
import com.cloud99.rental.domain.document.security.User;
import com.cloud99.rental.service.AccountService;
import com.cloud99.rental.service.FeatureService;
import com.cloud99.rental.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import de.flapdoodle.embed.process.collections.Collections;

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

	@Autowired
	private ObjectMapper objMapper;

	public static void main(String[] args) {
		ObjectMapper m = new ObjectMapper();
		DataCreator c = new DataCreator();
	}
	public Account execute() throws Exception {

		account = acctService.create(createAccount("Nicks 12345 Account"));

		features = createFeatures();
		features.stream().forEach(f -> featureService.create(f));

		user = userService.create(createUser(), null);

		account.addUserFeatureAccess(user.getId(), features);
		acctService.update(account);

		printJson(user);
		return account;
	}

	public void printJson(Object user) throws Exception {
		objMapper.writeValueAsString(user);
	}

	public User createUser() {
		User user = new User();

		user.setEmail("nickgilas@gmail.com");
		user.setPerson(createNewPerson("Nick", "nickgilas@gmail.com"));
		user.setPassword("password");
		user.setEnabled(true);
		user.addSecurityRole(SecurityRole.ADMIN);
		return user;
	}

	public Person createNewPerson(String name, String email) {
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

	public Feature createFeature(String name, String desc) {
		Feature feature = new Feature();
		feature.setEnabledDate(LocalDateTime.now());
		feature.setName(name);
		feature.setDescription(desc);
		return feature;
	}


	public Collection<Access> createSecurityAccess() {
		return Collections.newArrayList(Access.READ, Access.UPDATE);
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
