import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ResourceCentreTest {
	private Camcorder cc1;
	private Camcorder cc2;
	private Camcorder cc3;
	private Chromebook cb1;
	private Chromebook cb2;
	private Chromebook cb3;

	private ArrayList<Camcorder> camcorderList;
	private ArrayList<Chromebook> chromebookList;

	public ResourceCentreTest() {
		super();
	}

	@Before
	public void setUp() throws Exception {

		cc1 = new Camcorder("CC0011", "Nikon HDSLR", 40);
		cc2 = new Camcorder("CC0012", "Sony DSC-RX100M7", 20);
		cc3 = new Camcorder("CC0013", "panasoni DSC-RX100M7", 30);
		cb1 = new Chromebook("CB0011", "My Google Chromebook 1st", "Mac OS");
		cb2 = new Chromebook("CB0012", "SAMSUNG Chromebook 4+", "Win 10");
		cb3 = new Chromebook("CB0013", "HUAWEI Magicbook 100+", "Mac 10");

		camcorderList = new ArrayList<Camcorder>();
		chromebookList = new ArrayList<Chromebook>();
	}

	@Test
	public void addCamcorderTest() {

		assertNotNull("Check if there is valid Camcorder arraylist to add to", camcorderList);
		assertEquals("Check that Camcorder arraylist size is 0", 0, camcorderList.size());
		
		ResourceCentre.addCamcorder(camcorderList, cc1);
		assertEquals("Check that Camcorder arraylist size is 1", 1, camcorderList.size());
		assertEquals("Check that Camcorder is added", cc1, camcorderList.get(0));

		ResourceCentre.addCamcorder(camcorderList, cc2);
		assertEquals("Check that Camcorder arraylist size is 2", 2, camcorderList.size());
		assertEquals("Check that Camcorder is added", cc2, camcorderList.get(1));
		
		ResourceCentre.addCamcorder(camcorderList, cc3);
		assertEquals("Check that Camcorder arraylist size is 3", 3, camcorderList.size());
		assertEquals("Check that Camcorder is added", cc3, camcorderList.get(2));
		
		ResourceCentre.addCamcorder(camcorderList, new Camcorder("", "panasoni DSC-RX100M7", 30));
		ResourceCentre.addCamcorder(camcorderList, new Camcorder("CC0014", "", 30));
		ResourceCentre.addCamcorder(camcorderList, new Camcorder("CC0014", "panasoni DSC-RX100M7", -1));
		assertEquals("Check that Camcorder arraylist size is 3", 3, camcorderList.size());
		
		ResourceCentre.addCamcorder(camcorderList, cc3);
		assertEquals("Check that Camcorder arraylist size is 3", 3, camcorderList.size());
	}

	@Test
	public void addChromebookTest() {
		assertNotNull("Check if there is valid Chromebook arraylist to add to", chromebookList);
		assertEquals("Check that Chromebook arraylist size is 0", 0, chromebookList.size());
		ResourceCentre.addChromebook(chromebookList, cb1);

		assertEquals("Check that Chromebook arraylist size is 1", 1, chromebookList.size());
		assertEquals("Check that Chromebook is added", cb1, chromebookList.get(0));

		ResourceCentre.addChromebook(chromebookList, cb2);
		assertEquals("Check that Chromebook arraylist size is 2", 2, chromebookList.size());
		assertEquals("Check that Chromebook is added", cb2, chromebookList.get(1));
		
		ResourceCentre.addChromebook(chromebookList, cb3);
		assertEquals("Check that Chromebook arraylist size is 3", 3, chromebookList.size());
		assertEquals("Check that Chromebook is added", cb3, chromebookList.get(2));
		
		ResourceCentre.addChromebook(chromebookList, new Chromebook("CB0014","", "Win 10"));
		ResourceCentre.addChromebook(chromebookList, new Chromebook("","My Google Chromebook 1st", "Win 10"));
		ResourceCentre.addChromebook(chromebookList, new Chromebook("CB0014","My Google Chromebook 1st", ""));
		assertEquals("Check that Chromebook arraylist size is 3", 3, chromebookList.size());
		
		ResourceCentre.addChromebook(chromebookList, cb3);
		assertEquals("Check that Chromebook arraylist size is 3", 3, chromebookList.size());
	}

	@Test
	public void retrieveAllCamcorderTest() {

		assertNotNull("Check if there is valid Camcorder arraylist to add to", camcorderList);
		ResourceCentre.addCamcorder(camcorderList, cc1);
		ResourceCentre.addCamcorder(camcorderList, cc2);
		assertEquals("Check that Camcorder arraylist size is 2", 2, camcorderList.size());

		String allCamcorder = ResourceCentre.retrieveAllCamcorder(camcorderList);
		String testOutput = String.format("%-10s %-20s %-10s %-10s %-20s\n", "ASSET TAG", "DESCRIPTION", "AVAILABLE",
				"DUE DATE", "OPTICAL ZOOM");
		testOutput += String.format("%-10s %-20s %-10s %-10s %-20d\n", "CC0011", "Nikon HDSLR", "Yes", "", 40);
		testOutput += String.format("%-10s %-20s %-10s %-10s %-20d\n", "CC0012", "Sony DSC-RX100M7", "Yes", "", 20);

		assertEquals("Check that ViewAllCamcorderlist", testOutput, allCamcorder);

	}

	@Test
	public void retrieveAllChromebookTest() {

		assertNotNull("Check if there is valid Chromebook arraylist to add to", chromebookList);
		ResourceCentre.addChromebook(chromebookList, cb1);
		ResourceCentre.addChromebook(chromebookList, cb2);
		assertEquals("Check that Chromebook arraylist size is 2", 2, chromebookList.size());

		String allChromebook = ResourceCentre.retrieveAllChromebook(chromebookList);
		String testOutput = String.format("%-10s %-20s %-10s %-10s %-20s\n", "ASSET TAG", "DESCRIPTION", "AVAILABLE",
				"DUE DATE", "OPERATING SYSTEM");
		testOutput += String.format("%-10s %-20s %-10s %-10s %-20s\n", "CB0011", "My Google Chromebook 1st", "Yes", "",
				"Mac OS");
		testOutput += String.format("%-10s %-20s %-10s %-10s %-20s\n", "CB0012", "SAMSUNG Chromebook 4+", "Yes", "",
				"Win 10");

		assertEquals("Check that ViewAllChrombooklist", testOutput, allChromebook);
	}

	@Test
	public void doLoanCamcorderTest() {
		assertNotNull("Check if there is valid Camcorder arraylist to add to", camcorderList);
		ResourceCentre.addCamcorder(camcorderList, cc1);
		ResourceCentre.addCamcorder(camcorderList, cc2);
		cc2.setIsAvailable(false);

		Boolean ok = ResourceCentre.doLoanCamcorder(camcorderList, "CC0011", "8-8-2020");
		assertTrue("Check that item is ok to loan?", ok);

		ok = ResourceCentre.doLoanCamcorder(camcorderList, "CC0012", "8-8-2020");
		assertFalse("Check that item is ok to loan?", ok);

		ok = ResourceCentre.doLoanCamcorder(camcorderList, "CC0013", "8-8-2020");
		assertFalse("Check that item is ok to loan?", ok);

	}

	@Test
	public void doLoanChromebookTest() {
		assertNotNull("Check if there is valid Chromebook arraylist to add to", chromebookList);
		ResourceCentre.addChromebook(chromebookList, cb1);
		ResourceCentre.addChromebook(chromebookList, cb2);
		cb2.setIsAvailable(false);

		Boolean ok = ResourceCentre.doLoanChromebook(chromebookList, "CB0011", "8-8-2020");
		assertTrue("Check that item is ok to loan?", ok);

		ok = ResourceCentre.doLoanChromebook(chromebookList, "CB0012", "8-8-2020");
		assertFalse("Check that item is ok to loan?", ok);

		ok = ResourceCentre.doLoanChromebook(chromebookList, "CB0013", "8-8-2020");
		assertFalse("Check that item is ok to loan?", ok);

	}

	@Test
	public void doReturnCamcorderTest() {
		assertNotNull("Check if there is valid Camcorder arraylist to add to", camcorderList);
		ResourceCentre.addCamcorder(camcorderList, cc1);
		ResourceCentre.addCamcorder(camcorderList, cc2);
		cc2.setIsAvailable(false);

		Boolean isReturned = ResourceCentre.doReturnCamcorder(camcorderList, "CC0011");
		assertFalse("Check that item is returned?", isReturned);

		isReturned = ResourceCentre.doReturnCamcorder(camcorderList, "CC0012");
		assertTrue("Check that item is returned", isReturned);

		isReturned = ResourceCentre.doReturnCamcorder(camcorderList, "CC0013");
		assertFalse("Check that item is returned?", isReturned);

	}

	@Test
	public void doReturnChromebookTest() {
		assertNotNull("Check if there is valid Chromebook arraylist to add to", chromebookList);
		ResourceCentre.addChromebook(chromebookList, cb1);
		ResourceCentre.addChromebook(chromebookList, cb2);
		cb2.setIsAvailable(false);

		Boolean isReturned = ResourceCentre.doReturnChromebook(chromebookList, "CB0011");
		assertFalse("Check that item is returned?", isReturned);

		isReturned = ResourceCentre.doReturnChromebook(chromebookList, "CB0012");
		assertTrue("Check that item is returned", isReturned);

		isReturned = ResourceCentre.doReturnChromebook(chromebookList, "CB0013");
		assertFalse("Check that item is returned?", isReturned);

	}

	@After
	public void tearDown() throws Exception {
		cc1 = null;
		cc2 = null;
		cb1 = null;
		cb2 = null;
		camcorderList = null;
		chromebookList = null;

	}

}
