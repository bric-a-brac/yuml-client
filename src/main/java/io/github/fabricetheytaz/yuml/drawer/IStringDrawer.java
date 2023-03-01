package io.github.fabricetheytaz.yuml.drawer;

import io.github.fabricetheytaz.util.io.IInput;

/**
 * @version 0.1.0
 * @since 0.1.0
 */
@FunctionalInterface
public interface IStringDrawer<T extends IInput<?>> extends IDrawer<T, byte[], String>
	{
	}
