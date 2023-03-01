package io.github.fabricetheytaz.yuml.client;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import org.apache.commons.lang3.function.FailableConsumer;
import io.github.fabricetheytaz.util.exceptions.NullArgumentException;
import io.github.fabricetheytaz.util.io.FileInput;
import io.github.fabricetheytaz.util.io.FileOutput;
import io.github.fabricetheytaz.util.io.IInput;
import io.github.fabricetheytaz.util.io.IOutput;
import io.github.fabricetheytaz.util.io.StandardInput;
import io.github.fabricetheytaz.util.io.StandardOutput;
import io.github.fabricetheytaz.yuml.client.exceptions.YUMLException;

import static io.github.fabricetheytaz.util.Argument.notNull;

/**
 * @version 0.1.0
 * @since 0.1.0
 */
public class API
	{
	public static final Type DEFAULT_TYPE = Type.CLASS;
	public static final Style DEFAULT_STYLE = Style.SCRUFFY;
	public static final Direction DEFAULT_DIRECTION = Direction.LEFT_TO_RIGHT;
	public static final Format DEFAULT_FORMAT = Format.SVG;

	private static final String URL = "https://yuml.me/";

	// POST
	//private static final String POST_URL = "https://yuml.me/diagram/scruffy/class/";
	@SuppressWarnings("unused")
	private static final String POST_URL = URL + "diagram/%s/%s/";

	protected final IClient client;

	/**
	 * @throws NullArgumentException
	 * 
	 * @since 0.1.0
	 */
	public API(final IClient client)
		{
		super();

		this.client = notNull(client);
		}

	public final void draw(final IInput<String> input, final IOutput<byte[]> output, final Format format) throws IOException, YUMLException
		{
		notNull(input);
		notNull(output);
		notNull(format);

		final Diagram diagram = Diagram.parse(input.get());

		// Prendre les options depuis le diagramme sinon prendre celles par défaut
		// PAS BESOIN SI ON UTILISE PARSE ON A DEJA LES OPTIONS PAR DEFAUT !!!!!!!!!!!!!!!!!!!!
		//final Type type = getOption(Type.class, diagram.getType(), DEFAULT_TYPE);
		//final Style style = getOption(Style.class, diagram.getStyle(), DEFAULT_STYLE);
		//final Direction direction = getOption(Direction.class, diagram.getDirection(), DEFAULT_DIRECTION);

		final String dsl = String.join(",", diagram.getLines());

		// TODO: Build URL
		//final String url = String.format(POST_URL, style.name().toLowerCase(), type.name().toLowerCase());

		//final byte[] bytes = post(dsl, type, style, direction, format);
		//output.accept(bytes);

		final String response = client.post("https://yuml.me/diagram/scruffy/class/", "[Curl]->[Example]-.-[Nice{bg:wheat}]");
		System.out.println(response);
		/*
		//	curl -X POST -d "dsl_text=[Curl]->[Example]-.-[Nice{bg:wheat}]" https://yuml.me/diagram/scruffy/class/
		String response = Request.Post("https://yuml.me/diagram/scruffy/class/").userAgent(USER_AGENT)
			.bodyForm(new BasicNameValuePair("dsl_text", "[Curl]->[Example]-.-[Nice{bg:wheat}]"))
			.execute().returnContent().asString();

		//https://yuml.me/e3c59524.json
		*/

		//throw new UnsupportedOperationException();
		}

	/**
	 * @since 0.1.0
	 */
	public final void draw(final IInput<String> input, final IOutput<byte[]> output) throws IOException, YUMLException
		{
		draw(input, output, DEFAULT_FORMAT);
		}

	/**
	 * @since 0.1.0
	 */
	public final void draw() throws IOException, YUMLException
		{
		draw(new StandardInput(), new StandardOutput());
		}

	/**
	 * @since 0.1.0
	 */
	public final void draw(final Path input, final Path output, final Format format) throws IOException, YUMLException
		{
		draw(new FileInput(input), new FileOutput(output), format);
		}

	/**
	 * @since 0.1.0
	 */
	public final void draw(final Path input, final Path output) throws IOException, YUMLException
		{
		draw(input, output, DEFAULT_FORMAT);
		}

	/**
	 * @since 0.1.0
	 */
	public final void draw(final File input, final File output) throws IOException, YUMLException
		{
		draw(new FileInput(input), new FileOutput(output));
		}

	/**
	 * @since 0.1.0
	 */
	public final void draw(final String input, final String output) throws IOException, YUMLException
		{
		draw(new FileInput(input), new FileOutput(output));
		}

	/**
	 * @since 0.1.0
	 */
	public final void draw(final Path input, final FailableConsumer<byte[], IOException> outputConsumer) throws IOException, YUMLException
		{
		draw(new FileInput(input), bytes -> outputConsumer.accept(bytes));
		}

	/**
	 * @since 0.1.0
	 */
	public final void draw(final File input, final FailableConsumer<byte[], IOException> outputConsumer) throws IOException, YUMLException
		{
		draw(new FileInput(input), bytes -> outputConsumer.accept(bytes));
		}

	/**
	 * @since 0.1.0
	 */
	public final void draw(final String input, final FailableConsumer<byte[], IOException> outputConsumer) throws IOException, YUMLException
		{
		draw(new FileInput(input), bytes -> outputConsumer.accept(bytes));
		}

	/*
	public byte[] draw(final Diagram diagram, final Type type, final Style style, final Direction direction, final Format format) throws IOException
		{
		throw new UnsupportedOperationException();
		}

	public byte[] draw(final Diagram diagram, final Style style, final Direction direction, final Format format) throws IOException
		{
		//notNull(diagram);
		//return draw(diagram, diagram.getType(), style, direction, format);
		throw new UnsupportedOperationException();
		}
	*/

	/*
	public static final void draw(final String dsl, final Type type, final Style style, final Direction direction, final Format format)
		{
		final Diagram diagram = Diagram.parse(dsl);

		diagram.setType(notNull(type));
		diagram.setStyle(notNull(style));
		diagram.setDirection(notNull(direction));

		draw(diagram);
		}

	private static final void draw(final Diagram diagram)
		{
		throw new UnsupportedOperationException();
		}
	*/
	}
