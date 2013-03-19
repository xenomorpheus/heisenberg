package au.net.hal9000.heisenberg.worldeditor;

import static org.junit.Assert.*;
import org.junit.Test;

import au.net.hal9000.heisenberg.worldeditor.WorldEditor;

public class WorldEditorTest {

	@Test
	public void testGetDemoWorld() {
		/*
		 * This unit test is here so:<br>
		 * 1) We know the test data will build before running the UI.
		 * 2) Code coverage.
		 */
		try {
		  WorldEditor.getDemoWorld();
		}
		catch (Exception e){
			fail(e.toString());
		}
	}

}