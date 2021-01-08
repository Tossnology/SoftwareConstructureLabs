package AppTest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import applications.SocialNetworkCircle;
import applications.StellarSystem;
import exceptions.NeedRereadFileException;
import phisicalObject.Planet;

class StellarSystemTest {

	@Test
	void ReadFileTest() throws IOException, NeedRereadFileException {
		try {
			StellarSystem.ReadFile(new File("src/txts/AtomicStructure1.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			assertEquals(e.getMessage(),"src\\txts\\AtomicStructure1.txt (系统找不到指定的文件。)");
		} catch (NeedRereadFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try{
			StellarSystem.ReadFile(new File("src/txts/StellarSystem.txt"));

		}catch(NeedRereadFileException e) {
			assertEquals(e.getMessage(),"文件有问题，需要重新读取文件");
			
		}
		
		try{
			StellarSystem.ReadFile(new File("src/txts/AtomicStructure.txt"));

		}catch(NeedRereadFileException e) {
			assertEquals(e.getMessage(),"文件有问题，需要重新读取文件");
			
		}
		
		StellarSystem.ReadFile(new File("src/txts/StellarSystemRight.txt"));
		
	}
	
	@Test
	void CalculatePositionTest() throws IOException, NeedRereadFileException {
		StellarSystem.ReadFile(new File("src/txts/StellarSystemRight.txt"));
		
		Planet p = new Planet("Earth","Solid","Blue",6378.137,1.49e8,29.783,"CW",0);
		assertEquals(StellarSystem.CalculatePosition(p,25),359.9999950028523);
	}
	
	@Test
	void CalculateDistanceTest() throws IOException, NeedRereadFileException {
		StellarSystem.ReadFile(new File("src/txts/StellarSystemRight.txt"));
		
		Planet p1 = new Planet("Earth","Solid","Blue",6378.137,1.49e8,29.783,"CW",0);
		Planet p2 = new Planet("Saturn","Liquid","Red",2378,1.49e6,2.33e5,"CW",39.21);
		assertEquals(StellarSystem.CalculateDistance(p1, p2, 25),1.49661381415905E8);
	}
}
