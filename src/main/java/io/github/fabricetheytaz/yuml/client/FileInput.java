package io.github.fabricetheytaz.yuml.client;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static io.github.fabricetheytaz.util.Argument.notNull;

/**
 * @version 0.1.0
 * @since 0.1.0
 */
public class FileInput extends AbstractInput
	{
	private final Path path;

	/**
	 * @since 0.1.0
	 */
	public FileInput(final Path path) throws IOException
		{
		super();

		this.path = notNull(path).toRealPath();
		}

	/**
	 * @since 0.1.0
	 */
	public FileInput(final File file) throws IOException
		{
		this(notNull(file).toPath());
		}

	/**
	 * @since 0.1.0
	 */
	public FileInput(final String path) throws IOException
		{
		this(new File(notNull(path)));
		}

	/**
	 * @since 0.1.0
	 */
	@Override
	public final Diagram get() throws IOException
		{
		return parser.parse(Files.readAllLines(path));
		}
	}
