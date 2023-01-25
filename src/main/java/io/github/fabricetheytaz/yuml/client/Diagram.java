package io.github.fabricetheytaz.yuml.client;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 0.1.0
 * @since 0.1.0
 */
public class Diagram
	{
	protected final List<String> lines = new ArrayList<>();

	protected Type type;
	protected Style style;

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
	}
