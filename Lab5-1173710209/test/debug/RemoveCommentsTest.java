package debug;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class RemoveCommentsTest {

	@Test
	void test1() {
		String[] source = { "/*Test program */", "int main()", "{ ", "  // variable declaration ", "int a, b, c;",
				"/* This is a test", "   multiline  ", "   comment for ", "   testing */", "a = b + c;", "}" };
		RemoveComments r = new RemoveComments();
		assertEquals(r.removeComments(source),
				Arrays.asList("int main()", "{ ", "  ", "int a, b, c;", "a = b + c;", "}"));
	}

	@Test
	void test2() {
		RemoveComments remove = new RemoveComments();
		String[] source = { "a/*comment", "line", "more_comment*/b" };
		// String[] output = {"ab"};
		List<String> out = new ArrayList<String>();
		out.add("ab");
		assertEquals(out, remove.removeComments(source));
	}

}
