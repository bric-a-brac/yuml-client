package io.github.fabricetheytaz.yuml.client.exceptions;

/**
 * @version 0.1.0
 * @since 0.1.0
 */
public abstract class YUMLException extends Exception
	{
	public static final String DEFAULT_MESSAGE = "We're sorry, but something went wrong";

	/**
	 * @since 0.1.0
	 */
	public YUMLException()
		{
		super(DEFAULT_MESSAGE);
		}

	/**
	 * @since 0.1.0
	 */
	public YUMLException(final String message)
		{
		super(message);
		}

	/**
	 * @since 0.1.0
	 */
	public YUMLException(final Throwable cause)
		{
		super(DEFAULT_MESSAGE, cause);
		}

	/**
	 * @since 0.1.0
	 */
	public YUMLException(final String message, final Throwable cause)
		{
		super(message, cause);
		}
	}
