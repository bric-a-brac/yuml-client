package io.github.fabricetheytaz.yuml.client;

/**
 * @version 0.1.0
 * @since 0.1.0
 */
public enum Direction
	{
	LEFT_TO_RIGHT("leftToRight"),
	RIGHT_TO_LEFT("rightToLeft"),
	TOP_DOWN("topDown");

	private final String value;

	/**
	 * @since 0.1.0
	 */
	private Direction(final String value)
		{
		this.value = value;
		}

	/**
	 * @since 0.1.0
	 */
	@Override
	public String toString()
		{
		return value;
		}
	}
