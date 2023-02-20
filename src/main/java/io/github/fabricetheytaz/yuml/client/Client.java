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
public final class Client extends AbstractClient
	{
	private static final String INPUT_NAME = "dsl_text";

	/**
	 * @since 0.1.0
	 */
	public Client(final String userAgent)
		{
		super(userAgent);
		}

	/**
	 * @since 0.1.0
	 */
	@Override
	public void get(final String url) throws IOException
		{
		throw new UnsupportedOperationException();
		}

	/**
	 * @since 0.1.0
	 */
	@Override
	public void post(final String url) throws IOException
		{
		throw new UnsupportedOperationException();
		}

	/**
	 * @since 0.1.0
	 */
	@Override
	@Deprecated
	public byte[] post(final String dsl, final Type type, final Style style, final Direction direction, final Format format) throws IOException, YUMLException
		{
		/*
		notNull(dsl);
		notNull(type);
		notNull(style);
		notNull(direction);
		notNull(format);

		final String url = String.format(POST_URL, style.name().toLowerCase(), type.name().toLowerCase());

		// TODO: Build URL
		final Request request = Request.Post(url).bodyForm(new BasicNameValuePair(INPUT_NAME, dsl));

		// FIXME: Format comment ??? pas toujours .svg ???
		final String filename = execute(request).asString();

		return execute(Request.Get(URL + filename)).asBytes();
		*/

		/*
		//	curl -X POST -d "dsl_text=[Curl]->[Example]-.-[Nice{bg:wheat}]" https://yuml.me/diagram/scruffy/class/ 
		String response = Request.Post("https://yuml.me/diagram/scruffy/class/").userAgent(USER_AGENT)
			.bodyForm(new BasicNameValuePair("dsl_text", "[Curl]->[Example]-.-[Nice{bg:wheat}]"))
			.execute().returnContent().asString();

		//https://yuml.me/e3c59524.json

		System.out.println(response);
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
