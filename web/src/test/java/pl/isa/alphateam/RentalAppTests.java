package pl.isa.alphateam;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import pl.isa.alphateam.reservation.FilterDates;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class RentalAppTests {
	@Autowired
	WebApplicationContext context;
	@Autowired
	MockMvc moc;

	@Test
	void contextLoads() {
		assertNotNull(context);
	}

	@Test
	void FilterDatesTests() throws Exception {
		FilterDates filterD = new FilterDates();
	filterD.setEndDate(LocalDate.of(2024,6,29));
	filterD.setStartDate(LocalDate.of(2024,6,30));
	moc.perform(get("/boats-parser").flashAttr("filterDates",filterD)).andExpectAll(
			status().isBadRequest()
	);

	}
}