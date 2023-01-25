package io.github.fabricetheytaz.yuml.client;

import java.io.IOException;

import static io.github.fabricetheytaz.util.Argument.notNull;

/**
 * @version 0.1.0
 * @since 0.1.0
 */
public class Client
	{
	public static final Type DEFAULT_TYPE = Type.CLASS;
	public static final Style DEFAULT_STYLE = Style.SCRUFFY;
	public static final Direction DEFAULT_DIRECTION = Direction.LEFT_TO_RIGHT;
	public static final Format DEFAULT_FORMAT = Format.SVG;

	/**
	 * @since 0.1.0
	 */
	public final void draw(final Input input, final Output output, final Format format) throws IOException
		{
		notNull(input);
		notNull(output);
		notNull(format);

		final Diagram diagram = input.get();

		if (diagram == null)
			{
			// TODO: Error
			throw new UnsupportedOperationException("TODO");
			}

		final Type type = getOption(Type.class, null, diagram, DEFAULT_TYPE);

		// API call

		//notNull(output).a
		output.accept(null);
		}

	/**
	 * @since 0.1.0
	 */
	public final void draw(final Input input, final Output output) throws IOException
		{
		draw(input, output, DEFAULT_FORMAT);
		}

	/**
	 * @since 0.1.0
	 */
	private final <T extends Enum<T>> T getOption(final Class<T> classOfT, final T option, final Diagram diagram, final T defaultValue)
		{
		return defaultValue;
		}
	}
