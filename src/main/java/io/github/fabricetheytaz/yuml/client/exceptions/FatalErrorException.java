package io.github.fabricetheytaz.yuml.client.exceptions;

/**
 * @version 0.1.0
 * @since 0.1.0
 */
public final class FatalErrorException extends YUMLException
	{
	/**
	 * @since 0.1.0
	 */
	public FatalErrorException()
		{
		super();
		}

	/**
	 * @since 0.1.0
	 */
	public FatalErrorException(final String message)
		{
		super(message);
		}

	/**
	 * @since 0.1.0
	 */
	public FatalErrorException(final Throwable cause)
		{
		super(cause);
		}

	/**
	 * @since 0.1.0
	 */
	public FatalErrorException(final String message, final Throwable cause)
		{
		super(message, cause);
		}
	}
