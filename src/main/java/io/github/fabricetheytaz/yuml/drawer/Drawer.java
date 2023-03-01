package io.github.fabricetheytaz.yuml.drawer;

import java.awt.image.BufferedImage;
import io.github.fabricetheytaz.util.io.IInput;
import io.github.fabricetheytaz.util.io.IStringInput;

public class Drawer
	{
	public static final IImageDrawer<? extends IInput<?>> IMAGE4 = ddds -> null;
	public static final IDrawer<? extends IInput<?>, byte[], BufferedImage> PNG = null;

	public static final IImageDrawer<IStringInput> IMAGE = input ->
		{
		@SuppressWarnings("unused")
		final String sdsd = input.get();

		return null;
		};

	public static final IStringDrawer<IStringInput> SVG = null;

	public static final IDrawer<IStringInput, byte[], String> SVG2 = input ->
		{
		@SuppressWarnings("unused")
		final String sdsd = input.get();

		return null;
		};
	}
