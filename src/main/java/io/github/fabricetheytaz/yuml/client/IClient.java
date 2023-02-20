package io.github.fabricetheytaz.yuml.client;

import java.io.IOException;
import io.github.fabricetheytaz.yuml.client.exceptions.YUMLException;

/**
 * @version 0.1.0
 * @since 0.1.0
 */
public interface IClient
	{
	/**
	 * @since 0.1.0
	 */
	public void get(String url) throws IOException;

	/**
	 * @since 0.1.0
	 */
	public void post(String url) throws IOException;

	/**
	 * @since 0.1.0
	 */
	@Deprecated
	public byte[] post(String dsl, Type type, Style style, Direction direction, Format format) throws IOException, YUMLException;
	}
