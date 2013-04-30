package ld26_kiasaki_dagothig.helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import ld26_kiasaki_dagothig.entity.BlockColor;
import ld26_kiasaki_dagothig.entity.BlockShape;
import ld26_kiasaki_dagothig.entity.Factory;
import ld26_kiasaki_dagothig.entity.FactoryImpl;
import ld26_kiasaki_dagothig.entity.Machine;
import ld26_kiasaki_dagothig.entity.Pipe;
import ld26_kiasaki_dagothig.entity.Processor;
import ld26_kiasaki_dagothig.entity.ProcessorImpl;
import ld26_kiasaki_dagothig.entity.Router;

public class SavingHelper 
{
	public Factory factory;
	public int money, level;
	public List<Processor> buildMenu = new ArrayList<Processor>();
	
	private SavingHelper(){}
	
	public static File[] getSaveFiles()
	{
		File tFile = new File("saves");
		tFile.mkdir();
		File[] tSaves = tFile.listFiles();
		List<File> validSaves = new ArrayList<File>();
		for (File file : tSaves)
			if (file.getName().endsWith(".ini"))
				validSaves.add(file);
		File[] output = new File[validSaves.size()];
		for (int index = 0; index < validSaves.size();index++){
			output[index] = validSaves.get(index);
		}
		return output;
	}
	
	private static String getStringFromProcessor(Processor pProcessor)
	{
		String ligne = "Processor=" + 
				pProcessor.getTileX() + "," + pProcessor.getTileY() + ":" + 
				pProcessor.getTileWidth() + "x" + pProcessor.getTileHeight() + ":" +
				pProcessor.getColor() + ":";
		for (BlockShape shape : (pProcessor.getShapeIns()))
			ligne += shape + ",";
		if (pProcessor.getShapeIns().size() > 0)
			ligne = ligne.substring(0, ligne.length() - 1);
		ligne += ":" + pProcessor.getShapeOut();
		return ligne;
	}
	private static String getStringFromRouter(Router pRouter)
	{
		return "Router=" + pRouter.getTileX() + "," + pRouter.getTileY();
	}
	private static String getStringFromPipe(Pipe pPipe)
	{

		return  "Pipe=" + pPipe.getTileX() + "," + pPipe.getTileY() + ":" + pPipe.getAngle() + ":" + pPipe.getAngleOut();
	}
	private static void addMachineFromString(String pMachineString, Factory pFactory) throws Exception
	{
		String[] type = pMachineString.split("=");
		if (type[0].equals("Processor"))
		{
			String[] infos = type[1].split(":");
			String[] subinfos1 = infos[0].split(",");
			String[] subinfos2 = infos[1].split("x");
			String[] subinfos3 = infos[3].split(",");
			List<BlockShape> shapes = new ArrayList<BlockShape>();
			for (String shape : subinfos3)
				shapes.add(BlockShape.valueOf(shape));
			
			pFactory.addProcessor(Integer.parseInt(subinfos1[0]), Integer.parseInt(subinfos1[1]), 
								  Integer.parseInt(subinfos2[0]), Integer.parseInt(subinfos2[1]),
								  shapes, 
								  BlockShape.valueOf(infos[4]), 
								  BlockColor.valueOf(infos[2]));
		}
		else if (type[0].equals("Router"))
		{
			String[] infos = type[1].split(",");
			pFactory.addRouter(Integer.parseInt(infos[0]), Integer.parseInt(infos[1]), 0);
		}
		else if (type[0].equals("Pipe"))
		{
			String[] infos = type[1].split(":");
			String[] subinfos = infos[0].split(",");
			pFactory.addPipe(Integer.parseInt(subinfos[0]), Integer.parseInt(subinfos[1]), Integer.parseInt(infos[1]), Integer.parseInt(infos[2]));
		}
	}
	
