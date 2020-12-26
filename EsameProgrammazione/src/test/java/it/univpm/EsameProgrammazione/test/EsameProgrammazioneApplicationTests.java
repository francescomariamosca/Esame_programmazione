package it.univpm.EsameProgrammazione.test;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import it.univpm.EsameProgrammazione.Exception.NoDataException;
import it.univpm.EsameProgrammazione.Stats.*;
import it.univpm.EsameProgrammazione.Utilities.DataSet;
import it.univpm.EsameProgrammazione.Utilities.DownloadDataSet;

@SpringBootTest
class EsameProgrammazioneApplicationTests {

	private Statstemperature stats;
	private DataSet dataSet;
	private DownloadDataSet download;
	
	@BeforeEach
	protected void setUp() throws Exception
	{
		stats = new Statstemperature();
		dataSet = new DataSet();
		download = new DownloadDataSet();
		dataSet.setWeathers(download.DownloadArray());
		stats.caricaArrayTemperatura(dataSet, "Roma");
	}
	
	@AfterEach
	protected void tearDown() throws Exception{}
	
	
	@Test
	@DisplayName("Test 1: Corretto caricamento vettore dal dataSet")
	void test1() {
		assertNotNull(dataSet.getWeathers());
	}
	
	@Test
	@DisplayName("Test 2: Eccezione ArrayTemperatura")
	void test2() {
		try {	
			stats.temperatura.clear();
			stats.caricaArrayTemperatura(dataSet, "cittaCheNonEsiste");
			fail("Eccezione non generata");
		} catch (NoDataException e) {
			e.printStackTrace();
		}
	}

}
