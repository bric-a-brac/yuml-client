package io.github.fabricetheytaz.yuml.client;

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
public class Parser
	{
	private static final String EOL = "\\r?\\n";
	private static final String COMMENT = "//";
	private static final Pattern OPTION = Pattern.compile("\\/\\/\\s*\\{\\s*([^:]+):([^\\}]+)\\}");

	/**
	 * @since 0.1.0
	 */
	public final Diagram parse(final String yuml)
		{
		return parse(Arrays.asList(notNull(yuml).split(EOL)));
		}

	/**
	 * @since 0.1.0
	 */
	public final Diagram parse(final List<String> lines)
		{
		// Prendre les lignes qui ne sont pas des commentaires
		final List<String> dsl = notNull(lines).stream()
			.filter(Predicate.not(String::isBlank))
			.filter(Predicate.not(this::isComment))
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

		return diagram;
		}

	/**
	 * Est-ce que la ligne est un commentaire (//) ? Pour filtrer les lignes :)
	 * 
	 * @since 0.1.0
	 */
	private final boolean isComment(final String line)
		{
		return notNull(line).startsWith(COMMENT);
		}

	/**
	 * @since 0.1.0
	 */
	private final SimpleEntry<String, String> parseOption(final String comment)
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
	private final Stream<SimpleEntry<String, String>> getOptions(final List<String> lines)
		{
		return notNull(lines).stream().filter(this::isComment).map(this::parseOption).filter(Objects::nonNull);
		}
	}
