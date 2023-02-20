package io.github.fabricetheytaz.yuml.client;

import io.github.fabricetheytaz.util.exceptions.NullArgumentException;

import static io.github.fabricetheytaz.util.Argument.notNull;

/**
 * @version 0.1.0
 * @since 0.1.0
 */
public abstract class AbstractClient implements IClient
	{
	protected final String userAgent;

	/**
	 * @throws NullArgumentException
	 * 
	 * @since 0.1.0
	 */
	public AbstractClient(final String userAgent)
		{
		super();

		this.userAgent = notNull(userAgent);
		}

	/**
	 * @since 0.1.0
	 */
	public final String getUserAgent()
		{
		return userAgent;
		}

	/**
	 * @since 0.1.0
	 */
	// TODO: Dans Diagram parser ??
	@SuppressWarnings("unchecked")
	public final <T extends Enum<T>> T getOption(final Class<T> classOfT, final T... options)
		{
		for (final T option : options)
			{
			if (option != null)
				{
				return option;
				}
			}

		return null;
		}

	/*
	public final void todo(final IInput<String> input, final IOutput<byte[]> output, final Format format) throws IOException, YUMLException
		{
		notNull(input);
		notNull(output);
		notNull(format);

		// TODO: Parse
		//final Diagram diagram = parse(input.get());
		final Diagram diagram = new Diagram();

		// Prendre les options depuis le diagramme sinon prendre celles par défaut
		final Type type = getOption(Type.class, diagram.getType(), DEFAULT_TYPE);
		final Style style = getOption(Style.class, diagram.getStyle(), DEFAULT_STYLE);
		final Direction direction = getOption(Direction.class, diagram.getDirection(), DEFAULT_DIRECTION);

		final String dsl = String.join(",", diagram.getLines());

		final byte[] bytes = post(dsl, type, style, direction, format);

		output.accept(bytes);
		}
	*/

	/*
	public final void todo(final IInput<String> input, final IOutput<byte[]> output) throws IOException, YUMLException
		{
		todo(input, output, DEFAULT_FORMAT);
		}
	*/
	}
