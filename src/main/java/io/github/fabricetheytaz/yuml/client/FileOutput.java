package io.github.fabricetheytaz.yuml.client;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static io.github.fabricetheytaz.util.Argument.notNull;

/**
 * @version 0.1.0
 * @since 0.1.0
 */
public class FileOutput implements Output
	{
	private final Path path;

	/**
	 * @since 0.1.0
	 */
	public FileOutput(final Path path)
		{
		super();

		this.path = notNull(path);
		}

	/**
	 * @since 0.1.0
	 */
	@Override
	public final void accept(final byte[] bytes) throws IOException
		{
		Files.write(path, bytes);
		}
	}
