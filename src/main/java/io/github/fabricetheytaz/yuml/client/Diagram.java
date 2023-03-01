package io.github.fabricetheytaz.yuml.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import io.github.fabricetheytaz.util.Util;

import static io.github.fabricetheytaz.util.Argument.notNull;

/**
 * @version 0.1.0
 * @since 0.1.0
 */
public class Diagram
	{
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

	/**
	 * @since 0.1.0
	 */
	public static final Diagram parse(final String yuml)
		{
		return parse(Util.lines(yuml));
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

		// Prendre les lignes qui sont des commentaires
		final Stream<Pair<String, String>> options = lines.stream()
			.filter(Diagram::isComment)
			.map(Diagram::parseOption).
			filter(Objects::nonNull);

		// TODO: Options
		options.forEach(option ->
			{
			//System.out.println(option.getKey() + " -> " + option.getValue());

			final String key = option.getKey();
			final String value = option.getValue();

			if ("type".equalsIgnoreCase(key))
				{
				diagram.setType(getOrDefault(value, Type.class, API.DEFAULT_TYPE));
				}

			if ("style".equalsIgnoreCase(key))
				{
				diagram.setStyle(getOrDefault(value, Style.class, API.DEFAULT_STYLE));
				}

			if ("direction".equalsIgnoreCase(key))
				{
				diagram.setDirection(getOrDefault(value, Direction.class, API.DEFAULT_DIRECTION));
				}
			});

		return diagram;
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
	private static final Pair<String, String> parseOption(final String comment)
		{
		final Matcher matcher = OPTION.matcher(notNull(comment));

		if (matcher.matches())
			{
			return new ImmutablePair<>(matcher.group(1).trim(), matcher.group(2).trim());
			}

		return null;
		}

	/**
	 * @since 0.1.0
	 */
	private static final <T extends Enum<T>> T getOrDefault(final String name, final Class<T> classOfT, final T defaultValue)
		{
		try
			{
			// Rechercher avec son nom
			return Enum.valueOf(notNull(classOfT), notNull(name).toUpperCase());
			}
		catch (final IllegalArgumentException ex)
			{
			// Pas trouvé
			}

		// Rechercher avec toString()
		for (final T t : classOfT.getEnumConstants())
			{
			if (t.toString().equalsIgnoreCase(name))
				{
				return t;
				}
			}

		// Pas trouvé
		return defaultValue;
		}
	}
