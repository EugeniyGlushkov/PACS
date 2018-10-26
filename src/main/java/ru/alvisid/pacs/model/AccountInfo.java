package ru.alvisid.pacs.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collection;

/**
 * @author GenCloud
 * @date 10/2018
 */
@Entity
public class AccountInfo {
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Getter
	private int id;

	@Getter
	@Setter
	private boolean admin = false;

	@Getter
	@Setter
	private boolean isActive;

	@Getter
	@Setter
	private boolean isBanned;

	Collection<? extends GrantedAuthority> getAuthorities() {
		if (isAdmin()) {
			return AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
		}

		return AuthorityUtils.createAuthorityList("ROLE_USER");
	}
}
