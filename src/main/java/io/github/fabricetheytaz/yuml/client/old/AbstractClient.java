package io.github.fabricetheytaz.yuml.client.old;

import io.github.fabricetheytaz.util.exceptions.NullArgumentException;
import io.github.fabricetheytaz.yuml.client.IClient;

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

	/*
	// TODO: Dans Diagram parser ?? Sert à rien ???
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
	*/
	}
