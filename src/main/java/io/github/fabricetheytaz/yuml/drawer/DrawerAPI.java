package io.github.fabricetheytaz.yuml.drawer;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import io.github.fabricetheytaz.util.io.FileInput;
import io.github.fabricetheytaz.util.io.IStringInput;
import io.github.fabricetheytaz.yuml.client.API;
import io.github.fabricetheytaz.yuml.client.IClient;

import static io.github.fabricetheytaz.util.Argument.notNull;

/**
 * @version 0.1.0
 * @since 0.1.0
 */
public class DrawerAPI<T extends IStringInput> extends API implements IImageDrawer<T>
	{
	/**
	 * @since 0.1.0
	 */
	public DrawerAPI(final IClient client)
		{
		super(client);
		}

	/**
	 * @since 0.1.0
	 */
	@Override
	public final BufferedImage apply(final T input) throws IOException
		{
		@SuppressWarnings("unused")
		final String dsdsd = notNull(input).get();

		//client.post....

		return null;
		}

	/**
	 * @since 0.1.0
	 */
	public static final BufferedImage imageFromBytes(final byte[] bytes) throws IOException
		{
		notNull(bytes);

		try (final InputStream input = new ByteArrayInputStream(bytes))
			{
			final BufferedImage image = ImageIO.read(input);

			// TODO: check image null

			return image;
			}
		}

	public static void dev() throws IOException
		{
		final DrawerAPI<FileInput> api = new DrawerAPI<>(null);

		final BufferedImage image = api.draw(new FileInput("test.yuml"));

		System.out.println(image);
		}
	}
