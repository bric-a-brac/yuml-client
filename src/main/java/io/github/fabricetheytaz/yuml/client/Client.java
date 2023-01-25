package io.github.fabricetheytaz.yuml.client;

import java.io.IOException;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import io.github.fabricetheytaz.util.exceptions.NullArgumentException;
import io.github.fabricetheytaz.yuml.client.exceptions.ErrorException;
import io.github.fabricetheytaz.yuml.client.exceptions.NotFoundException;

import static io.github.fabricetheytaz.util.Argument.notNull;

/**
 * @version 0.1.0
 * @since 0.1.0
 */
public class Client
	{
	public static final String USER_AGENT = "Bric-à-Brac yUML/0.1.0 (https://github.com/bric-a-brac/yuml)";

	public static final Type DEFAULT_TYPE = Type.CLASS;
	public static final Style DEFAULT_STYLE = Style.SCRUFFY;
	public static final Direction DEFAULT_DIRECTION = Direction.LEFT_TO_RIGHT;
	public static final Format DEFAULT_FORMAT = Format.SVG;

	private static final String URL = "https://yuml.me/";

	private static final String IMAGE_URL = URL + "%s.%s";

	/**
	 * @since 0.1.0
	 */
	public final void draw(final Input input, final Output output, final Format format) throws IOException
		{
		notNull(input);
		notNull(output);
		notNull(format);

		final Diagram diagram = input.get();

		if (diagram == null)
			{
			// TODO: Error
			throw new UnsupportedOperationException("TODO");
			}

		final Type type = getOption(Type.class, null, diagram.getType(), DEFAULT_TYPE);
		final Style style = getOption(Style.class, null, diagram.getStyle(), DEFAULT_STYLE);

		// API call

		//notNull(output).a
		//output.accept(null);

		throw new UnsupportedOperationException();
		}

	/**
	 * @since 0.1.0
	 */
	public final void draw(final Input input, final Output output) throws IOException
		{
		draw(input, output, DEFAULT_FORMAT);
		}

	/**
	 * @since 0.1.0
	 */
	public final void getImage(final String digest, final String extension, final Output output) throws IOException, NotFoundException, ErrorException
		{
		notNull(output).accept(getImage(digest, extension));
		}

	/**
	 * @since 0.1.0
	 */
	protected final byte[] getImage(final String url) throws IOException, NotFoundException, ErrorException
		{
		return execute(Request.Get(notNull(url))).asBytes();
		}

	/**
	 * @since 0.1.0
	 */
	protected final byte[] getImage(final String digest, final String extension) throws IOException, NotFoundException, ErrorException
		{
		return getImage(String.format(IMAGE_URL, notNull(digest), notNull(extension)));
		}

	/**
	 * @since 0.1.0
	 */
	private final <T extends Enum<T>> T getOption(final Class<T> classOfT, final T fromOption, final T fromDiagram, final T defaultValue)
		{
		if (fromOption != null)
			{
			return fromOption;
			}

		if (fromDiagram != null)
			{
			return fromDiagram;
			}

		return defaultValue;
		}

	/**
	 * Exécute une requête vers yUML. Ajoute simplement User-Agent et gère (huuummmm) réponse 500.
	 * 
	 * @throws NullArgumentException
	 * @throws IOException
	 * @throws NotFoundException
	 * @throws ErrorException
	 * 
	 * @since 0.1.0
	 */
	private final Content execute(final Request request) throws IOException, NotFoundException, ErrorException
		{
		try
			{
			return notNull(request).userAgent(USER_AGENT).execute().returnContent();
			}
		catch (final HttpResponseException ex)
			{
			final int code = ex.getStatusCode();

			switch (code)
				{
				case HttpStatus.SC_NOT_FOUND:
					throw new NotFoundException(ex);
				case HttpStatus.SC_INTERNAL_SERVER_ERROR:
					throw new ErrorException(ex);
				}

			throw ex;
			}
		}
	}