	public static void saveToFile(Factory pFactory, int pMoney, List<Processor> pBuildMenu, int pLevel)
	{
		FileWriter outFile = null;
		PrintWriter out = null;
		try
		{
			File file = new File("saves/level" + pLevel + ".ini");
			file.getParentFile().mkdirs();
			outFile = new FileWriter(file);
			out = new PrintWriter(outFile);
			
			out.println("[WorldInfo]");
			out.println("Money=" + pMoney);
			out.println("Level=" + pLevel);
			out.println("FactoryInfo=" + pFactory.getTileXAmount() + "," + pFactory.getTileYAmount() + "," + pFactory.getX() + "," + pFactory.getY());
			out.println("[Factory]");
			for (Machine mach : pFactory.getMachines())
			{
				if (mach instanceof Processor)
					out.println(getStringFromProcessor((Processor)mach));
				else if (mach instanceof Router)
					out.println(getStringFromRouter((Router)mach));
				else if (mach instanceof Pipe)
					out.println(getStringFromPipe((Pipe)mach));
			}
			out.println("[BuildMenu]");
			for (Processor mach : pBuildMenu)
				out.println(getStringFromProcessor((Processor)mach));
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if (out != null)
				out.close();
		}
	}
	
	public static SavingHelper readWorldFromFile(int pLevel)
	{ 
		SavingHelper state = new SavingHelper();
		
		FileReader inFile = null;
		BufferedReader in = null;
		try
		{
			File file = new File("saves/level" + pLevel + ".ini");
			file.getParentFile().mkdirs();
			inFile = new FileReader(file);
			in = new BufferedReader(inFile);
		
			String line = in.readLine(), currentType = "";
			while (line != null)
			{
				if (line.startsWith("[") && line.endsWith("]"))
					currentType = line;
				else
				{
					if (currentType.equals("[WorldInfo]"))
					{
						String[] rd = line.split("=");
						if (rd[0].equals("Money"))
							state.money = Integer.parseInt(rd[1]);
						else if (rd[0].equals("Level"))
							state.level = Integer.parseInt(rd[1]);
						else if (rd[0].equals("FactoryInfo"))
						{
							String[] rdsub = rd[1].split(",");
							state.factory = new FactoryImpl(Integer.parseInt(rdsub[0]), 
															Integer.parseInt(rdsub[1]),
															Integer.parseInt(rdsub[2]),
															Integer.parseInt(rdsub[3]));
						}
					}
					else if (currentType.equals("[Factory]"))
						addMachineFromString(line, state.factory);
					else if (currentType.equals("[BuildMenu]"))
					{
						ProcessorImpl mach = new ProcessorImpl();
						String[] rd = line.split("=")[1].split(":");
						String[] rdsub = rd[0].split(",");
						mach.setTileX(Integer.parseInt(rdsub[0]));
						mach.setTileY(Integer.parseInt(rdsub[1]));
						rdsub = rd[1].split("x");
						mach.setTileWidth(Integer.parseInt(rdsub[0]));
						mach.setTileHeight(Integer.parseInt(rdsub[1]));
						mach.setColor(BlockColor.valueOf(rd[2]));
						rdsub = rd[3].split(",");
						for (String shape : rdsub)
							mach.getShapeIns().add(BlockShape.valueOf(shape));
						mach.setShapeOut(BlockShape.valueOf(rd[4]));
						mach.setCost(mach.getCostFromSize(mach.getTileWidth(), mach.getTileHeight()));
						mach.setImage(new BlockImage(BlockImage.getImage("Processor_" + mach.getTileWidth() + "x" +  mach.getTileHeight() + ".png")));
						mach.setForeground(new BlockImage(BlockImage.getImage("ProcessorForeground_" + mach.getTileWidth() + "x" +  mach.getTileHeight() + ".png")));
						
						state.buildMenu.add(mach);
					}
				}
				line = in.readLine();
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				if (in != null)
					in.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		
		return state;
	}
}
