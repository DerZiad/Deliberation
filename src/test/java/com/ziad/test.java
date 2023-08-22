package com.ziad;

import org.hibernate.service.spi.InjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ziad.repositories.EtudiantRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
  locations = "classpath:application.properties")
public class test {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private EtudiantRepository et;
    @Test
    public void testCff() {
    	et.findAll();
    }
    // write test cases here
}
/**
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("junit")
public class ApplicationServiceControllerTest {
	
	@Value("${application.backend.ws.user}")
	private String user;

	@Value("${application.backend.ws.password}")
	private String passwd;
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	public void whenFindAllApplications_returnOk() throws Exception 
	{
	
		mvc.perform( MockMvcRequestBuilders
	          .get("/applications")
	          .header(HttpHeaders.AUTHORIZATION, "Basic " + Base64Utils.encodeToString((user + ":" + passwd).getBytes()))
		      .accept(MediaType.APPLICATION_JSON))
		      .andExpect(status().isOk());
	}
	
	@Test
	public void whenFindApplicationById_returnOk() throws Exception 
	{
	
		ApplicationModel application= new ApplicationModel("testAddApp", "testAppLogicalName", new ArrayList<AppAttributeMap>(), null);
		Long id = insertApplication(application);
				
		mvc.perform( MockMvcRequestBuilders
		      .get("/application/{id}", id)
		      .header(HttpHeaders.AUTHORIZATION, "Basic " + Base64Utils.encodeToString((user + ":" + passwd).getBytes()))
		      .accept(MediaType.APPLICATION_JSON))
		      .andExpect(status().isOk());
	}
	**/