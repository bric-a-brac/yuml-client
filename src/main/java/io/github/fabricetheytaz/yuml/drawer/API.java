package io.github.fabricetheytaz.yuml.drawer;

import java.awt.image.BufferedImage;
import java.io.IOException;
import io.github.fabricetheytaz.util.io.IStringInput;
import io.github.fabricetheytaz.util.io.IStringOutput;

/**
 * @version 0.1.0
 * @since 0.1.0
 */
public class API
	{
	public String svg(final IStringInput input) throws IOException
		{
		return draw(input, dfdf -> "");
		}

	public String svg(final String input) throws IOException
		{
		return svg(() -> input);
		}

	public void svg(final IStringInput input, final IStringOutput output) throws IOException
		{
		output.accept(svg(input));
		}

	public BufferedImage png(final IStringInput input) throws IOException
		{
		return draw(input, dfdf -> new BufferedImage(3,3,3));
		}

	private final <T extends IStringInput, R> R draw(final T input, final IStrDrawer<T, R> drawer) throws IOException
		{
		return drawer.apply(input);
		}
	}
