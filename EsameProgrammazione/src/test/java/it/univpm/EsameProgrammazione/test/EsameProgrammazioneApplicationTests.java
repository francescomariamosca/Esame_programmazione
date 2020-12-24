/*package it.univpm.EsameProgrammazione.test;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.springframework.boot.test.context.SpringBootTest;
import it.univpm.EsameProgrammazione.Exception.NoDataException;
import it.univpm.EsameProgrammazione.Model.Weather;
import it.univpm.EsameProgrammazione.Stats.*;
import it.univpm.EsameProgrammazione.Utilities.DataSet;
import it.univpm.EsameProgrammazione.Utilities.DownloadDataSet;
import junit.framework.TestCase;

@SpringBootTest
class EsameProgrammazioneApplicationTests extends TestCase{

	private Statstemperature stats;
	private DataSet dataSet;
	private DownloadDataSet download;
	
	protected void setUp() throws Exception
	{
		stats = new Statstemperature();
		dataSet = new DataSet();
		download = new DownloadDataSet();
		dataSet.setWeathers(download.DownloadArray());
		stats.caricaArrayTemperatura(dataSet, "Roma");
	}
	
	protected void tearDown() throws Exception{}
	
	
	@org.junit.Test
	void test2() {
		for(Weather w : dataSet.getWeathers())
			assertEquals(w.getNomeCitta(),"Roma");
	}
	
	@Test
	void test4() {
		try {
			stats.temperatura.clear();
			stats.caricaArrayTemperatura(dataSet, "cittaCheNonEsiste");
			fail("Eccezione non generata");
		} catch (NoDataException e) {
			e.printStackTrace();
		}
	}

}
*/