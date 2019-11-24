package hh.swd20.BookReview;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.runner.RunWith;

@RunWith(SpringRunner.class) // ??
@WebMvcTest
public class WebLayerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void TestDefaultFirstPage() throws Exception {
		//mockMvc.perform(get("/"))
	}

}
