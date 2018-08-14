package com.example.demo.contrtoller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserJpaControllerTest {
	// 模拟MVC对象，通过MockMvcBuilders.webAppContextSetup(this.wac).build()初始化。
	private MockMvc mockMvc;

	// 注入WebApplicationContext
	@Autowired
	private WebApplicationContext wac;

	// 注入模拟的http session
	// @Autowired
	// private MockHttpSession session;

	// 注入模拟的http request
	// @Autowired
	// private MockHttpServletRequest request;

	@Before // 在测试开始前初始化工作
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testJdbcController() throws Exception {
		// 两次调用，ehcache生效
		for (int i = 0; i < 2; i++) {
			// MockMvcRequestBuilders.post/get 请求
			// MockMvcResultMatchers.content/status 结果
			MvcResult result = mockMvc.perform(get("/jdbc/user/1")).andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(content()
							.json("{\"id\":1,\"username\":\"account1\",\"name\":\"张三\",\"age\":20,\"balance\":100.01}"))
					// charset=UTF-8
					.andReturn();// 返回执行请求的结果
			System.out.println(result.getResponse().getContentAsString());
		}
	}

	@Test
	public void testJpaController() throws Exception {
		MvcResult result = mockMvc.perform(get("/jpa/user/1")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(content()
						.json("{\"id\":1,\"username\":\"account1\",\"name\":\"张三\",\"age\":20,\"balance\":100.01}"))
				// charset=UTF-8
				.andReturn();// 返回执行请求的结果
		System.out.println(result.getResponse().getContentAsString());
	}

}
