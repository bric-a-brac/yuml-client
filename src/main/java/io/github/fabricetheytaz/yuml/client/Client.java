package io.github.fabricetheytaz.yuml.client;

import java.io.IOException;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import io.github.fabricetheytaz.util.exceptions.NullArgumentException;
import io.github.fabricetheytaz.util.io.Input;
import io.github.fabricetheytaz.util.io.Output;
import io.github.fabricetheytaz.yuml.client.exceptions.FatalErrorException;
import io.github.fabricetheytaz.yuml.client.exceptions.NotFoundException;

import static io.github.fabricetheytaz.util.Argument.notNull;

/**
 * @version 0.1.0
 * @since 0.1.0
 */
public class Client extends Parser
	{
	public static final String USER_AGENT = "Bric-à-Brac yUML/0.1.0 (https://github.com/bric-a-brac/yuml)";

	public static final Type DEFAULT_TYPE = Type.CLASS;
	public static final Style DEFAULT_STYLE = Style.SCRUFFY;
	public static final Direction DEFAULT_DIRECTION = Direction.LEFT_TO_RIGHT;
	public static final Format DEFAULT_FORMAT = Format.SVG;

	private static final String URL = "https://yuml.me/";

	// POST
	//private static final String POST_URL = "https://yuml.me/diagram/scruffy/class/";
	private static final String POST_URL = URL + "diagram/%s/%s/";
	private static final String INPUT_NAME = "dsl_text";

	//private static final String IMAGE_URL = URL + "%s.%s";

	/**
	 * @throws NullArgumentException
	 * 
	 * @since 0.1.0
	 */
	public final void invoke(final Input<String> input, final Output<byte[]> output, final Format format) throws IOException, NotFoundException, FatalErrorException
		{
		notNull(input);
		notNull(output);
		notNull(format);

		final Diagram diagram = parse(input.get());

		// Prendre les options depuis le diagramme sinon prendre celles par défaut
		final Type type = getOption(Type.class, diagram.getType(), DEFAULT_TYPE);
		final Style style = getOption(Style.class, diagram.getStyle(), DEFAULT_STYLE);
		final Direction direction = getOption(Direction.class, diagram.getDirection(), DEFAULT_DIRECTION);

		final String dsl = String.join(",", diagram.getLines());

		final byte[] bytes = post(dsl, type, style, direction, format);

		output.accept(bytes);
		}

	/**
	 * @since 0.1.0
	 */
	public final void invoke(final Input<String> input, final Output<byte[]> output) throws IOException, NotFoundException, FatalErrorException
		{
		invoke(input, output, DEFAULT_FORMAT);
		}

	/*
	public final void getImage(final String digest, final String extension, final Output output) throws IOException, NotFoundException, ErrorException
		{
		notNull(output).accept(getImage(digest, extension));
		}

	protected final byte[] getImage(final String url) throws IOException, NotFoundException, ErrorException
		{
		return execute(Request.Get(notNull(url))).asBytes();
		}

	protected final byte[] getImage(final String digest, final String extension) throws IOException, NotFoundException, ErrorException
		{
		return getImage(String.format(IMAGE_URL, notNull(digest), notNull(extension)));
		}
	*/

	/**
	 * @since 0.1.0
	 */
	@SuppressWarnings("unchecked")
	public final <T extends Enum<T>> T getOption(final Class<T> classOfT, final T ...options)
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

	/**
	 * @since 0.1.0
	 */
	private final byte[] post(final String dsl, final Type type, final Style style, final Direction direction, final Format format) throws IOException, NotFoundException, FatalErrorException
		{
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

		/*
		//	curl -X POST -d "dsl_text=[Curl]->[Example]-.-[Nice{bg:wheat}]" https://yuml.me/diagram/scruffy/class/ 
		String response = Request.Post("https://yuml.me/diagram/scruffy/class/").userAgent(USER_AGENT)
			.bodyForm(new BasicNameValuePair("dsl_text", "[Curl]->[Example]-.-[Nice{bg:wheat}]"))
			.execute().returnContent().asString();

		//https://yuml.me/e3c59524.json

		System.out.println(response);
		*/
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
	private final Content execute(final Request request) throws IOException, NotFoundException, FatalErrorException
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
					throw new FatalErrorException(ex);
				}

			throw ex;
			}
		}
	}
