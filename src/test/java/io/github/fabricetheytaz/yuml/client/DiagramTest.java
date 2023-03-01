package io.github.fabricetheytaz.yuml.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DiagramTest extends Assertions
	{
	@Test
	public void testParse()
		{
		final Diagram diagram = Diagram.parse("sdsd\ndasdas\nfsdfdf");

		assertNotNull(diagram);

		assertNotNull(diagram.getLines());

		assertEquals(3, diagram.getLines().size());

		assertEquals("dasdas", diagram.getLines().get(1));
		}

	@Test
	public void testParseType()
		{
		assertEquals(Type.ACTIVITY, Diagram.parse("// {type:activity}").getType());
		assertEquals(Type.CLASS, Diagram.parse("// {type:class}").getType());
		assertEquals(Type.USECASE, Diagram.parse("// {type:usecase}").getType());

		assertEquals(Type.CLASS, Diagram.parse("// {type:invalid}").getType());

		assertNull(Diagram.parse("jjsdhfkjsdhfkdjsfh").getType());
		}

	@Test
	public void testParseStyle()
		{
		assertEquals(Style.NOFUNKY, Diagram.parse("// {style:nofunky}").getStyle());
		assertEquals(Style.PLAIN, Diagram.parse("// {style:plain}").getStyle());
		assertEquals(Style.SCRUFFY, Diagram.parse("// {style:scruffy}").getStyle());

		assertEquals(Style.SCRUFFY, Diagram.parse("// {style:invalid}").getStyle());

		assertNull(Diagram.parse("jjsdhfkjsdhfkdjsfh").getStyle());
		}

	// {direction:topDown}
	// direction:leftToRight
	}
