package io.github.fabricetheytaz.yuml.client.old;

/**
 * @version 0.1.0
 * @since 0.1.0
 */
public final class Meta
	{
	// "file_only": false, ??

	private String digest;
	private String type;
	private String dsl;
	private String mimetype;
	private String extension;
	private String customisations;

	/**
	 * @since 0.1.0
	 */
	public String getDigest()
		{
		return digest;
		}

	/**
	 * @since 0.1.0
	 */
	public String getType()
		{
		return type;
		}

	/**
	 * @since 0.1.0
	 */
	public String getDSL()
		{
		return dsl;
		}

	/**
	 * @since 0.1.0
	 */
	public String getMimetype()
		{
		return mimetype;
		}

	/**
	 * @since 0.1.0
	 */
	public String getExtension()
		{
		return extension;
		}

	/**
	 * @since 0.1.0
	 */
	public String getCustomisations()
		{
		return customisations;
		}
	}
