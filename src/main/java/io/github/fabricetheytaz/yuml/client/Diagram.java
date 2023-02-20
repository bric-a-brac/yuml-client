package io.github.fabricetheytaz.yuml.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.AbstractMap.SimpleEntry;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.github.fabricetheytaz.util.Argument.notNull;

/**
 * @version 0.1.0
 * @since 0.1.0
 */
public class Diagram //implements IInput<String>
	{
	// TODO: \R ou \N je sais plus.... Util :)
	private static final String EOL = "\\r?\\n";
	private static final String COMMENT = "//";
	private static final Pattern OPTION = Pattern.compile("\\/\\/\\s*\\{\\s*([^:]+):([^\\}]+)\\}");

	protected final List<String> lines = new ArrayList<>();

	protected Type type;
	protected Style style;
	protected Direction direction;

	/**
	 * @since 0.1.0
	 */
	public Diagram()
		{
		super();
		}

	/**
	 * @since 0.1.0
	 */
	public Diagram(final List<String> lines)
		{
		super();

		if (lines != null)
			{
			this.lines.addAll(lines);
			}
		}

	/**
	 * @since 0.1.0
	 */
	public final List<String> getLines()
		{
		return lines;
		}

	/**
	 * @since 0.1.0
	 */
	public final Type getType()
		{
		return type;
		}

	/**
	 * @since 0.1.0
	 */
	public final void setType(final Type type)
		{
		this.type = type;
		}

	/**
	 * @since 0.1.0
	 */
	public final Style getStyle()
		{
		return style;
		}

	/**
	 * @since 0.1.0
	 */
	public final void setStyle(final Style style)
		{
		this.style = style;
		}

	/**
	 * @since 0.1.0
	 */
	public final Direction getDirection()
		{
		return direction;
		}

	/**
	 * @since 0.1.0
	 */
	public final void setDirection(final Direction direction)
		{
		this.direction = direction;
		}

	// DEV
	/*
	public String get() throws IOException
		{
		throw new UnsupportedOperationException();
		}
	*/

	/**
	 * @since 0.1.0
	 */
	public static final Diagram parse(final String yuml)
		{
		return parse(Arrays.asList(notNull(yuml).split(EOL)));
		}

	/**
	 * @since 0.1.0
	 */
	public static final Diagram parse(final List<String> lines)
		{
		// Prendre les lignes qui ne sont pas des commentaires
		final List<String> dsl = notNull(lines).stream()
			.filter(Predicate.not(String::isBlank))
			.filter(Predicate.not(Diagram::isComment))
			.collect(Collectors.toList());

		final Diagram diagram = new Diagram(dsl);

		// TODO: Options
		getOptions(lines).forEach(option ->
			{
			System.out.println(option.getKey() + " -> " + option.getValue());

			/*
			if ("type".equalsIgnoreCase(option.getKey()))
				{
				diagram.setType(Util.getOrDefault(option.getValue(), Type.class, API.DEFAULT_TYPE));
				}

			if ("style".equalsIgnoreCase(option.getKey()))
				{
				diagram.setStyle(Util.getOrDefault(option.getValue(), Style.class, API.DEFAULT_STYLE));
				}
			*/
			});

		//return diagram;
		throw new UnsupportedOperationException();
		}

	/**
	 * Est-ce que la ligne est un commentaire (//) ? Pour filtrer les lignes :)
	 * 
	 * @since 0.1.0
	 */
	private static final boolean isComment(final String line)
		{
		return notNull(line).startsWith(COMMENT);
		}

	/**
	 * @since 0.1.0
	 */
	private static final SimpleEntry<String, String> parseOption(final String comment)
		{
		final Matcher matcher = OPTION.matcher(notNull(comment));

		if (matcher.matches())
			{
			return new SimpleEntry<>(matcher.group(1).trim(), matcher.group(2).trim());
			}

		return null;
		}

	/**
	 * @since 0.1.0
	 */
	private static final Stream<SimpleEntry<String, String>> getOptions(final List<String> lines)
		{
		return notNull(lines).stream().filter(Diagram::isComment).map(Diagram::parseOption).filter(Objects::nonNull);
		}
	}
