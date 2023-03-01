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
	public byte[] get(String url) throws IOException, YUMLException;

	/**
	 * @since 0.1.0
	 */
	public String post(String url, String dsl) throws IOException, YUMLException;

	//public byte[] post(String dsl, Type type, Style style, Direction direction, Format format) throws IOException, YUMLException;
	}
