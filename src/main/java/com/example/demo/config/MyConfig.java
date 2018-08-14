package com.example.demo.config;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

/**
 * @ConfigurationProperties指定propertis里加载的属性
 * @Validated 为config提供校验，需要依赖validate-start
 * @author lijin
 *
 */
@Configuration
@ConfigurationProperties(prefix = "app")
@Validated
public class MyConfig {
	@NotNull
	private String name;
	@NotEmpty
	private String description;
	private int version;
	private List<String> servers;
	private Security security = new Security();

	public static class Security {
		private String username;
		private String password;

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		@Override
		public String toString() {
			return "Security [username=" + username + ", password=" + password + "]";
		}

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "MyConfig [name=" + name + ", description=" + description + ", version=" + version + ", servers="
				+ servers + ", security=" + security + "]";
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public List<String> getServers() {
		return servers;
	}

	public void setServers(List<String> servers) {
		this.servers = servers;
	}

	public Security getSecurity() {
		return security;
	}

	public void setSecurity(Security security) {
		this.security = security;
	}

}
