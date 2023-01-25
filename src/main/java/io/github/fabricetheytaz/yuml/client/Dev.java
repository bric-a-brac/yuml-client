package io.github.fabricetheytaz.yuml.client;

public class Dev
	{
	static Client client = new Client();

	public static void devGetImage() throws Exception
		{
		// OK
		/*
		client.getImage("e3c59524", "svg", bytes ->
			{
			System.out.println(new String(bytes));
			});
		*/

		// OK
		// C'est des bytes mais bon c'est ce qu'on veut
		//client.getImage("e3c59524", "svg", new StandardOutput());

		// OK
		//client.getImage("e3c59524", "svg", new FileOutput("dev-yuml-client.svg"));

		// OK
		client.getImage("e3c59524", "png", new FileOutput("dev-yuml-client.png"));
		}

	public static void main(String[] args)
		{
		try
			{
			//devGetImage();
			}
		catch (Exception ex)
			{
			ex.printStackTrace();
			}
		}
	}
