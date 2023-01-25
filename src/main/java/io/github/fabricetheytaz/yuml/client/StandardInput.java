package io.github.fabricetheytaz.yuml.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;

/**
 * @version 0.1.0
 * @since 0.1.0
 */
public class StandardInput extends AbstractInput
	{
	/**
	 * @since 0.1.0
	 */
	@Override
	public final Diagram get() throws IOException
		{
		if (System.in.available() == 0)
			{
			return null;
			}

		try (final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)))
			{
			try (final StringWriter writer = new StringWriter())
				{
				reader.transferTo(writer);

				final String yuml = writer.toString();

				return parser.parse(yuml);
				}
			}
		}
	}
