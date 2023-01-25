package io.github.fabricetheytaz.yuml.client.exceptions;

/**
 * @version 0.1.0
 * @since 0.1.0
 */
public final class NotFoundException extends YUMLException
	{
	/**
	 * @since 0.1.0
	 */
	public NotFoundException()
		{
		super();
		}

	/**
	 * @since 0.1.0
	 */
	public NotFoundException(final String message)
		{
		super(message);
		}

	/**
	 * @since 0.1.0
	 */
	public NotFoundException(final Throwable cause)
		{
		super(cause);
		}

	/**
	 * @since 0.1.0
	 */
	public NotFoundException(final String message, final Throwable cause)
		{
		super(message, cause);
		}
	}
