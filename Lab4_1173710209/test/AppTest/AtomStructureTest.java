package AppTest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import applications.AtomStructure;
import exceptions.WrongFileTypeException;
import exceptions.WrongTrackNumberException;
import track.Track;

class AtomStructureTest {

	@Test
	void ReadFileTest() throws WrongTrackNumberException, IOException, WrongFileTypeException {
			try {
				AtomStructure.ReadFile(new File("src/txts/AtomicStructure1.txt"));
			} catch (WrongTrackNumberException | IOException | WrongFileTypeException e) {
				// TODO Auto-generated catch block
				assertEquals(e.getMessage(),"src\\txts\\AtomicStructure1.txt (ϵͳ�Ҳ���ָ�����ļ���)");
			}
			
			AtomStructure.ReadFile(new File("src/txts/AtomicStructure.txt"));
	}

	@Test
	void TransitionTest() throws WrongTrackNumberException, IOException, WrongFileTypeException {
		AtomStructure.ReadFile(new File("src/txts/AtomicStructure.txt"));
		
		Track t1 = new Track(1);
		Track t2 = new Track(2);
		
		AtomStructure.Transition(t1, t2);
	}
}
