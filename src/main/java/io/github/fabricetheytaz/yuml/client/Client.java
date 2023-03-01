package io.github.fabricetheytaz.yuml.client;

import java.io.IOException;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;

import io.github.fabricetheytaz.yuml.client.exceptions.FatalErrorException;
import io.github.fabricetheytaz.yuml.client.exceptions.NotFoundException;
import io.github.fabricetheytaz.yuml.client.exceptions.YUMLException;

import static io.github.fabricetheytaz.util.Argument.notNull;

/**
 * @version 0.1.0
 * @since 0.1.0
 */
public final class Client implements IClient
	{
	@SuppressWarnings("unused")
	private static final String INPUT_NAME = "dsl_text";

	private final String userAgent;

	/**
	 * @since 0.1.0
	 */
	public Client(final String userAgent)
		{
		super();

		this.userAgent = notNull(userAgent);
		}

	/**
	 * @since 0.1.0
	 */
	@Override
	public byte[] get(final String url) throws IOException, YUMLException
		{
		return execute(Request.Get(notNull(url))).asBytes();
		}

	/**
	 * @since 0.1.0
	 */
	@Override
	public String post(final String url, final String dsl) throws IOException, YUMLException
		{
		final Request request = Request.Post(url).bodyForm(new BasicNameValuePair(INPUT_NAME, dsl));

		// FIXME: Format comment ??? pas toujours .svg ???
		final String filename = execute(request).asString();

		return filename;
		//throw new UnsupportedOperationException();
		}

	@Deprecated
	public byte[] post(final String dsl, final Type type, final Style style, final Direction direction, final Format format) throws IOException, YUMLException
		{
		/*
		notNull(dsl);
		notNull(type);
		notNull(style);
		notNull(direction);
		notNull(format);

		

		
		

		

		return execute(Request.Get(URL + filename)).asBytes();
		*/

		throw new UnsupportedOperationException();
		}

	/**
	 * Exécute une requête HTTP, ajoute simplement User-Agent et gère (huuummmm) réponse 500.
	 * 
	 * @throws NullArgumentException
	 * @throws IOException
	 * @throws NotFoundException
	 * @throws ErrorException
	 * 
	 * @since 0.1.0
	 */
	private Content execute(final Request request) throws IOException, NotFoundException, FatalErrorException
		{
		try
			{
			return notNull(request).userAgent(userAgent).execute().returnContent();
			}
		catch (final HttpResponseException ex)
			{
			final int code = ex.getStatusCode();

			switch (code)
				{
				case HttpStatus.SC_NOT_FOUND:
					throw new NotFoundException(ex);
				case HttpStatus.SC_INTERNAL_SERVER_ERROR:
					throw new FatalErrorException(ex);
				}

			throw ex;
			}
		}
	}
