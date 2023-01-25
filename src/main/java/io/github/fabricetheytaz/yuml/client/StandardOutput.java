package io.github.fabricetheytaz.yuml.client;

import java.io.IOException;

/**
 * @version 0.1.0
 * @since 0.1.0
 */
public class StandardOutput implements Output
	{
	/**
	 * @since 0.1.0
	 */
	@Override
	public final void accept(final byte[] bytes) throws IOException
		{
		System.out.println(bytes);
		}
	}
